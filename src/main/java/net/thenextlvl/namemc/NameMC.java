package net.thenextlvl.namemc;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import core.annotation.FieldsAreNonnullByDefault;
import core.annotation.MethodsReturnNonnullByDefault;
import core.annotation.ParametersAreNonnullByDefault;
import core.api.file.format.GsonFile;
import lombok.Getter;
import net.thenextlvl.namemc.command.LikeCommand;
import net.thenextlvl.namemc.util.Config;
import net.thenextlvl.namemc.util.Placeholders;

import java.io.File;

@Getter
@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@Plugin(id = "namemc", name = "NameMC", version = "1.0.0", authors = "NonSwag", url = "https://thenextlvl.net")
public class NameMC {
    public static final Config CONFIG;

    static {
        var file = new GsonFile<>(
                new File("plugins/NameMC", "config.json"),
                new Config("example.com", 60000)
        );
        if (!file.getFile().isFile()) file.save();
        CONFIG = file.getRoot();
        Placeholders.init();
    }

    private final ProxyServer server;

    @Inject
    public NameMC(ProxyServer server, org.slf4j.Logger logger) {
        this.server = server;
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        getServer().getCommandManager().register("namemc", new LikeCommand(), "like");
    }
}
