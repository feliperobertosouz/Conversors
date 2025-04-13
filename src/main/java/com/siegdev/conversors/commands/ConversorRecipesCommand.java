package com.siegdev.conversors.commands;

import com.siegdev.conversors.configuration.LanguageManager;
import com.siegdev.conversors.handlers.OpenedGuis;
import com.siegdev.conversors.handlers.SavedItemsMap;
import com.siegdev.conversors.menus.RecipeMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConversorRecipesCommand implements CommandExecutor {
    private final LanguageManager languageManager;
    private final SavedItemsMap savedItemsMap;
    private final OpenedGuis openedGuis;

    public ConversorRecipesCommand(LanguageManager languageManager, SavedItemsMap savedItemsMap, OpenedGuis openedGuis){
        this.languageManager = languageManager;
        this.savedItemsMap = savedItemsMap;
        this.openedGuis = openedGuis;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(!(sender instanceof Player)){
            openedGuis.printOponenedMenus();
            sender.sendMessage(languageManager.getMessage("command.playeronly"));
            return true;
        }

        Player player = (Player) sender;

        var menu = new RecipeMenu(savedItemsMap);
        menu.openMenu(player);
        openedGuis.add(player,menu);
        return false;
    }
}
