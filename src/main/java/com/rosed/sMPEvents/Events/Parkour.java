package com.rosed.sMPEvents.Events;

import com.rosed.sMPEvents.ConfigLocations;
import com.rosed.sMPEvents.EventState;
import com.rosed.sMPEvents.PlayerManager;
import com.rosed.sMPEvents.Utils.Util;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Parkour extends EventGame {

    @Override
    public void stop() {
        Util.broadcastMessage(Component.text("Parkour.stop()"));
    }

    @Override
    public boolean addPlayer(Player player) {
        if (state == EventState.LIVE) return false;

        playerUUIDs.add(player.getUniqueId());
        PlayerManager.getPlayerLoc().put(player.getUniqueId(), player.getLocation());
        Location loc = state == EventState.PREPARE ? ConfigLocations.LOBBY : spawn;
        player.teleport(loc);
        return true;
    }
}
