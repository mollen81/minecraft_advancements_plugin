package plugin;

import commands.ProgressCommand;
import database.DatabaseManager;
import listeners.DeathHandler;
import listeners.PlayerAdvancementListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import player.PlayerProfileService;
import progress.ProgressService;
import sidebar.SidebarManager;
import storage.SqlStorage;
import storage.Storage;

import java.util.Objects;


public class AdvancementsTrackerPlugin extends JavaPlugin
{
    private DatabaseManager database;
    private Storage storage;
    private PlayerProfileService profiles;
    private ProgressService progress;
    private SidebarManager sidebars;
    public static final String pluginName = "plugin.AdvancementsTrackerPlugin";

    @Override
    public void onEnable() {
        database = new DatabaseManager(this);
        storage = new SqlStorage(database);

        profiles = new PlayerProfileService(this, storage);
        progress = new ProgressService(profiles, storage);
        sidebars = new SidebarManager(profiles, progress);

        Bukkit.getPluginManager().registerEvents(
                new PlayerAdvancementListener(profiles, progress), this);
        Bukkit.getPluginManager().registerEvents(
                new DeathHandler(profiles, progress), this);
        Bukkit.getPluginManager().registerEvents(
                sidebars, this);

        Objects.requireNonNull(getCommand("progress"))
                .setExecutor(new ProgressCommand(profiles));

        Bukkit.getScheduler().runTaskTimer(this, sidebars::tick, 20L, 20L);
    }

    @Override
    public void onDisable() {
        profiles.saveAll();
        database.shutdown();
    }
}
