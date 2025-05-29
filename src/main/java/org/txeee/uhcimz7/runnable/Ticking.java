package org.txeee.uhcimz7.runnable;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.txeee.uhcimz7.Uhcimz;

import java.util.Objects;

public class Ticking extends BukkitRunnable {
    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (Uhcimz.gameStarted) {
                String time = String.format("§3§l%02d§r:§3§l%02d§r:§3§l%02d§r", Uhcimz.hrs, Uhcimz.mins, Uhcimz.secs);
                String kills = "§4Kills§r: §6§l" + Uhcimz.getKills(player) + "§r";
                double borderSize = Objects.requireNonNull(Bukkit.getWorld("world")).getWorldBorder().getSize();
                String getSize = "§d§l±" + (int) borderSize + "§r";

                player.sendActionBar("§c§l◤" + time + "  ◆  " + kills + "  ◆  " + getSize + "§c§l◢");
            }
        }
    }
}
