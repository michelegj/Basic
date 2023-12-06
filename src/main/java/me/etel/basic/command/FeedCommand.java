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
    public void execute(Player player, @Optional OnlinePlayer onlinePlayer) {

        FileConfiguration langConfig = Basic.getInstance().getFileManager().getConfig("lang.yml");

        if (onlinePlayer == null) {
            player.setFoodLevel(20);
            player.setSaturation(20);
            player.sendMessage(Basic.translate(langConfig.getString("feed_command.feed-self")));
            return;
        }

        Player target = onlinePlayer.getPlayer();
        target.setFoodLevel(20);
        target.setSaturation(20);

        if (target == player) {
            player.sendMessage(Basic.translate(langConfig.getString("feed_command.feed-self")));
        } else {
            target.sendMessage(Basic.translate(langConfig.getString("feed_command.feed-other")
                    .replace("%issuer%", player.getDisplayName())));
            player.sendMessage(Basic.translate(langConfig.getString("feed_command.feed-self-other")
                    .replace("%target%", target.getDisplayName())));
        }
    }


}
