package com.lypaka.pokemonmythology.Handlers;

import com.lypaka.pokemonmythology.ConfigGetters;
import com.lypaka.pokemonmythology.MythicPokemon.*;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.Ribbon;
import com.pixelmonmod.pixelmon.api.pokemon.stats.BattleStatsType;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;

import java.awt.*;
import java.util.List;
import java.util.*;

public class MythicHandler {

    public static Map<String, MythicPokemon> mythicMap;
    public static List<String> mythicList = Arrays.asList("Alpha", "Beta", "Delta", "Gamma", "Omega", "Sigma", "Theta", "Zeta");

    public static String getRandomMythic() {

        List<String> options = new ArrayList<>(mythicList);
        if (ConfigGetters.customMythics.size() > 0) {

            options.addAll(ConfigGetters.customMythics.keySet());

        }
        return RandomHelper.getRandomElementFromList(options);

    }

    public static boolean isPokemonMythic (PixelmonEntity pixelmon) {

        return pixelmon.getPokemon().getPersistentData().contains("Mythic");

    }

    public static boolean isPokemonMythic (Pokemon pokemon) {

        return pokemon.getPersistentData().contains("Mythic");

    }

    public static MythicPokemon getMythicFromPokemon (PixelmonEntity pixelmon) {

        return mythicMap.get(pixelmon.getPokemon().getPersistentData().getString("Mythic"));

    }

    public static MythicPokemon getMythicFromPokemon (Pokemon pokemon) {

        return mythicMap.get(pokemon.getPersistentData().getString("Mythic"));

    }

    public static void loadMythics() {

        mythicMap = new HashMap<>();
        AlphaPokemon alpha = new AlphaPokemon("Alpha",
                new BattleStatsType[]{BattleStatsType.ATTACK},
                ConfigGetters.tints.get("Alpha"),
                ConfigGetters.scales.get("Alpha"),
                RibbonHandler.alphaRibbon,
                ConfigGetters.displayTitles.get("Alpha"),
                ConfigGetters.infoTexts.get("Alpha")
        );
        BetaPokemon beta = new BetaPokemon("Beta",
                new BattleStatsType[]{BattleStatsType.SPECIAL_DEFENSE},
                ConfigGetters.tints.get("Beta"),
                ConfigGetters.scales.get("Beta"),
                RibbonHandler.betaRibbon,
                ConfigGetters.displayTitles.get("Beta"),
                ConfigGetters.infoTexts.get("Beta")
        );
        DeltaPokemon delta = new DeltaPokemon("Delta",
                new BattleStatsType[]{BattleStatsType.HP},
                ConfigGetters.tints.get("Delta"),
                ConfigGetters.scales.get("Delta"),
                RibbonHandler.deltaRibbon,
                ConfigGetters.displayTitles.get("Delta"),
                ConfigGetters.infoTexts.get("Delta")
        );
        GammaPokemon gamma = new GammaPokemon("Gamma",
                new BattleStatsType[]{BattleStatsType.SPEED},
                ConfigGetters.tints.get("Gamma"),
                ConfigGetters.scales.get("Gamma"),
                RibbonHandler.gammaRibbon,
                ConfigGetters.displayTitles.get("Gamma"),
                ConfigGetters.infoTexts.get("Gamma")
        );
        OmegaPokemon omega = new OmegaPokemon("Omega",
                new BattleStatsType[]{BattleStatsType.SPECIAL_ATTACK},
                ConfigGetters.tints.get("Omega"),
                ConfigGetters.scales.get("Omega"),
                RibbonHandler.omegaRibbon,
                ConfigGetters.displayTitles.get("Omega"),
                ConfigGetters.infoTexts.get("Omega")
        );
        SigmaPokemon sigma = new SigmaPokemon("Sigma",
                new BattleStatsType[]{BattleStatsType.DEFENSE},
                ConfigGetters.tints.get("Sigma"),
                ConfigGetters.scales.get("Sigma"),
                RibbonHandler.sigmaRibbon,
                ConfigGetters.displayTitles.get("Sigma"),
                ConfigGetters.infoTexts.get("Sigma")
        );
        ThetaPokemon theta = new ThetaPokemon("Theta",
                new BattleStatsType[]{BattleStatsType.EVASION},
                ConfigGetters.tints.get("Theta"),
                ConfigGetters.scales.get("Theta"),
                RibbonHandler.thetaRibbon,
                ConfigGetters.displayTitles.get("Theta"),
                ConfigGetters.infoTexts.get("Theta")
        );
        ZetaPokemon zeta = new ZetaPokemon("Zeta",
                new BattleStatsType[]{BattleStatsType.ACCURACY},
                ConfigGetters.tints.get("Zeta"),
                ConfigGetters.scales.get("Zeta"),
                RibbonHandler.zetaRibbon,
                ConfigGetters.displayTitles.get("Zeta"),
                ConfigGetters.infoTexts.get("Zeta")
        );
        mythicMap.put("Alpha", alpha);
        mythicMap.put("Beta", beta);
        mythicMap.put("Delta", delta);
        mythicMap.put("Gamma", gamma);
        mythicMap.put("Omega", omega);
        mythicMap.put("Sigma", sigma);
        mythicMap.put("Theta", theta);
        mythicMap.put("Zeta", zeta);

        for (Map.Entry<String, Map<String, String>> entry : ConfigGetters.customMythics.entrySet()) {

            String mythicName = entry.getKey();
            Map<String, String> data = entry.getValue();
            String[] stats = data.get("Battle-Stats").split(", ");
            BattleStatsType[] battleStats = new BattleStatsType[stats.length];
            for (int i = 0; i < stats.length; i++) {

                battleStats[i] = BattleStatsType.valueOf(stats[i].toUpperCase().replace(" ", "_"));

            }
            String displayName = data.get("Display-Title");
            String informationText = data.get("Information-Text");
            float scale = Float.parseFloat(data.get("Scale"));
            String[] colorValues = data.get("Tint").split(", ");
            Color color = new Color(Integer.parseInt(colorValues[0]), Integer.parseInt(colorValues[1]), Integer.parseInt(colorValues[2]));
            Ribbon ribbon = RibbonHandler.customMythicRibbons.get(mythicName);

            CustomMythic customMythic = new CustomMythic(
                    mythicName,
                    battleStats,
                    color,
                    scale,
                    ribbon,
                    displayName,
                    informationText
            );

            mythicMap.put(mythicName, customMythic);

        }

    }

    public static BattleStatsType[] getBoostedStats (MythicPokemon pokemon) {

        return pokemon.getStatsBoosted();

    }

    public static void setPokemonToRandomMythic (PixelmonEntity pixelmon) {

        String mythic = getRandomMythic();
        MythicPokemon mythicPokemon = mythicMap.get(mythic);
        Color color = mythicPokemon.getColor();
        float scale = mythicPokemon.getScale();
        Ribbon ribbon = mythicPokemon.getRibbon();
        BattleStatsType[] battleStats = mythicPokemon.getStatsBoosted();

        Pokemon pokemon = pixelmon.getPokemon();
        pixelmon.setPixelmonScale(scale);
        pixelmon.setColor(color);
        pokemon.addRibbon(ribbon);
        pokemon.setDisplayedRibbon(ribbon);
        pokemon.getPersistentData().putString("Mythic", mythicPokemon.getName());
        for (BattleStatsType battleStat : battleStats) {

            int stat = pokemon.getStats().get(battleStat);
            int updated = (int) (stat * ConfigGetters.statModifier);
            pokemon.getStats().set(battleStat, updated);

        }

    }

    public static void reapplyStatBoost (Pokemon pokemon, BattleStatsType[] battleStats, int level) {

        for (BattleStatsType stat : battleStats) {

            int naturalStat = pokemon.getStats().calculateStat(stat, pokemon.getNature(), pokemon.getForm(), level);
            int updated = (int) (naturalStat * ConfigGetters.statModifier);
            pokemon.getStats().set(stat, updated);

        }

    }

    public static void setMythic (Pokemon pokemon, MythicPokemon mythic) {

        Ribbon ribbon = mythic.getRibbon();
        BattleStatsType[] battleStats = mythic.getStatsBoosted();

        pokemon.addRibbon(ribbon);
        pokemon.setDisplayedRibbon(ribbon);
        pokemon.getPersistentData().putString("Mythic", mythic.getName());
        for (BattleStatsType battleStat : battleStats) {

            int stat = pokemon.getStats().get(battleStat);
            int updated = (int) (stat * ConfigGetters.statModifier);
            pokemon.getStats().set(battleStat, updated);

        }

    }

    public static MythicPokemon getFromName (String name) {

        return mythicMap.getOrDefault(name, null);

    }

}
