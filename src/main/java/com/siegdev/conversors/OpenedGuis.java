package com.siegdev.conversors;

import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;

import java.util.HashMap;
import java.util.Map;

public class OpenedGuis {
    private final Map<Player, MenuBuilder> openGUIS;

    public OpenedGuis(){
        this.openGUIS = new HashMap<>();
    }

    public void add(Player player, MenuBuilder menuBuilder)
    {
        this.openGUIS.put(player,menuBuilder);
    }

    public void remove(Player player)
    {
        this.openGUIS.remove(player);
    }

    public boolean contains(Player player)
    {
        return this.openGUIS.containsKey(player);
    }

    public MenuBuilder getMenuFromPlayer(Player player)
    {
        return openGUIS.get(player);
    }

    public void printOponenedMenus()
    {
        System.out.println(openGUIS.toString());
    }
}
