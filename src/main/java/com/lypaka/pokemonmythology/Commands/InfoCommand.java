package com.lypaka.pokemonmythology.Commands;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.pokemonmythology.ConfigGetters;
import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.entity.player.ServerPlayerEntity;

public class InfoCommand {

    public InfoCommand (CommandDispatcher<CommandSource> dispatcher) {

        for (String a : PokemonMythologyCommand.ALIASES) {

            dispatcher.register(
                    Commands.literal(a)
                            .then(
                                    Commands.literal("info")
                                            .then(
                                                    Commands.argument("mythic", StringArgumentType.string())
                                                            .suggests(
                                                                    (context, builder) -> ISuggestionProvider.suggest(MythicHandler.allMythics, builder)
                                                            )
                                                            .executes(c -> {

                                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                                    String mythic = StringArgumentType.getString(c, "mythic");
                                                                    String message;
                                                                    if (ConfigGetters.customMythics.containsKey(mythic)) {

                                                                        message = ConfigGetters.customMythics.get(mythic).get("Information-Text");

                                                                    } else {

                                                                        message = ConfigGetters.infoTexts.get(mythic);

                                                                    }

                                                                    player.sendMessage(FancyText.getFormattedText(message), player.getUniqueID());

                                                                }

                                                                return 1;

                                                            })
                                            )
                            )
            );

        }

    }

}
