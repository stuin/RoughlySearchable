package com.stuintech.roughlysearchable.api;

import me.shedaniel.clothconfig2.api.ModifierKeyCode;

public interface ICustomConfig {
    ModifierKeyCode getRoughlySearchableKeybind();
    boolean searchOn();
    void toggleSearch();
}
