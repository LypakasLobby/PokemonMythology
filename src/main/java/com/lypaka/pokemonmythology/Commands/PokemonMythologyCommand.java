package com.lypaka.pokemonmythology.Commands;

import com.lypaka.pokemonmythology.ConfigGetters;
import com.lypaka.pokemonmythology.PokemonMythology;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.pixelmonmod.pixelmon.api.command.PixelmonCommandUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ISuggestionProvider;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = PokemonMythology.MOD_ID)
public class PokemonMythologyCommand {

    public static final List<String> ALIASES = Arrays.asList("pokemonmythology", "pkmnmyth", "pm");
    public static SuggestionProvider<CommandSource> POKEMON = (context, builder) -> ISuggestionProvider.suggest(PixelmonCommandUtils.tabCompletePokemon(), builder);
    public static SuggestionProvider<CommandSource> MYTHICS;

    @SubscribeEvent
    public static void onCommandRegistration (RegisterCommandsEvent event) {

        new GiveCommand(event.getDispatcher());
        new InfoCommand(event.getDispatcher());
        new ReloadCommand(event.getDispatcher());
        new SpawnCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
        loadSuggestions();

    }

    public static void loadSuggestions() {

        List<String> mythicList = new ArrayList<>(ConfigGetters.displayTitles.keySet());
        System.out.println("default mythics size == " + ConfigGetters.displayTitles.keySet().size());
        System.out.println("custom mythics size == " + ConfigGetters.customMythics.keySet().size());
        int indexes = ConfigGetters.displayTitles.keySet().size() + ConfigGetters.customMythics.keySet().size();
        System.out.println("amount of indexes == " + indexes);
        String[] mythicOptions = new String[indexes];
        int startingIndex = 0;
        for (int i = 0; i < mythicList.size(); i++) {

            System.out.println("setting index " + i + " on array to " + mythicList.get(i));
            mythicOptions[i] = mythicList.get(i);
            startingIndex++;

        }
        List<String> customMythics = new ArrayList<>(ConfigGetters.customMythics.keySet());
        System.out.println("keySet == " + ConfigGetters.customMythics.keySet());
        System.out.println("startingIndex == " + startingIndex + " at the start of adding custom mythics");
        for (int i = 0; i < customMythics.size(); i++) {

            mythicOptions[startingIndex] = customMythics.get(i);
            System.out.println("setting index " + startingIndex + " on array to " + customMythics.get(i) + " at list index " + i);
            startingIndex++;

        }
        MYTHICS = (context, builder) -> ISuggestionProvider.suggest(mythicOptions, builder);
        System.out.println("options == " + Arrays.toString(mythicOptions));

    }

}
