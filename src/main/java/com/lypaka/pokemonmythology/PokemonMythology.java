package com.lypaka.pokemonmythology;

import com.lypaka.lypakautils.ConfigurationLoaders.BasicConfigManager;
import com.lypaka.lypakautils.ConfigurationLoaders.ConfigUtils;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("pokemonmythology")
public class PokemonMythology {

    public static final String MOD_ID = "pokemonmythology";
    public static final String MOD_NAME = "PokemonMythology";
    public static final Logger logger = LogManager.getLogger("PokemonMythology");
    public static BasicConfigManager configManager;

    /**
     * Alpha, Beta, Gamma, Omega, Delta, Sigma
     */

    public PokemonMythology() throws IOException {

        Path dir = ConfigUtils.checkDir(Paths.get("./config/pokemonmythology"));
        String[] files = new String[]{"pokemonMythology.conf"};
        configManager = new BasicConfigManager(files, dir, PokemonMythology.class, MOD_NAME, MOD_ID, logger);
        configManager.init();

    }

}
