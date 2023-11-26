package com.lypaka.pokemonmythology.Commands;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.MiscHandlers.PermissionHandler;
import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.lypaka.pokemonmythology.MythicPokemon.MythicPokemon;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonBuilder;
import com.pixelmonmod.pixelmon.api.storage.PlayerPartyStorage;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
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
                                                                                            .suggests(PokemonMythologyCommand.MYTHICS)
                                                                                            .then(
                                                                                                    Commands.argument("level", IntegerArgumentType.integer(1, 100))
                                                                                                            .executes(c -> {

                                                                                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                                                                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                                                                                    if (!PermissionHandler.hasPermission(player, "pokemonmythology.command.admin")) {

                                                                                                                        player.sendMessage(FancyText.getFormattedText("&cYou don't have permission to use this command!"), player.getUniqueID());
                                                                                                                        return 0;

                                                                                                                    }

                                                                                                                }

                                                                                                                ServerPlayerEntity target = EntityArgument.getPlayer(c, "player");
                                                                                                                Pokemon pokemon = PokemonBuilder.builder().species(StringArgumentType.getString(c, "pokemon")).build();
                                                                                                                pokemon.setLevel(IntegerArgumentType.getInteger(c, "level"));
                                                                                                                MythicPokemon mythic = MythicHandler.getFromName(StringArgumentType.getString(c, "mythic"));
                                                                                                                if (mythic == null) {

                                                                                                                    c.getSource().sendErrorMessage(FancyText.getFormattedText("&cInvalid Mythic!"));
                                                                                                                    return 0;

                                                                                                                }
                                                                                                                MythicHandler.setMythic(pokemon, mythic);
                                                                                                                PlayerPartyStorage storage = StorageProxy.getParty(target);
                                                                                                                storage.add(pokemon);
                                                                                                                c.getSource().sendFeedback(FancyText.getFormattedText("&aSuccessfully gave " + target.getName().getString() + " a " + mythic.getName() + " " + pokemon.getSpecies().getName()), true);
                                                                                                                target.sendMessage(FancyText.getFormattedText("&eYou've received a " + mythic.getName() + " " + pokemon.getSpecies().getName() + "!"), target.getUniqueID());
                                                                                                                return 1;

                                                                                                            })
                                                                                            )
                                                                                            .executes(c -> {

                                                                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                                                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                                                                    if (!PermissionHandler.hasPermission(player, "pokemonmythology.command.admin")) {

                                                                                                        player.sendMessage(FancyText.getFormattedText("&cYou don't have permission to use this command!"), player.getUniqueID());
                                                                                                        return 0;

                                                                                                    }

                                                                                                }

                                                                                                ServerPlayerEntity target = EntityArgument.getPlayer(c, "player");
                                                                                                Pokemon pokemon = PokemonBuilder.builder().species(StringArgumentType.getString(c, "pokemon")).build();
                                                                                                pokemon.setLevel(RandomHelper.getRandomNumberBetween(pokemon.getForm().minLevel, pokemon.getForm().maxLevel));
                                                                                                MythicPokemon mythic = MythicHandler.getFromName(StringArgumentType.getString(c, "mythic"));
                                                                                                if (mythic == null) {

                                                                                                    c.getSource().sendErrorMessage(FancyText.getFormattedText("&cInvalid Mythic!"));
                                                                                                    return 0;

                                                                                                }
                                                                                                MythicHandler.setMythic(pokemon, mythic);
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
