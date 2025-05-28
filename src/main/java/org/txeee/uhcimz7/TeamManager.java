package org.txeee.uhcimz7;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TeamManager {
    private final Scoreboard scoreboard;

    public TeamManager() {
        this.scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
    }

    public void createTeam(String teamName, ChatColor color) {
        Team team = scoreboard.getTeam(teamName);
        if (team == null) {
            team = scoreboard.registerNewTeam(teamName);
        }

        team.setPrefix("⭐ " + color.toString());
        team.setColor(color);
        team.setDisplayName(color + teamName);
        team.setOption(Team.Option.DEATH_MESSAGE_VISIBILITY, Team.OptionStatus.ALWAYS);
        team.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.ALWAYS);
        team.setAllowFriendlyFire(false);
    }

    public void addPlayerToTeam(Player player, String teamName) {
        Team team = scoreboard.getTeam(teamName);
        if (team != null) {
            team.addEntry(player.getName());
            player.setScoreboard(scoreboard);
        }
    }

    public void removePlayerFromTeam(Player player) {
        for (Team team : scoreboard.getTeams()) {
            if (team.hasEntry(player.getName())) {
                team.removeEntry(player.getName());
            }
        }
    }

    public void deleteTeam(String teamName) {
        Team team = scoreboard.getTeam(teamName);
        if (team != null) {
            team.unregister();
        }
    }
}
