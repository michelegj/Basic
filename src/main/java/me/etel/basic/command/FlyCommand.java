package me.etel.basic.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import me.etel.basic.Basic;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

@CommandAlias("fly")
@CommandPermission("basic.command.fly")
public class FlyCommand extends BaseCommand {

    @Default
    @CommandCompletion("true|false")
    @Syntax("<allowFlight> [player]")
    public void execute(Player player, boolean option, @Optional OnlinePlayer onlinePlayer) {

        FileConfiguration langConfig = Basic.getInstance().getFileManager().getConfig("lang.yml");

        if (onlinePlayer == null) {
            player.setAllowFlight(option);
            player.sendMessage(Basic.translate(langConfig.getString("fly_command.fly-self")
                    .replace("%option%", option ? "&aenabled" : "&cdisabled")));
            return;
        }

        Player target = onlinePlayer.getPlayer();
        target.setAllowFlight(option);

        if (target == player) {
            player.sendMessage(Basic.translate(langConfig.getString("fly_command.fly-self")));
        } else {
            target.sendMessage(Basic.translate(langConfig.getString("fly_command.fly-other")
                    .replace("%issuer%", player.getDisplayName())
                    .replace("%option%", option ? "&aenabled" : "&cdisabled")));
            player.sendMessage(Basic.translate(langConfig.getString("fly_command.fly-self-other")
                    .replace("%target%", target.getDisplayName())
                    .replace("%option%", option ? "&aenabled" : "&cdisabled")));
        }
    }


}
