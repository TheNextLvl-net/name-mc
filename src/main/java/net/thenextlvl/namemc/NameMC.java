package net.thenextlvl.namemc;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import core.api.file.format.GsonFile;
import core.i18n.file.ComponentBundle;
import lombok.Getter;
import lombok.experimental.Accessors;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.thenextlvl.namemc.api.cache.Likes;
import net.thenextlvl.namemc.command.LikeCommand;
import net.thenextlvl.namemc.api.config.Config;
import net.thenextlvl.namemc.listener.ConnectionListener;
import org.slf4j.Logger;

import java.io.File;
import java.nio.file.Path;
import java.util.Locale;

@Getter
@Plugin(
        id = "namemc",
        name = "NameMC",
        version = "1.0.2",
        authors = "NonSwag",
        url = "https://thenextlvl.net"
)
@Accessors(fluent = true)
public class NameMC {
    private final ProxyServer server;
    private final Logger logger;
    private final File dataDirectory;

    private final Config config;
    private final ComponentBundle bundle;
    private final Likes cache;

    public NameMC(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
        this.server = server;
        this.logger = logger;
        this.dataDirectory = dataDirectory.toFile();
        this.config = new GsonFile<>(
                new File(dataDirectory(), "config.json"),
                new Config("example.com", 60000)
        ).saveIfAbsent().getRoot();
        this.bundle = new ComponentBundle(
                new File(dataDirectory(), "translations"),
                audience -> audience instanceof Player player
                        ? player.getPlayerSettings().getLocale()
                        : Locale.US)
                .register("namemc", Locale.US)
                .register("namemc_german", Locale.GERMANY)
                .fallback(Locale.US);
        bundle().miniMessage(MiniMessage.builder().tags(TagResolver.resolver(
                TagResolver.standard(),
                Placeholder.component("prefix", bundle().component(Locale.US, "prefix"))
        )).build());
        this.cache = new Likes(this);
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        server().getEventManager().register(this, new ConnectionListener(this));
        server().getCommandManager().register("namemc", new LikeCommand(this), "like");
    }
}
