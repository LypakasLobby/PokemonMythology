package com.lypaka.pokemonmythology.Listeners;

import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.lypaka.pokemonmythology.Handlers.RibbonHandler;
import com.lypaka.pokemonmythology.PokemonMythology;
import com.pixelmonmod.pixelmon.Pixelmon;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

@Mod.EventBusSubscriber(modid = PokemonMythology.MOD_ID)
public class ServerStartedListener {

    @SubscribeEvent
    public static void onServerStarted (FMLServerStartedEvent event) {

        RibbonHandler.loadRibbons();
        MythicHandler.loadMythics();

        Pixelmon.EVENT_BUS.register(new BreedListener());
        Pixelmon.EVENT_BUS.register(new LevelUpListener());
        Pixelmon.EVENT_BUS.register(new SpawnListener());
        Pixelmon.EVENT_BUS.register(new PixelmonUpdateListener());

    }

}
