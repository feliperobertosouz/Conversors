package com.siegdev.conversors.listeners.menus;

import com.siegdev.conversors.configuration.LanguageManager;
import com.siegdev.conversors.configuration.StorageJson;
import com.siegdev.conversors.handlers.OpenedGuis;
import com.siegdev.conversors.handlers.SavedItemsMap;
import com.siegdev.conversors.menus.CreationMenu;
import com.siegdev.conversors.menus.RecipeMenu;
import com.siegdev.conversors.menus.RecipeViewMenu;
import com.siegdev.conversors.utils.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class RecipeMenuViewListener implements Listener {

    private final OpenedGuis openedGuis;
    private final StorageJson storageJson;
    private final SavedItemsMap savedItemsMap;
    private final LanguageManager languageManager;
    private final ItemBuilder itemBuilder;

    public RecipeMenuViewListener(OpenedGuis openedGuis, StorageJson storageJson, SavedItemsMap savedItemsMap, LanguageManager languageManager, ItemBuilder itemBuilder){
        this.openedGuis = openedGuis;
        this.storageJson = storageJson;
        this.savedItemsMap = savedItemsMap;
        this.languageManager = languageManager;
        this.itemBuilder = itemBuilder;
    }

    @EventHandler
    public void onRecipeviewClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        var selectedMenu = openedGuis.getMenuFromPlayer(player);
        if(!(selectedMenu instanceof RecipeViewMenu))
            return;
        openedGuis.remove(player);

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

        int slot = event.getRawSlot();

        if(slot == 4){

            var view = (RecipeViewMenu) selectedMenu;
            var recipe = ((RecipeViewMenu) selectedMenu).getRecipe();
            var creationMenu = new CreationMenu(openedGuis,itemBuilder,languageManager.getMessage("menu.edit"),languageManager.getMessage("menu.confirm"),languageManager.getMessage("menu.clear"),recipe);
            openedGuis.remove(player);
            creationMenu.openMenu(player);
        }
        else if(slot == 5)
        {
            var view = (RecipeViewMenu) selectedMenu;
            var recipe = ((RecipeViewMenu) selectedMenu).getRecipe();
            var deleteStatus = storageJson.deleteJson(recipe.getRecipeId());
            if(!deleteStatus) {
                player.sendMessage(languageManager.getMessage("menu.delete.fail"));
                return;
            }
            player.sendMessage(languageManager.getMessage("menu.delete.success"));
            savedItemsMap.reload();
            player.closeInventory();

        }
    }
}
