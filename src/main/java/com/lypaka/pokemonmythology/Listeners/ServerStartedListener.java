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
        RibbonHandler.loadCustomMythicRibbonData();
        MythicHandler.loadMythics();
        MythicHandler.loadAllMythicsList();

        Pixelmon.EVENT_BUS.register(new BreedListener());
        Pixelmon.EVENT_BUS.register(new LevelUpListener());
        Pixelmon.EVENT_BUS.register(new SpawnListener());
        Pixelmon.EVENT_BUS.register(new PixelmonUpdateListener());
        Pixelmon.EVENT_BUS.register(new RecallListener());
        Pixelmon.EVENT_BUS.register(new CaptureListener());

        PokemonMythology.logger.warn("------------------WARNING------------------");
        PokemonMythology.logger.warn("------------------WARNING------------------");
        PokemonMythology.logger.warn("------------------WARNING------------------");
        PokemonMythology.logger.warn("This mod breaks viewing Ribbons in a Pokemon's summary menu.");
        PokemonMythology.logger.warn("Like, if a player clicks the little Ribbon button in their Pokemon's summary menu, they will crash their client.");
        PokemonMythology.logger.warn("And there's literally nothing I can do about it. If I fix that, it breaks my Ribbons showing the Mythic title.");

    }

}
