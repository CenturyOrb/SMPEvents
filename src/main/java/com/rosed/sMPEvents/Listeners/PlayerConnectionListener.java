package com.rosed.sMPEvents.Listeners;

import com.rosed.sMPEvents.EventController;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {

    @EventHandler
    public void onPlayerLeaveEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (EventController.getCurrentEvent() == null) return;
        EventController.getCurrentEvent().removePlayer(player);
    }

}
