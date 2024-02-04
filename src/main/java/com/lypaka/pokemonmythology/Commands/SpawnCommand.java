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
import com.pixelmonmod.pixelmon.api.pokemon.PokemonBuilder;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;

public class SpawnCommand {

    public SpawnCommand (CommandDispatcher<CommandSource> dispatcher) {

        for (String a : PokemonMythologyCommand.ALIASES) {

            dispatcher.register(
                    Commands.literal(a)
                            .then(
                                    Commands.literal("spawn")
                                            .then(
                                                    Commands.argument("mythic", StringArgumentType.string())
                                                            .suggests(
                                                                    (context, builder) -> ISuggestionProvider.suggest(MythicHandler.allMythics, builder)
                                                            )
                                                            .then(
                                                                    Commands.argument("pokemon", StringArgumentType.string())
                                                                            .suggests(PokemonMythologyCommand.POKEMON)
                                                                            .then(
                                                                                    Commands.argument("player", EntityArgument.player())
                                                                                            .then(
                                                                                                    Commands.argument("level", IntegerArgumentType.integer(1, 100))
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
                                                                                                                Pokemon pokemon = PokemonBuilder.builder().species(StringArgumentType.getString(c, "pokemon")).build();
                                                                                                                int level = IntegerArgumentType.getInteger(c, "level");
                                                                                                                pokemon.setLevel(level);
                                                                                                                MythicPokemon mythic = MythicHandler.getFromName(StringArgumentType.getString(c, "mythic"));
                                                                                                                if (mythic == null) {

                                                                                                                    c.getSource().sendErrorMessage(FancyText.getFormattedText("&cInvalid Mythic name!"));
                                                                                                                    return 0;

                                                                                                                }
                                                                                                                MythicHandler.setMythic(pokemon, mythic, true);
                                                                                                                double x = target.getPosX() + 0.5;
                                                                                                                double y = target.getPosY();
                                                                                                                double z = target.getPosZ() + 0.5;
                                                                                                                PixelmonEntity pixelmon = pokemon.getOrSpawnPixelmon(target.world, x, y, z);
                                                                                                                pixelmon.setSpawnLocation(pixelmon.getDefaultSpawnLocation());
                                                                                                                target.world.addEntity(pixelmon);
                                                                                                                c.getSource().sendFeedback(FancyText.getFormattedText("&aSuccessfully spawned a " + mythic.getName() + " " + pokemon.getSpecies().getName() + " on " + target.getName().getString()), true);
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
                                                                                                Pokemon pokemon = PokemonBuilder.builder().species(StringArgumentType.getString(c, "pokemon")).build();
                                                                                                int level = RandomHelper.getRandomNumberBetween(pokemon.getForm().minLevel, pokemon.getForm().maxLevel);
                                                                                                pokemon.setLevel(level);
                                                                                                MythicPokemon mythic = MythicHandler.getFromName(StringArgumentType.getString(c, "mythic"));
                                                                                                if (mythic == null) {

                                                                                                    c.getSource().sendErrorMessage(FancyText.getFormattedText("&cInvalid Mythic name!"));
                                                                                                    return 0;

                                                                                                }
                                                                                                MythicHandler.setMythic(pokemon, mythic, true);
                                                                                                double x = target.getPosX() + 0.5;
                                                                                                double y = target.getPosY();
                                                                                                double z = target.getPosZ() + 0.5;
                                                                                                PixelmonEntity pixelmon = pokemon.getOrSpawnPixelmon(target.world, x, y, z);
                                                                                                pixelmon.setSpawnLocation(pixelmon.getDefaultSpawnLocation());
                                                                                                target.world.addEntity(pixelmon);
                                                                                                c.getSource().sendFeedback(FancyText.getFormattedText("&aSuccessfully spawned a " + mythic.getName() + " " + pokemon.getSpecies().getName() + " on " + target.getName().getString()), true);
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
