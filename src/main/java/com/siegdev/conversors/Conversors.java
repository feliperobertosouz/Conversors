package com.siegdev.conversors;

import com.google.gson.JsonObject;
import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class Conversors extends JavaPlugin {

    @Override
    public void onEnable() {
        if (!NBT.preloadApi()) {
            getLogger().warning("NBT-API não foi carregada corretamente! cancelando load do plugin");
            getPluginLoader().disablePlugin(this);
            return;
        }

        StorageJson storageJson = new StorageJson(this);


        SavedItemsMap savedItemsMap = new SavedItemsMap();
        savedItemsMap.addItemPairToMap(new ItemStack(Material.DIAMOND),new ItemStack(Material.STONE));

        OpenedGuis openedGuis = new OpenedGuis();

        getCommand("conversor").setExecutor(new CommandExecute(storageJson,savedItemsMap,openedGuis));

        getServer().getPluginManager().registerEvents(new MenuListener(openedGuis),this);
        getLogger().info("CONVERSORS CARREGADO COM SUCESSO");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
