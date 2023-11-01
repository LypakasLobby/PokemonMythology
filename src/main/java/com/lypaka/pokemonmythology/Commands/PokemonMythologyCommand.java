package com.lypaka.pokemonmythology.Commands;

import com.lypaka.pokemonmythology.PokemonMythology;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = PokemonMythology.MOD_ID)
public class PokemonMythologyCommand {

    public static final List<String> ALIASES = Arrays.asList("pokemonmythology", "pkmnmyth", "pm");

    @SubscribeEvent
    public static void onCommandRegistration (RegisterCommandsEvent event) {

        new InfoCommand(event.getDispatcher());
        new ReloadCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());

    }

}
