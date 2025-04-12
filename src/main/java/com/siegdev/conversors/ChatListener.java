package com.siegdev.conversors;

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

    public ChatListener(OpenedGuis openedGuis)
    {
        playersMessage = new ArrayList<>();
        this.openedGuis = openedGuis;
    }

    @EventHandler
    public void EventChat(AsyncPlayerChatEvent event)
    {
        Player player = event.getPlayer();
        player.sendMessage("pegando sua mensagem");
        if(!playersMessage.contains(player))
            return;

        String message = event.getMessage();

        if(message.equalsIgnoreCase("cancelar")) {
            playersMessage.remove(player);
            player.sendMessage("cancelado selecionamento de evento");
            return;
        }

        Bukkit.getScheduler().runTask(Conversors.getInstance(), () -> {
            var menu = openedGuis.getMenuFromPlayer(player);
            menu.setRecipeId(message);
            player.sendMessage("Id selecionado");
            menu.openMenu(player);
            playersMessage.remove(player);
        });

    }
}
