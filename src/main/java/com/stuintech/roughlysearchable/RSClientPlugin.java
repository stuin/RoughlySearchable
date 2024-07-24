package com.stuintech.roughlysearchable;

import com.stuintech.roughlysearchable.api.ICustomConfig;
import me.shedaniel.rei.api.client.config.addon.ConfigAddonRegistry;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.impl.client.config.ConfigObjectImpl;
import me.shedaniel.rei.impl.client.gui.config.options.AllREIConfigCategories;
import me.shedaniel.rei.impl.client.gui.config.options.CompositeOption;
import me.shedaniel.rei.impl.client.gui.config.options.OptionGroup;

import java.util.function.BiConsumer;
import java.util.function.Function;

import static me.shedaniel.rei.impl.client.gui.config.options.ConfigUtils.translatable;

public class RSClientPlugin implements REIClientPlugin {
    static <T> CompositeOption<T> make(String id, Function<ConfigObjectImpl, T> bind,
                                       BiConsumer<ConfigObjectImpl, T> save) {
        return new CompositeOption<>(id, translatable("config." + id),
                translatable("config." + id + ".desc"), bind, save);
    }

    OptionGroup optionGroup = new OptionGroup(RoughlySearchable.MODID,
            translatable("config.roughlyenoughitems.roughlysearchable"));

    @Override
    public void registerConfigAddons(ConfigAddonRegistry registry) {
        optionGroup.add(make("roughlysearchable.keybind", i -> ((ICustomConfig)i).RS_getConfig().RS_keybind,
                (i, v) -> ((ICustomConfig)i).RS_getConfig().RS_keybind = v).keybind());
        optionGroup.add(make("roughlysearchable.search", i -> ((ICustomConfig)i).RS_getConfig().RS_search,
                (i, v) -> ((ICustomConfig)i).RS_getConfig().RS_search = v).enabledDisabled());
        optionGroup.add(make("roughlysearchable.indicator.trueSize", i -> ((ICustomConfig)i).RS_getConfig().RS_indicator.RS_trueSize,
                (i, v) -> ((ICustomConfig)i).RS_getConfig().RS_indicator.RS_trueSize = v).options(0.25F, 0.5F, 0.75F, 1F, 1.25F, 1.5F, 1.75F, 2.0F));
        optionGroup.add(make("roughlysearchable.indicator.falseSize", i -> ((ICustomConfig)i).RS_getConfig().RS_indicator.RS_falseSize,
                (i, v) -> ((ICustomConfig)i).RS_getConfig().RS_indicator.RS_falseSize = v).options(0.25F, 0.5F, 0.75F, 1F, 1.25F, 1.5F, 1.75F, 2.0F));
        optionGroup.add(make("roughlysearchable.indicator.background", i -> ((ICustomConfig)i).RS_getConfig().RS_indicator.RS_background,
                (i, v) -> ((ICustomConfig)i).RS_getConfig().RS_indicator.RS_background = v).enabledDisabled());

        AllREIConfigCategories.ACCESSIBILITY.add(optionGroup);
    }
}
