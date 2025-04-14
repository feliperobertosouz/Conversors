package com.siegdev.conversors.menus;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface MenuPlugin {

    Inventory getMenu();
    void setRecipeId(String recipeId);
    void setInventory(Inventory inventory);
    void openMenu(Player player);
    String getRecipeId();

}
