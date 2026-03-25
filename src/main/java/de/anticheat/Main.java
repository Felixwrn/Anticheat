
package de.anticheat;

import org.bukkit.plugin.java.JavaPlugin;
import de.anticheat.checks.*;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new SpeedCheck(), this);
        getServer().getPluginManager().registerEvents(new FlyCheck(), this);
        getServer().getPluginManager().registerEvents(new XRayCheck(), this);
        getLogger().info("AdvancedAntiCheat enabled!");
    }
}
