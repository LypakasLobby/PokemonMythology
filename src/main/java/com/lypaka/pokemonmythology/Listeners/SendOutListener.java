package com.lypaka.pokemonmythology.Listeners;

import com.pixelmonmod.pixelmon.api.events.PokemonSendOutEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SendOutListener {

    @SubscribeEvent
    public void onSendOut (PokemonSendOutEvent event) {

        ServerPlayerEntity player = event.getPlayer();
        Pokemon pokemon = event.getPokemon();


    }

}
