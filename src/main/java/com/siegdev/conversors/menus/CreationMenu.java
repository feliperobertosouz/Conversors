package com.siegdev.conversors.menus;

import com.siegdev.conversors.model.ConversorRecipe;
import com.siegdev.conversors.utils.ItemBuilder;
import com.siegdev.conversors.handlers.OpenedGuis;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CreationMenu implements  MenuPlugin{

    private final Inventory menu;
    private OpenedGuis openedGuis;
    private String recipeId = "indefinido";

    private ItemBuilder itemBuilder;

    public CreationMenu(OpenedGuis openedGuis, ItemBuilder itemBuilder){
        this.menu = Bukkit.createInventory(null,9,"criar conversor");
        this.openedGuis = openedGuis;
        ItemBuilder.buildMenuTemplate(menu);
    }

    public CreationMenu(OpenedGuis openedGuis, ConversorRecipe recipe)
    {
        this.menu = Bukkit.createInventory(null,9,"criar conversor");
        this.openedGuis = openedGuis;
        ItemBuilder.buildMenuTemplate(menu);
        menu.setItem(1,recipe.getInput());
        menu.setItem(7,recipe.getOutput());
        this.recipeId = recipe.getRecipeId();

    }

    @Override
    public Inventory getMenu() {
        return this.menu;
    }


    public void openMenu(Player player){
        player.openInventory(menu);
        var item = menu.getItem(0);
        var meta = item.getItemMeta();
        meta.setItemName("Id: " + recipeId);
        item.setItemMeta(meta);
        menu.setItem(0,item);
        openedGuis.add(player,this);
    }

    public void setRecipeId(String recipeId)
    {
        this.recipeId = recipeId;
    }

    @Override
    public void setInventory(Inventory inventory) {

    }

    public String getRecipeId()
    {
        return this.recipeId;
    }
}
