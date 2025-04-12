package com.siegdev.conversors;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandExecute  implements CommandExecutor {

    final StorageJson storageJson;
    final SavedItemsMap savedItemsMap;
    final OpenedGuis openedGuis;
    final ItemBuilder itemBuilder;

    public CommandExecute(StorageJson storage, SavedItemsMap saveditems, OpenedGuis openedGuis, ItemBuilder itemBuilder){
        this.storageJson = storage;
        this.savedItemsMap = saveditems;
        this.openedGuis = openedGuis;
        this.itemBuilder = itemBuilder;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {


        if(!(sender instanceof Player)){
            openedGuis.printOponenedMenus();
            sender.sendMessage("Apenas jogadores podem usar este comando!!");
            return true;
        }

        Player player = (Player) sender;
        player.sendMessage("Você usou o comando /conversor");

        if(strings[0].equals("reloadrecipes")) {
            savedItemsMap.reload();
            return true;
        }

        if(strings[0].equals("open")){
            if(openedGuis.contains(player)) {
                var inv = openedGuis.getMenuFromPlayer(player);
                inv.openMenu(player);
                return true;
            }

            MenuBuilder menu = new MenuBuilder(openedGuis,itemBuilder);
            menu.openMenu(player);
            return true;
        }

        if(strings[0].equals("convert")){
            ItemStack item = null;
            item = player.getInventory().getItemInMainHand();

            if(item.getType() == Material.AIR){
                player.sendMessage("Por favor use um item na mão");
                return false;
            }

            ItemStack result = savedItemsMap.getResult(item);
            player.getInventory().addItem(result);

            return true;
        }

        return false;
    }
}
