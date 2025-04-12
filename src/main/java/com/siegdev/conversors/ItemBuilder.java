package com.siegdev.conversors;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemBuilder {

    public ItemBuilder()
    {

    }

    public ItemStack buildItem(Material material, String name){
        var item = new ItemStack(material);
        var meta = item.getItemMeta();
        meta.setItemName(name);
        item.setItemMeta(meta);
        return item;
    }
}
