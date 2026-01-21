package config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import plugin.AdvancementsTrackerPlugin;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.logging.Level;


public class Config
{
    private static File file;
    private static FileConfiguration config;

    private static final String fileNameConfig = "config.yml";


    public static void init()
    {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin(AdvancementsTrackerPlugin.pluginName);
        if(plugin == null)
        {
            Bukkit.getLogger().log(
                    Level.WARNING,
                    MessageFormat.format("Cannot get plugin {0}", AdvancementsTrackerPlugin.pluginName)
            );
            return;
        }

        file = new File(plugin.getDataFolder(), fileNameConfig);

        try
        {
            if (file.createNewFile())
            {
                plugin.getLogger().log(
                        Level.INFO,
                        MessageFormat.format("New config file with name: {0} was created", fileNameConfig)
                );
            }
        }
        catch (IOException e)
        {
           plugin.getLogger().log(Level.SEVERE, e.toString());
           return;
        }

        // At this moment config is empty
        // Then, load it from file
        reload();
    }

    public static FileConfiguration getConfig()
    {
        return config;
    }

    public static void reload()
    {
        config = YamlConfiguration.loadConfiguration(file);
    }
}
