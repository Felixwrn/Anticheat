package de.anticheat.checks;

import org.bukkit.GameMode;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Player;
import de.anticheat.util.ViolationManager;

public class FlyCheck implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        // ✅ Creative & Spectator ignorieren
        if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) {
            return;
        }

        // ✅ Wenn Spieler legit fliegen darf
        if (p.getAllowFlight()) return;

        double yDiff = e.getTo().getY() - e.getFrom().getY();

        // Nur checken wenn Spieler in Luft ist
        if (!p.isOnGround()) {

            // Verdächtig: zu lange "stehen" in der Luft
            if (Math.abs(yDiff) < 0.01) {

                ViolationManager.add(p, "Fly");

                if (ViolationManager.get(p, "Fly") > 8) {
                    p.kickPlayer("Fly detected");
                }

            } else {
                // Reset wenn Bewegung normal ist
                // (optional, macht es stabiler)
            }
        }
    }
}
