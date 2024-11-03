package com.rosed.sMPEvents;

import org.bukkit.plugin.java.JavaPlugin;

public final class SMPEvents extends JavaPlugin {

    private static SMPEvents instance;
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        startEventController();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private EventController startEventController() { return EventController.INSTANCE; }

    public static SMPEvents getInstance() { return instance; }
}
