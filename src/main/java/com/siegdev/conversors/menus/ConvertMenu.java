package com.siegdev.conversors.menus;

import com.siegdev.conversors.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ConvertMenu implements MenuPlugin{

    public Inventory inventory;
    public final ItemBuilder itemBuilder;
    public ConvertMenu(ItemBuilder itemBuilder)
    {
        this.itemBuilder = itemBuilder;
        this.inventory = Bukkit.createInventory(null,9,"converter");
        inventory.setItem(0,itemBuilder.buildItem(Material.BLACK_STAINED_GLASS_PANE," "));
        inventory.setItem(2,itemBuilder.buildItem(Material.BLACK_STAINED_GLASS_PANE," "));
        inventory.setItem(3,itemBuilder.buildItem(Material.BLACK_STAINED_GLASS_PANE," "));
        inventory.setItem(4, itemBuilder.buildItem(Material.LIME_STAINED_GLASS_PANE,"confirmar!!"));
        inventory.setItem(5,itemBuilder.buildItem(Material.BLACK_STAINED_GLASS_PANE," "));
        inventory.setItem(6,itemBuilder.buildItem(Material.BLACK_STAINED_GLASS_PANE," "));
        inventory.setItem(8,itemBuilder.buildItem(Material.BLACK_STAINED_GLASS_PANE," "));
    }

    @Override
    public Inventory getMenu() {
        return this.inventory;
    }

    @Override
    public void setRecipeId(String recipeId) {

    }

    @Override
    public void setInventory(Inventory inventory) {

    }


    @Override
    public void openMenu(Player player) {

    }

    @Override
    public String getRecipeId() {
        return "";
    }

}
