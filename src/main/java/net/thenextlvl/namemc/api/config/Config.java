package net.thenextlvl.namemc.api.config;

import org.jspecify.annotations.NullMarked;

@NullMarked
public record Config(
        String domain,
        String url,
        long updateCooldown
) {
}
