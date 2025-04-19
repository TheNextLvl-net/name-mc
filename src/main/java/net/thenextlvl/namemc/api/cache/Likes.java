package net.thenextlvl.namemc.api.cache;

import com.velocitypowered.api.proxy.Player;
import net.thenextlvl.namemc.NameMC;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@NullMarked
public class Likes {
    private final HashMap<Player, Map.@Nullable Entry<Long, Boolean>> cache = new HashMap<>();
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final NameMC plugin;

    public Likes(NameMC plugin) {
        this.plugin = plugin;
    }

    public boolean hasLiked(Player player) {
        try {
            var cooldown = plugin.config().updateCooldown();
            var result = cache.computeIfPresent(player, (key, pair) ->
                    System.currentTimeMillis() - pair.getKey() < cooldown ? pair : null);
            if (result != null) return result.getValue();
            var url = "https://api.namemc.com/server/"
                      + plugin.config().domain()
                      + "/likes?profile="
                      + player.getUniqueId().toString();
            var request = HttpRequest.newBuilder(URI.create(url)).build();
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) return false;
            var voted = Boolean.parseBoolean(response.body());
            cache.put(player, Map.entry(System.currentTimeMillis(), voted));
            return voted;
        } catch (IOException | InterruptedException e) {
            plugin.logger().error("Failed to lookup likes", e);
            return false;
        }
    }

    public void remove(Player player) {
        cache.remove(player);
    }
}