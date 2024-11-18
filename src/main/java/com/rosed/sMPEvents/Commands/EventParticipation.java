package com.rosed.sMPEvents.Commands;

import com.rosed.sMPEvents.EventController;
import com.rosed.sMPEvents.Events.EventGame;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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
            player.sendMessage(Component.text("Already in " + eventGame.getClass().getSimpleName() + " event").color(NamedTextColor.RED));
            return;
        }

        eventGame.addPlayer(player);
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