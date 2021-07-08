package com.stuintech.roughlysearchable.mixins;

import com.stuintech.roughlysearchable.api.ICustomConfig;
import com.stuintech.roughlysearchable.api.RoughlySearchableConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.rei.api.client.config.ConfigObject;
import me.shedaniel.rei.impl.client.config.ConfigObjectImpl;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ConfigObjectImpl.class)
public abstract class ConfigObjectMixin implements ConfigObject, ConfigData, ICustomConfig {
    @ConfigEntry.Category("roughlysearchable")
    @ConfigEntry.Gui.TransitiveObject
    public RoughlySearchableConfig.Config config = new RoughlySearchableConfig.Config();

    @Override
    public RoughlySearchableConfig.Config RS_getConfig() {
        return config;
    }
}
