package org.txeee.uhcimz7.runnable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.txeee.uhcimz7.Uhcimz;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Timing extends BukkitRunnable {
    private final Plugin plugin;

    public Timing(Plugin plugin) {
        this.plugin = plugin;
    }

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
            if (Uhcimz.gameStarted) {
                sendToServer(player, "world");
            }
        }
    }

    public void sendToServer(Player player, String targetServer) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(out);

        try {
            dataOut.writeUTF("Connect");
            dataOut.writeUTF(targetServer);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }
}
