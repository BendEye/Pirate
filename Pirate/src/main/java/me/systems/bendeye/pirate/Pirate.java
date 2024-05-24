package me.systems.bendeye.pirate;


import com.retrooper.packetevents.PacketEvents;
import com.retrooper.packetevents.packettype.PacketType;
import com.retrooper.packetevents.packetwrappers.in.useentity.WrappedPacketInUseEntity;
import org.bukkit.plugin.java.JavaPlugin;

public final class Pirate extends JavaPlugin {


FiberManager fiberManager = new FiberManager();
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        PacketEvents.getSettings().setServer(this).setGlobalClick(true).setPlayerEventListener(new PacketListener()).inject();
        fiberManager.init();
        
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
