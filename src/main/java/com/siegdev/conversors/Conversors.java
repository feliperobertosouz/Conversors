package com.siegdev.conversors;

import de.tr7zw.nbtapi.NBT;
import org.bukkit.plugin.java.JavaPlugin;

public final class Conversors extends JavaPlugin {

    @Override
    public void onEnable() {
        if (!NBT.preloadApi()) {
            getLogger().warning("NBT-API wasn't initialized properly, disabling the plugin");
            getPluginLoader().disablePlugin(this);
            return;
        }
        getLogger().info("CONVERSORS CARREGADO COM SUCESSO");
        // Load other things
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
