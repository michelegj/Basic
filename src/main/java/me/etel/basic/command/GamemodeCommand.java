package me.etel.basic.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import me.etel.basic.Basic;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class GamemodeCommand extends BaseCommand {

    private static final FileConfiguration langConfig = Basic.getInstance().getFileManager().getConfig("lang.yml");

    @CommandAlias("creative|gmc")
    @CommandPermission("basic.command.creative")
    @Description("Switches gamemode to creative")
    public static void onCreative(Player player, @Optional OnlinePlayer onlinePlayer) {

        setGamemode(player, onlinePlayer, GameMode.CREATIVE, langConfig);

    }

    @CommandAlias("survival|gms")
    @CommandPermission("basic.command.survival")
    @Description("Switches gamemode to survival")
    public static void onSurvival(Player player, @Optional OnlinePlayer onlinePlayer) {

        setGamemode(player, onlinePlayer, GameMode.SURVIVAL, langConfig);

    }

    @CommandAlias("adventure|gma")
    @CommandPermission("basic.command.adventure")
    @Description("Switches gamemode to adventure")
    public static void onAdventure(Player player, @Optional OnlinePlayer onlinePlayer) {

        setGamemode(player, onlinePlayer, GameMode.ADVENTURE, langConfig);

    }

    @CommandAlias("spectator|gmp")
    @CommandPermission("basic.command.spectator")
    @Description("Switches gamemode to spectator")
    public static void onSpectator(Player player, @Optional OnlinePlayer onlinePlayer) {

        setGamemode(player, onlinePlayer, GameMode.SPECTATOR, langConfig);

    }

    private static void setGamemode(Player sender, OnlinePlayer onlinePlayer, GameMode gameMode, FileConfiguration langConfig) {
        if (onlinePlayer == null) {
            sender.setGameMode(gameMode);
            sender.sendMessage(Basic.translate(langConfig.getString("gamemode_command.gamemode-self")
                    .replace("%gamemode%", gameMode.toString())
            ));
        } else {
            Player target = onlinePlayer.getPlayer();
            target.setGameMode(gameMode);

            if (target == sender) {
                sender.sendMessage(Basic.translate(langConfig.getString("gamemode_command.gamemode-self")
                        .replace("%gamemode%", gameMode.toString())
                ));
            } else {
                target.sendMessage(Basic.translate(langConfig.getString("gamemode_command.gamemode-other")
                        .replace("%gamemode%", gameMode.toString())
                        .replace("%issuer%", sender.getDisplayName())
                ));
                sender.sendMessage(Basic.translate(langConfig.getString("gamemode_command.gamemode-self-other")
                        .replace("%gamemode%", gameMode.toString())
                        .replace("%target%", target.getDisplayName())
                ));
            }
        }

    }


}
