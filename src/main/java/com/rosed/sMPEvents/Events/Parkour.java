package com.rosed.sMPEvents.Events;

import com.rosed.sMPEvents.EventState;
import com.rosed.sMPEvents.Utils.Util;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class Parkour extends EventGame {

    @Override
    public void start() {
        Util.broadcastMessage(Component.text("Parkour.start()"));
    }

    @Override
    public void stop() {
        Util.broadcastMessage(Component.text("Parkour.stop()"));
    }

    @Override
    public boolean addPlayer(Player player) {
        if (state == EventState.LIVE) return false;
        player.sendMessage("Parkour.addPlayer(" + player.getName() + ")");
        playerUUIDs.add(player.getUniqueId());
        return true;
    }
}
