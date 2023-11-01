package com.lypaka.pokemonmythology.API;

import com.lypaka.pokemonmythology.MythicPokemon.MythicPokemon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class MythicEvolveEvent extends Event {

    private final ServerPlayerEntity player;
    private final Pokemon pokemon;
    private final MythicPokemon mythic;

    public MythicEvolveEvent (ServerPlayerEntity player, Pokemon pokemon, MythicPokemon mythic) {

        this.player = player;
        this.pokemon = pokemon;
        this.mythic = mythic;

    }

    public ServerPlayerEntity getPlayer() {

        return this.player;

    }

    public Pokemon getPokemon() {

        return this.pokemon;

    }

    public MythicPokemon getMythic() {

        return this.mythic;

    }

}
