package com.rosed.sMPEvents.Commands;

import com.rosed.sMPEvents.EventController;
import com.rosed.sMPEvents.EventState;
import com.rosed.sMPEvents.Events.EventGame;
import com.rosed.sMPEvents.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;

public class EventParticipation {

    /**
     * For players to use when want to join lobby
     * if the game already started then that depends on game specific
     * implementation
     * @param player player command sender
     */
    @Command("eventjoin")
    public void eventJoin(Player player) {
        EventGame eventGame = EventController.getCurrentEvent();
        if (eventGame.getPlayers().contains(player.getUniqueId())) {
            player.sendMessage("Already in event");
            return;
        }
        // Save where the player location is currently
        PlayerManager.getPlayerLoc().put(player.getUniqueId(), player.getLocation());
        // Add player to players list
        if (!eventGame.addPlayer(player)) {
            player.sendMessage(eventGame.getClass().getSimpleName() + " has already started. Cannot join");
            return;
        }
        // save player current location
        PlayerManager.getPlayerLoc().put(player.getUniqueId(), player.getLocation());
        // teleport to wherever the lobby/event is if event allows
        if (eventGame.getState() == EventState.PREPARE)
            player.teleport(Bukkit.getWorld("Lobby").getSpawnLocation());
        else
            player.sendMessage("Teleport to " + eventGame.getClass().getSimpleName() + " arena");
    }

    @Command("eventleave")
    public void eventLeave(Player player) {
        // Teleport player to where they were before they joined the event
        // Add player to the players list
        EventGame eventGame = EventController.getCurrentEvent();
        if (!eventGame.getPlayers().contains(player.getUniqueId())) {
            player.sendMessage("You are not in an event");
            return;
        }

        player.teleport(PlayerManager.getPlayerLoc().get(player.getUniqueId()));
        eventGame.getPlayers().remove(player.getUniqueId());
        PlayerManager.getPlayerLoc().remove(player.getUniqueId());
    }

}
