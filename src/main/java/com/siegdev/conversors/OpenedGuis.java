package com.siegdev.conversors;

import org.bukkit.inventory.InventoryView;

import java.util.HashMap;
import java.util.Map;

public class OpenedGuis {
    private final Map<InventoryView, MenuBuilder> openGUIS;

    public OpenedGuis(){
        this.openGUIS = new HashMap<>();
    }

    public void add(InventoryView inventoryView, MenuBuilder menuBuilder)
    {
        this.openGUIS.put(inventoryView,menuBuilder);
    }

    public void remove(InventoryView inventoryView)
    {
        this.openGUIS.remove(inventoryView);
    }

    public boolean contains(InventoryView inventoryView)
    {
        return this.openGUIS.containsKey(inventoryView);
    }

    public void printOponenedMenus()
    {
        System.out.println(openGUIS.toString());
    }
}
