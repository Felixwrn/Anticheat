package de.anticheat.checks;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import de.anticheat.util.ViolationManager;

public class SpeedCheck implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        // ❌ Creative & Flight ignorieren
        if (p.getGameMode() == GameMode.CREATIVE || p.getAllowFlight()) return;

        double distance = e.getFrom().distance(e.getTo());
        double maxSpeed = 0.7;

        // ✅ Speed Effekt
        if (p.hasPotionEffect(PotionEffectType.SPEED)) {
            int level = p.getPotionEffect(PotionEffectType.SPEED).getAmplifier() + 1;
            maxSpeed += 0.2 * level;
        }

        // ✅ Slowness
        if (p.hasPotionEffect(PotionEffectType.SLOWNESS)) {
            int level = p.getPotionEffect(PotionEffectType.SLOWNESS).getAmplifier() + 1;
            maxSpeed -= 0.15 * level;
        }

        // 📉 Wenn Spieler fällt → IGNORIEREN
        if (p.getVelocity().getY() < -0.08) return;

        // 🧊 Eis prüfen
        Block block = p.getLocation().getBlock();
        if (block.getType() == Material.ICE ||
            block.getType() == Material.PACKED_ICE ||
            block.getType() == Material.BLUE_ICE) {
            maxSpeed += 0.4;
        }

        // 🪜 Treppen / Slabs
        if (block.getType().toString().contains("STAIRS") ||
            block.getType().toString().contains("SLAB")) {
            maxSpeed += 0.2;
        }

        // 🏃 Sprint Jump
        if (p.isSprinting() && !p.isOnGround()) {
            maxSpeed += 0.15;
        }

        // 💥 Knockback
        if (p.getVelocity().length() > 0.5) {
            return;
        }

        // 🐢 Lag Protection (kleine Toleranz)
        maxSpeed += 0.05;

        // 🚨 Check
        if (distance > maxSpeed) {
            ViolationManager.add(p, "Speed");

            if (ViolationManager.get(p, "Speed") > 10) {
                p.kickPlayer("Speed detected");
            }
        }
    }
}