package com.siegdev.conversors.utils;

import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ItemBuilder {

    private ItemStack defaultConversor;

    public ItemBuilder(Material materialDefaultConversor, String nameDefaultConversor)
    {
        this.defaultConversor = buildDefaultConversor(materialDefaultConversor,nameDefaultConversor);
    }

    public static ItemStack buildItem(Material material, String name){
        var item = new ItemStack(material);
        var meta = item.getItemMeta();
        meta.setItemName(name);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack buildDefaultConversor(Material material, String name)
    {
        var item = buildItem(material,name);
        NBT.modify(item, nbt -> {
            nbt.setBoolean("isConverter",true);
        });
        return item;
    }

    public ItemStack getDefaultConversor()
    {
        return this.defaultConversor;
    }

    public Material getDefaultConversorType(){
        return this.defaultConversor.getType();
    }

    public void buildMenuTemplate(Inventory menu, String confirmItemName){
        menu.setItem(0,buildItem(Material.PAPER,"Id "));
        menu.setItem(2,buildItem(Material.BLACK_STAINED_GLASS_PANE,""));
        menu.setItem(3,buildItem(Material.BLACK_STAINED_GLASS_PANE,""));
        menu.setItem(4, buildItem(Material.LIME_STAINED_GLASS_PANE,confirmItemName));
        menu.setItem(5,buildItem(Material.BLACK_STAINED_GLASS_PANE,""));
        menu.setItem(6,buildItem(Material.BLACK_STAINED_GLASS_PANE,""));
        menu.setItem(8,buildItem(Material.BLACK_STAINED_GLASS_PANE,""));
    }
}
