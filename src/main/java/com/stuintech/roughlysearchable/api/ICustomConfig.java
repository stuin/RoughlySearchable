package com.stuintech.roughlysearchable.api;

import me.shedaniel.rei.api.ConfigObject;

public interface ICustomConfig {
    RoughlySearchableConfig.Config RS_getConfig();
    
    static RoughlySearchableConfig.Config getConfig() {
        return ((ICustomConfig) ConfigObject.getInstance()).RS_getConfig();
    }

    static RoughlySearchableConfig.Indicator getIndicator() {
        return ((ICustomConfig) ConfigObject.getInstance()).RS_getConfig().RS_indicator;
    }
}
