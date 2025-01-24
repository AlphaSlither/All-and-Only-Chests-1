package dev.skippaddin.allAndOnlyChests.structures;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class OceanRuin extends Structure {

    private final HashMap<Material, Boolean> loot = new HashMap<>() {{
        put(Material.COAL, false);
        put(Material.WHEAT, false);
        put(Material.GOLD_NUGGET, false);
        put(Material.FILLED_MAP, false);
        put(Material.ENCHANTED_BOOK, false);
        put(Material.FISHING_ROD, false);
        put(Material.EMERALD, false);
        put(Material.LEATHER_CHESTPLATE, false);
        put(Material.GOLDEN_APPLE, false);
        put(Material.GOLDEN_HELMET, false);
    }};

    public static int getItemCount() {
        return 10;
    }

    @Override
    public String getName() {
        return "underwater_ruin";
    }

    @Override
    public @NotNull HashMap<Material, Boolean> getLoot() {
        return loot;
    }
}
