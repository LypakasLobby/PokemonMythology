package com.lypaka.pokemonmythology.Listeners;

import com.lypaka.pokemonmythology.API.MythicKillEvent;
import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.lypaka.pokemonmythology.MythicPokemon.MythicPokemon;
import com.pixelmonmod.pixelmon.api.events.BeatWildPixelmonEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KillListener {

    @SubscribeEvent
    public void onKill (BeatWildPixelmonEvent event) {

        ServerPlayerEntity player = event.player;
        Pokemon pokemon = event.wpp.controlledPokemon.get(0).pokemon;

        if (MythicHandler.isPokemonMythic(pokemon)) {

            MythicPokemon mythic = MythicHandler.getMythicFromPokemon(pokemon);
            MythicKillEvent killEvent = new MythicKillEvent(player, pokemon, mythic);
            MinecraftForge.EVENT_BUS.post(killEvent);

        }

    }

}
