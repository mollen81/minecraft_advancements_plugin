package animations;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class Spiral
{
    private static final double RADIUS = 0.5;
    private static final double HEIGHT = 2.3;
    private static final double STEP = 0.1;

    public static void advancement(Plugin plugin, @NotNull Player player, Advancement advancement)
    {


        new BukkitRunnable()
        {
            double y = 0.0;

            @Override
            public void run()
            {
                Location location = player.getLocation();

                if(!player.isOnline())
                {
                    cancel();
                    return;
                }

                double x = RADIUS * Math.cos(y);
                double z = RADIUS * Math.sin(y);

                Location particleLocation = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());

                player.spawnParticle(Particle.EFFECT, particleLocation.add(x, y / 10, z), 2, new Particle.DustOptions(Color.GREEN, 1.0F));

                y += STEP;

                if(y > HEIGHT * 10)
                {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 2L);
    }
}
