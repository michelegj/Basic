package me.etel.basic.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import me.etel.basic.Basic;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

@CommandAlias("heal")
@CommandPermission("basic.command.heal")
public class HealCommand extends BaseCommand {

    @Default
    public void execute(Player player, @Optional OnlinePlayer onlinePlayer) {

        FileConfiguration langConfig = Basic.getInstance().getFileManager().getConfig("lang.yml");

        if (onlinePlayer == null) {
            player.setHealth(player.getMaxHealth());
            player.sendMessage(Basic.translate(langConfig.getString("heal_command.heal-self")));
            return;
        }

        Player target = onlinePlayer.getPlayer();
        target.setHealth(target.getMaxHealth());

        if (target == player) {
            player.sendMessage(Basic.translate(langConfig.getString("heal_command.heal-self")));
        } else {
            target.sendMessage(Basic.translate(langConfig.getString("heal_command.heal-other")
                    .replace("%issuer%", player.getDisplayName())));
            player.sendMessage(Basic.translate(langConfig.getString("heal_command.heal-self-other")
                    .replace("%target%", target.getDisplayName())));
        }
    }


}
