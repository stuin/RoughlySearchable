package com.stuintech.roughlysearchable.mixins;

import com.stuintech.roughlysearchable.api.ICustomConfig;
import com.stuintech.roughlysearchable.api.IEntryList;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.gui.ContainerScreenOverlay;
import me.shedaniel.rei.gui.OverlaySearchField;
import me.shedaniel.rei.gui.widget.EntryListWidget;
import me.shedaniel.rei.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OverlaySearchField.class)
public abstract class SearchFieldMixin extends TextFieldWidget {
    public SearchFieldMixin(Rectangle rectangle) {
        super(rectangle);
    }

    @Inject(at = @At("RETURN"), method = "keyPressed(III)Z")
    public void keyPressed(int int_1, int int_2, int int_3, CallbackInfoReturnable cir) {
        EntryListWidget widget = ContainerScreenOverlay.getEntryListWidget();
        if(ICustomConfig.getConfig().RS_search && ICustomConfig.getIndicator().RS_background)
            OverlaySearchField.isSearching = ((IEntryList) widget).shouldSearch();
    }

    /*@Inject(at = @At("TAIL"), method = "renderBorder(Lnet/minecraft/client/util/math/MatrixStack;)V")
    public void renderBorder(MatrixStack matrices, CallbackInfo ci) {
        EntryListWidget widget = ContainerScreenOverlay.getEntryListWidget();
        if (isMain && ((IEntryList) widget).shouldSearch()) {
            fill(matrices, this.getBounds().x - 1, this.getBounds().y - 1, this.getBounds().x + this.getBounds().width + 1, this.getBounds().y + this.getBounds().height + 1, -852212);
        }
    }*/
}