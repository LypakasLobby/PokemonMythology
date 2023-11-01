package com.lypaka.pokemonmythology.Listeners;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.pokemonmythology.ConfigGetters;
import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.lypaka.pokemonmythology.MythicPokemon.MythicPokemon;
import com.pixelmonmod.pixelmon.api.events.PixelmonUpdateEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PixelmonUpdateListener {

    @SubscribeEvent
    public void onUpdate (PixelmonUpdateEvent event) {

        PixelmonEntity pixelmon = event.pokemon;
        Pokemon pokemon = pixelmon.getPokemon();

        if (pokemon.getPersistentData().contains("Mythic")) {

            String mythicName = pokemon.getPersistentData().getString("Mythic");
            MythicPokemon mythic = MythicHandler.mythicMap.get(mythicName);
            pixelmon.setPixelmonScale(mythic.getScale());
            if (!pixelmon.hasOwner()) { // wild Pokemon, try to set ribbon data

                pixelmon.setCustomName(FancyText.getFormattedText(ConfigGetters.displayTitles.get(mythic.getName()) + pokemon.getSpecies().getName()));

            } else {

                pixelmon.setColor(mythic.getColor());

            }

        }

    }

}
