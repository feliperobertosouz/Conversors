package com.siegdev.conversors.configuration;

import com.siegdev.conversors.Conversors;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LanguageManager {
    private final Conversors conversors;
    private String selectedLanguage = "en";
    private FileConfiguration languageConfig;

    public LanguageManager(Conversors conversors,String language){
        this.conversors = conversors;
        this.selectedLanguage = language;
        loadLanguage();
    }

    public void loadLanguage() {
        File langFolder = new File(conversors.getDataFolder(), "lang");
        if (!langFolder.exists()) langFolder.mkdirs();

        File langFile = new File(langFolder, selectedLanguage + ".yml");

        if (!langFile.exists()) {
            conversors.saveResource("lang/" + selectedLanguage + ".yml", false);
        }

        this.languageConfig = YamlConfiguration.loadConfiguration(langFile);
        conversors.getLogger().info("Language loaded: " + selectedLanguage);
    }

    public String getMessage(String key)
    {
        if(languageConfig == null)
            return "notFound";
        return languageConfig.getString(key,"notFound");
    }
}
