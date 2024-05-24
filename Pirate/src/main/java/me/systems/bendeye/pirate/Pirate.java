package me.systems.bendeye.pirate;

import org.bukkit.plugin.java.JavaPlugin;

public final class Pirate extends JavaPlugin {


FiberManager fiberManager = new FiberManager();
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        fiberManager.init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
