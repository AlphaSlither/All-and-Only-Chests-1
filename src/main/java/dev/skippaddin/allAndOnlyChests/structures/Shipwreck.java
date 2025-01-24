package dev.skippaddin.allAndOnlyChests.structures;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Shipwreck extends Structure {

    private final HashMap<Material, Boolean> loot = new HashMap<>() {{
        put(Material.SUSPICIOUS_STEW, false);
        put(Material.PAPER, false);
        put(Material.WHEAT, false);
        put(Material.CARROT, false);
        put(Material.POISONOUS_POTATO, false);
        put(Material.POTATO, false);
        put(Material.MOSS_BLOCK, false);
        put(Material.COAL, false);
        put(Material.ROTTEN_FLESH, false);
        put(Material.GUNPOWDER, false);
        put(Material.LEATHER_HELMET, false);
        put(Material.LEATHER_CHESTPLATE, false);
        put(Material.LEATHER_LEGGINGS, false);
        put(Material.LEATHER_BOOTS, false);
        put(Material.COAST_ARMOR_TRIM_SMITHING_TEMPLATE, false);
        put(Material.BAMBOO, false);
        put(Material.PUMPKIN, false);
        put(Material.TNT, false);
        put(Material.IRON_INGOT, false);
        put(Material.IRON_NUGGET, false);
        put(Material.EMERALD, false);
        put(Material.LAPIS_LAZULI, false);
        put(Material.GOLD_NUGGET, false);
        put(Material.GOLD_INGOT, false);
        put(Material.EXPERIENCE_BOTTLE, false);
        put(Material.DIAMOND, false);
        put(Material.MAP, false);
        put(Material.FILLED_MAP, false);
        put(Material.FEATHER, false);
        put(Material.BOOK, false);
        put(Material.CLOCK, false);
        put(Material.COMPASS, false);
    }};

    public static int getItemCount() {
        return 32;
    }

    @Override
    public String getName() {
        return "shipwreck";
    }

    @Override
    public @NotNull HashMap<Material, Boolean> getLoot() {
        return loot;
    }
}
