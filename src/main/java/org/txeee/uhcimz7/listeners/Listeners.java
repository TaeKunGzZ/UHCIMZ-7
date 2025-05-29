package org.txeee.uhcimz7.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.txeee.uhcimz7.Uhcimz;
import org.txeee.uhcimz7.id.Item;

import java.util.Objects;

public class Listeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.sendMessage("");
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        Player killer = Objects.requireNonNull(event.getEntity().getPlayer()).getKiller();
        assert player != null;
        // get head from Item
        ItemStack head = Item.head(player);

        if (killer != null) {
            String killerName = killer.getName();
            Uhcimz.kills.put(killerName, Uhcimz.kills.getOrDefault(killerName, 0) + 1);
        }

        player.getWorld().dropItem(player.getLocation(), head);
        player.setGameMode(GameMode.SPECTATOR);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String name = event.getPlayer().getName();
        String msg = event.getMessage();

        msg = ChatColor.translateAlternateColorCodes('&', msg);

        event.setFormat(name + ": " + ChatColor.GRAY + msg);
    }
}
