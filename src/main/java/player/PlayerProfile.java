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


    public UUID getUuid()
    {
        return uuid;
    }

    public PlayerMode getPlayerMode()
    {
        return this.playerMode;
    }

    public int getAttempt()
    {
        return this.attempt;
    }

    public long getAttemptTime()
    {
        return this.attemptTime;
    }

    public Set<NamespacedKey> getCompletedAdvancements()
    {
        return this.completedAdvancements;
    }
}
