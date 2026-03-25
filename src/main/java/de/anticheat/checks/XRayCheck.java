
package de.anticheat.checks;

import org.bukkit.event.*;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import de.anticheat.util.ViolationManager;

public class XRayCheck implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (e.getBlock().getType() == Material.DIAMOND_ORE) {
            ViolationManager.add(p, "XRay");

            if (ViolationManager.get(p, "XRay") > 10) {
                p.kickPlayer("XRay suspected");
            }
        }
    }
}
