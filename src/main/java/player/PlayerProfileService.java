package player;

import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class PlayerProfileService
{
    Map<UUID, PlayerProfile> cache;

    public PlayerProfile get(Player player)
    {
        return cache.get(player.getUniqueId());
    }

    public void unload()
    {

    }

    public void saveAll()
    {

    }
}
