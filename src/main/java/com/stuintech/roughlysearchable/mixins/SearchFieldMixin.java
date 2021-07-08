package com.stuintech.roughlysearchable.mixins;

import com.stuintech.roughlysearchable.api.ICustomConfig;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.impl.client.REIRuntimeImpl;
import me.shedaniel.rei.impl.client.gui.widget.basewidgets.TextFieldWidget;
import me.shedaniel.rei.impl.client.gui.widget.search.OverlaySearchField;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OverlaySearchField.class)
public abstract class SearchFieldMixin extends TextFieldWidget implements TextFieldWidget.TextFormatter {
    public SearchFieldMixin(Rectangle rectangle) {
        super(rectangle);
    }

    @Inject(at = @At("RETURN"), method = "keyPressed(III)Z")
    public void keyPressed(int int_1, int int_2, int int_3, CallbackInfoReturnable cir) {
        if(ICustomConfig.getConfig().RS_search && ICustomConfig.getIndicator().RS_background)
            OverlaySearchField.isHighlighting = !REIRuntimeImpl.getSearchField().getText().isEmpty();
    }
}
