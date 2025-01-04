package com.rosed.sMPEvents.Utils;

import com.rosed.sMPEvents.EventController;
import com.rosed.sMPEvents.Events.EventGame;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.apache.commons.lang3.ArrayUtils;
import org.bukkit.Bukkit;

public class MessageUtil {

    private static int[] intervals = {1800, 900, 600, 300, 60, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

    public static void broadcastCountdown(EventGame event, int secondsPassed) {
        String eventName = event.getClass().getSimpleName();
        int secondsRemain = EventController.getEventCountdownSeconds() - secondsPassed;

        if (!ArrayUtils.contains(intervals, secondsRemain)) return;
        if (secondsRemain > 59) {
            broadcastMessage(Component.text(eventName + " starting in " + secondsRemain / 60 + " minutes!").color(NamedTextColor.GOLD));
        } else {
            broadcastMessage(Component.text(eventName + " starting in " + secondsRemain + " seconds!").color(NamedTextColor.GOLD));
        }
    }

    public static void broadcastMessage(TextComponent message) {
        Bukkit.getServer().broadcast(message);
    }

}
