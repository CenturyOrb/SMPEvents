package com.rosed.sMPEvents.Events;

import com.rosed.sMPEvents.Utils.Util;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class Bait extends EventGame {

    @Override
    public void start() {
        Util.broadcastMessage(Component.text("Bait.start()"));
    }

    @Override
    public void stop() {
        Util.broadcastMessage(Component.text("Bait.end()"));
    }

    @Override
    public boolean addPlayer(Player player) {
        player.sendMessage(Component.text("Bait.addPlayer(" + player.getName() + ")"));
        playerUUIDs.add(player.getUniqueId());
        return true;
    }
}
