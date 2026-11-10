package com.glektarssza.gtnh_customizer.mixins.late.journeymap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import com.glektarssza.gtnh_customizer.mixins.extensions.journeymap.GhostTextBox;

import io.github.tox1cozz.mixinextras.injector.ModifyReturnValue;
import journeymap.api.v2.common.util.BlockPos;
import journeymap.client.JourneymapClient;
import journeymap.client.ui.component.TextBox;
import journeymap.client.ui.component.TextBox.OnPress;
import journeymap.client.ui.component.layouts.GridLayout;
import journeymap.client.ui.waypointmanager.waypoint.WaypointEditor;
import journeymap.client.waypoint.ClientWaypointImpl;

/**
 * A mixin for the {@link WaypointEditor} class.
 */
@Mixin(value = WaypointEditor.class)
public abstract class WaypointEditorMixin {
    /**
     * A shadow for the {@link WaypointEditor#nameTextBox nameTextBox} field.
     */
    @Shadow(remap = false)
    private TextBox nameTextBox;

    /**
     * A shadow for the {@link WaypointEditor#xLocTextBox xLocTextBox} field.
     */
    @Shadow(remap = false)
    private TextBox xLocTextBox;

    /**
     * A shadow for the {@link WaypointEditor#yLocTextBox yLocTextBox} field.
     */
    @Shadow(remap = false)
    private TextBox yLocTextBox;

    /**
     * A shadow for the {@link WaypointEditor#zLocTextBox zLocTextBox} field.
     */
    @Shadow(remap = false)
    private TextBox zLocTextBox;

    /**
     * A shadow for the {@link WaypointEditor#xyzLocTextBox xyzLocTextBox}
     * field.
     */
    @Shadow(remap = false)
    private TextBox xyzLocTextBox;

    /**
     * A shadow for the {@link WaypointEditor#waypoint waypoint} field.
     */
    @Shadow(remap = false)
    private ClientWaypointImpl waypoint;

    /**
     * An injection for the {@link WaypointEditor#buildLocGridLayout
     * buildLocGridLayout} method set up the values for the ghost text.
     *
     * @param original The {@link GridLayout} that was to be returned
     *        originally.
     *
     * @return The original {@link GridLayout}.
     */
    @ModifyReturnValue(method = "buildLocGridLayout()Ljourneymap/client/ui/component/layouts/GridLayout;", at = @At("RETURN"), remap = false)
    public GridLayout setupLocGhostText(final GridLayout original) {
        if (!JourneymapClient.getInstance().getStateHandler()
            .isHideCoordinates()) {
            final BlockPos pos = new BlockPos(
                Minecraft.getMinecraft().thePlayer.posX,
                Minecraft.getMinecraft().thePlayer.posY,
                Minecraft.getMinecraft().thePlayer.posZ);
            ((GhostTextBox) this.xLocTextBox).setGhostText(Integer.toString(
                pos.getX()));
            ((GhostTextBox) this.yLocTextBox).setGhostText(Integer.toString(
                pos.getY()));
            ((GhostTextBox) this.zLocTextBox).setGhostText(Integer.toString(
                pos.getZ()));
        }
        return original;
    }

    /**
     * An injection for the {@link WaypointEditor#buildLocGridLayout
     * buildLocGridLayout} method to replace the normal {@link TextBox}
     * instances with our own.
     *
     * @param text The text to display by default.
     * @param fontRenderer The font renderer.
     * @param width The width of the text box.
     * @param height The height of the text box.
     * @param isNumeric Whether the text box should be purely numeric.
     * @param negative Whether to allow next numbers in numeric text boxes.
     * @param onPress The key press callback.
     * @param _original The original constructor call for reference.
     *
     * @return The new {@link GhostTextBox} instance.
     */
    @WrapOperation(method = "initGui()V", at = @At(value = "NEW", target = "(Ljava/lang/Object;Lnet/minecraft/client/gui/FontRenderer;IIZZLjourneymap/client/ui/component/TextBox$OnPress;)Ljourneymap/client/ui/component/TextBox;"))
    private TextBox redirectTextBoxCreationToGhostTextBoxes(
        final Object text, final FontRenderer fontRenderer, final int width,
        final int height,
        final boolean isNumeric, final boolean negative, final OnPress onPress,
        final Operation<TextBox> _original) {
        return new GhostTextBox(text, fontRenderer, width, height,
            isNumeric, negative, onPress);
    }

    /**
     * An injection for the {@link WaypointEditor#initGui initGui} method to
     * allow empty text boxes.
     *
     * @param instance The {@link TextBox} instance.
     * @param _minLength The incoming minimum length.
     *
     * @return Whether to allow the minimum length to be set.
     */
    @WrapWithCondition(method = "initGui()V", at = @At(value = "INVOKE", target = "Ljourneymap/client/ui/component/TextBox;setMinLength(I)V", remap = false))
    public boolean allowEmptyTextBoxes(final TextBox instance,
        final int _minLength) {
        return !(instance instanceof GhostTextBox);
    }

    /**
     * An injection for the {@link WaypointEditor#validateTextBoxes
     * validateTextBoxes} and {@link WaypointEditor#save save} methods to swap
     * out the text that would have been retrieved from the {@link TextBox}
     * instances with the ghost text if they are empty.
     *
     * @param box The original text box instance.
     * @param original The original operation that would have been performed.
     *
     * @return The new return value.
     */
    @WrapOperation(method = {
        "save(Ljourneymap/client/ui/component/buttons/Button;)V",
        "validateTextBoxes([Ljourneymap/client/ui/component/TextBox;)Z"
    }, at = @At(value = "INVOKE", target = "Ljourneymap/client/ui/component/TextBox;getText()Ljava/lang/String;"))
    private String getGhostTextIfNoTextEntered(final TextBox box,
        final Operation<String> original) {
        final String originalResult = original.call(box);
        return originalResult.isEmpty() && (box instanceof final GhostTextBox g)
            ? g.getGhostText()
            : originalResult;
    }

    @WrapMethod(method = "keyTyped(CI)V")
    private void handleTabInputs(final char typedChar, final int keyCode,
        final Operation<Void> original) {

        if (keyCode == Keyboard.KEY_TAB) {
            final List<TextBox> boxes = new ArrayList<TextBox>();
            boxes.add(this.nameTextBox);
            switch (JourneymapClient.getInstance()
                .getWaypointProperties().coordinatesFormat
                .get()) {
                case SINGLE_FIELD:
                    boxes.add(this.xyzLocTextBox);
                    break;
                case XYZ:
                    boxes.addAll(Arrays.asList(
                        this.xLocTextBox,
                        this.yLocTextBox,
                        this.zLocTextBox));
                    break;
                case XZY:
                    boxes.addAll(Arrays.asList(
                        this.xLocTextBox,
                        this.zLocTextBox,
                        this.yLocTextBox));
                    break;
            }
            final List<TextBox> workingList = Collections
                .unmodifiableList(boxes);
            Integer focusedIndex = null;
            for (int i = 0; i < workingList.size(); i += 1) {
                final TextBox box = workingList.get(i);
                if (!box.getVisible()) {
                    boxes.remove(box);
                    continue;
                }
                if (box.isFocused()) {
                    if (focusedIndex == null) {
                        focusedIndex = i;
                    }
                    box.setFocused(false);
                    box.setSelectionPos(box.getCursorPosition());
                }
            }
            int offset = 0;
            if ((Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)
                || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))) {
                offset = -1;
            } else {
                offset = 1;
            }
            if (focusedIndex != null) {
                focusedIndex = focusedIndex + offset;
                if (focusedIndex < 0) {
                    focusedIndex = boxes.size() + focusedIndex;
                }
                focusedIndex = focusedIndex % boxes.size();
                boxes.get(focusedIndex).setFocused(true);
            } else {
                boxes.get(0).setFocused(true);
            }
        }
        original.call(typedChar, keyCode);
    }
}
