package com.stuintech.roughlysearchable.api;

import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import me.shedaniel.clothconfig2.api.Modifier;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
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
        public float RS_trueSize = 1.25F;
        @UseRatio(
                min = 0.25F,
                max = 2.0F
        )
        public float RS_falseSize = 0.75F;

        @Comment("Uses default REI search")
        public boolean RS_background = false;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    @interface UseRatio {
        float min();
        float max();
    }
}
