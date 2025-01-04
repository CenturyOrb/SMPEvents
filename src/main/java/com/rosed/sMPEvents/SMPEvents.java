package com.rosed.sMPEvents;

import com.rosed.sMPEvents.Commands.DebugCommands;
import com.rosed.sMPEvents.Commands.EventParticipation;
import com.rosed.sMPEvents.Utils.SideBar.FastBoard;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
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
        getLogger().info("7");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Initiates EventController
     * @return EventController
     */
    private void startEventController() { EventController eventController = EventController.INSTANCE; }

    /**
     * Registers all commands
     */
    private void registerCommands() {
        Lamp<BukkitCommandActor> lamp = BukkitLamp.builder(this).build();
        lamp.register(new DebugCommands());
        lamp.register(new EventParticipation());
    }

    public static SMPEvents getInstance() { return instance; }

}