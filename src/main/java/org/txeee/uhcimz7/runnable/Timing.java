package org.txeee.uhcimz7.runnable;

import org.bukkit.scheduler.BukkitRunnable;
import org.txeee.uhcimz7.Uhcimz;

public class Timing extends BukkitRunnable {
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
    }
}
