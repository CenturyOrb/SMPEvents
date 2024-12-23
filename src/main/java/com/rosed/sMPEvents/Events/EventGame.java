package com.rosed.sMPEvents.Events;

import com.rosed.sMPEvents.ConfigLocations;
import com.rosed.sMPEvents.EventState;
import com.rosed.sMPEvents.PlayerManager;
import com.rosed.sMPEvents.SMPEvents;
import com.rosed.sMPEvents.Utils.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
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
    protected Location spawn;

    EventGame() {
        playerUUIDs = new ArrayList<>();
        listeners = new ArrayList<>();
        state = EventState.PREPARE;
    }

    /**
     * Starts the event
     */
    public void start() {
        registerListeners();
        state = EventState.LIVE;
        Util.broadcastMessage(Component.text(getClass().getSimpleName() + " has started"));
    }

    public abstract void stop();

    public void registerListeners() {
        listeners.forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, SMPEvents.getInstance()));
    };

    public void unregisterListeners() {
        listeners.forEach(HandlerList::unregisterAll);
    };

    public List<UUID> getPlayers() { return playerUUIDs; }

    /**
     * Adds player to list by default. Some events handles adding players
     * differently by disallowing players to join if the event is LIVE
     * @param player player to be added
     * @return true if successfully added player
     */
    public boolean addPlayer(Player player) {
        playerUUIDs.add(player.getUniqueId());
        PlayerManager.getPlayerLoc().put(player.getUniqueId(), player.getLocation());
        Location loc = state == EventState.PREPARE ? ConfigLocations.LOBBY : spawn;
        player.teleport(loc);
        return true;
    }

    /**
     * Attempts to remove player from event player list
     * @param player player to remove
     * @return true if successful, false if player isn't in the event
     */
    public boolean removePlayer(Player player) {
        if (!playerUUIDs.contains(player.getUniqueId())) return false;
        player.teleport(PlayerManager.getPlayerLoc().get(player.getUniqueId()));
        playerUUIDs.remove(player.getUniqueId());
        PlayerManager.getPlayerLoc().remove(player.getUniqueId());
        player.sendMessage(Component.text("You have left " + this.getClass().getSimpleName() + " event").color(NamedTextColor.GOLD));
        return true;
    }

    public EventState getState() { return state; }
    public void setState(EventState state) { this.state = state; }

}
