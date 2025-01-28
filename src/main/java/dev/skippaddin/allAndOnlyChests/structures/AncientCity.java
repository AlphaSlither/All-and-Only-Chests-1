package dev.skippaddin.allAndOnlyChests.structures;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class AncientCity extends Structure {

    private final HashMap<Material, Boolean> loot = new HashMap<>() {{
        put(Material.COAL, false);
        put(Material.BONE, false);
        put(Material.SOUL_TORCH, false);
        put(Material.BOOK, false);
        put(Material.ENCHANTED_BOOK, false);
        put(Material.DISC_FRAGMENT_5, false);
        put(Material.ECHO_SHARD, false);
        put(Material.AMETHYST_SHARD, false);
        put(Material.GLOW_BERRIES, false);
        put(Material.SCULK, false);
        put(Material.CANDLE, false);
        put(Material.EXPERIENCE_BOTTLE, false);
        put(Material.SCULK_SENSOR, false);
        put(Material.IRON_LEGGINGS, false);
        put(Material.SCULK_CATALYST, false);
        put(Material.COMPASS, false);
        put(Material.MUSIC_DISC_13, false);
        put(Material.MUSIC_DISC_CAT, false);
        put(Material.LEAD, false);
        put(Material.NAME_TAG, false);
        put(Material.SADDLE, false);
        put(Material.DIAMOND_HOE, false);
        put(Material.DIAMOND_HORSE_ARMOR, false);
        put(Material.DIAMOND_LEGGINGS, false);
        put(Material.ENCHANTED_GOLDEN_APPLE, false);
        put(Material.MUSIC_DISC_OTHERSIDE, false);
        put(Material.WARD_ARMOR_TRIM_SMITHING_TEMPLATE, false);
        put(Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE, false);
        put(Material.SNOWBALL, false);
        put(Material.PACKED_ICE, false);
        put(Material.BAKED_POTATO, false);
        put(Material.GOLDEN_CARROT, false);
        put(Material.SUSPICIOUS_STEW, false);
        put(Material.POTION, false);
    }};

    public static int getItemCount() {
        return 34;
    }

    @Override
    public String getName() {
        return "ancient_city";
    }

    @Override
    public @NotNull HashMap<Material, Boolean> getLoot() {
        return loot;
    }
}
