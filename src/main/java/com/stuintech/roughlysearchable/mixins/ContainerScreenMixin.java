package com.stuintech.roughlysearchable.mixins;

import com.stuintech.roughlysearchable.api.ICustomConfig;
import me.shedaniel.rei.api.ConfigManager;
import me.shedaniel.rei.api.ConfigObject;
import me.shedaniel.rei.gui.ContainerScreenOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ContainerScreenOverlay.class)
public abstract class ContainerScreenMixin {

    @Inject(at = @At("HEAD"), cancellable = true, method = "keyPressed(III)Z")
    public void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if(ConfigObject.getInstance() instanceof ICustomConfig &&
                ((ICustomConfig) ConfigObject.getInstance()).getRoughlySearchableKeybind().matchesKey(keyCode, scanCode)) {
            ((ICustomConfig) ConfigObject.getInstance()).toggleSearch();
            ConfigManager.getInstance().saveConfig();
            cir.setReturnValue(true);
        }
    }
}
