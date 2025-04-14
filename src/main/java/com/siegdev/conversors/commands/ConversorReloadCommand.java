package com.siegdev.conversors.commands;

import com.siegdev.conversors.Conversors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ConversorReloadCommand implements CommandExecutor {
    private final Conversors conversors;

    public ConversorReloadCommand(Conversors conversors){
        this.conversors = conversors;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        conversors.reloadConfig();
        conversors.getLanguageManager().loadLanguage();
        conversors.getSavedItemsMap().reload();
        conversors.conversorReloadPlugin();
        sender.sendMessage("plugin reloaded");
        return false;
    }
}
