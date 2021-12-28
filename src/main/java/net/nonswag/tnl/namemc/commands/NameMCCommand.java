package net.nonswag.tnl.namemc.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.nonswag.tnl.cloud.Cloud;
import net.nonswag.tnl.cloud.api.permission.Command;
import net.nonswag.tnl.namemc.api.Vote;

import javax.annotation.Nonnull;

public class NameMCCommand extends Command {

    @Override
    public boolean canUse(@Nonnull CommandSource commandSource) {
        return true;
    }

    @Override
    public void execute(@Nonnull SimpleCommand.Invocation invocation) {
        CommandSource source = invocation.source();
        if (source instanceof Player player) {
            new Thread(() -> {
                boolean voted = Vote.hasVoted(player);
                player.sendMessage(Component.text("§8§m" + " ".repeat(60)));
                player.sendMessage(Component.text("§8| §6§lNameMC §8x §7§oHow to like us on NameMC\n"));
                player.sendMessage(Component.text("§6§l1. §7Go to ").append(Component.text("§e§nhttps://www.thenextlvl.net/namemc").clickEvent(ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL, "https://www.thenextlvl.net/namemc"))));
                player.sendMessage(Component.text("§6§l2. §7Register or login into your NameMC account"));
                player.sendMessage(Component.text("§6§l3. §7Click the thumbs up icon to like"));
                player.sendMessage(Component.text("§6§l4. §7Execute §e/namemc§7 again to verify your like\n"));
                if (voted) player.sendMessage(Component.text("§8» §7You already liked our server"));
                player.sendMessage(Component.text("§8§m" + " ".repeat(60)));
            }).start();
        } else {
            Cloud.getInstance().sendMessage(source, "%prefix% §cThis is a player command");
        }
    }
}
