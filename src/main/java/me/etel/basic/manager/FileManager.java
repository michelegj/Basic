package me.etel.basic.manager;

import me.etel.basic.Basic;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private File file;
    private static final Basic plugin = Basic.getInstance();

    public void saveConfig(FileConfiguration config, String fileName) {
        try {
            File file = new File(plugin.getDataFolder(), fileName);
            config.save(file);
        } catch (IOException ex) {
            Bukkit.getLogger().info("[Basic] Unable to save " + file.getName() + ": " + ex.getMessage());
        }
    }

    public FileConfiguration getConfig(String fileName) {
        File configFile = new File(plugin.getDataFolder(), fileName);

        if (!configFile.exists()) {
            plugin.saveResource(fileName, true);
        }

        FileConfiguration config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException ex) {
            Bukkit.getLogger().info("[Basic] Unable to get " + file.getName() + ": " + ex.getMessage());
        }

        return config;
    }

    public void loadConfig(String fileName) {
        this.file = new File(plugin.getDataFolder(), fileName);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource(fileName, true);
        }

        FileConfiguration config = new YamlConfiguration();
        try {
            config.load(this.file);
        } catch (IOException | InvalidConfigurationException ex) {
            Bukkit.getLogger().info("[Basic] Unable to load " + file.getName() + ": " + ex.getMessage());
        }
    }

}
