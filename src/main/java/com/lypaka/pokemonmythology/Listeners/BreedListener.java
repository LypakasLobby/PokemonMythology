package com.lypaka.pokemonmythology.Listeners;

import com.lypaka.pokemonmythology.API.MythicBreedEvent;
import com.lypaka.pokemonmythology.Handlers.BreedHandler;
import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.lypaka.pokemonmythology.MythicPokemon.MythicPokemon;
import com.pixelmonmod.pixelmon.api.daycare.event.DayCareEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BreedListener {

    @SubscribeEvent
    public void onMakeEgg (DayCareEvent.PostCollect event) {

        ServerPlayerEntity player = event.getPlayer();
        Pokemon parent1 = event.getParentOne();
        Pokemon parent2 = event.getParentTwo();
        Pokemon child = event.getChildGiven();
        MythicPokemon mythic = BreedHandler.getMythicPassedDown(event.getParentOne(), event.getParentTwo());

        MythicBreedEvent breedEvent = new MythicBreedEvent(player, parent1, parent2, child, mythic);
        MinecraftForge.EVENT_BUS.post(breedEvent);

        if (!breedEvent.isCanceled()) {

            MythicHandler.setMythic(breedEvent.getChild(), breedEvent.getMythic());

        }

    }

}
