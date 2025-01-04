package com.rosed.sMPEvents.Events;

import com.rosed.sMPEvents.ConfigLocations;
import com.rosed.sMPEvents.EventState;
import com.rosed.sMPEvents.PlayerManager;
import com.rosed.sMPEvents.Utils.MessageUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Parkour extends EventGame {

    Parkour() {
        super();
        spawn = ConfigLocations.PARKOUR;
    }

    @Override
    public void stop() {
        MessageUtil.broadcastMessage(Component.text("Parkour.stop()"));
    }

    @Override
    public boolean addPlayer(Player player) {
        if (state == EventState.LIVE) return false;

        playerUUIDs.add(player.getUniqueId());
        PlayerManager.getPlayerLoc().put(player.getUniqueId(), player.getLocation());
        Location loc = state == EventState.PREPARE ? ConfigLocations.LOBBY : spawn;
        player.teleport(loc);
        player.sendMessage(Component.text("You have joined the " + getClass().getSimpleName() + " event").color(NamedTextColor.GOLD));
        return true;
    }

    @Override
    protected void startScoreboards() {

    }

    @Override
    public void updateBoards() {

    }


}
