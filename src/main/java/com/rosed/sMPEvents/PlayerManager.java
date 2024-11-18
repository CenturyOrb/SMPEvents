package com.rosed.sMPEvents;

import org.bukkit.Location;
import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {

    // Track player locations prior to joining event
    private static HashMap<UUID, Location> playerLoc = new HashMap<UUID, Location>();

    public static HashMap<UUID, Location> getPlayerLoc() { return playerLoc; }

    public static void clear() { playerLoc.clear();}

}
