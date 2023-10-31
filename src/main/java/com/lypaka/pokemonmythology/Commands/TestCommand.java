package com.lypaka.pokemonmythology.Commands;

import com.lypaka.lypakautils.FancyText;
import com.mojang.brigadier.CommandDispatcher;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonBuilder;
import com.pixelmonmod.pixelmon.api.pokemon.boss.BossTiers;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.MutableRibbonData;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.Ribbon;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.RibbonRegistry;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.awt.*;
import java.time.LocalDateTime;

public class TestCommand {

    public TestCommand (CommandDispatcher<CommandSource> dispatcher) {

        for (String a : PokemonMythologyCommand.ALIASES) {

            dispatcher.register(
                    Commands.literal(a)
                            .then(
                                    Commands.literal("test")
                                            .executes(c -> {

                                                ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                Pokemon pokemon = PokemonBuilder.builder().species("Bulbasaur").build();
                                                PixelmonEntity entity = pokemon.getOrCreatePixelmon();
                                                MutableRibbonData data = new MutableRibbonData();
                                                data.setPrefix(FancyText.getFormattedText("Alpha "));
                                                Ribbon ribbon = new Ribbon(RibbonRegistry.SPECIAL.getKey(), LocalDateTime.now().getDayOfYear(), FancyText.getFormattedText(""), data);
                                                pokemon.addRibbon(ribbon);
                                                pokemon.setDisplayedRibbon(ribbon);
                                                entity.setPixelmonScale(1.5f);
                                                entity.setColor(new Color(214, 41, 32));
                                                entity.setPosition(player.getPosX(), player.getPosY(), player.getPosZ());
                                                player.world.addEntity(entity);
                                                return 1;

                                            })
                            )
            );

        }

    }

}
