package com.siegdev.conversors.handlers;

import com.siegdev.conversors.menus.CreationMenu;
import com.siegdev.conversors.menus.MenuPlugin;
import com.siegdev.conversors.utils.ItemBuilder;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class OpenedGuis {
    private final Map<Player, MenuPlugin> openGUIS;
    public OpenedGuis(){
        this.openGUIS = new HashMap<>();
    }

    public void add(Player player, MenuPlugin menu)
    {
        this.openGUIS.put(player,menu);
    }


    public void remove(Player player)
    {
        this.openGUIS.remove(player);
    }

    public boolean contains(Player player)
    {
        return this.openGUIS.containsKey(player);
    }

    public MenuPlugin getMenuFromPlayer(Player player)
    {
        return openGUIS.get(player);
    }

    public void printOponenedMenus()
    {
        System.out.println(openGUIS.toString());
    }
}
