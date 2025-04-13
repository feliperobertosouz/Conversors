package com.siegdev.conversors.configuration;

import com.siegdev.conversors.LanguageManager;
import com.siegdev.conversors.commands.ConversorCommand;
import com.siegdev.conversors.commands.ConversorRecipesCommand;
import com.siegdev.conversors.commands.ConversorReloadCommand;
import com.siegdev.conversors.commands.GetConversorCommand;
import com.siegdev.conversors.handlers.OpenedGuis;
import com.siegdev.conversors.handlers.SavedItemsMap;
import com.siegdev.conversors.listeners.*;
import com.siegdev.conversors.listeners.menus.ConvertMenuListener;
import com.siegdev.conversors.listeners.menus.CreationMenuListener;
import com.siegdev.conversors.listeners.menus.RecipeMenuViewListener;
import com.siegdev.conversors.listeners.menus.RecipesMenuListener;
import com.siegdev.conversors.utils.ItemBuilder;
import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public final class Conversors extends JavaPlugin {

    private static Conversors instance;
    private ItemBuilder itemBuilder;
    private LanguageManager languageManager;
    private StorageJson storageJson;
    private SavedItemsMap savedItemsMap;
    private OpenedGuis openedGuis;

    public static Conversors getInstance()
    {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("Loading Conversors plugin");
        saveDefaultConfig();
        reloadConfig();

        loadLanguage();
        checkDependencies();
        initItemBuilder();
        registerCore();
        registerListeners();
        registerCommands();



        getLogger().info("CONVERSORS v1-SNAPSHOT");
        getLogger().info(languageManager.getMessage("plugin.load"));

        boolean itemsAdderSup = this.getConfig().getBoolean("items-adder");
        getLogger().info(languageManager.getMessage("plugin.itemsadder") + itemsAdderSup);
        if(itemsAdderSup) registerItemsAdderSupport();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void checkDependencies()
    {
        getLogger().info("Loading dependencys");
        if (!NBT.preloadApi()) {
            getLogger().warning("Loading nbt-api dependency");
            getPluginLoader().disablePlugin(this);
        }
    }

    private void initItemBuilder() {
        String matName = getConfig().getString("defaultconversor.material", "BREWING_STAND");
        Material material = Material.matchMaterial(matName.toUpperCase());
        String name = getConfig().getString("defaultconversor.name", "conversor");
        this.itemBuilder = new ItemBuilder(material, name);
    }

    private void registerCore(){
        this.storageJson = new StorageJson(this);
        this.savedItemsMap = new SavedItemsMap(storageJson);
        savedItemsMap.reload();

        this.openedGuis = new OpenedGuis(this.itemBuilder);
    }

    private void loadLanguage(){
        getLogger().info("Loading Language");
        String lang = this.getConfig().getString("language");
        this.languageManager = new LanguageManager(this,lang);
    }

    private void registerListeners(){
        getLogger().info("Loading eventListeners");
        var chatListener = new ChatListener(this.openedGuis, languageManager);
        getServer().getPluginManager().registerEvents(chatListener,this);
        getServer().getPluginManager().registerEvents(new CreationMenuListener(openedGuis,chatListener,storageJson,languageManager),this);
        getServer().getPluginManager().registerEvents(chatListener,this);
        getServer().getPluginManager().registerEvents(new BlockListener(itemBuilder,languageManager), this);
        getServer().getPluginManager().registerEvents(new BlockInteractionListener(itemBuilder, openedGuis),this);
        getServer().getPluginManager().registerEvents(new ConvertMenuListener(openedGuis,savedItemsMap), this);
        getServer().getPluginManager().registerEvents(new RecipesMenuListener(openedGuis,savedItemsMap),this);
        getServer().getPluginManager().registerEvents(new RecipeMenuViewListener(openedGuis),this);
    }

    private void registerCommands(){
        getLogger().info("Loading commands");
        getCommand("conversor-creation").setExecutor(new ConversorCommand(storageJson,savedItemsMap,openedGuis,itemBuilder,languageManager));
        getCommand("conversor-get").setExecutor(new GetConversorCommand(itemBuilder, languageManager));
        getCommand("conversor-reloadrecipes").setExecutor(new ConversorReloadCommand(languageManager,savedItemsMap));
        getCommand("conversor-recipes").setExecutor(new ConversorRecipesCommand(languageManager,savedItemsMap,openedGuis));
    }

    private void registerItemsAdderSupport()
    {
        getLogger().info("Loading itemsAdder support");
    }



}
