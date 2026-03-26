package de.anticheat.checks;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerMoveEvent;

import de.anticheat.util.ViolationManager;

public class FlyCheck implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        // ❌ Creative & Spectator ignorieren
        if (p.getGameMode() == GameMode.CREATIVE ||
            p.getGameMode() == GameMode.SPECTATOR) return;

        // ❌ Elytra ignorieren
        if (p.isGliding()) return;

        // ❌ Wenn Flight erlaubt ist
        if (p.getAllowFlight()) return;

        // ❌ Wenn Spieler fällt → legit
        if (p.getVelocity().getY() < -0.08) return;

        double yDiff = e.getTo().getY() - e.getFrom().getY();

        if (!p.isOnGround()) {

            // Spieler "steht" in der Luft
            if (Math.abs(yDiff) < 0.01) {

                ViolationManager.add(p, "Fly");

                if (ViolationManager.get(p, "Fly") > 8) {
                    p.kickPlayer("Fly detected");
                }
            }
        }
    }
}