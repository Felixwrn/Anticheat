
package de.anticheat.checks;

import org.bukkit.event.*;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Player;
import de.anticheat.util.ViolationManager;

public class SpeedCheck implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        double dist = e.getFrom().distance(e.getTo());

        if (dist > 0.75) {
            ViolationManager.add(p, "Speed");

            if (ViolationManager.get(p, "Speed") > 8) {
                p.kickPlayer("Speed detected");
            }
        }
    }
}
