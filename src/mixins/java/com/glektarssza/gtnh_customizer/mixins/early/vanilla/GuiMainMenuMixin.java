package com.glektarssza.gtnh_customizer.mixins.early.vanilla;

import java.net.ProxySelector;

import net.minecraft.client.gui.GuiMainMenu;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.glektarssza.gtnh_customizer.utils.CustomProxySelector;

@Mixin(GuiMainMenu.class)
public class GuiMainMenuMixin {
    @Inject(method = "initGui", at = @At("HEAD"), cancellable = false)
    public void setupDefaultProxySelector(final CallbackInfo ci) {
        ProxySelector.setDefault(new CustomProxySelector());
    }
}
