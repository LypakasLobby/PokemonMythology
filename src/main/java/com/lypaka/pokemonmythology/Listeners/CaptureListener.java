package com.lypaka.pokemonmythology.Listeners;

import com.lypaka.pokemonmythology.API.MythicCaptureEvent;
import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.lypaka.pokemonmythology.MythicPokemon.MythicPokemon;
import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CaptureListener {

    @SubscribeEvent
    public void onCapture (CaptureEvent.SuccessfulCapture event) {

        ServerPlayerEntity player = event.getPlayer();
        PixelmonEntity pixelmon = event.getPokemon();
        Pokemon pokemon = pixelmon.getPokemon();

        if (MythicHandler.isPokemonMythic(pokemon)) {

            MythicPokemon mythic = MythicHandler.getMythicFromPokemon(pokemon);
            MythicCaptureEvent captureEvent = new MythicCaptureEvent(player, pokemon, mythic);
            MinecraftForge.EVENT_BUS.post(captureEvent);

        }

    }

}
