package com.stuintech.roughlysearchable.mixins;

import com.stuintech.roughlysearchable.api.ICustomConfig;
import com.stuintech.roughlysearchable.api.RoughlySearchableConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import me.shedaniel.rei.api.ConfigObject;
import me.shedaniel.rei.impl.ConfigObjectImpl;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ConfigObjectImpl.class)
public abstract class ConfigObjectMixin implements ConfigObject, ConfigData, ICustomConfig {
    @ConfigEntry.Category("roughlysearchable")
    @ConfigEntry.Gui.TransitiveObject
    public RoughlySearchableConfig.Config config = new RoughlySearchableConfig.Config();

    @Override
    public ModifierKeyCode getRoughlySearchableKeybind() {
        return config.roughlySearchableKeybind == null ? ModifierKeyCode.unknown() : config.roughlySearchableKeybind;
    }

    @Override
    public boolean searchOn() {
        return config.search;
    }

    @Override
    public void toggleSearch() {
        config.search = !config.search;
    }
}
