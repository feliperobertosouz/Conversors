package com.siegdev.conversors.commands;

import com.siegdev.conversors.utils.ItemBuilder;
import com.siegdev.conversors.LanguageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetConversorCommand implements CommandExecutor {

    private final ItemBuilder itemBuilder;
    private final LanguageManager languageManager;

    public GetConversorCommand(ItemBuilder itemBuilder, LanguageManager languageManager){
        this.itemBuilder = itemBuilder;
        this.languageManager = languageManager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if(!(sender instanceof Player)){
            sender.sendMessage(languageManager.getMessage("command.playeronly"));
            return true;
        }

        Player player = (Player) sender;
        ItemStack defaultConversor = itemBuilder.getDefaultConversor();
        player.getInventory().addItem(defaultConversor);
        player.sendMessage(languageManager.getMessage("command.givedconversor"));

        return false;
    }
}
