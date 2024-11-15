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
     * For players to use when want to join lobby. Teleport players to the
     * lobby if the event hasn't started yet. If it already started then
     * teleport the player to the game spawn
     * Some events does not allow players to join during LIVE
     * @param player player command sender
     */
    @Command("eventjoin")
    public void eventJoin(Player player) {
        EventGame eventGame = EventController.getCurrentEvent();
        if (eventGame.getPlayers().contains(player.getUniqueId())) {
            player.sendMessage("Already in event");
            return;
        }

        // Add player to players list
        if (eventGame.addPlayer(player))
            player.sendMessage("Event joined");
        else
            player.sendMessage("Event not joined");
    }

    /**
     * Player attempts to leave lobby
     * @param player player command sender
     */
    @Command("eventleave")
    public void eventLeave(Player player) {
        EventGame eventGame = EventController.getCurrentEvent();
        if (!eventGame.removePlayer(player)) {
            player.sendMessage("You are not in an event");
        }
    }

}
