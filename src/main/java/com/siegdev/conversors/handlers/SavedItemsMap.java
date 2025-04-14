package com.siegdev.conversors.handlers;

import com.siegdev.conversors.Conversors;
import com.siegdev.conversors.configuration.StorageJson;
import com.siegdev.conversors.model.ConversorRecipe;
import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class SavedItemsMap {

    public HashMap<ItemStack, ItemStack> itemsConversor;
    public HashMap<String, ConversorRecipe> recipes;
    private final StorageJson storageJson;
    public SavedItemsMap(StorageJson storageJson) {
        itemsConversor = new HashMap<>();
        this.storageJson = storageJson;
        recipes = new HashMap<>();
    }

    public void reload()
    {
        itemsConversor.clear();
        recipes.clear();
        var recipes = storageJson.readAllRecipeJsons();
        Conversors.getInstance().getLogger().info("Carregando receitas");

        for(var pair : recipes)
        {
            String recipeId = pair.key;

            ReadWriteNBT input = NBT.parseNBT(pair.value.key);
            ReadWriteNBT output = NBT.parseNBT(pair.value.value);
            Conversors.getInstance().getLogger().info("INPUT: " + input + " | " + "OUPUT: " + output);
            ItemStack inputItem = NBT.itemStackFromNBT(input);
            ItemStack outputItem = NBT.itemStackFromNBT(output);
            ConversorRecipe conversorRecipe = new ConversorRecipe(recipeId,inputItem,outputItem);
            itemsConversor.put(inputItem,outputItem);
            this.recipes.put(recipeId,conversorRecipe);
        }
    }

    public ItemStack getResult(ItemStack key) {
        ItemStack result = itemsConversor.get(key);
        if(result == null)
            return new ItemStack(Material.AIR);
        return result;
    }

    public ConversorRecipe getRecipeByRecipeName(String recipeId){
        return recipes.get(recipeId);
    }
}
