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

        Pixelmon.EVENT_BUS.register(new BreedListener());
        Pixelmon.EVENT_BUS.register(new LevelUpListener());
        Pixelmon.EVENT_BUS.register(new SpawnListener());
        Pixelmon.EVENT_BUS.register(new PixelmonUpdateListener());

        MythicHandler.loadAllMythicsList();

        PokemonMythology.logger.warn("------------------WARNING------------------");
        PokemonMythology.logger.warn("------------------WARNING------------------");
        PokemonMythology.logger.warn("------------------WARNING------------------");
        PokemonMythology.logger.warn("This mod breaks viewing Ribbons in a Pokemon's summary menu.");
        PokemonMythology.logger.warn("Like, if a player clicks the little Ribbon button in their Pokemon's summary menu, they will crash their client.");
        PokemonMythology.logger.warn("And there's literally nothing I can do about it. If I fix that, it breaks my Ribbons showing the Mythic title.");
        PokemonMythology.logger.warn("And when I spoke to Pixelmon about it, I was told \"Its working as intended, that's a server-owner issue.\" because they are too lazy to fix Ribbons properly.");
        PokemonMythology.logger.warn("And when I inquired for assistance in the Pixelmon Discord about said issue, I was ignored.");
        PokemonMythology.logger.warn("So, I do apologize for the inconvenience, but my Mythics are better than looking at useless Ribbons anyway.");
        PokemonMythology.logger.warn("Blame Pixelmon for wanting to release half-assed \"stable\" features they just flat out refuse to listen to being told are broken.");

    }

}
