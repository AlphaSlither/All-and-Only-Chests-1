package dev.skippaddin.allAndOnlyChests.structures;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class EndCity extends Structure {

    private final HashMap<Material, Boolean> loot = new HashMap<>() {{
        put(Material.GOLD_INGOT, false);
        put(Material.IRON_INGOT, false);
        put(Material.BEETROOT_SEEDS, false);
        put(Material.DIAMOND, false);
        put(Material.SADDLE, false);
        put(Material.IRON_PICKAXE, false);
        put(Material.IRON_SHOVEL, false);
        put(Material.IRON_SWORD, false);
        put(Material.IRON_HELMET, false);
        put(Material.IRON_CHESTPLATE, false);
        put(Material.IRON_LEGGINGS, false);
        put(Material.IRON_BOOTS, false);
        put(Material.DIAMOND_PICKAXE, false);
        put(Material.DIAMOND_SHOVEL, false);
        put(Material.DIAMOND_SWORD, false);
        put(Material.DIAMOND_HELMET, false);
        put(Material.DIAMOND_CHESTPLATE, false);
        put(Material.DIAMOND_LEGGINGS, false);
        put(Material.DIAMOND_BOOTS, false);
        put(Material.EMERALD, false);
        put(Material.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE, false);
        put(Material.IRON_HORSE_ARMOR, false);
        put(Material.GOLDEN_HORSE_ARMOR, false);
        put(Material.DIAMOND_HORSE_ARMOR, false);
    }};

    public static int getItemCount() {
        return 24;
    }

    @Override
    public String getName() {
        return "end_city";
    }

    @Override
    public @NotNull HashMap<Material, Boolean> getLoot() {
        return loot;
    }
}
