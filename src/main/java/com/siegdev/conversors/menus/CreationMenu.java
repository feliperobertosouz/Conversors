package com.siegdev.conversors.menus;

import com.siegdev.conversors.configuration.LanguageManager;
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
    private final ItemBuilder itemBuilder;

    public CreationMenu(OpenedGuis openedGuis, ItemBuilder itemBuilder, String menuName, String confirmItemName, String clearItemName){
        this.itemBuilder = itemBuilder;
        this.menu = Bukkit.createInventory(null,9,menuName);
        this.openedGuis = openedGuis;
        itemBuilder.buildMenuTemplate(menu, confirmItemName);
        this.menu.setItem(5,ItemBuilder.buildItem(Material.BARRIER,clearItemName));
    }

    public CreationMenu(OpenedGuis openedGuis, ItemBuilder itemBuilder, String menuName, String confirmItemName, String clearItemName, ConversorRecipe recipe)
    {
        this.itemBuilder = itemBuilder;
        this.menu = Bukkit.createInventory(null,9,menuName + recipe.getRecipeId());
        this.openedGuis = openedGuis;
        itemBuilder.buildMenuTemplate(menu, confirmItemName);
        menu.setItem(1,recipe.getInput());
        menu.setItem(7,recipe.getOutput());
        this.recipeId = recipe.getRecipeId();
        this.menu.setItem(5,ItemBuilder.buildItem(Material.BARRIER,clearItemName));

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
