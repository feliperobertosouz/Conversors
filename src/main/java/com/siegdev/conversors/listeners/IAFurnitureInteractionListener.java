package com.siegdev.conversors.listeners;

import com.siegdev.conversors.handlers.OpenedGuis;
import com.siegdev.conversors.menus.ConvertMenu;
import com.siegdev.conversors.utils.ItemBuilder;
import dev.lone.itemsadder.api.Events.FurnitureInteractEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class IAFurnitureInteractionListener implements Listener {

    private final String furnitureId;
    private final OpenedGuis openedGuis;
    private final ItemBuilder itemBuilder;

    public IAFurnitureInteractionListener(String furnitureId, OpenedGuis openedGuis, ItemBuilder itemBuilder){
        this.furnitureId = furnitureId;
        this.openedGuis = openedGuis;
        this.itemBuilder = itemBuilder;
    }

    @EventHandler
    public void FurnitureInteractEvent(FurnitureInteractEvent event)
    {
        Player player = (Player) event.getPlayer();
        var furniture = event.getFurniture().getNamespacedID();

        if(!furniture.equals(this.furnitureId))
            return;

        player.sendMessage("você clicou em uma furniture valida como conversor");
        var menu = new ConvertMenu(itemBuilder);
        player.openInventory(menu.getMenu());
        openedGuis.add(player,menu);
    }
}
