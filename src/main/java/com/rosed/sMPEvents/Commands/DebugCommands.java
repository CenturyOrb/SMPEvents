package com.rosed.sMPEvents.Commands;

import com.rosed.sMPEvents.EventController;
import com.rosed.sMPEvents.PlayerManager;
import com.rosed.sMPEvents.SMPEvents;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.command.CommandActor;
import java.util.List;
import java.util.UUID;

public class DebugCommands {

    @Command("countdown")
    public void countdown(Player player) {
        new BukkitRunnable() {
            int time = 20 * 60 * 2;
            @Override
            public void run() {
                time -= 20;
                if (time <= 0)
                    cancel();
                else if (time % 1200 == 0)
                    player.sendMessage(Component.text(time / 1200 + " minutes left"));
                else if ((time < 1200 && time % 300 == 0) || time < 200)
                    player.sendMessage(Component.text(time / 20 + " seconds left"));
            }
        }.runTaskTimer(SMPEvents.getInstance(), 0, 20);
    }

    @Command("scoreboardtest")
    public void scoreboardtest(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective obj = board.registerNewObjective("test board", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.GREEN + "test board");

        Score score = obj.getScore(ChatColor.YELLOW + "bewger");
        score.setScore(1);

        Score space = obj.getScore("");
        space.setScore(2);

        Team dynamic = board.registerNewTeam("dynamic");
        dynamic.addEntry(ChatColor.RED.toString());
        obj.getScore(ChatColor.RED.toString()).setScore(3);

        player.setScoreboard(board);
    }

    @Command("printloc")
    public void printloc(Player player) {
        player.sendMessage(PlayerManager.getPlayerLoc().toString());
    }

    @Command("printlist")
    public void printlist(Player player) {
        player.sendMessage("printlist: ");
        for (UUID uuid : EventController.getCurrentEvent().getPlayers()) {
            player.sendMessage(Component.text(Bukkit.getPlayer(uuid).getName()));
        }
    }

    @Command("printevent")
    public void printevent(Player player) {
        player.sendMessage(EventController.getCurrentEvent().getClass().getSimpleName());
    }

    @Command("printstate")
    public void printstate(Player player) {
        player.sendMessage(EventController.getCurrentEvent().getState().toString());
    }

    @Command("printlistener")
    public void printlistener(CommandActor sender) {
        List<Listener> listeners = EventController.getCurrentEvent().getListeners();
        listeners.forEach(listener -> SMPEvents.getInstance().getLogger().info(listener.getClass().getSimpleName()));
    }

}
