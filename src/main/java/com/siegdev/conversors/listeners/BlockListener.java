package com.siegdev.conversors.listeners;

import com.siegdev.conversors.utils.ItemBuilder;
import com.siegdev.conversors.LanguageManager;
import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.NBTBlock;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {

    private final ItemBuilder itemBuilder;
    private final LanguageManager languageManager;
    public BlockListener(ItemBuilder itemBuilder, LanguageManager languageManager){
        this.itemBuilder = itemBuilder;
        this.languageManager = languageManager;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event)
    {
        Player player = event.getPlayer();
        var playerHand = player.getInventory().getItemInMainHand();
        if (!(playerHand.getType() == itemBuilder.getDefaultConversorType()))
            return;

        var conversorTag = NBT.get(playerHand, nbt -> (boolean) nbt.getBoolean("isConverter"));

        if(conversorTag != null)
            player.sendMessage(languageManager.getMessage("conversor.place"));

        var block = event.getBlockPlaced();
        if(block.getType() == Material.AIR)
            return;

        ReadWriteNBT nbt = new NBTBlock(block).getData();
        nbt.setBoolean("isConversor",true);
    }
}
