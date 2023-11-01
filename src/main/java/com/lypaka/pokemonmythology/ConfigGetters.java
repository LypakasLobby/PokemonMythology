package com.lypaka.pokemonmythology;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ConfigGetters {

    public static double conversionChance;
    public static Map<String, String> displayTitles;
    public static Map<String, String> infoTexts;
    public static Map<String, Color> tints;
    public static Map<String, Float> scales;
    public static double statModifier;

    public static void load() throws ObjectMappingException {

        conversionChance = PokemonMythology.configManager.getConfigNode(0, "Conversion-Chance").getDouble();
        displayTitles = PokemonMythology.configManager.getConfigNode(0, "Data", "Display-Titles").getValue(new TypeToken<Map<String, String>>() {});
        infoTexts = PokemonMythology.configManager.getConfigNode(0, "Data", "Information-Text").getValue(new TypeToken<Map<String, String>>() {});
        Map<String, String> map = PokemonMythology.configManager.getConfigNode(0, "Data", "Tint").getValue(new TypeToken<Map<String, String>>() {});
        tints = new HashMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {

            String[] value = entry.getValue().split(", ");
            Color color = new Color(Integer.parseInt(value[0]), Integer.parseInt(value[1]), Integer.parseInt(value[2]));
            tints.put(entry.getKey(), color);

        }
        scales = PokemonMythology.configManager.getConfigNode(0, "Data", "Scale").getValue(new TypeToken<Map<String, Float>>() {});
        statModifier = PokemonMythology.configManager.getConfigNode(0, "Data", "Stat-Modifier").getDouble();

    }

}
