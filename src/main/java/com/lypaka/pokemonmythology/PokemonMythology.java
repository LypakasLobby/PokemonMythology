package com.lypaka.pokemonmythology;

import com.lypaka.lypakautils.ConfigurationLoaders.BasicConfigManager;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("pokemonmythology")
public class PokemonMythology {

    public static final String MOD_ID = "pokemonmythology";
    public static final String MOD_NAME = "PokemonMythology";
    public static final Logger logger = LogManager.getLogger("PokemonMythology");
    public static BasicConfigManager configManager;

    public PokemonMythology() {



    }

}
