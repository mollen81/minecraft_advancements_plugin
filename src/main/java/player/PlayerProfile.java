package player;

import org.bukkit.NamespacedKey;

import java.util.Set;
import java.util.UUID;

public class PlayerProfile
{
    private final UUID uuid;

    private PlayerMode playerMode;

    private int attempt;
    private long attemptTime;

    private final Set<NamespacedKey> completedAdvancements;

    // Constructor
    public PlayerProfile(UUID uuid, Set<NamespacedKey> completedAdvancements)
    {
        this.uuid = uuid;
        this.completedAdvancements = completedAdvancements;
    }

    public UUID getUuid()
    {
        return uuid;
    }

    public PlayerMode getPlayerMode()
    {
        return this.playerMode;
    }

    // attempt
    public int getAttempt()
    {
        return this.attempt;
    }

    // attemptTime
    public long getAttemptTime()
    {
        return this.attemptTime;
    }

    public void setAttemptTime(long time)
    {
        this.attemptTime = time;
    }

    public void tickTime()
    {
        attemptTime++;
    }

    // completedAdvancements
    public Set<NamespacedKey> getCompletedAdvancements()
    {
        return this.completedAdvancements;
    }


    public void resetProgress()
    {
        completedAdvancements.clear();
        attemptTime = 0;
    }
}
