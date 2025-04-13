package com.siegdev.conversors.listeners.menus;

import com.siegdev.conversors.handlers.OpenedGuis;
import com.siegdev.conversors.handlers.SavedItemsMap;
import com.siegdev.conversors.menus.RecipeMenu;
import com.siegdev.conversors.menus.RecipeViewMenu;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class RecipesMenuListener implements Listener {
    private final OpenedGuis openedGuis;
    private final SavedItemsMap savedItemsMap;

    public RecipesMenuListener(OpenedGuis openedGuis, SavedItemsMap savedItemsMap)
    {
        this.openedGuis = openedGuis;
        this.savedItemsMap = savedItemsMap;
    }

    @EventHandler
    public void closeRecipeMenuEvent(InventoryCloseEvent event)
    {
        Player player = (Player) event.getPlayer();
        var selectedMenu = openedGuis.getMenuFromPlayer(player);
        if(!(selectedMenu instanceof RecipeMenu))
            return;
        openedGuis.remove(player);
    }

    @EventHandler
    public void RecipeMenuInteract(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        if(!openedGuis.contains(player)){
            return;
        }
        var selectedMenu = openedGuis.getMenuFromPlayer(player);

        if(!(selectedMenu instanceof RecipeMenu))
            return;
        event.setCancelled(true);
        var top = event.getView().getTopInventory();
        if(!(event.getClickedInventory() == top))
            return;

        int slot = event.getRawSlot();
        var clickedItem = top.getItem(slot);
        if(clickedItem == null){
            return;
        }

        String name = ChatColor.stripColor(clickedItem.getItemMeta().getItemName());
        var recipe = savedItemsMap.getRecipeByRecipeName(name);
        if(recipe == null){
            player.sendMessage("não foi possivel encontrar a receita");
            return;
        }
        var newMenu = new RecipeViewMenu(recipe.getRecipeId(),recipe);
        openedGuis.remove(player);
        newMenu.openMenu(player);
        openedGuis.add(player,newMenu);
        event.setCancelled(true);
    }
}
