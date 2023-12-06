package me.etel.basic.manager;

import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.CommandIssuer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class BasicManager extends BukkitCommandManager {

    public BasicManager(Plugin plugin) {
        super(plugin);
    }

    @Override
    public boolean hasPermission(CommandIssuer issuer, String permission) {
        CommandSender sender = issuer.getIssuer();

        if (sender instanceof ConsoleCommandSender) return true;

        if (sender instanceof Player) {
            for (String perm : Arrays.asList(permission.split("\\|"))) {
                if (sender.hasPermission("basic.*") || sender.hasPermission(perm)) return true;
            }
        }

        return false;
    }



}
