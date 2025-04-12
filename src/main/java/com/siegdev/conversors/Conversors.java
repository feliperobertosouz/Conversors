package com.siegdev.conversors;

import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.plugin.java.JavaPlugin;

public final class Conversors extends JavaPlugin {

    public ItemBuilder itemBuilder;
    private ChatListener chatListener;
    private static Conversors instance;

    @Override
    public void onEnable() {
        if (!NBT.preloadApi()) {
            getLogger().warning("NBT-API não foi carregada corretamente! cancelando load do plugin");
            getPluginLoader().disablePlugin(this);
            return;
        }
        instance = this;
        StorageJson storageJson = new StorageJson(this);

        this.itemBuilder = new ItemBuilder();

        SavedItemsMap savedItemsMap = new SavedItemsMap(storageJson);
        savedItemsMap.reload();

        OpenedGuis openedGuis = new OpenedGuis();
        this.chatListener = new ChatListener(openedGuis);

        getCommand("conversor").setExecutor(new CommandExecute(storageJson,savedItemsMap,openedGuis,itemBuilder));

        getServer().getPluginManager().registerEvents(new MenuListener(openedGuis,chatListener,storageJson),this);
        getServer().getPluginManager().registerEvents(this.chatListener,this);
        getLogger().info("CONVERSORS CARREGADO COM SUCESSO v1.1");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ChatListener getChatListener()
    {
        return this.chatListener;
    }

    public static Conversors getInstance()
    {
        return instance;
    }


}
