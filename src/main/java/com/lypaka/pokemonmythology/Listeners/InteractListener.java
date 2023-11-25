package com.lypaka.pokemonmythology.Listeners;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.lypaka.pokemonmythology.MythicPokemon.MythicPokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.StatueEntity;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InteractListener {

    public static Map<UUID, String> statueConversionMap = new HashMap<>();

    @SubscribeEvent
    public void onStatueClick (PlayerInteractEvent.EntityInteractSpecific event) {

        UUID uuid = event.getPlayer().getUniqueID();
        if (!statueConversionMap.containsKey(uuid)) return;

        if (event.getTarget() instanceof StatueEntity) {

            statueConversionMap.entrySet().removeIf(entry -> {

                if (entry.getKey().toString().equalsIgnoreCase(uuid.toString())) {

                    StatueEntity statue = (StatueEntity) event.getTarget();
                    String value = statueConversionMap.get(uuid);
                    MythicPokemon mythic = MythicHandler.getFromName(value);
                    if (mythic != null) {

                        statue.setColor(mythic.getColor());
                        statue.setPixelmonScale(mythic.getScale());

                    } else {

                        event.getPlayer().sendMessage(FancyText.getFormattedText("&cError in converting statue to Mythic! Invalid Mythic!"), uuid);

                    }

                    return true;

                }

                return false;

            });

        }

    }

}
