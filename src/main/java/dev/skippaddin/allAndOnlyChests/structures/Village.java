package dev.skippaddin.allAndOnlyChests.structures;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Village extends Structure {

    private final HashMap<Material, Boolean> loot = new HashMap<>() {{
        put(Material.BREAD, false);
        put(Material.IRON_INGOT, false);
        put(Material.EMERALD, false);
        put(Material.IRON_HELMET, false);
        put(Material.BEEF, false);
        put(Material.MUTTON, false);
        put(Material.PORKCHOP, false);
        put(Material.WHEAT, false);
        put(Material.COAL, false);
        put(Material.PAPER, false);
        put(Material.MAP, false);
        put(Material.STICK, false);
        put(Material.COMPASS, false);
        put(Material.WHEAT_SEEDS, false);
        put(Material.COD, false);
        put(Material.BARREL, false);
        put(Material.SALMON, false);
        put(Material.WATER_BUCKET, false);
        put(Material.FEATHER, false);
        put(Material.FLINT, false);
        put(Material.ARROW, false);
        put(Material.EGG, false);
        put(Material.STONE, false);
        put(Material.STONE_BRICKS, false);
        put(Material.CLAY_BALL, false);
        put(Material.FLOWER_POT, false);
        put(Material.SMOOTH_STONE, false);
        put(Material.YELLOW_DYE, false);
        put(Material.WHITE_WOOL, false);
        put(Material.BROWN_WOOL, false);
        put(Material.BLACK_WOOL, false);
        put(Material.GRAY_WOOL, false);
        put(Material.LIGHT_GRAY_WOOL, false);
        put(Material.SHEARS, false);
        put(Material.LEATHER_HELMET, false);
        put(Material.LEATHER_CHESTPLATE, false);
        put(Material.LEATHER_LEGGINGS, false);
        put(Material.LEATHER_BOOTS, false);
        put(Material.LEATHER, false);
        put(Material.SADDLE, false);
        put(Material.ROTTEN_FLESH, false);
        put(Material.REDSTONE, false);
        put(Material.LAPIS_LAZULI, false);
        put(Material.GOLD_INGOT, false);
        put(Material.IRON_PICKAXE, false);
        put(Material.IRON_SHOVEL, false);
        put(Material.DIAMOND, false);
        put(Material.APPLE, false);
        put(Material.OAK_SAPLING, false);
        put(Material.OBSIDIAN, false);
        put(Material.IRON_SWORD, false);
        put(Material.IRON_CHESTPLATE, false);
        put(Material.IRON_LEGGINGS, false);
        put(Material.IRON_BOOTS, false);
        put(Material.IRON_HORSE_ARMOR, false);
        put(Material.GOLDEN_HORSE_ARMOR, false);
        put(Material.DIAMOND_HORSE_ARMOR, false);
        put(Material.CACTUS, false);
        put(Material.DEAD_BUSH, false);
        put(Material.BOOK, false);
        put(Material.GREEN_DYE, false);
        put(Material.POTATO, false);
        put(Material.DANDELION, false);
        put(Material.GOLD_NUGGET, false);
        put(Material.POPPY, false);
        put(Material.ACACIA_SAPLING, false);
        put(Material.SHORT_GRASS, false);
        put(Material.TALL_GRASS, false);
        put(Material.TORCH, false);
        put(Material.BUCKET, false);
        put(Material.SNOWBALL, false);
        put(Material.BEETROOT_SEEDS, false);
        put(Material.SNOW_BLOCK, false);
        put(Material.BEETROOT_SOUP, false);
        put(Material.BLUE_ICE, false);
        put(Material.FURNACE, false);
        put(Material.SPRUCE_LOG, false);
        put(Material.SWEET_BERRIES, false);
        put(Material.PUMPKIN_SEEDS, false);
        put(Material.SPRUCE_SAPLING, false);
        put(Material.FERN, false);
        put(Material.LARGE_FERN, false);
        put(Material.IRON_NUGGET, false);
        put(Material.PUMPKIN_PIE, false);
        put(Material.SPRUCE_SIGN, false);
    }};

    public static int getItemCount() {
        return 85;
    }

    @Override
    public String getName() {
        return "village";
    }

    @Override
    public @NotNull HashMap<Material, Boolean> getLoot() {
        return loot;
    }
}
