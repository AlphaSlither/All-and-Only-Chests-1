package dev.skippaddin.allAndOnlyChests.structures;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class NetherFortress extends Structure {

    private final HashMap<Material, Boolean> loot = new HashMap<>() {{
        put(Material.GOLD_INGOT, false);
        put(Material.SADDLE, false);
        put(Material.GOLDEN_HORSE_ARMOR, false);
        put(Material.NETHER_WART, false);
        put(Material.IRON_INGOT, false);
        put(Material.DIAMOND, false);
        put(Material.FLINT_AND_STEEL, false);
        put(Material.IRON_HORSE_ARMOR, false);
        put(Material.GOLDEN_SWORD, false);
        put(Material.GOLDEN_CHESTPLATE, false);
        put(Material.DIAMOND_HORSE_ARMOR, false);
        put(Material.OBSIDIAN, false);
        put(Material.RIB_ARMOR_TRIM_SMITHING_TEMPLATE, false);
    }};

    public static int getItemCount() {
        return 13;
    }

    @Override
    public String getName() {
        return "nether_bridge";
    }

    @Override
    public @NotNull HashMap<Material, Boolean> getLoot() {
        return loot;
    }
}
