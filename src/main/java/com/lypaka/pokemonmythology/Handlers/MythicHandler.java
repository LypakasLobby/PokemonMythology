package com.lypaka.pokemonmythology.Handlers;

import com.lypaka.pokemonmythology.ConfigGetters;
import com.lypaka.pokemonmythology.MythicPokemon.*;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.Ribbon;
import com.pixelmonmod.pixelmon.api.pokemon.stats.BattleStatsType;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MythicHandler {

    public static Map<String, MythicPokemon> mythicMap;
    public static List<String> mythicList = Arrays.asList("Alpha", "Beta", "Delta", "Gamma", "Omega", "Sigma", "Theta", "Zeta");

    public static String getRandomMythic() {

        return RandomHelper.getRandomElementFromList(mythicList);

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
        AlphaPokemon alpha = new AlphaPokemon("Alpha", BattleStatsType.ATTACK, ConfigGetters.tints.get("Alpha"), ConfigGetters.scales.get("Alpha"), RibbonHandler.alphaRibbon);
        BetaPokemon beta = new BetaPokemon("Beta", BattleStatsType.SPECIAL_DEFENSE, ConfigGetters.tints.get("Beta"), ConfigGetters.scales.get("Beta"), RibbonHandler.betaRibbon);
        DeltaPokemon delta = new DeltaPokemon("Delta", BattleStatsType.HP, ConfigGetters.tints.get("Delta"), ConfigGetters.scales.get("Delta"), RibbonHandler.deltaRibbon);
        GammaPokemon gamma = new GammaPokemon("Gamma", BattleStatsType.SPEED, ConfigGetters.tints.get("Gamma"), ConfigGetters.scales.get("Gamma"), RibbonHandler.gammaRibbon);
        OmegaPokemon omega = new OmegaPokemon("Omega", BattleStatsType.SPECIAL_ATTACK, ConfigGetters.tints.get("Omega"), ConfigGetters.scales.get("Omega"), RibbonHandler.omegaRibbon);
        SigmaPokemon sigma = new SigmaPokemon("Sigma", BattleStatsType.DEFENSE, ConfigGetters.tints.get("Sigma"), ConfigGetters.scales.get("Sigma"), RibbonHandler.sigmaRibbon);
        ThetaPokemon theta = new ThetaPokemon("Theta", BattleStatsType.EVASION, ConfigGetters.tints.get("Theta"), ConfigGetters.scales.get("Theta"), RibbonHandler.thetaRibbon);
        ZetaPokemon zeta = new ZetaPokemon("Zeta", BattleStatsType.ACCURACY, ConfigGetters.tints.get("Zeta"), ConfigGetters.scales.get("Zeta"), RibbonHandler.zetaRibbon);
        mythicMap.put("Alpha", alpha);
        mythicMap.put("Beta", beta);
        mythicMap.put("Delta", delta);
        mythicMap.put("Gamma", gamma);
        mythicMap.put("Omega", omega);
        mythicMap.put("Sigma", sigma);
        mythicMap.put("Theta", theta);
        mythicMap.put("Zeta", zeta);

    }

    public static BattleStatsType getBoostedStat (MythicPokemon pokemon) {

        return pokemon.getStatBoosted();

    }

    public static void setPokemonToRandomMythic (PixelmonEntity pixelmon) {

        String mythic = getRandomMythic();
        MythicPokemon mythicPokemon = mythicMap.get(mythic);
        Color color = mythicPokemon.getColor();
        float scale = mythicPokemon.getScale();
        Ribbon ribbon = mythicPokemon.getRibbon();
        BattleStatsType battleStats = mythicPokemon.getStatBoosted();

        Pokemon pokemon = pixelmon.getPokemon();
        pixelmon.setPixelmonScale(scale);
        pixelmon.setColor(color);
        pokemon.addRibbon(ribbon);
        pokemon.setDisplayedRibbon(ribbon);
        pokemon.getPersistentData().putString("Mythic", mythicPokemon.getName());
        int stat = pokemon.getStats().get(battleStats);
        int updated = (int) (stat * ConfigGetters.statModifier);
        pokemon.getStats().set(battleStats, updated);

    }

    public static void reapplyStatBoost (Pokemon pokemon, BattleStatsType battleStats, int level) {

        int naturalStat = pokemon.getStats().calculateStat(battleStats, pokemon.getNature(), pokemon.getForm(), level);
        int updated = (int) (naturalStat * ConfigGetters.statModifier);
        pokemon.getStats().set(battleStats, updated);

    }

    public static void setMythic (Pokemon pokemon, MythicPokemon mythic) {

        Color color = mythic.getColor();
        float scale = mythic.getScale();
        Ribbon ribbon = mythic.getRibbon();
        BattleStatsType battleStats = mythic.getStatBoosted();

        pokemon.addRibbon(ribbon);
        pokemon.setDisplayedRibbon(ribbon);
        pokemon.getPersistentData().putString("Mythic", mythic.getName());
        int stat = pokemon.getStats().get(battleStats);
        int updated = (int) (stat * ConfigGetters.statModifier);
        pokemon.getStats().set(battleStats, updated);

    }

}
