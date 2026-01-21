package listeners;

import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import player.PlayerMode;
import player.PlayerProfile;
import player.PlayerProfileService;
import progress.ProgressService;

public class PlayerAdvancementListener implements Listener
{
    private final PlayerProfileService playerProfileService;
    private final ProgressService progressService;

    public PlayerAdvancementListener(PlayerProfileService playerProfileService, ProgressService progressService)
    {
        this.playerProfileService = playerProfileService;
        this.progressService = progressService;
    }

    //handler
    @EventHandler(ignoreCancelled = true)
    public void onPlayerAdvancement(PlayerAdvancementDoneEvent event)
    {
        Player player = event.getPlayer();
        PlayerProfile profile = playerProfileService.get(player);

        if(profile.getPlayerMode() == PlayerMode.DISABLED)
        {
            return;
        }

        Advancement advancement = event.getAdvancement();
        NamespacedKey key = advancement.getKey();

        progressService.completeAdvancement(player, key);
    }
}
