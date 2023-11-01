package com.lypaka.pokemonmythology.Listeners;

import com.lypaka.pokemonmythology.ConfigGetters;
import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.pixelmonmod.pixelmon.api.events.spawning.SpawnEvent;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SpawnListener {

    @SubscribeEvent
    public void onSpawn (SpawnEvent event) {

        if (event.action.getOrCreateEntity() instanceof PixelmonEntity) {

            if (!RandomHelper.getRandomChance(ConfigGetters.conversionChance)) return;
            PixelmonEntity pixelmon = (PixelmonEntity) event.action.getOrCreateEntity();
            MythicHandler.setPokemonToRandomMythic(pixelmon);

        }

    }

}
