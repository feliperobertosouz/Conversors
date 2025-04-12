package com.siegdev.conversors;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MenuBuilder {

    private final Inventory menu;
    private OpenedGuis openedGuis;
    private String recipeId = "indefinido";
    private ItemBuilder itemBuilder;

    public MenuBuilder(OpenedGuis openedGuis, ItemBuilder itemBuilder){
        this.menu = Bukkit.createInventory(null,9,"criar conversor");
        this.openedGuis = openedGuis;
        menu.setItem(0,itemBuilder.buildItem(Material.PAPER,"Id "));
        menu.setItem(2,itemBuilder.buildItem(Material.BLACK_STAINED_GLASS_PANE,""));
        menu.setItem(3,itemBuilder.buildItem(Material.BLACK_STAINED_GLASS_PANE,""));
        menu.setItem(4, itemBuilder.buildItem(Material.LIME_STAINED_GLASS_PANE,"confirmar!!"));
        menu.setItem(5,itemBuilder.buildItem(Material.BLACK_STAINED_GLASS_PANE,""));
        menu.setItem(6,itemBuilder.buildItem(Material.BLACK_STAINED_GLASS_PANE,""));
        menu.setItem(8,itemBuilder.buildItem(Material.BLACK_STAINED_GLASS_PANE,""));
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

    public String getRecipeId()
    {
        return this.recipeId;
    }

}
