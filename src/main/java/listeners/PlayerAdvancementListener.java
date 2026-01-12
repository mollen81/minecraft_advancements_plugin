package listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import plugin.AdvancementsTracker;

public class PlayerAdvancementListener implements Listener
{
    private final AdvancementsTracker plugin;

    public PlayerAdvancementListener(AdvancementsTracker plugin)
    {
        this.plugin = plugin;
    }

    //handler
    @EventHandler
    public void onPlayerAdvancement(PlayerAdvancementDoneEvent event)
    {
        Player player = event.getPlayer();

        // actions after event
        player.;
    }
}
