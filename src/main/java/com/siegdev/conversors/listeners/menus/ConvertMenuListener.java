package com.siegdev.conversors.listeners.menus;

import com.siegdev.conversors.configuration.LanguageManager;
import com.siegdev.conversors.handlers.OpenedGuis;
import com.siegdev.conversors.handlers.SavedItemsMap;
import com.siegdev.conversors.menus.ConvertMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class ConvertMenuListener implements Listener {
    private final OpenedGuis openedGuis;
    private final SavedItemsMap savedItemsMap;
    private final LanguageManager languageManager;

    public ConvertMenuListener(OpenedGuis openedGuis, SavedItemsMap savedItemsMap, LanguageManager languageManager)
    {
        this.openedGuis = openedGuis;
        this.savedItemsMap = savedItemsMap;
        this.languageManager = languageManager;
    }

    @EventHandler
    public void ConvertMenuClose(InventoryCloseEvent event)
    {
        Player player = (Player) event.getPlayer();

        if(!openedGuis.contains(player)){
            return;
        }


        var menu = openedGuis.getMenuFromPlayer(player);
        if(!(menu instanceof ConvertMenu))
            return;
        var input = menu.getMenu().getItem(1);
        var output = menu.getMenu().getItem(7);
        if(input != null){
            if (player.getInventory().firstEmpty() != -1) {
                player.getInventory().addItem(input);
            } else {
                player.getWorld().dropItemNaturally(player.getLocation(), input);
            }
        }

        if(output != null){
            if (player.getInventory().firstEmpty() != -1) {
                player.getInventory().addItem(output);
            } else {
                player.getWorld().dropItemNaturally(player.getLocation(), output);
            }
        }
        openedGuis.remove(player);

    }
    @EventHandler
    public void ConvertMenuInteract(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        if(!openedGuis.contains(player)){
            return;
        }
        var selectedMenu = openedGuis.getMenuFromPlayer(player);

        if(!(selectedMenu instanceof ConvertMenu))
            return;

        var top = event.getView().getTopInventory();

        if(!event.getClickedInventory().equals(top)){
            return;
        }

        int slot =  event.getRawSlot();

        if((slot != 1) && (slot != 7)){
            event.setCancelled(true);
        }

        if(slot == 4){
            var input = top.getItem(1);

            if(input == null)
                return;


            var output = top.getItem(7);
            if(output != null){
                player.sendMessage(languageManager.getMessage("convert.outputfull"));
                return;
            }

            var inputClone = input.clone();
            inputClone.setAmount(1);

            ItemStack result = savedItemsMap.getResult(inputClone);
            if(result == null)
            {
                player.sendMessage(languageManager.getMessage("convert.fail"));
                return;
            }
            int amount = input.getAmount();
            if(amount <= 1)
                top.setItem(1,null);
            else{
                input.setAmount(amount - 1);
                top.setItem(1,input);
            }


            top.setItem(7,result);

        }
    }
}
