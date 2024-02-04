package com.lypaka.pokemonmythology.Listeners;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.pokemonmythology.ConfigGetters;
import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.lypaka.pokemonmythology.PokemonMythology;
import com.pixelmonmod.pixelmon.api.events.spawning.SpawnEvent;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SpawnListener {

    @SubscribeEvent
    public void onSpawn (SpawnEvent event) {

        if (event.action.getOrCreateEntity() instanceof PixelmonEntity) {

            if (!RandomHelper.getRandomChance(ConfigGetters.conversionChance)) return;
            if (!ConfigGetters.disclaimer) {

                PokemonMythology.logger.info(FancyText.getFormattedText("&cDisclaimer is not agreed to!"));
                PokemonMythology.logger.info(FancyText.getFormattedText("&cGo in \"/config/pokemonmythology/pokemonmythology.conf\" and set the disclaimer node to true!"));
                PokemonMythology.logger.info(FancyText.getFormattedText("&cAfter changing that configuration node, run \"/pkmnmyth reload\" to apply the changes and enable the mod."));
                return;

            }
            PixelmonEntity pixelmon = (PixelmonEntity) event.action.getOrCreateEntity();
            MythicHandler.setPokemonToRandomMythic(pixelmon, true, true);

        }

    }

}
