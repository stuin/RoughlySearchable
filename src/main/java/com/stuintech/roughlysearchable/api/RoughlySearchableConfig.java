package com.stuintech.roughlysearchable.api;

import me.shedaniel.clothconfig2.api.Modifier;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import net.minecraft.client.util.InputUtil;

public class RoughlySearchableConfig {
    public static class Config {
        public ModifierKeyCode roughlySearchableKeybind;
        public boolean search;
        
        public Config() {
            search = false;
            roughlySearchableKeybind = ModifierKeyCode.of(InputUtil.Type.KEYSYM.createFromCode(73), Modifier.of(false, true, false));
        }
    }
}
