package com.siegdev.conversors.listeners.menus;

import com.siegdev.conversors.handlers.OpenedGuis;
import com.siegdev.conversors.menus.RecipeViewMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class RecipeMenuViewListener implements Listener {

    private final OpenedGuis openedGuis;

    public RecipeMenuViewListener(OpenedGuis openedGuis){
        this.openedGuis = openedGuis;
    }

    @EventHandler
    public void onRecipeViewClick(InventoryClickEvent event){

        Player player = (Player) event.getWhoClicked();

        if(!openedGuis.contains(player)){
            return;
        }
        var selectedMenu = openedGuis.getMenuFromPlayer(player);

        if(!(selectedMenu instanceof RecipeViewMenu))
            return;

        event.setCancelled(true);
    }
}
