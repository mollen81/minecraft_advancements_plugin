package sidebar;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class SidebarManager
{
    private Map<UUID, Sidebar> sidebars;

    public void create(Player player)
    {
        FastBoard newFastBoard = new FastBoard(player);
        Sidebar newSidebar = new Sidebar(newFastBoard, player);

        sidebars.put(player.getUniqueId(), newSidebar);
    }

    public void remove(Player player)
    {
        sidebars.remove(player.getUniqueId());
    }

    public void tick()
    {
        for(Sidebar sidebar : sidebars.values())
        {
            sidebar.update();
        }
    }
}
