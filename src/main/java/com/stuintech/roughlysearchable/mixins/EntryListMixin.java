package com.stuintech.roughlysearchable.mixins;

import com.stuintech.roughlysearchable.api.ICustomConfig;
import com.stuintech.roughlysearchable.api.IEntryList;
import me.shedaniel.rei.api.ConfigObject;
import me.shedaniel.rei.gui.widget.EntryListWidget;
import me.shedaniel.rei.gui.widget.WidgetWithBounds;
import me.shedaniel.rei.impl.ScreenHelper;
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
        if(((ICustomConfig) ConfigObject.getInstance()).searchOn() && MinecraftClient.getInstance().currentScreen instanceof HandledScreen)
            return !lastSearchArguments.isEmpty();
        return false;
    }
}
