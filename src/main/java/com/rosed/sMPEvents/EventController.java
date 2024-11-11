package com.rosed.sMPEvents;

import com.rosed.sMPEvents.Events.Event;
import com.rosed.sMPEvents.Events.EventGame;
import com.rosed.sMPEvents.Utils.Util;
import net.kyori.adventure.text.Component;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public enum EventController {
    INSTANCE;

    private BukkitTask eventTimer;
    private static EventGame currentEvent = null;
    private static final int eventCountdownSeconds = 60 * 30; // testing with 30 minutes

    /**
     * Repeating task that selects random event to run
     */
    EventController() {
        eventTimer = new BukkitRunnable() {
            @Override
            public void run() {
                // select a random event and prepare //
                currentEvent = Event.createRandomEvent();
                Util.broadcastMessage(Component.text("Created random event"));
                prepare();
                Util.broadcastMessage(Component.text("hi"));
            }
        }.runTaskTimer(SMPEvents.getInstance(), 0, 20 * 60 * 60); // testing with 60 minutes
    }

    /**
     * State before the game starts, time for players to join
     * Starts the lobby countdown
     */
    private void prepare() {
        // change event state
        currentEvent.setState(EventState.PREPARE);
        /**
         * Gives 30 minutes for players to join the lobby
         * last 15, 10, 5 minutes for player to join the lobby
         * last 1 minute
         * last 1-10 seconds
         * run start after countdown is over (make sure to cancel the task)
         */
        countdown();

    }

    public static void countdown() {
        new BukkitRunnable() {
            int secondsPassed = -1;
            @Override
            public void run() {
                secondsPassed++; // 1 second passes
                if (secondsPassed == eventCountdownSeconds) {
                    // teleport players to the event map
                    currentEvent.start();
                    Util.broadcastMessage(Component.text("Teleport players to map"));

                    // stop the countdown
                    cancel();
                } else {
                    // display server wide countdown
                    Util.broadcastCountdown(currentEvent, secondsPassed);
                }
            }
        }.runTaskTimer(SMPEvents.getInstance(), 0,20);
    }

    public static EventGame getCurrentEvent() { return currentEvent; }

    public static int getEventCountdownSeconds() { return eventCountdownSeconds; }
}
