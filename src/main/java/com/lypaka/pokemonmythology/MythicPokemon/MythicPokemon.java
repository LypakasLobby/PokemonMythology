package com.lypaka.pokemonmythology.MythicPokemon;

import com.pixelmonmod.pixelmon.api.pokemon.ribbon.Ribbon;
import com.pixelmonmod.pixelmon.api.pokemon.stats.BattleStatsType;

import java.awt.*;

public abstract class MythicPokemon {

    private final String name;
    private final BattleStatsType[] statsBoosted;
    private final Color color;
    private final float scale;
    private final Ribbon ribbon;
    private final String displayName;
    private final String informationText;

    public MythicPokemon (String name, BattleStatsType[] statsBoosted, Color color, float scale, Ribbon ribbon, String displayName, String informationText) {

        this.name = name;
        this.statsBoosted = statsBoosted;
        this.color = color;
        this.scale = scale;
        this.ribbon = ribbon;
        this.displayName = displayName;
        this.informationText = informationText;

    }

    public String getName() {

        return this.name;

    }

    public BattleStatsType[] getStatsBoosted() {

        return this.statsBoosted;

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

    public String getDisplayName() {

        return this.displayName;

    }

    public String getInformationText() {

        return this.informationText;

    }

}
