package com.lypaka.pokemonmythology.Listeners;

import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.lypaka.pokemonmythology.MythicPokemon.MythicPokemon;
import com.pixelmonmod.pixelmon.api.events.EvolveEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EvolutionListener {

    @SubscribeEvent
    public void onEvolve (EvolveEvent.Post event) {

        Pokemon pokemon = event.getPokemon();
        if (MythicHandler.isPokemonMythic(pokemon)) {

            MythicPokemon mythic = MythicHandler.getMythicFromPokemon(pokemon);
            MythicHandler.reapplyStatBoost(pokemon, mythic.getStatsBoosted(), pokemon.getPokemonLevel());

        }

    }

}
