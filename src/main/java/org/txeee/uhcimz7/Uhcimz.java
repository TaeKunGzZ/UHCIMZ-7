package org.txeee.uhcimz7;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.txeee.uhcimz7.commands.LobbyCommand;
import org.txeee.uhcimz7.commands.TestCommand;
import org.txeee.uhcimz7.listeners.Listeners;
import org.txeee.uhcimz7.runnable.Ticking;
import org.txeee.uhcimz7.runnable.Timing;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Uhcimz extends JavaPlugin {
    // initialize Timing and Ticking
    private final Timing timing = new Timing(this);
    private final Ticking ticking = new Ticking();

    // hrs, mins, secs, gameStarted, kills
    public static int hrs;
    public static int mins;
    public static int secs;
    public static boolean gameStarted = false;
    public static boolean isPVPon = false;
    public static final Map<String, Integer> kills = new HashMap<>();

    // initialize FileManager
    private FileManager fileManager;

    @Override
    public void onEnable() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getPluginManager().registerEvents(new Listeners(), this); // Event Listener
        registeredCommand(); // registeredCommands

        timing.runTaskTimer(this, 0L, 20L);
        ticking.runTaskTimer(this, 0L, 20L);

        fileManager = new FileManager(this);
        fileManager.loadScore();

        createTeam();
    }

    @Override
    public void onDisable() {
        if (fileManager != null) {
            fileManager.saveScore();
        }
    }

    private void registeredCommand() {
        Objects.requireNonNull(getCommand("lobby")).setExecutor(new LobbyCommand(this));
        Objects.requireNonNull(getCommand("test")).setExecutor(new TestCommand());
    }

    public static int getKills(Player player) {
        return kills.getOrDefault(player.getName(), 0);
    }

    public static void createTeam() {
        TeamManager teamManager = new TeamManager();

        teamManager.createTeam("red", ChatColor.RED);
        teamManager.createTeam("blue", ChatColor.BLUE);
        teamManager.createTeam("green", ChatColor.GREEN);
        teamManager.createTeam("yellow", ChatColor.YELLOW);
        teamManager.createTeam("gold", ChatColor.GOLD);
        teamManager.createTeam("aqua", ChatColor.AQUA);
        teamManager.createTeam("light_purple", ChatColor.LIGHT_PURPLE);
        teamManager.createTeam("dark_purple", ChatColor.DARK_PURPLE);
    }
}

