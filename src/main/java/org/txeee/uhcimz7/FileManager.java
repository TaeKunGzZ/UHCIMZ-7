package org.txeee.uhcimz7;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Map;
import java.util.Objects;

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
        Uhcimz.secs = config.getInt("timer.secs", 0);
        Uhcimz.mins = config.getInt("timer.mins", 0);
        Uhcimz.hrs = config.getInt("timer.hrs", 0);
        Uhcimz.gameStarted = config.getBoolean("game.is_starting", false);
        if (config.contains("player.kills")) {
            for (String playerName : Objects.requireNonNull(config.getConfigurationSection("player.kills")).getKeys(false)) {
                int kills = config.getInt("player.kills." + playerName);
                Uhcimz.kills.put(playerName, kills);
            }
        }
    }

    public void saveScore() {
        config.set("timer.secs", Uhcimz.secs);
        config.set("timer.mins", Uhcimz.mins);
        config.set("timer.hrs", Uhcimz.hrs);
        config.set("game.is_starting", Uhcimz.gameStarted);
        for (Map.Entry<String, Integer> entry : Uhcimz.kills.entrySet()) {
            config.set("player.kills." + entry.getKey(), entry.getValue());
        }

        try {
            config.save(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
