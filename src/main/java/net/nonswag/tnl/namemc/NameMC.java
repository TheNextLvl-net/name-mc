package net.nonswag.tnl.namemc;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.Getter;
import net.nonswag.core.api.annotation.FieldsAreNonnullByDefault;
import net.nonswag.core.api.annotation.MethodsReturnNonnullByDefault;
import net.nonswag.tnl.cloud.Cloud;
import net.nonswag.tnl.namemc.commands.NameMCCommand;
import net.nonswag.tnl.namemc.listeners.ConnectionListener;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@Getter
@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@Plugin(id = "namemc", name = "TNLNameMC", version = "1.0", authors = "NonSwag",
        dependencies = @Dependency(id = "tnlcloud"), url = "https://www.thenextlvl.net")
public class NameMC {
    @Nullable
    private static NameMC instance = null;
    private final ProxyServer server;

    @Inject
    public NameMC(ProxyServer server, org.slf4j.Logger logger) {
        instance = this;
        this.server = server;
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        getServer().getEventManager().register(this, new ConnectionListener());
        Cloud.getInstance().registerCommand("namemc", new NameMCCommand());
    }

    public static NameMC getInstance() {
        assert instance != null;
        return instance;
    }
}
