package player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import storage.Storage;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerProfileService
{
    private final JavaPlugin javaPlugin;
    private final Storage storage;
    private final Map<UUID, PlayerProfile> cache = new ConcurrentHashMap<>();

    public PlayerProfileService(JavaPlugin javaPlugin, Storage storage)
    {
        this.javaPlugin = javaPlugin;
        this.storage = storage;
    }

    public PlayerProfile get(Player player)
    {
        return cache.computeIfAbsent(player.getUniqueId(), storage::loadProfile);
    }

    public void unload(Player player)
    {
        PlayerProfile playerProfile = cache.remove(player.getUniqueId());

        if(playerProfile != null)
        {
            save(playerProfile);
        }
    }

    public void save(PlayerProfile playerProfile)
    {
        Bukkit.getScheduler().runTaskAsynchronously(javaPlugin, () -> storage.saveProfile(playerProfile));
    }

    public void saveAll()
    {
        cache.values().forEach(storage::saveProfile);
    }
}
