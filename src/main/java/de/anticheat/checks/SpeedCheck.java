package de.anticheat.checks;

import org.bukkit.event.;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import de.anticheat.util.ViolationManager;

public class SpeedCheck implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        double distance = e.getFrom().distance(e.getTo());

        // Base Speed
        double maxSpeed = 0.7;

        // ✅ Speed Effekt berücksichtigen
        if (p.hasPotionEffect(PotionEffectType.SPEED)) {
            int level = p.getPotionEffect(PotionEffectType.SPEED).getAmplifier() + 1;

            // pro Level ~20% schneller
            maxSpeed += 0.2 level;
        }

        // ✅ Slowness berücksichtigen
        if (p.hasPotionEffect(PotionEffectType.SLOWNESS)) {
            int level = p.getPotionEffect(PotionEffectType.SLOWNESS).getAmplifier() + 1;
            maxSpeed -= 0.15 * level;
        }

        // Check
        if (distance > maxSpeed) {
            ViolationManager.add(p, "Speed");

            if (ViolationManager.get(p, "Speed") > 8) {
                p.kickPlayer("Speed detected");
            }
        }
    }
