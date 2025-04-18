package com.siegdev.conversors.commands;

import com.siegdev.conversors.configuration.LanguageManager;
import com.siegdev.conversors.handlers.SavedItemsMap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ConversorReloadRecipesCommand implements CommandExecutor {

    private final LanguageManager languageManager;
    private final SavedItemsMap savedItemsMap;

    public ConversorReloadRecipesCommand(LanguageManager languageManager, SavedItemsMap savedItemsMap){
        this.savedItemsMap = savedItemsMap;
        this.languageManager = languageManager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

            savedItemsMap.reload();
            sender.sendMessage(languageManager.getMessage("command.reloadrecipes"));
            return true;
    }


}
