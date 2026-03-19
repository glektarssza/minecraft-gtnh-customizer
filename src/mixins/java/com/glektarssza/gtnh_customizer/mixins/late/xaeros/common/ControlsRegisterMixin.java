package com.glektarssza.gtnh_customizer.mixins.late.xaeros.common;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import xaero.common.controls.ControlsRegister;

/**
 * A mixin to the {@link ControlsRegister} class.
 */
@Mixin(ControlsRegister.class)
public class ControlsRegisterMixin extends ControlsRegister {
    /**
     * Constructor to make Java happy.
     */
    public ControlsRegisterMixin() {
        super();
    }

    /**
     * Injection into the {@link ControlsRegister#registerKeybindings} method.
     *
     * @param ci The callback info.
     */
    @Inject(method = "registerKeybindings", at = @At(value = "HEAD"))
    protected void registerKeybindings$injectCustomKeybinds(CallbackInfo ci) {
        this.keybindings.add(ModSettingsMixin.keySwitchDimUp);
        this.keybindings.add(ModSettingsMixin.keySwitchDimDown);
    }
}
