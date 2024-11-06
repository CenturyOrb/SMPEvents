package com.rosed.sMPEvents.Commands;

import com.rosed.sMPEvents.EventController;
import com.rosed.sMPEvents.Events.Event;
import com.rosed.sMPEvents.Events.EventGame;
import com.rosed.sMPEvents.SMPEvents;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import revxrsal.commands.annotation.Command;

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

    @Command("randomevent")
    public void randomEvent(Player player) {
        try  {
            Event randomEvent = Event.getRandomEvent();
            Bukkit.getServer().broadcast(Component.text("Randomly selected event: " + randomEvent));

            EventGame eventInstance = (EventGame) randomEvent.getEventClass().getDeclaredConstructor().newInstance();
            Bukkit.getServer().broadcast(Component.text("Created instance: "  + eventInstance));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
