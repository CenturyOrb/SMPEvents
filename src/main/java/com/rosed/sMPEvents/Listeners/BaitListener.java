package com.rosed.sMPEvents.Listeners;

import com.rosed.sMPEvents.EventController;
import com.rosed.sMPEvents.Events.Bait;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;

public class BaitListener implements Listener {

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        Bait bait = (Bait) EventController.getCurrentEvent();

        Player player = event.getPlayer();
        // check if the player fishes successfully
        // add point to the player
        if (event.getState() != PlayerFishEvent.State.CAUGHT_FISH) return;
        if (!bait.getPlayers().contains(player.getUniqueId())) return;
        bait.getPlayerPoints().put(player, (short) (bait.getPlayerPoints().get(player) == null ? 1 : bait.getPlayerPoints().get(player) + 1));
        bait.updateBoards();
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1f, 1f);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        /*
        Remove player from scoreboard
        Remove player from players list
         */
        Player player = event.getPlayer();
        EventController.getCurrentEvent().removePlayer(player);
    }

}
