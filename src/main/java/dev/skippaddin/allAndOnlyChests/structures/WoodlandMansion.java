package dev.skippaddin.allAndOnlyChests.structures;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class WoodlandMansion extends Structure {

    private final HashMap<Material, Boolean> loot = new HashMap<>() {{
        put(Material.BONE, false);
        put(Material.GUNPOWDER, false);
        put(Material.ROTTEN_FLESH, false);
        put(Material.STRING, false);
        put(Material.VEX_ARMOR_TRIM_SMITHING_TEMPLATE, false);
        put(Material.RESIN_CLUMP, false);
        put(Material.RESIN_BRICK, false);
        put(Material.LEAD, false);
        put(Material.NAME_TAG, false);
        put(Material.MUSIC_DISC_13, false);
        put(Material.MUSIC_DISC_CAT, false);
        put(Material.GOLDEN_APPLE, false);
        put(Material.DIAMOND_HOE, false);
        put(Material.WHEAT, false);
        put(Material.BREAD, false);
        put(Material.COAL, false);
        put(Material.REDSTONE, false);
        put(Material.ENCHANTED_BOOK, false);
        put(Material.CHAINMAIL_CHESTPLATE, false);
        put(Material.BEETROOT_SEEDS, false);
        put(Material.MELON_SEEDS, false);
        put(Material.PUMPKIN_SEEDS, false);
        put(Material.IRON_INGOT, false);
        put(Material.BUCKET, false);
        put(Material.DIAMOND_CHESTPLATE, false);
        put(Material.GOLD_INGOT, false);
        put(Material.ENCHANTED_GOLDEN_APPLE, false);
    }};

    public static int getItemCount() {
        return 27;
    }

    @Override
    public String getName() {
        return "woodland_mansion";
    }

    @Override
    public @NotNull HashMap<Material, Boolean> getLoot() {
        return loot;
    }
}
