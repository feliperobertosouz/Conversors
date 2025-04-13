package com.siegdev.conversors.listeners.menus;

import com.siegdev.conversors.configuration.StorageJson;
import com.siegdev.conversors.handlers.OpenedGuis;
import com.siegdev.conversors.handlers.SavedItemsMap;
import com.siegdev.conversors.menus.CreationMenu;
import com.siegdev.conversors.menus.RecipeMenu;
import com.siegdev.conversors.menus.RecipeViewMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class RecipeMenuViewListener implements Listener {

    private final OpenedGuis openedGuis;
    private final StorageJson storageJson;
    private final SavedItemsMap savedItemsMap;

    public RecipeMenuViewListener(OpenedGuis openedGuis, StorageJson storageJson, SavedItemsMap savedItemsMap){
        this.openedGuis = openedGuis;
        this.storageJson = storageJson;
        this.savedItemsMap = savedItemsMap;
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
            player.sendMessage("abrindo edição");

            var view = (RecipeViewMenu) selectedMenu;
            var recipe = ((RecipeViewMenu) selectedMenu).getRecipe();
            var creationMenu = new CreationMenu(openedGuis,recipe);
            openedGuis.remove(player);
            creationMenu.openMenu(player);
        }
        else if(slot == 5)
        {
            var view = (RecipeViewMenu) selectedMenu;
            var recipe = ((RecipeViewMenu) selectedMenu).getRecipe();
            var deleteStatus = storageJson.deleteJson(recipe.getRecipeId());
            if(!deleteStatus) {
                player.sendMessage("Não foi possivel deletar a receita");
                return;
            }
            player.sendMessage("Receita deletada com sucesso");
            savedItemsMap.reload();
            player.closeInventory();

        }
    }
}
