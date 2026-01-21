package progress;

import io.papermc.paper.advancement.AdvancementDisplay;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import player.PlayerProfile;
import player.PlayerProfileService;
import storage.Storage;

import java.util.HashSet;
import java.util.Set;

public class ProgressService
{
    private final PlayerProfileService profileService;
    private final Storage storage;

    private final Set<NamespacedKey> allAdvancements = new HashSet<>();

    public ProgressService(PlayerProfileService profileService, Storage storage)
    {
        this.profileService = profileService;
        this.storage = storage;
        cacheAdvancements();
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


    public Integer getCompletedCount(PlayerProfile profile)
    {
        return profile.getCompletedAdvancements().size();
    }


    private void cacheAdvancements()
    {
        Bukkit.advancementIterator().forEachRemaining(adv -> {

            NamespacedKey key = adv.getKey();

            // только ваниль
            if (!key.getNamespace().equals("minecraft")) return;

            // исключаем рецепты
            if (key.getKey().startsWith("recipes/")) return;

            // исключаем root (story/root, nether/root, end/root)
            if (key.getKey().endsWith("/root")) return;

            /* опционально: скрытые
            AdvancementDisplay display = adv.getDisplay();
            if (display != null && display.isHidden()) return;
            */

            allAdvancements.add(key);
        });
    }
}
