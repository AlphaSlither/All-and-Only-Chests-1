package dev.skippaddin.allAndOnlyChests.structures;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Igloo extends Structure {

    private final HashMap<Material, Boolean> loot = new HashMap<>() {{
        put(Material.GOLDEN_APPLE, false);
        put(Material.COAL, false);
        put(Material.APPLE, false);
        put(Material.WHEAT, false);
        put(Material.GOLD_NUGGET, false);
        put(Material.ROTTEN_FLESH, false);
        put(Material.STONE_AXE, false);
        put(Material.EMERALD, false);
    }};

    public static int getItemCount() {
        return 8;
    }

    @Override
    public String getName() {
        return "igloo";
    }

    @Override
    public @NotNull HashMap<Material, Boolean> getLoot() {
        return loot;
    }
}
