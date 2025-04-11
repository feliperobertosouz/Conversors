package com.siegdev.conversors;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MenuBuilder {

    private final Inventory menu;
    private OpenedGuis openedGuis;
    public MenuBuilder(OpenedGuis openedGuis){
        this.menu = Bukkit.createInventory(null,9,"criar conversor");
        this.openedGuis = openedGuis;
    }

    public void openMenu(Player player){
        player.openInventory(menu);
        openedGuis.add(player.openInventory(menu),this);

    }

}
