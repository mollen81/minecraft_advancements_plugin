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

    public Sidebar(Player player)
    {
        this.board = new FastBoard(player);
        this.player = player;
        board.updateTitle("[ Advancements Plugin ]");
    }


    public void update(PlayerProfile profile, ProgressService progressService)
    {
        if(profile.getPlayerMode() == PlayerMode.DISABLED)
        {
            board.delete();
            return;
        }

        List<String> lines = new ArrayList<>();

        if(profile.getPlayerMode() == PlayerMode.HARDCORE)
        {
            lines.add("Attempt: " + profile.getAttempt());
            lines.add("Attempt time: " + TimeFormatter.format(profile.getAttemptTime()));
        }

        lines.add("Progress : " + progressService.getCompletedCount(profile) + "% / 100.0%");


        board.updateLines(lines);
    }

    public void delete()
    {
        board.delete();
    }
}
