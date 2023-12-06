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
    public boolean execute(Player player, @Optional OnlinePlayer onlinePlayer) {

        setHealth(player, onlinePlayer, Basic.getInstance().getFileManager().getConfig("lang.yml"));
        return false;
    }

    private static void setHealth(Player sender, OnlinePlayer onlinePlayer, FileConfiguration langConfig) {
        if (onlinePlayer == null) {
            sender.setHealth(sender.getMaxHealth());
            sender.sendMessage(Basic.translate(langConfig.getString("heal_command.heal-self")));
        } else {
            Player target = onlinePlayer.getPlayer();
            target.setHealth(target.getMaxHealth());

            if (target == sender) {
                sender.sendMessage(Basic.translate(langConfig.getString("heal_command.heal-self")));
            } else {
                target.sendMessage(Basic.translate(langConfig.getString("heal_command.heal-other")
                        .replace("%issuer%", sender.getDisplayName())
                ));
                sender.sendMessage(Basic.translate(langConfig.getString("heal_command.heal-self-other")
                        .replace("%target%", target.getDisplayName())
                ));
            }
        }

    }

}
