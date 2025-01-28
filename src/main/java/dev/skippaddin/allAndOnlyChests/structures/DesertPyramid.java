package dev.skippaddin.allAndOnlyChests.structures;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class DesertPyramid extends Structure {

    private final HashMap<Material, Boolean> loot = new HashMap<>() {{
        put(Material.BONE, false);
        put(Material.ROTTEN_FLESH, false);
        put(Material.GUNPOWDER, false);
        put(Material.SAND, false);
        put(Material.STRING, false);
        put(Material.SPIDER_EYE, false);
        put(Material.ENCHANTED_BOOK, false);
        put(Material.SADDLE, false);
        put(Material.GOLDEN_APPLE, false);
        put(Material.GOLD_INGOT, false);
        put(Material.IRON_INGOT, false);
        put(Material.EMERALD, false);
        put(Material.IRON_HORSE_ARMOR, false);
        put(Material.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE, false);
        put(Material.GOLDEN_HORSE_ARMOR, false);
        put(Material.DIAMOND, false);
        put(Material.DIAMOND_HORSE_ARMOR, false);
        put(Material.ENCHANTED_GOLDEN_APPLE, false);
    }};

    public static int getItemCount() {
        return 18;
    }

    @Override
    public String getName() {
        return "desert_pyramid";
    }

    @Override
    public @NotNull HashMap<Material, Boolean> getLoot() {
        return loot;
    }
}
