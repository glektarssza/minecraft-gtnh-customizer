package com.glektarssza.gtnh_customizer.mixins.late.journeymap;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
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
@Mixin(value = WaypointEditor.class, remap = false)
public abstract class WaypointEditorMixin {
    /**
     * A shadow for the {@link WaypointEditor#xLocTextBox xLocTextBox} field.
     */
    @Shadow
    private TextBox xLocTextBox;

    /**
     * A shadow for the {@link WaypointEditor#yLocTextBox yLocTextBox} field.
     */
    @Shadow
    private TextBox yLocTextBox;

    /**
     * A shadow for the {@link WaypointEditor#zLocTextBox zLocTextBox} field.
     */
    @Shadow
    private TextBox zLocTextBox;

    /**
     * A shadow for the {@link WaypointEditor#xyzLocTextBox xyzLocTextBox}
     * field.
     */
    @Shadow
    private TextBox xyzLocTextBox;

    /**
     * A shadow for the {@link WaypointEditor#waypoint waypoint} field.
     */
    @Shadow
    private ClientWaypointImpl waypoint;

    @WrapOperation(method = "initGui", at = @At(value = "NEW", target = "(Ljava/lang/Object;Lnet/minecraft/client/gui/FontRenderer;IIZZLjourneymap/client/ui/component/TextBox$OnPress;)Ljourneymap/client/ui/component/TextBox;"))
    private TextBox redirectTextBoxCreationToGhostTextBoxes(
        Object text, FontRenderer fontRenderer, int width, int height,
        boolean isNumeric, boolean negative, OnPress onPress,
        Operation<TextBox> original) {
        return new GhostTextBox(text, fontRenderer, width, height,
            isNumeric, negative, onPress);
    }

    /**
     * An injection for the {@link WaypointEditor#buildLocGridLayout
     * buildLocGridLayout} method.
     *
     * @param ci The callback information.
     */
    @ModifyReturnValue(method = "buildLocGridLayout", at = @At("RETURN"))
    public GridLayout setupLocGhostText(GridLayout original) {
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

    @WrapWithCondition(method = "initGui", at = @At(value = "INVOKE", target = "Ljourneymap/client/ui/component/TextBox;setMinLength(I)V", remap = false), remap = true)
    public boolean allowEmptyTextBoxes(TextBox instance, int minLength) {
        return !(instance instanceof GhostTextBox);
    }

    @WrapOperation(method = {
        "save", "validateTextBoxes"
    }, at = @At(value = "INVOKE", target = "Ljourneymap/client/ui/component/TextBox;getText()Ljava/lang/String;"))
    private String getGhostTextIfNoTextEntered(TextBox box,
        Operation<String> original) {
        final String originalResult = original.call(box);
        return originalResult.isEmpty() && (box instanceof GhostTextBox)
            ? ((GhostTextBox) box).getGhostText()
            : originalResult;
    }
}
