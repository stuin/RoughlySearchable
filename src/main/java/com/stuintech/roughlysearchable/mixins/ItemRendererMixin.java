package com.stuintech.roughlysearchable.mixins;

import com.mojang.blaze3d.systems.RenderSystem;
import com.stuintech.roughlysearchable.api.IEntryList;
import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.gui.ContainerScreenOverlay;
import me.shedaniel.rei.gui.widget.EntryListWidget;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.SynchronousResourceReloadListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin implements SynchronousResourceReloadListener {
    private static final float SHRINK = 0.7F;
    private static final float GROW = 1.3F;

    @Inject(at = @At("NEW"), method = "renderGuiItemModel(Lnet/minecraft/item/ItemStack;IILnet/minecraft/client/render/model/BakedModel;)V")
    protected void renderGuiItemModel(ItemStack stack, int x, int y, BakedModel model, CallbackInfo ci) {
        EntryListWidget widget = ContainerScreenOverlay.getEntryListWidget();
        if(widget instanceof IEntryList && ((IEntryList) widget).shouldSearch()) {
            if(widget.canLastSearchTermsBeAppliedTo(EntryStack.create(stack)))
                RenderSystem.scalef(GROW, GROW, GROW);
            else
                RenderSystem.scalef(SHRINK, SHRINK, SHRINK);
        }
    }
}
