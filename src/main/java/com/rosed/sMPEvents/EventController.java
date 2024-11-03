package com.rosed.sMPEvents;

import com.rosed.sMPEvents.Events.Event;
import com.rosed.sMPEvents.Utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public enum EventController {
    INSTANCE;

    private BukkitTask eventTimer;
    private Event currentEvent = null;
    private EventState state = EventState.WAITING;

    EventController() {
        eventTimer = new BukkitRunnable() {
            @Override
            public void run() {
                // select a random event and prepare//
                Bukkit.broadcastMessage("EventController has selected a new Event");
                Bukkit.broadcastMessage("Selected random event: " + selectRandomEvent().toString());
            }
        }.runTaskTimer(SMPEvents.getInstance(), 0, 20 * 60 * 1);
    }

    private Event selectRandomEvent() {
        return Util.getRandomEnum(Event.class);
    }

    private void prepare() {

    }
}
