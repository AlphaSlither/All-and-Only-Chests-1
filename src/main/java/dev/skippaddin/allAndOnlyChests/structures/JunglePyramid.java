package dev.skippaddin.allAndOnlyChests.structures;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class JunglePyramid extends Structure {

    private final HashMap<Material, Boolean> loot = new HashMap<>() {{
        put(Material.BONE, false);
        put(Material.ROTTEN_FLESH, false);
        put(Material.GOLD_INGOT, false);
        put(Material.BAMBOO, false);
        put(Material.IRON_INGOT, false);
        put(Material.WILD_ARMOR_TRIM_SMITHING_TEMPLATE, false);
        put(Material.DIAMOND, false);
        put(Material.SADDLE, false);
        put(Material.EMERALD, false);
        put(Material.ENCHANTED_BOOK, false);
        put(Material.IRON_HORSE_ARMOR, false);
        put(Material.GOLDEN_HORSE_ARMOR, false);
        put(Material.DIAMOND_HORSE_ARMOR, false);
    }};

    public static int getItemCount() {
        return 13;
    }

    @Override
    public String getName() {
        return "jungle_temple";
    }

    @Override
    public @NotNull HashMap<Material, Boolean> getLoot() {
        return loot;
    }
}
