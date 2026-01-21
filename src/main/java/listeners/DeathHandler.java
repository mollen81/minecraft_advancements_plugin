package listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import player.PlayerMode;
import player.PlayerProfile;
import player.PlayerProfileService;
import progress.ProgressService;

public class DeathHandler implements Listener
{
    private final PlayerProfileService playerProfileService;
    private final ProgressService progressService;

    public DeathHandler(PlayerProfileService playerProfileService, ProgressService progressService)
    {
        this.playerProfileService = playerProfileService;
        this.progressService = progressService;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        PlayerProfile profile = playerProfileService.get(event.getPlayer());
        if(profile.getPlayerMode() != PlayerMode.HARDCORE)
        {
            return;
        }

        profile.resetProgress();
        progressService.resetProgress(event.getPlayer());
    }

}
