package com.siegdev.conversors.listeners.menus;

import com.google.gson.JsonObject;
import com.siegdev.conversors.LanguageManager;
import com.siegdev.conversors.handlers.OpenedGuis;
import com.siegdev.conversors.configuration.StorageJson;
import com.siegdev.conversors.listeners.ChatListener;
import com.siegdev.conversors.menus.CreationMenu;
import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.InventoryView;

import java.io.IOException;

public class CreationMenuListener implements Listener {
    private final OpenedGuis openedGuis;
    private final ChatListener chatListener;
    private final StorageJson storageJson;
    private final LanguageManager languageManager;

    public CreationMenuListener(OpenedGuis openedGuis, ChatListener chatListener, StorageJson storageJson, LanguageManager languageManager)
    {
        this.openedGuis = openedGuis;
        this.chatListener = chatListener;
        this.storageJson = storageJson;
        this.languageManager = languageManager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){

        Player player = (Player) event.getWhoClicked();
        if(!openedGuis.contains(player)){
            return;
        }
        var selectedMenu = openedGuis.getMenuFromPlayer(player);

        if(!(selectedMenu instanceof CreationMenu))
            return;

        var top = event.getView().getTopInventory();
        var bottom = event.getView().getBottomInventory();

        if(!event.getClickedInventory().equals(top)){
            return;
        }


        int slot =  event.getRawSlot();

        if((slot != 1) && (slot != 7)){
            event.setCancelled(true);
        }

        if(slot == 0) {
            player.sendMessage(languageManager.getMessage("creation.id.selection"));
            player.closeInventory();
            chatListener.playersMessage.add(player);
        }
        else if(slot == 4)
        {
            var input = top.getItem(1);
            var output = top.getItem(7);

            if(input == null){
                player.sendMessage(languageManager.getMessage("creation.input.null"));
                return;
            }
            input.setAmount(1);

            if(output == null)
            {
                player.sendMessage(languageManager.getMessage("creation.output.null"));
                return;
            }


            var nbtInput = NBT.itemStackToNBT(input);
            var nbtOutput = NBT.itemStackToNBT(output);

            var recipeId = ((CreationMenu) openedGuis.getMenuFromPlayer(player)).getRecipeId();

            if(recipeId.equalsIgnoreCase("indefinido") || recipeId.isBlank()){
                player.sendMessage(languageManager.getMessage("creation.id.undefined"));
                return;
            }

            if(!storageJson.IsIdValid(recipeId)){
                player.sendMessage(languageManager.getMessage("creation.id.alredyexists"));
            }

            var json = new JsonObject();
            json.addProperty("input",nbtInput.toString());
            json.addProperty("output",nbtOutput.toString());

            try{
                storageJson.saveJson(json,recipeId);
                player.sendMessage(languageManager.getMessage("creation.success"));
                openedGuis.remove(player);
                player.closeInventory();
            } catch (IOException e) {
                player.sendMessage(languageManager.getMessage("creation.fail"));
                throw new RuntimeException("erro ao salvar receita " + e);
            }

        }



    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
         Player player = event.getPlayer();
         InventoryView inv = player.getOpenInventory();
         openedGuis.remove(player);
    }

}
