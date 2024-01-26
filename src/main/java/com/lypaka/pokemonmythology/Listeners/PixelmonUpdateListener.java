package com.lypaka.pokemonmythology.Listeners;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.lypaka.pokemonmythology.Handlers.RibbonHandler;
import com.lypaka.pokemonmythology.MythicPokemon.MythicPokemon;
import com.pixelmonmod.pixelmon.api.events.PixelmonUpdateEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.Ribbon;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;

public class PixelmonUpdateListener {

    @SubscribeEvent
    public void onUpdate (PixelmonUpdateEvent event) {

        PixelmonEntity pixelmon = event.pokemon;
        Pokemon pokemon = pixelmon.getPokemon();

        if (!MythicHandler.isPokemonMythic(pokemon)) return;
        MythicPokemon mythic = MythicHandler.getMythicFromPokemon(pokemon);
        pixelmon.setPixelmonScale(mythic.getScale());
        if (!pixelmon.hasOwner()) { // wild Pokemon, try to set ribbon data

            pixelmon.setCustomName(FancyText.getFormattedText(mythic.getDisplayName() + pokemon.getSpecies().getName()));

        }
        pixelmon.setColor(mythic.getColor());

        // Hopefully fixes older versions of the mod's RibbonData
        // This is just a little check as to not be looping over a Pokemon's Ribbons every tick if we've already fixed it
        if (pokemon.getPersistentData().contains("FixedRibbon")) return;
        RibbonHandler.fixOlderRibbonsIfNecessary(pokemon);

    }

}
