package net.thenextlvl.namemc.util;

import com.velocitypowered.api.proxy.Player;
import core.annotation.FieldsAreNonnullByDefault;
import core.annotation.MethodsReturnNonnullByDefault;
import core.annotation.ParametersAreNonnullByDefault;
import net.thenextlvl.namemc.NameMC;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.WeakHashMap;

@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public final class Like {
    public static final String SERVER_LIKES = "https://api.namemc.com/server/" + NameMC.CONFIG.domain() + "/likes";
    public static final String PROFILE_LIKES = SERVER_LIKES + "?profile=";

    public static final Map<Player, Map.Entry<Long, Boolean>> CACHE = new WeakHashMap<>();

    public static boolean hasLiked(Player player) {
        try {
            Map.Entry<Long, Boolean> pair = CACHE.get(player);
            if (pair != null) {
                long cooldown = NameMC.CONFIG.updateCooldown();
                if (System.currentTimeMillis() - pair.getKey() < cooldown) return pair.getValue();
                else CACHE.remove(player);
            }
            URL url = new URL(PROFILE_LIKES + player);
            URLConnection urlConnection = url.openConnection();
            try (InputStream input = urlConnection.getInputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
                boolean voted = Boolean.parseBoolean(reader.readLine());
                CACHE.put(player, Map.entry(System.currentTimeMillis(), voted));
                return voted;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
