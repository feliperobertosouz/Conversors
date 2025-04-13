package com.siegdev.conversors.utils;

import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemBuilder {

    private ItemStack defaultConversor;

    public ItemBuilder(Material materialDefaultConversor, String nameDefaultConversor)
    {
        this.defaultConversor = buildDefaultConversor(materialDefaultConversor,nameDefaultConversor);
    }

    public ItemStack buildItem(Material material, String name){
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
}
