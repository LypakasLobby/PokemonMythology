package com.lypaka.pokemonmythology.MythicPokemon;

import com.pixelmonmod.pixelmon.api.pokemon.ribbon.Ribbon;
import com.pixelmonmod.pixelmon.api.pokemon.stats.BattleStatsType;

import java.awt.*;

public abstract class MythicPokemon {

    private final String name;
    private final BattleStatsType[] statBoosted;
    private final Color color;
    private final float scale;
    private final Ribbon ribbon;

    public MythicPokemon (String name, BattleStatsType[] statBoosted, Color color, float scale, Ribbon ribbon) {

        this.name = name;
        this.statBoosted = statBoosted;
        this.color = color;
        this.scale = scale;
        this.ribbon = ribbon;

    }

    public String getName() {

        return this.name;

    }

    public BattleStatsType[] getStatBoosted() {

        return this.statBoosted;

    }

    public Color getColor() {

        return this.color;

    }

    public float getScale() {

        return this.scale;

    }

    public Ribbon getRibbon() {

        return this.ribbon;

    }

}
