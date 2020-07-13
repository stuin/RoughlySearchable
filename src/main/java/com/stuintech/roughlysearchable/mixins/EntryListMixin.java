package com.stuintech.roughlysearchable.mixins;

import com.stuintech.roughlysearchable.api.ICustomConfig;
import com.stuintech.roughlysearchable.api.IEntryList;
import me.shedaniel.rei.gui.widget.EntryListWidget;
import me.shedaniel.rei.gui.widget.WidgetWithBounds;
import me.shedaniel.rei.impl.SearchArgument;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(EntryListWidget.class)
public abstract class EntryListMixin extends WidgetWithBounds implements IEntryList {
    @Shadow
    private List<SearchArgument.SearchArguments> lastSearchArguments;

    @Override
    public boolean shouldSearch() {
        if(ICustomConfig.getConfig().RS_search && MinecraftClient.getInstance().currentScreen instanceof HandledScreen)
            return !lastSearchArguments.isEmpty();
        return false;
    }

    /*@Inject(at = @At("HEAD"), cancellable = true, method = "canLastSearchTermsBeAppliedTo(Lme/shedaniel/rei/api/EntryStack;)Z")
    public void canLastSearchTermsBeApplied(EntryStack stack, CallbackInfoReturnable<Boolean> cir) {
        if(ICustomConfig.getConfig().RS_shulker && stack.getItemStack().hasTag()) {
            CompoundTag compoundTag = stack.getItemStack().getSubTag("BlockEntityTag");
            if(compoundTag != null && compoundTag.contains("Items", 9)) {
                try {
                    DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(27, ItemStack.EMPTY);
                    Inventories.fromTag(compoundTag, defaultedList);

                    for(ItemStack itemStack : defaultedList)
                        if(!itemStack.isEmpty() && SearchArgument.canSearchTermsBeAppliedTo(EntryStack.create(itemStack), this.lastSearchArguments))
                            cir.setReturnValue(true);
                } catch(Exception e) {
                    RoughlySearchable.LOGGER.warn("Error searching in item container: " + stack.toString());
                    RoughlySearchable.LOGGER.warn(e);
                }
            }
        }
    }*/
}
