package com.siegdev.conversors.commands;

import com.siegdev.conversors.configuration.LanguageManager;
import com.siegdev.conversors.configuration.StorageJson;
import com.siegdev.conversors.handlers.OpenedGuis;
import com.siegdev.conversors.handlers.SavedItemsMap;
import com.siegdev.conversors.menus.CreationMenu;
import com.siegdev.conversors.utils.ItemBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConversorCommand implements CommandExecutor {

    final StorageJson storageJson;
    final SavedItemsMap savedItemsMap;
    final OpenedGuis openedGuis;
    final ItemBuilder itemBuilder;
    final LanguageManager languageManager;

    public ConversorCommand(StorageJson storage, SavedItemsMap saveditems, OpenedGuis openedGuis, ItemBuilder itemBuilder, LanguageManager languageManager){
        this.storageJson = storage;
        this.savedItemsMap = saveditems;
        this.openedGuis = openedGuis;
        this.itemBuilder = itemBuilder;
        this.languageManager = languageManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {


        if(!(sender instanceof Player)){
            openedGuis.printOponenedMenus();
            sender.sendMessage(languageManager.getMessage("command.playeronly"));
            return true;
        }

        Player player = (Player) sender;

        var createdMenu = openedGuis.getMenuFromPlayer(player);

        if (!(createdMenu instanceof CreationMenu)) {
            var newMenu = new CreationMenu(openedGuis, itemBuilder,languageManager.getMessage("menu.creation"),languageManager.getMessage("menu.confirm"),languageManager.getMessage("menu.clear"));
            newMenu.openMenu(player);
            return true;
        }
        createdMenu.openMenu(player);

        return true;
    }
}
