package net.nonswag.tnl.namemc.api;

import com.velocitypowered.api.proxy.Player;
import net.nonswag.core.api.logger.Logger;
import net.nonswag.core.api.object.Pair;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.UUID;

public final class Vote {

    @Nonnull
    public static final String SERVER_LIKES = "https://api.namemc.com/server/thenextlvl.net/likes";
    @Nonnull
    public static final String PROFILE_LIKES = SERVER_LIKES + "?profile=";

    @Nonnull
    public static final HashMap<UUID, Pair<Long, Boolean>> CACHE = new HashMap<>();

    public static boolean hasVoted(@Nonnull Player player) {
        return hasVoted(player.getUniqueId());
    }

    public static boolean hasVoted(@Nonnull UUID player) {
        try {
            Pair<Long, Boolean> pair = CACHE.get(player);
            if (pair != null) {
                if (System.currentTimeMillis() - pair.getKey() < 60000) return Boolean.TRUE.equals(pair.getValue());
                else CACHE.remove(player);
            }
            URL url = new URL(PROFILE_LIKES + player);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            boolean voted = Boolean.parseBoolean(bufferedReader.readLine());
            CACHE.put(player, new Pair<>(System.currentTimeMillis(), voted));
            return voted;
        } catch (Exception e) {
            Logger.error.println(e);
        }
        return false;
    }
}
