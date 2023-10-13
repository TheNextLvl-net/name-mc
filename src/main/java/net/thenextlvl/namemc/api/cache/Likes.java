package net.thenextlvl.namemc.api.cache;

import com.velocitypowered.api.proxy.Player;
import lombok.RequiredArgsConstructor;
import net.thenextlvl.namemc.NameMC;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.WeakHashMap;

@RequiredArgsConstructor
public class Likes extends WeakHashMap<Player, Map.Entry<Long, Boolean>> {
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final NameMC plugin;

    public boolean hasLiked(Player player) {
        try {
            var cooldown = plugin.config().updateCooldown();
            var result = computeIfPresent(player, (key, pair) ->
                    System.currentTimeMillis() - pair.getKey() < cooldown ? pair : null);
            if (result != null) return result.getValue();
            var url = "https://api.namemc.com/server/"
                    + plugin.config().domain()
                    + "/likes?profile="
                    + player.getUsername();
            var request = HttpRequest.newBuilder(URI.create(url)).build();
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) return false;
            var voted = Boolean.parseBoolean(response.body());
            put(player, Map.entry(System.currentTimeMillis(), voted));
            return voted;
        } catch (IOException | InterruptedException e) {
            plugin.logger().error("Failed to lookup likes", e);
            return false;
        }
    }
}