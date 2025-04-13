package com.siegdev.conversors.menus;

import com.siegdev.conversors.model.ConversorRecipe;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class RecipeViewMenu implements MenuPlugin{

    Inventory menu;
    public RecipeViewMenu(String recipeId, ConversorRecipe recipe){
        menu = Bukkit.createInventory(null,9,"receita: " + recipeId);
        menu.setItem(1, recipe.getInput());
        menu.setItem(7,recipe.getOutput());
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
