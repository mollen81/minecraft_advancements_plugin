package progress;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import player.PlayerProfile;
import player.PlayerProfileService;
import storage.Storage;

public class ProgressService
{
    private final PlayerProfileService profileService;
    private final Storage storage;

    public ProgressService(PlayerProfileService profileService, Storage storage)
    {
        this.profileService = profileService;
        this.storage = storage;
    }

    public void completeAdvancement(Player player, NamespacedKey key)
    {
        PlayerProfile profile = profileService.get(player);

        if(profile.getCompletedAdvancements().add(key))
        {
            storage.addAdvancement(profile.getUuid(), key, profile.getAttempt());
        }
    }

    public void resetProgress(Player player)
    {
        PlayerProfile profile = profileService.get(player);
        storage.clearAdvancements(player.getUniqueId(), profile.getAttempt() - 1);
        profile.resetProgress();
    }


    public Double getProgressPercent(Player player)
    {
        return null;
    }

    public Integer getCompletedCount(PlayerProfile profile)
    {
        return profile.getCompletedAdvancements().size();
    }
}
