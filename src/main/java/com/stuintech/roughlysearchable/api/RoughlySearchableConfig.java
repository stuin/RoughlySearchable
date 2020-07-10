package com.stuintech.roughlysearchable.api;

import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.shedaniel.clothconfig2.api.Modifier;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import me.shedaniel.rei.gui.OverlaySearchField;
import net.minecraft.client.util.InputUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class RoughlySearchableConfig {
    public static class Config {
        public ModifierKeyCode RS_keybind;
        public boolean RS_search;

        @ConfigEntry.Gui.CollapsibleObject(
                startExpanded = true
        )
        public Indicator RS_indicator;
        
        public Config() {
            RS_keybind = ModifierKeyCode.of(InputUtil.Type.KEYSYM.createFromCode(73), Modifier.of(false, true, false));
            RS_search = false;
            RS_indicator = new Indicator();
        }
        
        public void toggleSearch() {
            RS_search = !RS_search;
        }
    }
    
    public static class Indicator {
        @UseRatio(
                min = 0.25F,
                max = 2.0F
        )
        public float RS_trueSize = 1.3F;
        @UseRatio(
                min = 0.25F,
                max = 2.0F
        )
        public float RS_falseSize = 0.7F;
        public boolean RS_background = false;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    @interface UseRatio {
        float min();
        float max();
    }
}
