package net.nonswag.tnl.namemc.listeners;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import net.nonswag.tnl.namemc.api.Vote;

import javax.annotation.Nonnull;

public class ConnectionListener {

    @Subscribe
    public void onJoin(@Nonnull DisconnectEvent event) {
        Vote.CACHE.remove(event.getPlayer().getUniqueId());
    }
}
