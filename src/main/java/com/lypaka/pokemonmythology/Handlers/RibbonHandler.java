package com.lypaka.pokemonmythology.Handlers;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.MiscHandlers.SimplerRibbonBuilder;
import com.lypaka.pokemonmythology.ConfigGetters;
import com.lypaka.pokemonmythology.PokemonMythology;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.MutableRibbonData;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.Ribbon;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.RibbonRegistry;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.type.RibbonType;
import com.pixelmonmod.pixelmon.api.util.ResourceWithFallback;
import com.pixelmonmod.pixelmon.api.util.helpers.ResourceLocationHelper;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RibbonHandler {

    public static Map<String, MutableRibbonData> ribbonDataMap;
    public static Map<String, Ribbon> ribbonMap;
    public static Map<String, Ribbon> customMythicRibbons;
    public static Map<String, MutableRibbonData> customMythicRibbonData;

    public static void loadRibbons() {

        ribbonDataMap = new HashMap<>();
        ribbonMap = new HashMap<>();
        for (Map.Entry<String, String> displayTexts : ConfigGetters.displayTitles.entrySet()) {

            String key = displayTexts.getKey();
            String value = displayTexts.getValue();
            SimplerRibbonBuilder ribbonBuilder = new SimplerRibbonBuilder(value, "ability", ConfigGetters.infoTexts.get(key), value, "");
            MutableRibbonData data = ribbonBuilder.create();
            ribbonDataMap.put(key, data);
            Ribbon ribbon = new Ribbon(RibbonRegistry.SPECIAL.getKey(), LocalDateTime.now().getDayOfYear(), FancyText.getFormattedText(""), data);
            ribbonMap.put(key, ribbon);

        }

    }

    public static void loadCustomMythicRibbonData() {

        if (ConfigGetters.customMythics.size() == 0) return;
        customMythicRibbons = new HashMap<>();
        customMythicRibbonData = new HashMap<>();
        for (Map.Entry<String, Map<String, String>> entry : ConfigGetters.customMythics.entrySet()) {

            Map<String, String> data = entry.getValue();
            String displayTitle = data.get("Display-Title");
            String icon = "ability";
            SimplerRibbonBuilder builder = new SimplerRibbonBuilder(displayTitle, icon, displayTitle, displayTitle, "");
            MutableRibbonData ribbonData = builder.create();
            Ribbon ribbon = new Ribbon(RibbonRegistry.SPECIAL.getKey(), LocalDateTime.now().getDayOfYear(), FancyText.getFormattedText(""), ribbonData);
            customMythicRibbons.put(entry.getKey(), ribbon);
            customMythicRibbonData.put(entry.getKey(), ribbonData);

        }

    }

}
