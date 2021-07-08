package com.stuintech.roughlysearchable.mixins;

import com.stuintech.roughlysearchable.api.ICustomConfig;
import com.stuintech.roughlysearchable.api.RoughlySearchableConfig;
import me.shedaniel.rei.api.client.config.ConfigManager;
import me.shedaniel.rei.api.client.config.ConfigObject;
import me.shedaniel.rei.impl.client.gui.ScreenOverlayImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ScreenOverlayImpl.class)
public abstract class ContainerScreenMixin {

    @Inject(at = @At("HEAD"), cancellable = true, method = "keyPressed(III)Z")
    public void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if(ConfigObject.getInstance() instanceof ICustomConfig) {
            RoughlySearchableConfig.Config config = ((ICustomConfig) ConfigObject.getInstance()).RS_getConfig();
            if(config.RS_keybind.matchesKey(keyCode, scanCode)) {
                config.toggleSearch();
                ConfigManager.getInstance().saveConfig();
                cir.setReturnValue(true);
            }
        }
    }
}
