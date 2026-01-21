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

    private Set<NamespacedKey> completedAdvancements;

    // Constructor
    public PlayerProfile(UUID uuid)
    {
        this.uuid = uuid;
    }

    public UUID getUuid()
    {
        return uuid;
    }

    public PlayerMode getPlayerMode()
    {
        return this.playerMode;
    }

    public void setPlayerMode(PlayerMode playerMode)
    {
        this.playerMode = playerMode;
    }

    // attempt
    public int getAttempt()
    {
        return this.attempt;
    }

    public void setAttempt(int attempt)
    {
        this.attempt = attempt;
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
        return completedAdvancements;
    }


    public void resetProgress()
    {
        completedAdvancements.clear();
        attemptTime = 0;
    }
}
