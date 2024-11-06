package com.rosed.sMPEvents;

import com.rosed.sMPEvents.Events.EventGame;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public enum EventController {
    INSTANCE;

    private BukkitTask eventTimer;
    private EventGame currentEvent = null;

    /**
     * Repeating task that selects random event to run
     */
    EventController() {
        eventTimer = new BukkitRunnable() {
            @Override
            public void run() {
                // select a random event and prepare //
            }
        }.runTaskTimer(SMPEvents.getInstance(), 0, 20 * 60 * 10);
    }

    /**
     * State before the game starts, time for players to join
     * Starts the lobby countdown
     */
    private void prepare() {
        // changes the currentEvent variable
        // change event state
        // Gives 15 minutes for player to join the lobby
        // last 1 minute countdown displays every 15 seconds
        // last 10 seconds countdown displays every 1 second
        // run start after countdown is over (make sure to cancel the task)

    }

    public static void countdown() {
        new BukkitRunnable() {
            @Override
            public void run() {

            }
        }.runTaskTimer(SMPEvents.getInstance(), 0, 20 * 60 * 10);
    }
}
