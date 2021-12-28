package net.nonswag.tnl.namemc;

import com.google.inject.Inject;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import net.nonswag.tnl.cloud.Cloud;
import net.nonswag.tnl.namemc.commands.NameMCCommand;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Plugin(id = "namemc", name = "TNLNameMC", version = "1.0", authors = "NonSwag",
        dependencies = @Dependency(id = "tnlcloud"), url = "https://www.thenextlvl.net")
public class NameMC {

    @Nullable
    private static NameMC instance = null;

    @Nonnull
    private final ProxyServer server;

    @Inject
    public NameMC(@Nonnull ProxyServer server, @Nonnull org.slf4j.Logger logger) {
        setInstance(this);
        this.server = server;
        Cloud.getInstance().registerCommand("namemc", new NameMCCommand());
    }

    @Nonnull
    public ProxyServer getServer() {
        return server;
    }

    @Nonnull
    public static NameMC getInstance() {
        assert instance != null;
        return instance;
    }

    private static void setInstance(@Nonnull NameMC instance) {
        NameMC.instance = instance;
    }
}
