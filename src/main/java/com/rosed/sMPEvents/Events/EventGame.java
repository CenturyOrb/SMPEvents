package com.rosed.sMPEvents.Events;

import com.rosed.sMPEvents.EventState;
import com.rosed.sMPEvents.SMPEvents;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.List;

@Data
public abstract class EventGame {

    private List<Player> players;
    private List<Listener> listeners;
    private EventState state;

    public void start() {}
    public void stop() {}

    public void registerListeners() {
        listeners.forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, SMPEvents.getInstance()));
    };

    public void unregisterListeners() {
        listeners.forEach(HandlerList::unregisterAll);
    };

}
