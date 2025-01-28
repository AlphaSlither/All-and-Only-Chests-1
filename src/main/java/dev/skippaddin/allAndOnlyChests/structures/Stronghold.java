package dev.skippaddin.allAndOnlyChests.structures;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Stronghold extends Structure {

    private final HashMap<Material, Boolean> loot = new HashMap<>() {{
        put(Material.APPLE, false);
        put(Material.BREAD, false);
        put(Material.IRON_INGOT, false);
        put(Material.ENDER_PEARL, false);
        put(Material.REDSTONE, false);
        put(Material.GOLD_INGOT, false);
        put(Material.IRON_PICKAXE, false);
        put(Material.IRON_SWORD, false);
        put(Material.IRON_HELMET, false);
        put(Material.IRON_CHESTPLATE, false);
        put(Material.IRON_LEGGINGS, false);
        put(Material.IRON_BOOTS, false);
        put(Material.EYE_ARMOR_TRIM_SMITHING_TEMPLATE, false);
        put(Material.DIAMOND, false);
        put(Material.MUSIC_DISC_OTHERSIDE, false);
        put(Material.ENCHANTED_BOOK, false);
        put(Material.SADDLE, false);
        put(Material.IRON_HORSE_ARMOR, false);
        put(Material.GOLDEN_APPLE, false);
        put(Material.GOLDEN_HORSE_ARMOR, false);
        put(Material.DIAMOND_HORSE_ARMOR, false);
        put(Material.COAL, false);
        put(Material.PAPER, false);
        put(Material.BOOK, false);
        put(Material.COMPASS, false);
        put(Material.MAP, false);
    }};

    public static int getItemCount() {
        return 26;
    }

    @Override
    public String getName() {
        return "stronghold";
    }

    @Override
    public @NotNull HashMap<Material, Boolean> getLoot() {
        return loot;
    }
}
