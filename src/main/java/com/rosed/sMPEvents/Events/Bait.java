package com.rosed.sMPEvents.Events;

import com.rosed.sMPEvents.ConfigLocations;
import com.rosed.sMPEvents.Utils.Util;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;

public class Bait extends EventGame {

    protected Location spawn = ConfigLocations.BAIT;

    @Override
    public void stop() {
        Util.broadcastMessage(Component.text("Bait.end()"));
    }
}
