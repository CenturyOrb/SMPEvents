package com.rosed.sMPEvents.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;

public class BaitListener implements Listener {

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(event.getState().toString());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

    }

}
