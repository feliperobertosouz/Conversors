package com.siegdev.conversors;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class SavedItemsMap {

    HashMap<ItemStack, ItemStack> itemsConversor;

    public SavedItemsMap() {
        itemsConversor = new HashMap<>();
    }

    public void addItemPairToMap(ItemStack key,ItemStack result) {
        itemsConversor.put(key,result);
    }

    public ItemStack getResult(ItemStack key) {
        ItemStack result = itemsConversor.get(key);
        if(result == null)
            return new ItemStack(Material.AIR);
        return result;
    }
}
