package player;

import org.bukkit.NamespacedKey;

import java.util.Set;
import java.util.UUID;

public class PlayerProfile
{
    UUID uuid;

    PlayerMode playerMode;

    int attempt;
    long attemptTime;

    Set<NamespacedKey> completedAdvancements;
}
