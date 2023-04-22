package net.thenextlvl.namemc.util;

import core.annotation.FieldsAreNonnullByDefault;
import core.api.file.format.MessageFile;
import core.api.placeholder.MessageKey;
import core.api.placeholder.Placeholder;
import core.api.placeholder.SystemMessageKey;
import net.kyori.adventure.audience.Audience;

import java.util.Locale;

@FieldsAreNonnullByDefault
public class Messages {
    public static final Locale ENGLISH = Locale.forLanguageTag("en-US");
    public static final Placeholder.Formatter<Audience> FORMATTER = new Placeholder.Formatter<>();
    public static final SystemMessageKey<Audience> PREFIX = new SystemMessageKey<>("namemc.prefix", FORMATTER);
    public static final MessageKey<Audience> COMMAND_SENDER = new MessageKey<>("command.sender", FORMATTER);
    public static final MessageKey<Audience> NAMEMC_LIKED = new MessageKey<>("namemc.liked", FORMATTER);
    public static final MessageKey<Audience> NAMEMC_VERIFICATION_INFO = new MessageKey<>("namemc.info.verification", FORMATTER);
    public static final MessageKey<Audience> NAMEMC_GUIDE = new MessageKey<>("namemc.guide", FORMATTER);
    public static final MessageKey<Audience> NAMEMC_GUIDE_WEBSITE = new MessageKey<>("namemc.guide.website", FORMATTER);
    public static final MessageKey<Audience> NAMEMC_GUIDE_LOGIN = new MessageKey<>("namemc.guide.login", FORMATTER);
    public static final MessageKey<Audience> NAMEMC_GUIDE_LIKE = new MessageKey<>("namemc.guide.like", FORMATTER);
    public static final MessageKey<Audience> NAMEMC_GUIDE_VERIFY = new MessageKey<>("namemc.guide.verify", FORMATTER);
    public static final MessageKey<Audience> NAMEMC_GUIDE_HEADER = new MessageKey<>("namemc.guide.header", FORMATTER);
    public static final MessageKey<Audience> NAMEMC_GUIDE_FOOTER = new MessageKey<>("namemc.guide.footer", FORMATTER);

    static {
        initRoot();
        initEnglish();
        initGerman();
    }

    private static void initRoot() {
        var file = MessageFile.ROOT;
        file.setDefault(PREFIX, "<white>NameMC <dark_gray>»<reset>");
        file.save();
    }

    private static void initEnglish() {
        var file = MessageFile.getOrCreate(ENGLISH);
        file.setDefault(COMMAND_SENDER, "%prefix% <red>You cannot use this command");
        file.setDefault(NAMEMC_LIKED, "<dark_gray>» <gray>You already liked our server");
        file.setDefault(NAMEMC_VERIFICATION_INFO, "<dark_gray>» <gray>The verification can take up to a minute");
        file.setDefault(NAMEMC_GUIDE, "<dark_gray>| <gold><bold>NameMC <dark_gray>x <gray><italic>How to like us on NameMC<newline>");
        file.setDefault(NAMEMC_GUIDE_WEBSITE, "<gold><bold>1. <gray>Go to <yellow><click:open_url:https://example.com/namemc>https://example.com/namemc");
        file.setDefault(NAMEMC_GUIDE_LOGIN, "<gold><bold>2. <gray>Register or login into your NameMC account");
        file.setDefault(NAMEMC_GUIDE_LIKE, "<gold><bold>3. <gray>Click the thumbs up icon to like");
        file.setDefault(NAMEMC_GUIDE_VERIFY, "<gold><bold>4. <gray>Execute " +
                "<yellow><click:suggest_command:namemc>/namemc<reset> " +
                "<gray>again to verify your like<newline>");
        file.setDefault(NAMEMC_GUIDE_HEADER, "<dark_gray><strikethrough>" + " ".repeat(60) + "<reset>");
        file.setDefault(NAMEMC_GUIDE_FOOTER, "<dark_gray><strikethrough>" + " ".repeat(60) + "<reset>");
        file.save();
    }

    private static void initGerman() {
        var file = MessageFile.getOrCreate(Locale.forLanguageTag("de-DE"));
        file.setDefault(COMMAND_SENDER, "%prefix% <red>Du kannst diesen command nicht nutzen");
        file.setDefault(NAMEMC_LIKED, "<dark_gray>» <gray>Du hast unseren Server bereits geliked");
        file.setDefault(NAMEMC_VERIFICATION_INFO, "<dark_gray>» <gray>Die Verifizierung kann bis zu einer Minute dauern");
        file.setDefault(NAMEMC_GUIDE, "<dark_gray>| <gold><bold>NameMC <dark_gray>x <gray><italic>Wie du uns auf NameMC likest<newline>");
        file.setDefault(NAMEMC_GUIDE_WEBSITE, "<gold><bold>1. <gray>Besuche <yellow><click:open_url:https://example.com/namemc>https://example.com/namemc");
        file.setDefault(NAMEMC_GUIDE_LOGIN, "<gold><bold>2. <gray>Registriere oder melde dich bei deinem NameMC account an");
        file.setDefault(NAMEMC_GUIDE_LIKE, "<gold><bold>3. <gray>Klicke den Knopf der einen Daumen hoch zeigt");
        file.setDefault(NAMEMC_GUIDE_VERIFY, "<gold><bold>4. <gray>Führe " +
                "<yellow><click:suggest_command:namemc>/namemc<reset> " +
                "<gray>erneut aus um deinen like zu verifizieren<newline>");
        file.setDefault(NAMEMC_GUIDE_HEADER, "<dark_gray><strikethrough>" + " ".repeat(60) + "<reset>");
        file.setDefault(NAMEMC_GUIDE_FOOTER, "<dark_gray><strikethrough>" + " ".repeat(60) + "<reset>");
        file.save();
    }
}
