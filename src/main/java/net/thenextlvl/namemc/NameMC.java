package net.thenextlvl.namemc;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import core.file.format.GsonFile;
import core.i18n.file.ComponentBundle;
import core.io.IO;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.thenextlvl.namemc.api.cache.Likes;
import net.thenextlvl.namemc.api.config.Config;
import net.thenextlvl.namemc.command.LikeCommand;
import net.thenextlvl.namemc.listener.ConnectionListener;
import org.jspecify.annotations.NullMarked;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.Locale;

@Plugin(
        id = "namemc",
        name = "NameMC",
        version = "1.0.2",
        authors = "NonSwag",
        url = "https://thenextlvl.net"
)
@NullMarked
public class NameMC {
    private final ProxyServer server;
    private final Logger logger;

    private final Config config;
    private final ComponentBundle bundle;
    private final Likes cache;

    @Inject
    public NameMC(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
        this.server = server;
        this.logger = logger;
        var dir = dataDirectory.toFile();
        this.config = new GsonFile<>(
                IO.of(dir, "config.json"),
                new Config("example.com", "https://example.com/namemc", 60000)
        ).saveIfAbsent().getRoot();
        var key = Key.key("namemc", "translations");
        var translations = dataDirectory.resolve("translations");
        this.bundle = ComponentBundle.builder(key, translations)
                .placeholder("prefix", "prefix")
                .resource("namemc.properties", Locale.US)
                .resource("namemc_german.properties", Locale.GERMANY)
                .miniMessage(MiniMessage.builder().tags(TagResolver.resolver(
                        TagResolver.standard(),
                        Placeholder.parsed("url", config.url())
                )).build()).build();
        this.cache = new Likes(this);
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        server.getEventManager().register(this, new ConnectionListener(this));
        var meta = server.getCommandManager().metaBuilder("like").plugin(this).build();
        server.getCommandManager().register(meta, LikeCommand.create(this));
    }

    public ComponentBundle bundle() {
        return bundle;
    }

    public Config config() {
        return config;
    }

    public Likes cache() {
        return cache;
    }

    public Logger logger() {
        return logger;
    }
}
