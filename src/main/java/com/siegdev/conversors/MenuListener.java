package com.siegdev.conversors;

import com.google.gson.JsonObject;
import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.InventoryView;

import java.io.IOException;

public class MenuListener implements Listener {
    private final OpenedGuis openedGuis;
    private final ChatListener chatListener;
    private final StorageJson storageJson;
    public MenuListener(OpenedGuis openedGuis, ChatListener chatListener, StorageJson storageJson)
    {
        this.openedGuis = openedGuis;
        this.chatListener = chatListener;
        this.storageJson = storageJson;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        InventoryView inv = event.getWhoClicked().getOpenInventory();

        Player player = (Player) event.getWhoClicked();
        if(!openedGuis.contains(player)){
            return;
        }
        var top = event.getView().getTopInventory();
        var bottom = event.getView().getBottomInventory();

        if(!event.getClickedInventory().equals(top)){
            return;
        }


        int slot =  event.getRawSlot();
        player.sendMessage(""+slot);
        if((slot != 1) && (slot != 7)){
            event.setCancelled(true);
        }

        if(slot == 0) {
            player.sendMessage("digite no chat o id da receita");
            player.closeInventory();
            chatListener.playersMessage.add(player);
        }
        else if(slot == 4)
        {
            player.sendMessage("salvando");
            var input = top.getItem(1);
            var output = top.getItem(7);

            if(input == null){
                player.sendMessage("por favor insira um item de input");
                return;
            }

            if(output == null)
            {
                player.sendMessage("por favor insira um item de output");
                return;
            }


            var nbtInput = NBT.itemStackToNBT(input);
            var nbtOutput = NBT.itemStackToNBT(output);

            player.sendMessage("INPUT: " + nbtInput.toString());
            player.sendMessage("OUTPUT" + nbtOutput.toString());
            var recipeId = openedGuis.getMenuFromPlayer(player).getRecipeId();

            if(recipeId.equalsIgnoreCase("indefinido") || recipeId.isBlank()){
                player.sendMessage("Por favor informe um id valido");
                return;
            }
            var json = new JsonObject();
            json.addProperty("input",nbtInput.toString());
            json.addProperty("output",nbtOutput.toString());

            try{
                storageJson.saveJson(json,recipeId);
                player.sendMessage("Receita salva com sucesso");
                openedGuis.remove(player);
                player.closeInventory();
            } catch (IOException e) {
                player.sendMessage("Não foi possivel salvar a receita");
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
