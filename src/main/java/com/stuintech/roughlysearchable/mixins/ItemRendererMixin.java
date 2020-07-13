package com.stuintech.roughlysearchable.mixins;

import com.mojang.blaze3d.systems.RenderSystem;
import com.stuintech.roughlysearchable.RoughlySearchable;
import com.stuintech.roughlysearchable.api.ICustomConfig;
import com.stuintech.roughlysearchable.api.IEntryList;
import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.gui.ContainerScreenOverlay;
import me.shedaniel.rei.gui.widget.EntryListWidget;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resource.SynchronousResourceReloadListener;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin implements SynchronousResourceReloadListener {
    @Inject(at = @At("NEW"), method = "renderGuiItemModel(Lnet/minecraft/item/ItemStack;IILnet/minecraft/client/render/model/BakedModel;)V")
    protected void renderGuiItemModel(ItemStack stack, int x, int y, BakedModel model, CallbackInfo ci) {
        EntryListWidget widget = ContainerScreenOverlay.getEntryListWidget();
        if(widget instanceof IEntryList && ((IEntryList) widget).shouldSearch()) {
            float grow = ICustomConfig.getIndicator().RS_trueSize;
            float shrink = ICustomConfig.getIndicator().RS_falseSize;
            if(matches(widget, stack))
                RenderSystem.scalef(grow, grow, grow);
            else
                RenderSystem.scalef(shrink, shrink, shrink);
        }
    }

    private boolean matches(EntryListWidget widget, ItemStack stack) {
        if(widget.canLastSearchTermsBeAppliedTo(EntryStack.create(stack)))
            return true;

        if(ICustomConfig.getConfig().RS_shulker && stack.hasTag()) {
            CompoundTag compoundTag = stack.getSubTag("BlockEntityTag");
            if(compoundTag != null && compoundTag.contains("Items", 9)) {
                try {
                    DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(27, ItemStack.EMPTY);
                    Inventories.fromTag(compoundTag, defaultedList);

                    for(ItemStack itemStack : defaultedList)
                        if(!itemStack.isEmpty() && widget.canLastSearchTermsBeAppliedTo(EntryStack.create(itemStack)))
                            return true;
                } catch(Exception e) {
                    RoughlySearchable.LOGGER.warn("Error searching in item container: " + stack.toString());
                    RoughlySearchable.LOGGER.warn(e);
                }
            }
        }
        return false;
    }
}
