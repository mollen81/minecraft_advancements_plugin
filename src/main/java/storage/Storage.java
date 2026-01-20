package storage;

import org.bukkit.NamespacedKey;
import player.PlayerProfile;

import java.util.Set;
import java.util.UUID;

public interface Storage
{
    PlayerProfile loadProfile(UUID uuid);

    void saveProfile(PlayerProfile playerProfile);

    Set<NamespacedKey> loadAdvancements(UUID uuid, int attempt);

    public void addAdvancement(UUID uuid, NamespacedKey key, int attempt);

    void clearAdvancements(UUID uuid, int attempt);
}
