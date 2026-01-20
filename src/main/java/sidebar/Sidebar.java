package sidebar;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.entity.Player;
import player.PlayerMode;
import player.PlayerProfile;
import progress.ProgressService;
import util.TimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class Sidebar
{
    private final FastBoard board;
    private final Player player;

    public Sidebar(FastBoard board, Player player)
    {
        this.board = board;
        this.player = player;
    }


    public void update(PlayerProfile profile, ProgressService progressService)
    {
        if(profile.getPlayerMode() == PlayerMode.DISABLED)
        {
            board.delete();
            return;
        }

        List<String> lines = new ArrayList<>();
        lines.add("[ Advancements Plugin ]");

        if(profile.getPlayerMode() == PlayerMode.HARDCORE)
        {
            lines.add("Attempt: " + profile.getAttempt());
            lines.add("Attempt time: " + TimeFormatter.format(profile.getAttemptTime()));
        }

        lines.add("Progress : " + progressService.)

        board.updateLines();
    }
}
