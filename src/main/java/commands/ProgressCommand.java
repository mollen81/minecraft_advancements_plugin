package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import player.PlayerMode;
import player.PlayerProfile;
import player.PlayerProfileService;

public class ProgressCommand implements CommandExecutor
{
    private final PlayerProfileService profiles;

    public ProgressCommand(PlayerProfileService profiles) {
        this.profiles = profiles;
    }

    @Override
    public boolean onCommand(CommandSender s, Command c,
                             String l, String[] a) {

        if (!(s instanceof Player p)) return true;
        if (a.length != 1) return false;

        PlayerProfile profile = profiles.get(p);

        switch (a[0].toLowerCase()) {
            case "hardcore" -> profile.setPlayerMode(PlayerMode.HARDCORE);
            case "softcore" -> profile.setPlayerMode(PlayerMode.SOFTCORE);
            case "off" -> profile.setPlayerMode(PlayerMode.DISABLED);
            default -> { return false; }
        }

        return true;
    }
}
