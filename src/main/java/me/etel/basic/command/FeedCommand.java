package me.etel.basic.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import me.etel.basic.Basic;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

@CommandAlias("feed")
@CommandPermission("basic.command.feed")
public class FeedCommand extends BaseCommand {

    @Default
    public boolean execute(Player player, @Optional OnlinePlayer onlinePlayer) {

        setFood(player, onlinePlayer, Basic.getInstance().getFileManager().getConfig("lang.yml"));
        return false;
    }

    private static void setFood(Player sender, OnlinePlayer onlinePlayer, FileConfiguration langConfig) {
        if (onlinePlayer == null) {
            sender.setFoodLevel(20);
            sender.setSaturation(20);
            sender.sendMessage(Basic.translate(langConfig.getString("feed_command.feed-self")));
        } else {
            Player target = onlinePlayer.getPlayer();
            target.setFoodLevel(20);
            target.setSaturation(20);

            if (target == sender) {
                sender.sendMessage(Basic.translate(langConfig.getString("feed_command.feed-self")));
            } else {
                target.sendMessage(Basic.translate(langConfig.getString("feed_command.feed-other")
                        .replace("%issuer%", sender.getDisplayName())
                ));
                sender.sendMessage(Basic.translate(langConfig.getString("feed_command.feed-self-other")
                        .replace("%target%", target.getDisplayName())
                ));
            }
        }

    }

}
