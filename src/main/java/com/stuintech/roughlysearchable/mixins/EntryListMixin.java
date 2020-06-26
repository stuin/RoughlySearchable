package com.stuintech.roughlysearchable.mixins;

import com.stuintech.roughlysearchable.IEntryList;
import me.shedaniel.rei.gui.widget.EntryListWidget;
import me.shedaniel.rei.gui.widget.WidgetWithBounds;
import me.shedaniel.rei.impl.SearchArgument;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(EntryListWidget.class)
public abstract class EntryListMixin extends WidgetWithBounds implements IEntryList {
    @Shadow private String lastSearchTerm;

    @Shadow private List<SearchArgument.SearchArguments> lastSearchArguments;

    @Override
    public boolean hasSearchTerm() {
        return !lastSearchArguments.isEmpty();
    }
}
