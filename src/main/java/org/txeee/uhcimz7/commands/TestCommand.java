package org.txeee.uhcimz7.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.txeee.uhcimz7.Uhcimz;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return false;

        if (args.length == 0) Uhcimz.gameStarted = !Uhcimz.gameStarted;

        if (args.length == 1 && args[0].equalsIgnoreCase("reset")) {
            Uhcimz.secs = 50;
            Uhcimz.mins = 19;
            Uhcimz.hrs = 0;
        }
        return false;
    }
}
