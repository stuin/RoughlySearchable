package com.stuintech.roughlysearchable.mixins;

import com.mojang.blaze3d.systems.RenderSystem;
import com.stuintech.roughlysearchable.api.ICustomConfig;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import me.shedaniel.rei.impl.client.REIRuntimeImpl;
import me.shedaniel.rei.impl.client.gui.ScreenOverlayImpl;
import me.shedaniel.rei.impl.client.gui.widget.EntryListWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.SynchronousResourceReloader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin implements SynchronousResourceReloader {
    @Inject(at = @At("NEW"), method = "renderGuiItemModel(Lnet/minecraft/item/ItemStack;IILnet/minecraft/client/render/model/BakedModel;)V")
    protected void renderGuiItemModel(ItemStack stack, int x, int y, BakedModel model, CallbackInfo ci) {
        EntryListWidget widget = ScreenOverlayImpl.getEntryListWidget();
        if(ICustomConfig.getConfig().RS_search && MinecraftClient.getInstance().currentScreen instanceof HandledScreen &&
                !REIRuntimeImpl.getSearchField().getText().isEmpty()) {
            float grow = ICustomConfig.getIndicator().RS_trueSize;
            float shrink = ICustomConfig.getIndicator().RS_falseSize;
            MatrixStack matrixStack = RenderSystem.getModelViewStack();
            if(widget.matches(EntryStack.of(VanillaEntryTypes.ITEM, stack)))
                matrixStack.scale(grow, grow, grow);
            else
                matrixStack.scale(shrink, shrink, shrink);
            RenderSystem.applyModelViewMatrix();
        }
    }
}
