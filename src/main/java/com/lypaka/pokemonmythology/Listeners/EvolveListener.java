package com.lypaka.pokemonmythology.Listeners;

import com.lypaka.pokemonmythology.API.MythicEvolveEvent;
import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.lypaka.pokemonmythology.MythicPokemon.MythicPokemon;
import com.pixelmonmod.pixelmon.api.events.EvolveEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EvolveListener {

    @SubscribeEvent
    public void onEvolve (EvolveEvent.Post event) {

        ServerPlayerEntity player = event.getPlayer();
        Pokemon pokemon = event.getPokemon();

        if (MythicHandler.isPokemonMythic(pokemon)) {

            MythicPokemon mythic = MythicHandler.getMythicFromPokemon(pokemon);
            MythicEvolveEvent evolveEvent = new MythicEvolveEvent(player, pokemon, mythic);
            MinecraftForge.EVENT_BUS.post(evolveEvent);

        }

    }

}
