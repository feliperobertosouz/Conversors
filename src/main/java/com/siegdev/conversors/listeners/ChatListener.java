package com.siegdev.conversors.listeners;

import com.siegdev.conversors.configuration.Conversors;
import com.siegdev.conversors.LanguageManager;
import com.siegdev.conversors.handlers.OpenedGuis;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import java.util.ArrayList;
import java.util.List;

public class ChatListener implements Listener {
    public List<Player> playersMessage;
    private final OpenedGuis openedGuis;
    private final LanguageManager languageManager;

    public ChatListener(OpenedGuis openedGuis, LanguageManager languageManager)
    {
        playersMessage = new ArrayList<>();
        this.openedGuis = openedGuis;
        this.languageManager = languageManager;
    }

    @EventHandler
    public void EventChat(AsyncPlayerChatEvent event)
    {
        Player player = event.getPlayer();
        if(!playersMessage.contains(player))
            return;

        String message = event.getMessage();

        if(message.equalsIgnoreCase(languageManager.getMessage("creation.cancel.message"))) {
            playersMessage.remove(player);
            player.sendMessage(languageManager.getMessage("creation.id.cancel"));
            return;
        }

        Bukkit.getScheduler().runTask(Conversors.getInstance(), () -> {
            var menu = openedGuis.getMenuFromPlayer(player);
            menu.setRecipeId(message);
            player.sendMessage(languageManager.getMessage("creation.id.selected"));
            menu.openMenu(player);
            playersMessage.remove(player);
        });

    }
}
