package sidebar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import player.PlayerMode;
import player.PlayerProfile;
import player.PlayerProfileService;
import progress.ProgressService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SidebarManager implements Listener
{
    private final Map<UUID, Sidebar> sidebars = new HashMap<>();
    private final PlayerProfileService profiles;
    private final ProgressService progress;

    public SidebarManager(PlayerProfileService profiles,
                          ProgressService progress) {
        this.profiles = profiles;
        this.progress = progress;
    }

    public void tick() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            Sidebar sb = sidebars.computeIfAbsent(
                    p.getUniqueId(), id -> new Sidebar(p));

            PlayerProfile profile = profiles.get(p);
            if (profile.getPlayerMode() == PlayerMode.HARDCORE) {
                profile.tickTime();
            }

            sb.update(profile, progress);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Sidebar sb = sidebars.remove(e.getPlayer().getUniqueId());
        if (sb != null) sb.delete();
        profiles.unload(e.getPlayer());
    }
}
