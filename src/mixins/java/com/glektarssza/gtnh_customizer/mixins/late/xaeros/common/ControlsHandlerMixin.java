package com.glektarssza.gtnh_customizer.mixins.late.xaeros.common;

import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.client.settings.KeyBinding;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import xaero.map.MapProcessor;
import xaero.map.controls.ControlsHandler;
import xaero.map.world.MapDimension;
import xaero.map.world.MapWorld;

/**
 * Mixin for the {@link ControlsHandler} class.
 */
@Mixin(ControlsHandler.class)
public class ControlsHandlerMixin extends ControlsHandler {
    /**
     * A shadow of the {@code mapProcessor} private field.
     */
    @Shadow
    private MapProcessor mapProcessor;

    /**
     * Constructor to make Java happy.
     *
     * @param mapProcessor The map processor.
     */
    public ControlsHandlerMixin(MapProcessor mapProcessor) {
        super(mapProcessor);
    }

    /**
     * Inject our custom handling code before the
     * {@link ControlsHandler#keyDownPost} call inside of
     * {@link ControlsHandler#keyDown}.
     *
     * @param kb The keybinding being triggered.
     * @param tickEnd Whether we're at the end of the tick or not.
     * @param isRepeat Whether this is a repeated invocation of the keybinding.
     * @param ci The callback information.
     */
    @Inject(method = "keyDown", at = @At(value = "INVOKE", target = "Lxaero/common/controls/ControlsHandler;keyDownPost(Lnet/minecraft/client/settings/KeyBinding;)", shift = Shift.BEFORE))
    public void keyDown$injectCustomKeybindingHandler(KeyBinding kb,
        boolean tickEnd, boolean isRepeat, CallbackInfo ci) {
        if (kb == ModSettingsMixin.keySwitchDimUp
            || kb == ModSettingsMixin.keySwitchDimDown) {
            final int offset = kb == ModSettingsMixin.keySwitchDimUp ? 1 : -1;
            final MapWorld mapWorld = this.mapProcessor.getMapWorld();
            final String currentDimension = mapWorld.getCurrentMultiworld();
            final List<MapDimension> allDimensions = mapWorld
                .getDimensionsList();
            final List<Integer> allDimensionIds = allDimensions.stream()
                .map((dimension) -> dimension.getDimId())
                .collect(Collectors.toList());
            final List<String> allDimensionNames = allDimensions.stream()
                .map((dimension) -> dimension.getCurrentMultiworld())
                .collect(Collectors.toList());
            int dimensionIndex = allDimensionNames.indexOf(currentDimension);
            if (dimensionIndex < 0) {
                return;
            }
            dimensionIndex += offset;
            dimensionIndex %= allDimensions.size();
            mapWorld.setFutureDimensionId(allDimensionIds.get(dimensionIndex));
        }
    }
}
