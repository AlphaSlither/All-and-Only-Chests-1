package dev.skippaddin.allAndOnlyChests.structures;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Mineshaft extends Structure {

    private final HashMap<Material, Boolean> loot = new HashMap<>() {{
        put(Material.RAIL, false);
        put(Material.TORCH, false);
        put(Material.NAME_TAG, false);
        put(Material.GLOW_BERRIES, false);
        put(Material.BREAD, false);
        put(Material.GOLDEN_APPLE, false);
        put(Material.COAL, false);
        put(Material.BEETROOT_SEEDS, false);
        put(Material.MELON_SEEDS, false);
        put(Material.PUMPKIN_SEEDS, false);
        put(Material.IRON_INGOT, false);
        put(Material.ACTIVATOR_RAIL, false);
        put(Material.DETECTOR_RAIL, false);
        put(Material.POWERED_RAIL, false);
        put(Material.LAPIS_LAZULI, false);
        put(Material.REDSTONE, false);
        put(Material.GOLD_INGOT, false);
        put(Material.ENCHANTED_BOOK, false);
        put(Material.DIAMOND, false);
        put(Material.IRON_PICKAXE, false);
        put(Material.ENCHANTED_GOLDEN_APPLE, false);
    }};

    public static int getItemCount() {
        return 21;
    }

    @Override
    public String getName() {
        return "mineshaft";
    }

    @Override
    public @NotNull HashMap<Material, Boolean> getLoot() {
        return loot;
    }
}
