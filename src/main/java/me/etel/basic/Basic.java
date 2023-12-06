package me.etel.basic;

import co.aikar.commands.BukkitCommandManager;
import lombok.Getter;
import me.etel.basic.command.*;
import me.etel.basic.manager.BasicManager;
import me.etel.basic.manager.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Locale;

public final class Basic extends JavaPlugin {

    @Getter private static Basic instance;
    @Getter private FileManager fileManager;

    @Override
    public void onEnable() {
        instance = this;

        if (!this.getDescription().getAuthors().contains("michelegj") || !this.getDescription().getName().equals("Basic")) {
            Bukkit.getLogger().warning("[Basic] Hey buddy why did you change the plugin.yml");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        BasicManager basicManager = new BasicManager(this);
        basicManager.enableUnstableAPI("help");

        this.fileManager = new FileManager();
        this.fileManager.loadConfig("lang.yml");

        if (!this.registerCommands(basicManager)) {
            Bukkit.getLogger().warning("[Basic] Couldn't register acf modules");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

    }

    private boolean registerCommands(BasicManager basicManager) {
        try {
            List.of(
                    new BasicCommand(),
                    new GamemodeCommand(),
                    new HealCommand(),
                    new FeedCommand(),
                    new BroadcastCommand(),
                    new FlyCommand()
            ).forEach(basicManager::registerCommand);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }


}
