package com.siegdev.conversors;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.InventoryView;

public class MenuListener implements Listener {
    private final OpenedGuis openedGuis;
    public MenuListener(OpenedGuis openedGuis)
    {
        this.openedGuis = openedGuis;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        InventoryView inv = event.getWhoClicked().getOpenInventory();

        Player player = (Player) event.getWhoClicked();
        if(openedGuis.contains(inv)){
            player.sendMessage("vc clicou no menu especial");
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
         Player player = event.getPlayer();
         InventoryView inv = player.getOpenInventory();
         openedGuis.remove(inv);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event)
    {
        Player player = (Player) event.getPlayer();
        InventoryView inv = player.getOpenInventory();
        openedGuis.remove(inv);
    }
}
