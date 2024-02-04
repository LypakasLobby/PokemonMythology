package com.lypaka.pokemonmythology.Listeners;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.pokemonmythology.API.MythicKillEvent;
import com.lypaka.pokemonmythology.ConfigGetters;
import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.lypaka.pokemonmythology.MythicPokemon.MythicPokemon;
import com.lypaka.pokemonmythology.PokemonMythology;
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

            if (!ConfigGetters.disclaimer) {

                PokemonMythology.logger.info(FancyText.getFormattedText("&cDisclaimer is not agreed to!"));
                PokemonMythology.logger.info(FancyText.getFormattedText("&cGo in \"/config/pokemonmythology/pokemonmythology.conf\" and set the disclaimer node to true!"));
                PokemonMythology.logger.info(FancyText.getFormattedText("&cAfter changing that configuration node, run \"/pkmnmyth reload\" to apply the changes and enable the mod."));
                return;

            }
            MythicPokemon mythic = MythicHandler.getMythicFromPokemon(pokemon);
            MythicKillEvent killEvent = new MythicKillEvent(player, pokemon, mythic);
            MinecraftForge.EVENT_BUS.post(killEvent);

        }

    }

}
