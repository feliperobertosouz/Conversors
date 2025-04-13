package com.siegdev.conversors.menus;

import com.siegdev.conversors.handlers.SavedItemsMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RecipeMenu implements MenuPlugin{

    private Inventory menu;
    private final SavedItemsMap savedItemsMap;
    public RecipeMenu(SavedItemsMap savedItemsMap){
        this.menu = Bukkit.createInventory(null, 54,"coming soon");
        this.savedItemsMap = savedItemsMap;
        loadItems();
    }

    public void loadItems(){
        int i = 0;
        for (var entry: savedItemsMap.recipes.entrySet()) {
            ItemStack input = entry.getValue().getInput();
            var recipe = entry.getValue().getRecipeId();

            var meta = input.getItemMeta();
            meta.setItemName(ChatColor.WHITE + recipe);
            input.setItemMeta(meta);

            this.menu.setItem(i,input);
            i++;
        }
    }
    @Override
    public Inventory getMenu() {
        return this.menu;
    }

    @Override
    public void setRecipeId(String recipeId) {

    }

    @Override
    public void setInventory(Inventory inventory) {
        this.menu = inventory;
    }


    @Override
    public void openMenu(Player player) {
        player.openInventory(this.menu);
    }

    @Override
    public String getRecipeId() {
        return "";
    }
}
