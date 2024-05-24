package me.systems.bendeye.pirate.listener;
import com.retrooper.packetevents.PacketEvents;
import com.retrooper.packetevents.packettype.PacketType;
import com.retrooper.packetevents.packetwrappers.in.useentity.WrappedPacketInUseEntity;
private class PacketListener implements com.retrooper.packetevents.event.PacketListener {

        @Override
        public void onPacketReceive(com.retrooper.packetevents.packetwrappers.PacketWrapper packetWrapper) {
            if (packetWrapper instanceof WrappedPacketInUseEntity) {
                WrappedPacketInUseEntity wrapper = (WrappedPacketInUseEntity) packetWrapper;
                Player player = wrapper.getPlayer();
}
}
}
