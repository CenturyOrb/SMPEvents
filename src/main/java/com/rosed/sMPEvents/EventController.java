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
    private static final int eventCountdownSeconds = 60 * 5;

    /**
     * Repeating task that selects random event to run
     */
    EventController() {
        eventTimer = new BukkitRunnable() {
            @Override
            public void run() {
                currentEvent = Event.createRandomEvent();
                Util.broadcastMessage(Component.text("Created random event"));
                prepare();
            }
        }.runTaskTimer(SMPEvents.getInstance(), 0, 20 * 60 * 10);
    }

    /**
     * State before the game starts, time for players to join
     * Starts the lobby countdown
     */
    private void prepare() {
        // change event state
        currentEvent.setState(EventState.PREPARE);
        countdown();
    }

    /**
     * Starts 30 minutes countdown for players to join the server
     */
    public static void countdown() {
        new BukkitRunnable() {
            int secondsPassed = -1;
            @Override
            public void run() {
                secondsPassed++; // 1 second passes
                if (secondsPassed == eventCountdownSeconds) {
                    currentEvent.start();
                    Util.broadcastMessage(Component.text("Teleport players to map"));
                    cancel();
                } else {
                    Util.broadcastCountdown(currentEvent, secondsPassed);
                }
            }
        }.runTaskTimer(SMPEvents.getInstance(), 0,20);
    }

    public static EventGame getCurrentEvent() { return currentEvent; }

    public static int getEventCountdownSeconds() { return eventCountdownSeconds; }
}
