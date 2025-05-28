package org.txeee.uhcimz7;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class FileManager {
    private final Uhcimz plugin;
    private File configFile;
    private YamlConfiguration config;

    FileManager(Uhcimz plugin) {
        this.plugin = plugin;
        setup();
    }

    public void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        configFile = new File(plugin.getDataFolder(), "saved.yml");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void loadScore() {
        if (config == null) return;

        Uhcimz.secs = config.getInt("timer.seconds", 0);
        Uhcimz.mins = config.getInt("timer.minutes", 0);
        Uhcimz.hrs = config.getInt("timer.hours", 0);
        Uhcimz.gameStarted = config.getBoolean("game.is_starting", false);
    }

    public void saveScore() {
        config.set("timer.seconds", Uhcimz.secs);
        config.set("timer.mins", Uhcimz.mins);
        config.set("timer.hrs", Uhcimz.hrs);
        config.set("game.is_starting", Uhcimz.gameStarted);

        try {
            config.save(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
