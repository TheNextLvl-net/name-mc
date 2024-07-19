package net.thenextlvl.namemc.command;

import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import lombok.RequiredArgsConstructor;
import net.thenextlvl.namemc.NameMC;

@RequiredArgsConstructor
public class LikeCommand implements SimpleCommand {
    private final NameMC plugin;

    @Override
    public void execute(Invocation invocation) {
        if (!(invocation.source() instanceof Player player))
            plugin.bundle().sendMessage(invocation.source(), "command.sender");
        else new Thread(() -> {
            plugin.bundle().sendMessage(player, "namemc.guide.header");
            plugin.bundle().sendMessage(player, "namemc.guide");
            plugin.bundle().sendMessage(player, "namemc.guide.website");
            plugin.bundle().sendMessage(player, "namemc.guide.login");
            plugin.bundle().sendMessage(player, "namemc.guide.like");
            plugin.bundle().sendMessage(player, "namemc.guide.verify");
            var message = plugin.cache().hasLiked(player) ? "namemc.info.verified" : "namemc.info.verification";
            plugin.bundle().sendMessage(player, message);
            plugin.bundle().sendMessage(player, "namemc.guide.footer");
        }, "name-mc verification").start();
    }
}
