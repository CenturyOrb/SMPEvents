package com.rosed.sMPEvents.Commands;

import com.rosed.sMPEvents.EventController;
import com.rosed.sMPEvents.PlayerManager;
import com.rosed.sMPEvents.SMPEvents;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import revxrsal.commands.annotation.Command;

import java.util.UUID;

public class DebugCommands {

    @Command("countdown")
    public void countdown(Player player) {
        new BukkitRunnable() {
            int time = 20 * 60 * 2;
            @Override
            public void run() {
                time -= 20;

                if (time % 1200 == 0) { // each minute
                    player.sendMessage(Component.text(time / 1200 + " minutes left"));
                } else if (time < 1200 && time % 300 == 0 || time < 200) { // each 15 seconds after 1 minute
                    player.sendMessage(Component.text(time / 20 + " seconds left"));
                }

                if (time <= 0) {
                    cancel();
                }
            }
        }.runTaskTimer(SMPEvents.getInstance(), 0, 20);
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
        player.sendMessage(EventController.INSTANCE.getCurrentEvent().getClass().getSimpleName());
    }

    @Command("printstate")
    public void printstate(Player player) {
        player.sendMessage(EventController.INSTANCE.getCurrentEvent().getState().toString());
    }

}
