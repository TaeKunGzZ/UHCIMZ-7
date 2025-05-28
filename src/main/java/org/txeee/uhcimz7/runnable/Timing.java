package org.txeee.uhcimz7.runnable;

import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.txeee.uhcimz7.Uhcimz;

public class Timing extends BukkitRunnable {
    private WorldBorder worldBorder = Bukkit.getWorld("world").getWorldBorder();
    private int worldBorderSize = (int) worldBorder.getSize();

    @Override
    public void run() {
        if (Uhcimz.gameStarted) {
            Uhcimz.secs++;

            if (Uhcimz.secs >= 60) {
                Uhcimz.mins++;
                Uhcimz.secs = 0;
            }

            if (Uhcimz.mins >= 60) {
                Uhcimz.hrs++;
                Uhcimz.mins = 0;
            }
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            String time = String.format("%02d:%02d:%02d", Uhcimz.hrs, Uhcimz.mins, Uhcimz.secs);
            String kills = "Kills: " + Uhcimz.getKills(player);
            String border = "±" + worldBorderSize;

            player.sendActionBar(time + "     " + kills + "     " + border);
        }
    }
}
