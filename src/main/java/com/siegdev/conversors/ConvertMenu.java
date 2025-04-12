package com.siegdev.conversors;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class ConvertMenu {

    public Inventory inventory;
    public ConvertMenu()
    {
        this.inventory = Bukkit.createInventory(null,9,"converter");
    }
}
