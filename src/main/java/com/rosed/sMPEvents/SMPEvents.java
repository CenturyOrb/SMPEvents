package com.rosed.sMPEvents;

import com.rosed.sMPEvents.Events.Bait;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SMPEvents extends JavaPlugin {

    private static SMPEvents instance;
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        Listener listener = new Bait();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    
    public static SMPEvents getInstance() { return instance; }
}
