package com.siegdev.conversors.listeners;

import com.siegdev.conversors.configuration.LanguageManager;
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
    private final LanguageManager languageManager;

    public BlockInteractionListener(ItemBuilder itemBuilder, OpenedGuis openedGuis, LanguageManager languageManager){
        this.itemBuilder = itemBuilder;
        this.openedGuis = openedGuis;
        this.languageManager = languageManager;
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
        var menu = new ConvertMenu(itemBuilder, languageManager.getMessage("menu.convertor"), languageManager.getMessage("menu.confirm"));
        player.openInventory(menu.getMenu());
        openedGuis.add(player,menu);
    }
}
