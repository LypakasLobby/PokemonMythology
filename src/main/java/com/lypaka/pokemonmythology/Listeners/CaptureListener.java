package com.lypaka.pokemonmythology.Listeners;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.pokemonmythology.API.MythicCaptureEvent;
import com.lypaka.pokemonmythology.ConfigGetters;
import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.lypaka.pokemonmythology.MythicPokemon.MythicPokemon;
import com.lypaka.pokemonmythology.PokemonMythology;
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

            if (!ConfigGetters.disclaimer) {

                PokemonMythology.logger.info(FancyText.getFormattedText("&cDisclaimer is not agreed to!"));
                PokemonMythology.logger.info(FancyText.getFormattedText("&cGo in \"/config/pokemonmythology/pokemonmythology.conf\" and set the disclaimer node to true!"));
                PokemonMythology.logger.info(FancyText.getFormattedText("&cAfter changing that configuration node, run \"/pkmnmyth reload\" to apply the changes and enable the mod."));
                return;

            }
            MythicPokemon mythic = MythicHandler.getMythicFromPokemon(pokemon);
            MythicCaptureEvent captureEvent = new MythicCaptureEvent(player, pokemon, mythic);
            MinecraftForge.EVENT_BUS.post(captureEvent);

        }

    }

}
