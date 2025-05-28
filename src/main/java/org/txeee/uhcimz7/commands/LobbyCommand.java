package org.txeee.uhcimz7.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class LobbyCommand implements CommandExecutor, TabCompleter {
    private final Plugin plugin;

    public LobbyCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return false;

        sendToServer((Player) sender, "lobby");
        return false;
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

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return Collections.emptyList();
    }
}
