package com.siegdev.conversors.model;

import org.bukkit.inventory.ItemStack;

public class ConversorRecipe {

    String recipeId;

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public ItemStack getInput() {
        return input;
    }

    public void setInput(ItemStack input) {
        this.input = input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public void setOutput(ItemStack output) {
        this.output = output;
    }

    ItemStack input;
    ItemStack output;

    public ConversorRecipe(String recipeId, ItemStack input, ItemStack output){
        this.recipeId = recipeId;
        this.input = input;
        this.output = output;
    }


}
