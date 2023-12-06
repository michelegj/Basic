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

        if (onlinePlayer != null) {
            handleOnlinePlayer(player, onlinePlayer, GameMode.CREATIVE, langConfig);
        } else {
            handleSelf(player, GameMode.CREATIVE, langConfig);
        }

    }

    @CommandAlias("survival|gms")
    @CommandPermission("basic.command.survival")
    @Description("Switches gamemode to survival")
    public static void onSurvival(Player player, @Optional OnlinePlayer onlinePlayer) {

        if (onlinePlayer != null) {
            handleOnlinePlayer(player, onlinePlayer, GameMode.SURVIVAL, langConfig);
        } else {
            handleSelf(player, GameMode.SURVIVAL, langConfig);
        }

    }

    @CommandAlias("adventure|gma")
    @CommandPermission("basic.command.adventure")
    @Description("Switches gamemode to adventure")
    public static void onAdventure(Player player, @Optional OnlinePlayer onlinePlayer) {

        if (onlinePlayer != null) {
            handleOnlinePlayer(player, onlinePlayer, GameMode.ADVENTURE, langConfig);
        } else {
            handleSelf(player, GameMode.ADVENTURE, langConfig);
        }

    }

    @CommandAlias("spectator|gmp")
    @CommandPermission("basic.command.spectator")
    @Description("Switches gamemode to spectator")
    public static void onSpectator(Player player, @Optional OnlinePlayer onlinePlayer) {

        if (onlinePlayer != null) {
            handleOnlinePlayer(player, onlinePlayer, GameMode.SPECTATOR, langConfig);
        } else {
            handleSelf(player, GameMode.SPECTATOR, langConfig);
        }

    }

    private static void handleSelf(Player player, GameMode targetGameMode, FileConfiguration langConfig) {
        String errorMessage = langConfig.getString("gamemode_command.gamemode-error-self")
                .replace("%gamemode%", targetGameMode.toString());
        String successMessage = langConfig.getString("gamemode_command.gamemode-change-self")
                .replace("%gamemode%", targetGameMode.toString());

        if (player.getGameMode() == targetGameMode) {
            player.sendMessage(Basic.translate(errorMessage));
            return;
        }

        setGamemode(player, player, targetGameMode, successMessage);
    }

    private static void handleOnlinePlayer(Player sender, OnlinePlayer onlinePlayer, GameMode targetGameMode, FileConfiguration langConfig) {
        Player targetPlayer = onlinePlayer.getPlayer();
        String errorMessage = langConfig.getString("gamemode_command.gamemode-error-other")
                .replace("%target%", targetPlayer.getDisplayName())
                .replace("%gamemode%", targetGameMode.toString());
        String successMessage = langConfig.getString("gamemode_command.gamemode-change-other")
                .replace("%issuer%", sender.getDisplayName())
                .replace("%gamemode%", targetGameMode.toString());

        if (targetPlayer.getGameMode() == targetGameMode) {
            sender.sendMessage(Basic.translate(errorMessage));
            return;
        }

        setGamemode(targetPlayer, sender, targetGameMode, successMessage);
    }

    private static void setGamemode(Player targetPlayer, Player sender, GameMode targetGameMode, String successMessage) {
        targetPlayer.setGameMode(targetGameMode);

        if (targetPlayer != sender) {
            sender.sendMessage(Basic.translate(successMessage));
            targetPlayer.sendMessage(Basic.translate(successMessage.replace("%target%", targetPlayer.getDisplayName())));
        } else {
            targetPlayer.sendMessage(Basic.translate(successMessage));
        }
    }

}
