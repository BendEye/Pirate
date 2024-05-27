import java.util.HashMap;
import java.util.Map;
import com.retrooper.packetevents.PacketEvents;
import com.retrooper.packetevents.event.PacketListener;
import me.systems.bendeye.pirate.manager.fiber.Check;
import me.systems.bendeye.pirate.manager.fiber.ExampleCheck;
import me.systems.bendeye.pirate.manager.fiber.FiberManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PacketManager {

    private JavaPlugin plugin;
    private FiberManager fiberManager;
    private Map<String, PacketListener> packetListeners;

    public PacketManager(JavaPlugin plugin, FiberManager fiberManager) {
        this.plugin = plugin;
        this.fiberManager = fiberManager;
        this.packetListeners = new HashMap<>();
    }

    public void init() {
        // Initialize PacketEvents library
        PacketEvents.get().init(plugin);

        // Initialize your FiberManager
        fiberManager.init();
    }

    public void registerPacketListener(String packetType, PacketListener listener) {
        packetListeners.put(packetType, listener);
        // Register packet listener with PacketEvents library
        PacketEvents.get().registerListener(listener);
    }

    public void unregisterPacketListener(String packetType) {
        PacketListener listener = packetListeners.get(packetType);
        if (listener != null) {
            packetListeners.remove(packetType);
            // Unregister packet listener with RetroOper's PacketEvents library
            PacketEvents.get().unregisterListener(listener);
        }
    }

    public void unregisterAllPacketListeners() {
        for (PacketListener listener : packetListeners.values()) {
            // Unregister all packet listeners with RetroOper's PacketEvents library
            PacketEvents.get().unregisterListener(listener);
        }
        packetListeners.clear();
    }
}
