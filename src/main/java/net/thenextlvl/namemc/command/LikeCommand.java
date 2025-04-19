package net.thenextlvl.namemc.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import net.thenextlvl.namemc.NameMC;
import org.jspecify.annotations.NullMarked;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@NullMarked
public class LikeCommand {
    public static BrigadierCommand create(NameMC plugin) {
        return new BrigadierCommand(BrigadierCommand.literalArgumentBuilder("namemc")
                .requires(source -> source instanceof Player)
                .executes(context -> execute(context, plugin))
                .build());
    }

    private static int execute(CommandContext<CommandSource> context, NameMC plugin) {
        CompletableFuture.runAsync(() -> {
            var player = (Player) context.getSource();
            plugin.bundle().sendMessage(player, "namemc.guide.header");
            plugin.bundle().sendMessage(player, "namemc.guide");
            plugin.bundle().sendMessage(player, "namemc.guide.website");
            plugin.bundle().sendMessage(player, "namemc.guide.login");
            plugin.bundle().sendMessage(player, "namemc.guide.like");
            plugin.bundle().sendMessage(player, "namemc.guide.verify");
            plugin.bundle().sendMessage(player, plugin.cache().hasLiked(player)
                    ? "namemc.info.verified" : "namemc.info.verification");
            plugin.bundle().sendMessage(player, "namemc.guide.footer");
        }).orTimeout(5, TimeUnit.SECONDS).exceptionally(throwable -> {
            plugin.logger().error("Failed to lookup likes", throwable);
            return null;
        });
        return Command.SINGLE_SUCCESS;
    }
}
