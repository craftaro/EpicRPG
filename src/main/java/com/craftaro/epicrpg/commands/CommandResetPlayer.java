package com.craftaro.epicrpg.commands;

import com.craftaro.core.commands.AbstractCommand;
import com.craftaro.epicrpg.EpicRPG;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CommandResetPlayer extends AbstractCommand {
    private final EpicRPG plugin;

    public CommandResetPlayer(EpicRPG plugin) {
        super(CommandType.PLAYER_ONLY, "resetplayer");
        this.plugin = plugin;
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        if (args.length != 1) {
            return ReturnType.SYNTAX_ERROR;
        }

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);

        this.plugin.getContendentManager().getPlayer(offlinePlayer).reset();

        this.plugin.getLocale().getMessage("command.resetplayer.success")
                .processPlaceholder("player", offlinePlayer.getName())
                .sendPrefixedMessage(sender);

        if (offlinePlayer.isOnline()) {
            this.plugin.getLocale().getMessage("command.resetplayer.reset")
                    .sendPrefixedMessage(offlinePlayer.getPlayer());
        }

        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "epicrpg.admin";
    }

    @Override
    public String getSyntax() {
        return "resetplayer";
    }

    @Override
    public String getDescription() {
        return "Reset a players story.";
    }
}
