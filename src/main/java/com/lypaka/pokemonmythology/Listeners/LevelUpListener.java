package com.lypaka.pokemonmythology.Listeners;

import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.lypaka.pokemonmythology.MythicPokemon.MythicPokemon;
import com.pixelmonmod.pixelmon.api.events.LevelUpEvent;
import com.pixelmonmod.pixelmon.api.events.RareCandyEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LevelUpListener {

    @SubscribeEvent
    public void onLevelUp (LevelUpEvent.Post event) {

        Pokemon pokemon = event.getPokemon();
        if (MythicHandler.isPokemonMythic(pokemon)) {

            MythicPokemon mythic = MythicHandler.getMythicFromPokemon(pokemon);
            MythicHandler.reapplyStatBoost(pokemon, mythic.getStatBoosted(), event.getAfterLevel());

        }

    }

    @SubscribeEvent
    public void onRareCandy (RareCandyEvent event) {

        Pokemon pokemon = event.getPixelmon().getPokemon();
        if (MythicHandler.isPokemonMythic(pokemon)) {

            MythicPokemon mythic = MythicHandler.getMythicFromPokemon(pokemon);
            MythicHandler.reapplyStatBoost(pokemon, mythic.getStatBoosted(), pokemon.getPokemonLevel());

        }

    }

}
