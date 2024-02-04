package com.lypaka.pokemonmythology.Listeners;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.pokemonmythology.API.MythicBreedEvent;
import com.lypaka.pokemonmythology.ConfigGetters;
import com.lypaka.pokemonmythology.Handlers.BreedHandler;
import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.lypaka.pokemonmythology.MythicPokemon.MythicPokemon;
import com.lypaka.pokemonmythology.PokemonMythology;
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

        if (mythic != null) {

            if (!ConfigGetters.disclaimer) {

                PokemonMythology.logger.info(FancyText.getFormattedText("&cDisclaimer is not agreed to!"));
                PokemonMythology.logger.info(FancyText.getFormattedText("&cGo in \"/config/pokemonmythology/pokemonmythology.conf\" and set the disclaimer node to true!"));
                PokemonMythology.logger.info(FancyText.getFormattedText("&cAfter changing that configuration node, run \"/pkmnmyth reload\" to apply the changes and enable the mod."));
                return;

            }

            MythicBreedEvent breedEvent = new MythicBreedEvent(player, parent1, parent2, child, mythic);
            MinecraftForge.EVENT_BUS.post(breedEvent);

            if (!breedEvent.isCanceled()) {

                MythicHandler.setMythic(breedEvent.getChild(), breedEvent.getMythic(), false);

            }

        }

    }

}
