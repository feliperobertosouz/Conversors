package com.siegdev.conversors;

import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class SavedItemsMap {

    HashMap<ItemStack, ItemStack> itemsConversor;
    private final StorageJson storageJson;
    public SavedItemsMap(StorageJson storageJson) {
        itemsConversor = new HashMap<>();
        this.storageJson = storageJson;
    }

    public void reload()
    {
        itemsConversor.clear();
        var recipes = storageJson.readAllRecipeJsons();
        Conversors.getInstance().getLogger().info("Carregando receitas");

        for(Pair<String, String> pair : recipes)
        {
            ReadWriteNBT input = NBT.parseNBT(pair.key);
            ReadWriteNBT output = NBT.parseNBT(pair.value);
            Conversors.getInstance().getLogger().info("INPUT: " + input + " | " + "OUPUT: " + output);
            ItemStack inputItem = NBT.itemStackFromNBT(input);
            ItemStack outputItem = NBT.itemStackFromNBT(output);

            itemsConversor.put(inputItem,outputItem);
        }
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
