package com.lypaka.pokemonmythology.Commands;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.MiscHandlers.PermissionHandler;
import com.lypaka.pokemonmythology.ConfigGetters;
import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.lypaka.pokemonmythology.MythicPokemon.MythicPokemon;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.storage.PlayerPartyStorage;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;

public class GiveCommand {

    public GiveCommand (CommandDispatcher<CommandSource> dispatcher) {

        for (String a : PokemonMythologyCommand.ALIASES) {

            dispatcher.register(
                    Commands.literal(a)
                            .then(
                                    Commands.literal("give")
                                            .then(
                                                    Commands.argument("player", EntityArgument.player())
                                                            .then(
                                                                    Commands.argument("pokemon", StringArgumentType.string())
                                                                            .suggests(PokemonMythologyCommand.POKEMON)
                                                                            .then(
                                                                                    Commands.argument("mythic", StringArgumentType.string())
                                                                                            .suggests(
                                                                                                    (context, builder) -> ISuggestionProvider.suggest(MythicHandler.allMythics, builder)
                                                                                            )
                                                                                            .then(
                                                                                                    Commands.argument("level", IntegerArgumentType.integer(1, 100))
                                                                                                            .then(
                                                                                                                    Commands.argument("palette", StringArgumentType.word())
                                                                                                                            .executes(c -> {

                                                                                                                                if (!ConfigGetters.disclaimer) {

                                                                                                                                    c.getSource().sendErrorMessage(FancyText.getFormattedText("&cDisclaimer is not agreed to!"));
                                                                                                                                    c.getSource().sendErrorMessage(FancyText.getFormattedText("&cGo in \"/config/pokemonmythology/pokemonmythology.conf\" and set the disclaimer node to true to be able to use this command!"));
                                                                                                                                    c.getSource().sendErrorMessage(FancyText.getFormattedText("&cAfter changing that configuration node, run \"/pkmnmyth reload\" to apply the changes and enable the mod."));
                                                                                                                                    return 1;

                                                                                                                                }
                                                                                                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                                                                                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                                                                                                    if (!PermissionHandler.hasPermission(player, "pokemonmythology.command.admin")) {

                                                                                                                                        player.sendMessage(FancyText.getFormattedText("&cYou don't have permission to use this command!"), player.getUniqueID());
                                                                                                                                        return 0;

                                                                                                                                    }

                                                                                                                                }

                                                                                                                                ServerPlayerEntity target = EntityArgument.getPlayer(c, "player");
                                                                                                                                MythicPokemon mythic = MythicHandler.getFromName(StringArgumentType.getString(c, "mythic"));
                                                                                                                                if (mythic == null) {

                                                                                                                                    c.getSource().sendErrorMessage(FancyText.getFormattedText("&cInvalid Mythic!"));
                                                                                                                                    return 0;

                                                                                                                                }
                                                                                                                                String palette = StringArgumentType.getString(c, "palette");
                                                                                                                                Pokemon pokemon = MythicHandler.buildMythicPokemon(StringArgumentType.getString(c, "pokemon"), mythic.getName(), IntegerArgumentType.getInteger(c, "level"), palette);
                                                                                                                                PlayerPartyStorage storage = StorageProxy.getParty(target);
                                                                                                                                storage.add(pokemon);
                                                                                                                                c.getSource().sendFeedback(FancyText.getFormattedText("&aSuccessfully gave " + target.getName().getString() + " a " + mythic.getName() + " " + pokemon.getSpecies().getName()), true);
                                                                                                                                target.sendMessage(FancyText.getFormattedText("&eYou've received a " + mythic.getName() + " " + pokemon.getSpecies().getName() + "!"), target.getUniqueID());
                                                                                                                                return 1;

                                                                                                                            })
                                                                                                            )
                                                                                                            .executes(c -> {

                                                                                                                if (!ConfigGetters.disclaimer) {

                                                                                                                    c.getSource().sendErrorMessage(FancyText.getFormattedText("&cDisclaimer is not agreed to!"));
                                                                                                                    c.getSource().sendErrorMessage(FancyText.getFormattedText("&cGo in \"/config/pokemonmythology/pokemonmythology.conf\" and set the disclaimer node to true to be able to use this command!"));
                                                                                                                    c.getSource().sendErrorMessage(FancyText.getFormattedText("&cAfter changing that configuration node, run \"/pkmnmyth reload\" to apply the changes and enable the mod."));
                                                                                                                    return 1;

                                                                                                                }
                                                                                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                                                                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                                                                                    if (!PermissionHandler.hasPermission(player, "pokemonmythology.command.admin")) {

                                                                                                                        player.sendMessage(FancyText.getFormattedText("&cYou don't have permission to use this command!"), player.getUniqueID());
                                                                                                                        return 0;

                                                                                                                    }

                                                                                                                }

                                                                                                                ServerPlayerEntity target = EntityArgument.getPlayer(c, "player");
                                                                                                                MythicPokemon mythic = MythicHandler.getFromName(StringArgumentType.getString(c, "mythic"));
                                                                                                                if (mythic == null) {

                                                                                                                    c.getSource().sendErrorMessage(FancyText.getFormattedText("&cInvalid Mythic!"));
                                                                                                                    return 0;

                                                                                                                }
                                                                                                                Pokemon pokemon = MythicHandler.buildMythicPokemon(StringArgumentType.getString(c, "pokemon"), mythic.getName(), IntegerArgumentType.getInteger(c, "level"), "");
                                                                                                                PlayerPartyStorage storage = StorageProxy.getParty(target);
                                                                                                                storage.add(pokemon);
                                                                                                                c.getSource().sendFeedback(FancyText.getFormattedText("&aSuccessfully gave " + target.getName().getString() + " a " + mythic.getName() + " " + pokemon.getSpecies().getName()), true);
                                                                                                                target.sendMessage(FancyText.getFormattedText("&eYou've received a " + mythic.getName() + " " + pokemon.getSpecies().getName() + "!"), target.getUniqueID());
                                                                                                                return 1;

                                                                                                            })
                                                                                            )
                                                                                            .executes(c -> {

                                                                                                if (!ConfigGetters.disclaimer) {

                                                                                                    c.getSource().sendErrorMessage(FancyText.getFormattedText("&cDisclaimer is not agreed to!"));
                                                                                                    c.getSource().sendErrorMessage(FancyText.getFormattedText("&cGo in \"/config/pokemonmythology/pokemonmythology.conf\" and set the disclaimer node to true to be able to use this command!"));
                                                                                                    c.getSource().sendErrorMessage(FancyText.getFormattedText("&cAfter changing that configuration node, run \"/pkmnmyth reload\" to apply the changes and enable the mod."));
                                                                                                    return 1;

                                                                                                }
                                                                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                                                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                                                                    if (!PermissionHandler.hasPermission(player, "pokemonmythology.command.admin")) {

                                                                                                        player.sendMessage(FancyText.getFormattedText("&cYou don't have permission to use this command!"), player.getUniqueID());
                                                                                                        return 0;

                                                                                                    }

                                                                                                }

                                                                                                ServerPlayerEntity target = EntityArgument.getPlayer(c, "player");
                                                                                                MythicPokemon mythic = MythicHandler.getFromName(StringArgumentType.getString(c, "mythic"));
                                                                                                if (mythic == null) {

                                                                                                    c.getSource().sendErrorMessage(FancyText.getFormattedText("&cInvalid Mythic!"));
                                                                                                    return 0;

                                                                                                }
                                                                                                Pokemon pokemon = MythicHandler.buildMythicPokemon(StringArgumentType.getString(c, "pokemon"), mythic.getName(), 0, "");
                                                                                                PlayerPartyStorage storage = StorageProxy.getParty(target);
                                                                                                storage.add(pokemon);
                                                                                                c.getSource().sendFeedback(FancyText.getFormattedText("&aSuccessfully gave " + target.getName().getString() + " a " + mythic.getName() + " " + pokemon.getSpecies().getName()), true);
                                                                                                target.sendMessage(FancyText.getFormattedText("&eYou've received a " + mythic.getName() + " " + pokemon.getSpecies().getName() + "!"), target.getUniqueID());
                                                                                                return 1;

                                                                                            })
                                                                            )
                                                            )
                                            )
                            )
            );

        }

    }

}
