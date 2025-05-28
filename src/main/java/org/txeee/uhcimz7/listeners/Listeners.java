package org.txeee.uhcimz7.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.txeee.uhcimz7.Uhcimz;

import java.util.Objects;

public class Listeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.sendMessage("hello");
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        Player killer = Objects.requireNonNull(event.getEntity().getPlayer()).getKiller();

        if (killer != null) {
            String killerName = killer.getName();
            Uhcimz.kills.put(killerName, Uhcimz.kills.getOrDefault(killerName, 0) + 1);
        }

        assert player != null;
        player.setGameMode(GameMode.SPECTATOR);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String name = event.getPlayer().getName();
        String msg = event.getMessage();

        event.setFormat(name + ": " + ChatColor.GRAY + msg);
    }
}
