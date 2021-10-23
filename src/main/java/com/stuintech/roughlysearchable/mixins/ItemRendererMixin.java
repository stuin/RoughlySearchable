package com.stuintech.roughlysearchable.mixins;

import com.stuintech.roughlysearchable.api.ICustomConfig;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import me.shedaniel.rei.impl.client.REIRuntimeImpl;
import me.shedaniel.rei.impl.client.gui.ScreenOverlayImpl;
import me.shedaniel.rei.impl.client.gui.widget.EntryListWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.SynchronousResourceReloader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin implements SynchronousResourceReloader {
    boolean shouldSearch() {
        return ICustomConfig.getConfig().RS_search && MinecraftClient.getInstance().currentScreen instanceof HandledScreen &&
                !REIRuntimeImpl.getSearchField().getText().isEmpty();
    }

    @Inject(at = @At("HEAD"), method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V")
    public void renderItem(ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider
        vertexConsumers, int light, int overlay, BakedModel model, CallbackInfo ci) {
        if(!stack.isEmpty() && shouldSearch()) {
            EntryListWidget widget = ScreenOverlayImpl.getEntryListWidget();
            float grow = ICustomConfig.getIndicator().RS_trueSize;
            float shrink = ICustomConfig.getIndicator().RS_falseSize;

            matrices.push();
            if(widget.matches(EntryStack.of(VanillaEntryTypes.ITEM, stack)))
                matrices.scale(grow, grow, grow);
            else
                matrices.scale(shrink, shrink, shrink);
        }
    }

    @Inject(at = @At("RETURN"), method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V")
    public void renderItemEnd(ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider
            vertexConsumers, int light, int overlay, BakedModel model, CallbackInfo ci) {
        if(!stack.isEmpty() && shouldSearch()) {
            matrices.pop();
        }
    }
}
