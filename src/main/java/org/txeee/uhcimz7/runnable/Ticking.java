package org.txeee.uhcimz7.runnable;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.txeee.uhcimz7.Uhcimz;

import java.util.Objects;

public class Ticking extends BukkitRunnable {
    private WorldBorder worldBorder;

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (Uhcimz.gameStarted) {
                String time = String.format("§3§l%02d§r:§3§l%02d§r:§3§l%02d§r", Uhcimz.hrs, Uhcimz.mins, Uhcimz.secs);
                String kills = "§4Kills§r: §6§l" + Uhcimz.getKills(player) + "§r";
                double borderSize = Objects.requireNonNull(Bukkit.getWorld("world")).getWorldBorder().getSize();
                String getSize = "§d§l±" + (int) borderSize + "§r";

                player.sendActionBar("§c§l◤" + time + "  ◆  " + kills + "  ◆  " + getSize + "§c§l◢");

                // Events
                if (Uhcimz.hrs == 0 && Uhcimz.mins == 1 && Uhcimz.secs == 0) {
                    setGameRule(GameRule.FALL_DAMAGE, true);
                    setDifficulty(Difficulty.NORMAL);
                }
                else if (Uhcimz.hrs == 0 && Uhcimz.mins == 10 && Uhcimz.secs == 0) {
                    PotionEffect regen = new PotionEffect(PotionEffectType.REGENERATION, 20, 255);
                    player.addPotionEffect(regen);
                    player.sendTitle("", "§dFinal Heal has Appeared.");
                }
                else if (Uhcimz.hrs == 0 && Uhcimz.mins == 20 && Uhcimz.secs == 0) {
                    Uhcimz.isPVPon = true;
                    player.sendMessage("");
                }
            }
        }
    }

     private <T> void setGameRule(GameRule<T> rule, T value) {
        for (World world : Bukkit.getServer().getWorlds()) {
            world.setGameRule(rule, value);
        }
    }

    private void setDifficulty(Difficulty difficulty) {
        for (World world : Bukkit.getServer().getWorlds()) {
            world.setDifficulty(difficulty);
        }
    }

    private void clearInventory(Player player) {
        player.getInventory().clear();
    }

    private void setupWorldBorder() {
        WorldBorder worldBorder = Objects.requireNonNull(Bukkit.getWorld("world")).getWorldBorder();
    }

    private double getWorldBorderSize() {
        return worldBorder.getSize();
    }
}
