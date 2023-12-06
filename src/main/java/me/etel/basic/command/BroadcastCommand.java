package me.etel.basic.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.etel.basic.Basic;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

@CommandAlias("broadcast|bc")
public class BroadcastCommand extends BaseCommand {

    @Default
    @CommandPermission("basic.command.broadcast")
    @Syntax("<message>")
    public void execute(String message) {
        Bukkit.broadcastMessage(Basic.translate(message));
    }

    @Subcommand("permission")
    @CommandPermission("basic.command.broadcast.permission")
    @Syntax("<permission> <message>")
    public void onPermission(CommandSender sender, @Single String permission, String message) {

        FileConfiguration langConfig = Basic.getInstance().getFileManager().getConfig("lang.yml");

        sender.sendMessage(Basic.translate(langConfig.getString("broadcast_command.broadcast-loading")
                .replace("%permission%", permission)));

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission(permission) && player.isOnline())
                player.sendMessage(Basic.translate(message));
        }

        sender.sendMessage(Basic.translate(langConfig.getString("broadcast_command.broadcast-permission")
                .replace("%permission%", permission)));
    }

}
