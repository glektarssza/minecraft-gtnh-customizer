package com.glektarssza.gtnh_customizer.mixins.late.xaeros.common;

import java.io.IOException;

import net.minecraft.client.settings.KeyBinding;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import xaero.common.IXaeroMinimap;
import xaero.common.settings.ModSettings;

/**
 * A mixin to the {@link ModSettings} class.
 */
@Mixin(ModSettings.class)
public class ModSettingsMixin extends ModSettings {

    /**
     * Constructor to make Java happy.
     *
     * @param modMain The main mod module.
     */
    public ModSettingsMixin(IXaeroMinimap modMain) throws IOException {
        super(modMain);
    }

    /**
     * Keybinding to switch to the next dimension quickly.
     */
    public static KeyBinding keySwitchDimUp = new KeyBinding(
        "gui.xaero_switch_dimension_up", 0, "Xaero's Minimap");

    /**
     * Keybinding to switch to the previous dimension quickly.
     */
    public static KeyBinding keySwitchDimDown = new KeyBinding(
        "gui.xaero_switch_dimension_down", 0, "Xaero's Minimap");

    /**
     * Injection into the {@link ModSettings#isKeyRepeat} method.
     *
     * @param kb The keybinding.
     * @param ci The callback info.
     */
    @Inject(method = "isKeyRepeat", at = @At("RETURN"))
    public void isKeyRepeat$injectSwitchDimensionCheck(KeyBinding kb,
        CallbackInfoReturnable<Boolean> ci) {
        ci.setReturnValue(ci.getReturnValue() && kb != keySwitchDimUp
            && kb != keySwitchDimDown);
    }
}
