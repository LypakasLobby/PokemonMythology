package com.lypaka.pokemonmythology.API;

import com.lypaka.pokemonmythology.MythicPokemon.MythicPokemon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class MythicBreedEvent extends Event {

    private final ServerPlayerEntity player;
    private final Pokemon parent1;
    private final Pokemon parent2;
    private final Pokemon child;
    private MythicPokemon mythic;

    public MythicBreedEvent (ServerPlayerEntity player, Pokemon parent1, Pokemon parent2, Pokemon child, MythicPokemon mythic) {

        this.player = player;
        this.parent1 = parent1;
        this.parent2 = parent2;
        this.child = child;
        this.mythic = mythic;

    }

    public ServerPlayerEntity getPlayer() {

        return this.player;

    }

    public Pokemon getParent1() {

        return this.parent1;

    }

    public Pokemon getParent2() {

        return this.parent2;

    }

    public Pokemon getChild() {

        return this.child;

    }
    public MythicPokemon getMythic() {

        return this.mythic;

    }

    public void setMythic (MythicPokemon mythic) {

        this.mythic = mythic;

    }

}
