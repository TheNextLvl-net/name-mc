package net.thenextlvl.namemc.listener;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import net.thenextlvl.namemc.NameMC;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class ConnectionListener {
    private final NameMC plugin;

    public ConnectionListener(NameMC plugin) {
        this.plugin = plugin;
    }

    @Subscribe
    public void onDisconnect(DisconnectEvent event) {
        plugin.cache().remove(event.getPlayer());
    }
}
