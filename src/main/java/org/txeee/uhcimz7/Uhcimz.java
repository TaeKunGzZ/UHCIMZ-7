package org.txeee.uhcimz7;

import org.bukkit.plugin.java.JavaPlugin;
import org.txeee.uhcimz7.commands.LobbyCommand;
import org.txeee.uhcimz7.listeners.Listeners;
import org.txeee.uhcimz7.runnable.Timing;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Uhcimz extends JavaPlugin {
    // initialize Timing
    private final Timing timing = new Timing();

    // hrs, mins, secs, gameStarted, kills
    public static int hrs;
    public static int mins;
    public static int secs;
    public static boolean gameStarted = false;
    public static final Map<String, Integer> kills = new HashMap<>();

    // initialize FileManager
    private FileManager fileManager;

    @Override
    public void onEnable() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getPluginManager().registerEvents(new Listeners(), this); // Event Listener
        registeredCommand(); // registeredCommands

        timing.runTaskTimer(this, 0L, 20L);

        fileManager = new FileManager(this);
        fileManager.loadScore();
    }

    @Override
    public void onDisable() {
        if (fileManager != null) {
            fileManager.saveScore();
        }
    }

    private void registeredCommand() {
        Objects.requireNonNull(getCommand("lobby")).setExecutor(new LobbyCommand(this));
    }
}

