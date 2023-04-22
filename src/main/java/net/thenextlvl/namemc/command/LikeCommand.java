package net.thenextlvl.namemc.command;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import core.annotation.ParametersAreNonnullByDefault;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.thenextlvl.namemc.util.Messages;
import net.thenextlvl.namemc.util.Like;

import java.util.Objects;

@ParametersAreNonnullByDefault
public class LikeCommand implements SimpleCommand {
    @Override
    public void execute(Invocation invocation) {
        CommandSource source = invocation.source();
        MiniMessage miniMessage = MiniMessage.miniMessage();
        if (source instanceof Player player) new Thread(() -> {
            boolean voted = Like.hasLiked(player);
            var locale = Objects.requireNonNullElse(player.getEffectiveLocale(), Messages.ENGLISH);
            player.sendMessage(miniMessage.deserialize(Messages.NAMEMC_GUIDE_HEADER.message(locale, player)));
            player.sendMessage(miniMessage.deserialize(Messages.NAMEMC_GUIDE.message(locale, player)));
            player.sendMessage(miniMessage.deserialize(Messages.NAMEMC_GUIDE_WEBSITE.message(locale, player)));
            player.sendMessage(miniMessage.deserialize(Messages.NAMEMC_GUIDE_LOGIN.message(locale, player)));
            player.sendMessage(miniMessage.deserialize(Messages.NAMEMC_GUIDE_LIKE.message(locale, player)));
            player.sendMessage(miniMessage.deserialize(Messages.NAMEMC_GUIDE_VERIFY.message(locale, player)));
            if (voted) player.sendMessage(miniMessage.deserialize(Messages.NAMEMC_LIKED.message(locale, player)));
            else player.sendMessage(miniMessage.deserialize(Messages.NAMEMC_VERIFICATION_INFO.message(locale, player)));
            player.sendMessage(miniMessage.deserialize(Messages.NAMEMC_GUIDE_FOOTER.message(locale, player)));
        }).start();
        else source.sendMessage(miniMessage.deserialize(Messages.COMMAND_SENDER.message(Messages.ENGLISH, source)));
    }
}
