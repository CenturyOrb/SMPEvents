package com.rosed.sMPEvents;

import com.rosed.sMPEvents.Commands.DebugCommands;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.Lamp;
import revxrsal.commands.bukkit.BukkitLamp;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;

public final class SMPEvents extends JavaPlugin {

    private static SMPEvents instance;
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        startEventController();
        registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Initiates EventController
     * @return EventController
     */
    private EventController startEventController() { return EventController.INSTANCE; }

    /**
     * Registers all commands
     */
    private void registerCommands() {
        Lamp<BukkitCommandActor> lamp = BukkitLamp.builder(this).build();
        lamp.register(new DebugCommands());
    }


    public static SMPEvents getInstance() { return instance; }
}
