package com.siegdev.conversors.listeners;

import com.siegdev.conversors.utils.ItemBuilder;
import com.siegdev.conversors.handlers.OpenedGuis;
import com.siegdev.conversors.menus.ConvertMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockInteractionListener implements Listener {
    private final ItemBuilder itemBuilder;
    private final OpenedGuis openedGuis;

    public BlockInteractionListener(ItemBuilder itemBuilder, OpenedGuis openedGuis){
        this.itemBuilder = itemBuilder;
        this.openedGuis = openedGuis;
    }

    @EventHandler
    public void BlockInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();

        var block = event.getClickedBlock();
        if(block == null)
            return;

        if(event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        if(!(block.getType() == itemBuilder.getDefaultConversorType()))
            return;

        event.setCancelled(true);
        var menu = new ConvertMenu(itemBuilder);
        player.openInventory(menu.getMenu());
        openedGuis.add(player,menu);
    }
}
