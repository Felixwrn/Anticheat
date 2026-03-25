
package de.anticheat.checks;

import org.bukkit.event.*;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Player;
import de.anticheat.util.ViolationManager;

public class FlyCheck implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (!p.isOnGround() && p.getVelocity().getY() == 0) {
            ViolationManager.add(p, "Fly");

            if (ViolationManager.get(p, "Fly") > 5) {
                p.kickPlayer("Fly detected");
            }
        }
    }
}
