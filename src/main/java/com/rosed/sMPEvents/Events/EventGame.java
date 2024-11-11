package com.rosed.sMPEvents.Events;

import com.rosed.sMPEvents.EventState;
import com.rosed.sMPEvents.SMPEvents;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class EventGame {

    protected List<UUID> playerUUIDs;
    protected List<Listener> listeners;
    protected EventState state;

    EventGame() {
        playerUUIDs = new ArrayList<>();
        listeners = new ArrayList<>();
        state = EventState.PREPARE;
    }

    public abstract void start();

    public abstract void stop();

    public void registerListeners() {
        listeners.forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, SMPEvents.getInstance()));
    };

    public void unregisterListeners() {
        listeners.forEach(HandlerList::unregisterAll);
    };

    public List<UUID> getPlayers() { return playerUUIDs; }
    public abstract boolean addPlayer(Player player);
    public void removePlayer(Player player) { playerUUIDs.remove(player.getUniqueId()); }

    public EventState getState() { return state; }
    public void setState(EventState state) { this.state = state; }

}
