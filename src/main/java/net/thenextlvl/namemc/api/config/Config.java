package net.thenextlvl.namemc.api.config;

public record Config(
        String domain,
        String url,
        long updateCooldown
) {
}
