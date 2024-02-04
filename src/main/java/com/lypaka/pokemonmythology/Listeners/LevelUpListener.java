package com.lypaka.pokemonmythology.Listeners;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.pokemonmythology.ConfigGetters;
import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.lypaka.pokemonmythology.MythicPokemon.MythicPokemon;
import com.lypaka.pokemonmythology.PokemonMythology;
import com.pixelmonmod.pixelmon.api.events.LevelUpEvent;
import com.pixelmonmod.pixelmon.api.events.RareCandyEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LevelUpListener {

    @SubscribeEvent
    public void onLevelUp (LevelUpEvent.Post event) {

        Pokemon pokemon = event.getPokemon();
        if (MythicHandler.isPokemonMythic(pokemon)) {

            if (!ConfigGetters.disclaimer) {

                PokemonMythology.logger.info(FancyText.getFormattedText("&cDisclaimer is not agreed to!"));
                PokemonMythology.logger.info(FancyText.getFormattedText("&cGo in \"/config/pokemonmythology/pokemonmythology.conf\" and set the disclaimer node to true!"));
                PokemonMythology.logger.info(FancyText.getFormattedText("&cAfter changing that configuration node, run \"/pkmnmyth reload\" to apply the changes and enable the mod."));
                return;

            }
            MythicPokemon mythic = MythicHandler.getMythicFromPokemon(pokemon);
            MythicHandler.reapplyStatBoost(pokemon, mythic.getStatsBoosted(), event.getAfterLevel());

        }

    }

    @SubscribeEvent
    public void onRareCandy (RareCandyEvent event) {

        Pokemon pokemon = event.getPixelmon().getPokemon();
        if (MythicHandler.isPokemonMythic(pokemon)) {

            if (!ConfigGetters.disclaimer) {

                PokemonMythology.logger.info(FancyText.getFormattedText("&cDisclaimer is not agreed to!"));
                PokemonMythology.logger.info(FancyText.getFormattedText("&cGo in \"/config/pokemonmythology/pokemonmythology.conf\" and set the disclaimer node to true!"));
                PokemonMythology.logger.info(FancyText.getFormattedText("&cAfter changing that configuration node, run \"/pkmnmyth reload\" to apply the changes and enable the mod."));
                return;

            }
            MythicPokemon mythic = MythicHandler.getMythicFromPokemon(pokemon);
            MythicHandler.reapplyStatBoost(pokemon, mythic.getStatsBoosted(), pokemon.getPokemonLevel());

        }

    }

}
