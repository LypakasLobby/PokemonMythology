package com.lypaka.pokemonmythology.Listeners;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.pokemonmythology.ConfigGetters;
import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.lypaka.pokemonmythology.MythicPokemon.MythicPokemon;
import com.lypaka.pokemonmythology.PokemonMythology;
import com.pixelmonmod.pixelmon.api.events.PixelmonUpdateEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PixelmonUpdateListener {

    @SubscribeEvent
    public void onUpdate (PixelmonUpdateEvent event) {

        PixelmonEntity pixelmon = event.pokemon;
        Pokemon pokemon = pixelmon.getPokemon();

        if (!MythicHandler.isPokemonMythic(pokemon)) return;
        if (!ConfigGetters.disclaimer) {

            PokemonMythology.logger.info(FancyText.getFormattedText("&cDisclaimer is not agreed to!"));
            PokemonMythology.logger.info(FancyText.getFormattedText("&cGo in \"/config/pokemonmythology/pokemonmythology.conf\" and set the disclaimer node to true!"));
            PokemonMythology.logger.info(FancyText.getFormattedText("&cAfter changing that configuration node, run \"/pkmnmyth reload\" to apply the changes and enable the mod."));
            return;

        }
        MythicPokemon mythic = MythicHandler.getMythicFromPokemon(pokemon);
        pixelmon.setPixelmonScale(mythic.getScale());
        if (!pixelmon.hasOwner()) { // wild Pokemon, try to set ribbon data

            pixelmon.setCustomName(FancyText.getFormattedText(mythic.getDisplayName() + pokemon.getSpecies().getName()));

        }
        pixelmon.setColor(mythic.getColor());
        if (!pokemon.getRibbons().contains(mythic.getRibbon())) {

            pokemon.addRibbon(mythic.getRibbon());
            pokemon.setDisplayedRibbon(mythic.getRibbon());

        }

    }

}
