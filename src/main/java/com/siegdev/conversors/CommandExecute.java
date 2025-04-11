package com.siegdev.conversors;

import com.google.gson.JsonObject;
import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.Console;
import java.io.IOException;
import java.util.Arrays;

public class CommandExecute  implements CommandExecutor {

    final StorageJson storageJson;
    final SavedItemsMap savedItemsMap;
    final OpenedGuis openedGuis;

    public CommandExecute(StorageJson storage, SavedItemsMap saveditems, OpenedGuis openedGuis){
        this.storageJson = storage;
        this.savedItemsMap = saveditems;
        this.openedGuis = openedGuis;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {


        if(!(sender instanceof Player)){
            openedGuis.printOponenedMenus();
            sender.sendMessage("Apenas jogadores podem usar este comando");
            return true;
        }

        Player player = (Player) sender;
        player.sendMessage("Você usou o comando /conversor");

        if(strings[0].equals("open")){
            MenuBuilder menu = new MenuBuilder(openedGuis);
            menu.openMenu(player);
            return true;
        }
        if(strings[0].equals("convert")){
            ItemStack item = null;
            item = player.getInventory().getItemInMainHand();

            if(item.getType() == Material.AIR){
                player.sendMessage("Por favor use um item na mão");
                return false;
            }

            ItemStack result = savedItemsMap.getResult(item);
            player.getInventory().addItem(result);

            return true;
        }


        if(strings[0].equals("get"))
        {
            String file = strings[1];

            if(file == null)
                return false;

            try {
                JsonObject itemFromJson = storageJson.getJson(file);
                player.sendMessage(itemFromJson.toString());
                String itemString = itemFromJson.get("item").getAsString();
                System.out.println(itemString);
                ReadWriteNBT itemFromNbt = NBT.parseNBT(itemString);
                ItemStack item = NBT.itemStackFromNBT(itemFromNbt);
                player.getInventory().addItem(item);
                return true;
            } catch (IOException e) {
                player.sendMessage("não foi possivel encontrar o item");
                return false;
            }

        }
        ItemStack item = null;
        item = player.getInventory().getItemInMainHand();

        if(item.getType() == Material.AIR){
            player.sendMessage("Por favor use um item na mão");
            return false;
        }

        ReadWriteNBT nbt = NBT.itemStackToNBT(item);
        player.sendMessage(nbt.toString());

        JsonObject json = new JsonObject();
        json.addProperty("item",nbt.toString());

        try{
            storageJson.saveJson(json,"player");
            player.sendMessage("salvo com sucesso");
        }catch (Exception ex){
            player.sendMessage("nao foi possivel salvar");
        }

        return false;
    }
}
