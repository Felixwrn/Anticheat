
package de.anticheat.util;

import java.util.*;
import org.bukkit.entity.Player;

public class ViolationManager {

    private static final Map<UUID, Map<String, Integer>> data = new HashMap<>();

    public static void add(Player p, String type) {
        data.putIfAbsent(p.getUniqueId(), new HashMap<>());
        Map<String, Integer> map = data.get(p.getUniqueId());
        map.put(type, map.getOrDefault(type, 0) + 1);
    }

    public static int get(Player p, String type) {
        return data.getOrDefault(p.getUniqueId(), new HashMap<>())
                .getOrDefault(type, 0);
    }
}
