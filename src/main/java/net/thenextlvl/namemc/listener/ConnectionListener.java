package net.thenextlvl.namemc.listener;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import lombok.RequiredArgsConstructor;
import net.thenextlvl.namemc.NameMC;

@RequiredArgsConstructor
public class ConnectionListener {
    private final NameMC plugin;

    @Subscribe
    public void onDisconnect(DisconnectEvent event) {
        plugin.cache().remove(event.getPlayer());
    }
}
