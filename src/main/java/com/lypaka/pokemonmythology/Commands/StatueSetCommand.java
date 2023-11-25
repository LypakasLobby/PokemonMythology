package com.lypaka.pokemonmythology.Commands;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.MiscHandlers.PermissionHandler;
import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.lypaka.pokemonmythology.Listeners.InteractListener;
import com.lypaka.pokemonmythology.MythicPokemon.MythicPokemon;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;

public class StatueSetCommand {

    public StatueSetCommand (CommandDispatcher<CommandSource> dispatcher) {

        for (String a : PokemonMythologyCommand.ALIASES) {

            dispatcher.register(
                    Commands.literal(a)
                            .then(
                                    Commands.literal("setstatue")
                                            .then(
                                                    Commands.argument("mythic", StringArgumentType.string())
                                                            .suggests(PokemonMythologyCommand.MYTHICS)
                                                            .executes(c -> {

                                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                                    if (!PermissionHandler.hasPermission(player, "pokemonmythology.command.admin")) {

                                                                        player.sendMessage(FancyText.getFormattedText("&cYou don't have permission to use this command!"), player.getUniqueID());
                                                                        return 0;

                                                                    }

                                                                    MythicPokemon mythic = MythicHandler.getFromName(StringArgumentType.getString(c, "mythic"));
                                                                    InteractListener.statueConversionMap.put(player.getUniqueID(), mythic.getName());
                                                                    player.sendMessage(FancyText.getFormattedText("&eRight click on a statue to convert it to " + mythic.getName() + "."), player.getUniqueID());
                                                                    return 1;

                                                                }

                                                                return 0;

                                                            })
                                            )
                            )
            );

        }

    }

}
