package com.lypaka.pokemonmythology.Commands;

import com.lypaka.pokemonmythology.PokemonMythology;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.pixelmonmod.pixelmon.api.command.PixelmonCommandUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ISuggestionProvider;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = PokemonMythology.MOD_ID)
public class PokemonMythologyCommand {

    public static final List<String> ALIASES = Arrays.asList("pokemonmythology", "pkmnmyth", "pm");
    public static SuggestionProvider<CommandSource> POKEMON = (context, builder) -> ISuggestionProvider.suggest(PixelmonCommandUtils.tabCompletePokemon(), builder);

    @SubscribeEvent
    public static void onCommandRegistration (RegisterCommandsEvent event) {

        new GiveCommand(event.getDispatcher());
        new InfoCommand(event.getDispatcher());
        new ReloadCommand(event.getDispatcher());
        new SpawnCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());

    }

}
