package dev.skippaddin.allAndOnlyChests;

import dev.skippaddin.allAndOnlyChests.commands.StructureCommand;
import dev.skippaddin.allAndOnlyChests.commands.StructuresCommand;
import dev.skippaddin.allAndOnlyChests.listeners.*;
import dev.skippaddin.allAndOnlyChests.menuSystem.utility.PlayerMenuUtility;
import dev.skippaddin.allAndOnlyChests.scoreboard.StructureScoreboard;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionType;

import java.util.*;


public final class AllAndOnlyChests extends JavaPlugin implements Listener {

    private static Plugin plugin;

    private static final HashSet<Block> placedBlocks = new HashSet<>();

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    //Add trial chambers later. Bastion last, because it gets handled different
    private static final String[] structures = new String[] {
            "ancient_city",
            "buried_treasure",
            "desert_pyramid",
            "end_city",
            "nether_bridge",
            "igloo",
            "jungle_temple",
            "underwater_ruin",
            "pillager_outpost",
            "ruined_portal",
            "shipwreck",
            "stronghold",
            "mineshaft",
            "village",
            "woodland_mansion",
            "simple_dungeon",
            "bastion",
            "trial_chambers"
    };

    //Cold and warm ocean ruin!!!! Multiple ruined portals!!! Multiple shipwrecks!!! Trial Chambers noch adden!!! Mineshaft mesa, Multiple villages
    private static final HashMap<String, Boolean> structureProgress = new HashMap<>() {{
        put(structures[0], false);
        put(structures[1], false);
        put(structures[2] , false);
        put(structures[3], false);
        put(structures[4], false);
        put(structures[5], false);
        put(structures[6], false);
        put(structures[7], false);
        put(structures[8], false);
        put(structures[9], false);
        put(structures[10], false);
        put(structures[11], false);
        put(structures[12], false);
        put(structures[13], false);
        put(structures[14], false);
        put(structures[15], false);
        put(structures[16], false);
        put(structures[17], false);
    }};

    private static String selectedStructure = "";

    private static final HashMap<Material, Boolean> ancientCityLoot = new HashMap<>() {{
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

    //!!!!!!!!!!!!!! Check if enchanted crossbow and golden armor and normal. golden boots with soul speed. Enchanted dia pickaxe and dia shovel and dia sword and dia armor !!!!!!!!!!!!!!!!!!!
    private static final HashMap<Material, Boolean> bastionRemnantLoot = new HashMap<>() {{
        put(Material.LODESTONE, false);
        put(Material.ARROW, false);
        put(Material.IRON_NUGGET, false);
        put(Material.GOLD_NUGGET, false);
        put(Material.STRING, false);
        put(Material.LEATHER, false);
        put(Material.SPECTRAL_ARROW, false);
        put(Material.GILDED_BLACKSTONE, false);
        put(Material.IRON_INGOT, false);
        put(Material.GOLD_INGOT, false);
        put(Material.CRYING_OBSIDIAN, false);
        put(Material.CROSSBOW, false);
        put(Material.GOLD_BLOCK, false);
        put(Material.GOLDEN_SWORD, false);
        put(Material.GOLDEN_AXE, false);
        put(Material.GOLDEN_HELMET, false);
        put(Material.GOLDEN_CHESTPLATE, false);
        put(Material.GOLDEN_LEGGINGS, false);
        put(Material.GOLDEN_BOOTS, false);
        put(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE, false);
        put(Material.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE, false);
        put(Material.MAGMA_CREAM, false);
        put(Material.CHAIN, false);
        put(Material.OBSIDIAN, false);
        put(Material.IRON_BLOCK, false);
        put(Material.IRON_SWORD, false);
        put(Material.BONE_BLOCK, false);
        put(Material.COOKED_PORKCHOP, false);
        put(Material.GOLDEN_CARROT, false);
        put(Material.ANCIENT_DEBRIS, false);
        put(Material.ENCHANTED_BOOK, false);
        put(Material.PIGLIN_BANNER_PATTERN, false);
        put(Material.GOLDEN_APPLE, false);
        put(Material.DIAMOND_SHOVEL, false);
        put(Material.DIAMOND_PICKAXE, false);
        put(Material.MUSIC_DISC_PIGSTEP, false);
        put(Material.NETHERITE_SCRAP, false);
        put(Material.CRIMSON_FUNGUS, false);
        put(Material.CRIMSON_NYLIUM, false);
        put(Material.CRIMSON_ROOTS, false);
        put(Material.GLOWSTONE, false);
        put(Material.SOUL_SAND, false);
        put(Material.PORKCHOP, false);
        put(Material.SADDLE, false);
        put(Material.NETHERITE_INGOT, false);
        put(Material.QUARTZ, false);
        put(Material.DIAMOND_SWORD, false);
        put(Material.DIAMOND_HELMET, false);
        put(Material.DIAMOND_CHESTPLATE, false);
        put(Material.DIAMOND_LEGGINGS, false);
        put(Material.DIAMOND_BOOTS, false);
        put(Material.DIAMOND, false);
        put(Material.ENCHANTED_GOLDEN_APPLE, false);
    }};

    private static final HashMap<Material, Boolean> bastionRemnantEnchantedLoot = new HashMap<>() {{
        put(Material.CROSSBOW, false);
        put(Material.GOLDEN_HELMET, false);
        put(Material.GOLDEN_CHESTPLATE, false);
        put(Material.GOLDEN_LEGGINGS, false);
        put(Material.GOLDEN_BOOTS, false);
        put(Material.DIAMOND_PICKAXE, false);
        put(Material.DIAMOND_SHOVEL, false);
        put(Material.DIAMOND_SWORD, false);
        put(Material.DIAMOND_HELMET, false);
        put(Material.DIAMOND_CHESTPLATE, false);
        put(Material.DIAMOND_LEGGINGS, false);
        put(Material.DIAMOND_BOOTS, false);
    }};

    private static final HashMap<Material, Boolean> shipwreckLoot = new HashMap<>() {{
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

    private static final HashMap<Material, Boolean> buriedTreasureLoot = new HashMap<>() {{
        put(Material.HEART_OF_THE_SEA, false);
        put(Material.IRON_INGOT, false);
        put(Material.GOLD_INGOT, false);
        put(Material.COOKED_COD, false);
        put(Material.COOKED_SALMON, false);
        put(Material.POTION, false);
        put(Material.TNT, false);
        put(Material.EMERALD, false);
        put(Material.PRISMARINE_CRYSTALS, false);
        put(Material.DIAMOND, false);
        put(Material.LEATHER_CHESTPLATE, false);
        put(Material.IRON_SWORD, false);
    }};

    private static final HashMap<Material, Boolean> desertPyramidLoot = new HashMap<>() {{
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

    private static final HashMap<Material, Boolean> endCityLoot = new HashMap<>() {{
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

    private static final HashMap<Material, Boolean> netherFortressLoot = new HashMap<>() {{
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

    private static final HashMap<Material, Boolean> iglooLoot = new HashMap<>() {{
        put(Material.GOLDEN_APPLE, false);
        put(Material.COAL, false);
        put(Material.APPLE, false);
        put(Material.WHEAT, false);
        put(Material.GOLD_NUGGET, false);
        put(Material.ROTTEN_FLESH, false);
        put(Material.STONE_AXE, false);
        put(Material.EMERALD, false);
    }};

    private static final HashMap<Material, Boolean> junglePyramidLoot = new HashMap<>() {{
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

    private static final HashMap<Material, Boolean> oceanRuinLoot = new HashMap<>() {{
        put(Material.COAL, false);
        put(Material.WHEAT, false);
        put(Material.GOLD_NUGGET, false);
        put(Material.FILLED_MAP, false);
        put(Material.ENCHANTED_BOOK, false);
        put(Material.FISHING_ROD, false);
        put(Material.EMERALD, false);
        put(Material.LEATHER_CHESTPLATE, false);
        put(Material.GOLDEN_APPLE, false);
        put(Material.GOLDEN_HELMET, false);
    }};

    private static final HashMap<Material, Boolean> pillagerOutpostLoot = new HashMap<>() {{
        put(Material.DARK_OAK_LOG, false);
        put(Material.WHEAT, false);
        put(Material.EXPERIENCE_BOTTLE, false);
        put(Material.CARROT, false);
        put(Material.POTATO, false);
        put(Material.CROSSBOW, false);
        put(Material.GOAT_HORN, false);
        put(Material.ARROW, false);
        put(Material.STRING, false);
        put(Material.TRIPWIRE_HOOK, false);
        put(Material.IRON_INGOT, false);
        put(Material.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE, false);
        put(Material.ENCHANTED_BOOK, false);
    }};

    private static final HashMap<Material, Boolean> ruinedPortalLoot = new HashMap<>() {{
        put(Material.IRON_INGOT, false);
        put(Material.FLINT, false);
        put(Material.OBSIDIAN, false);
        put(Material.FIRE_CHARGE, false);
        put(Material.FLINT_AND_STEEL, false);
        put(Material.GOLD_NUGGET, false);
        put(Material.GOLDEN_APPLE, false);
        put(Material.GOLDEN_AXE, false);
        put(Material.GOLDEN_HOE, false);
        put(Material.GOLDEN_PICKAXE, false);
        put(Material.GOLDEN_SHOVEL, false);
        put(Material.GOLDEN_SWORD, false);
        put(Material.GOLDEN_HELMET, false);
        put(Material.GOLDEN_CHESTPLATE, false);
        put(Material.GOLDEN_LEGGINGS, false);
        put(Material.GOLDEN_BOOTS, false);
        put(Material.GLISTERING_MELON_SLICE, false);
        put(Material.GOLDEN_CARROT, false);
        put(Material.GOLD_INGOT, false);
        put(Material.CLOCK, false);
        put(Material.LIGHT_WEIGHTED_PRESSURE_PLATE, false);
        put(Material.GOLDEN_HORSE_ARMOR, false);
        put(Material.GOLD_BLOCK, false);
        put(Material.BELL, false);
        put(Material.ENCHANTED_GOLDEN_APPLE, false);
    }};

    private static final HashMap<Material, Boolean> strongholdLoot = new HashMap<>() {{
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

    private static final HashMap<Material, Boolean> mineshaftLoot = new HashMap<>() {{
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

    private static final HashMap<Material, Boolean> villageLoot = new HashMap<>() {{
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

    private static final HashMap<Material, Boolean> monsterRoomLoot = new HashMap<>() {{
        put(Material.BONE, false);
        put(Material.GUNPOWDER, false);
        put(Material.ROTTEN_FLESH, false);
        put(Material.STRING, false);
        put(Material.WHEAT, false);
        put(Material.BREAD, false);
        put(Material.NAME_TAG, false);
        put(Material.SADDLE, false);
        put(Material.COAL, false);
        put(Material.REDSTONE, false);
        put(Material.MUSIC_DISC_13, false);
        put(Material.MUSIC_DISC_CAT, false);
        put(Material.IRON_HORSE_ARMOR, false);
        put(Material.GOLDEN_APPLE, false);
        put(Material.BEETROOT_SEEDS, false);
        put(Material.MELON_SEEDS, false);
        put(Material.PUMPKIN_SEEDS, false);
        put(Material.IRON_INGOT, false);
        put(Material.BUCKET, false);
        put(Material.ENCHANTED_BOOK, false);
        put(Material.GOLDEN_HORSE_ARMOR, false);
        put(Material.GOLD_INGOT, false);
        put(Material.DIAMOND_HORSE_ARMOR, false);
        put(Material.MUSIC_DISC_OTHERSIDE, false);
        put(Material.ENCHANTED_GOLDEN_APPLE, false);
    }};

    private static final HashMap<Material, Boolean> woodlandMansionLoot = new HashMap<>() {{
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

    private static final HashMap<Material, Boolean> trialChambersLoot = new HashMap<>() {{
        put(Material.EMERALD, false);
        put(Material.ARROW, false);
        put(Material.IRON_INGOT, false);
        put(Material.WIND_CHARGE, false);
        put(Material.HONEY_BOTTLE, false);
        put(Material.OMINOUS_BOTTLE, false);
        put(Material.SHIELD, false);
        put(Material.BOW, false);
        put(Material.DIAMOND, false);
        put(Material.GOLDEN_APPLE, false);
        put(Material.GOLDEN_CARROT, false);
        put(Material.ENCHANTED_BOOK, false);
        put(Material.CROSSBOW, false);
        put(Material.IRON_AXE, false);
        put(Material.IRON_CHESTPLATE, false);
        put(Material.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE, false);
        put(Material.MUSIC_DISC_PRECIPICE, false);
        put(Material.GUSTER_BANNER_PATTERN, false);
        put(Material.DIAMOND_AXE, false);
        put(Material.DIAMOND_CHESTPLATE, false);
        put(Material.TRIDENT, false);
        put(Material.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE, false);
        put(Material.ENCHANTED_GOLDEN_APPLE, false);
        put(Material.FLOW_BANNER_PATTERN, false);
        put(Material.EMERALD_BLOCK, false);
        put(Material.IRON_BLOCK, false);
        put(Material.MUSIC_DISC_CREATOR, false);
        put(Material.HEAVY_CORE, false);
        put(Material.DIAMOND_BLOCK, false);
        put(Material.HONEYCOMB, false);
        put(Material.WOODEN_AXE, false);
        put(Material.STICK, false);
        put(Material.TRIAL_KEY, false);
        put(Material.AMETHYST_SHARD, false);
        put(Material.CAKE, false);
        put(Material.DIAMOND_PICKAXE, false);
        put(Material.GLOW_BERRIES, false);
        put(Material.BAKED_POTATO, false);
        put(Material.STONE_PICKAXE, false);
        put(Material.TUFF, false);
        put(Material.ACACIA_PLANKS, false);
        put(Material.TORCH, false);
        put(Material.BONE_MEAL, false);
        put(Material.MOSS_BLOCK, false);
        put(Material.MILK_BUCKET, false);
        put(Material.BAMBOO_PLANKS, false);
        put(Material.GOLDEN_AXE, false);
        put(Material.GOLDEN_PICKAXE, false);
        put(Material.BUCKET, false);
        put(Material.COMPASS, false);
        put(Material.SCAFFOLDING, false);
        put(Material.BAMBOO_HANGING_SIGN, false);
        put(Material.ENDER_PEARL, false);
        put(Material.STONE_AXE, false);
        put(Material.BREAD, false);
        put(Material.COOKED_CHICKEN, false);
        put(Material.OMINOUS_TRIAL_KEY, false);
        put(Material.COOKED_BEEF, false);
    }};

    private static final HashMap<PotionType, Boolean> trialChambersArrowEffects = new HashMap<>() {{
        put(PotionType.POISON, false);
        put(PotionType.SLOWNESS, false);
    }};

    private static final HashMap<PotionType, Boolean> trialChambersPotions = new HashMap<>() {{
       put(PotionType.REGENERATION, false);
       put(PotionType.STRENGTH, false);
       put(PotionType.SWIFTNESS, false);
    }};

    private static final HashMap<Material, Boolean> trialChambersEnchantedLoot = new HashMap<>() {{
        put(Material.DIAMOND_AXE, false);
    }};

    // Bastion Remnant left out because it's a special case. Add logic case handling in listener
    public static final HashMap<String, HashMap<Material, Boolean>> structureMaterials = new HashMap<>() {{
        put(structures[0], ancientCityLoot);
        put(structures[1], buriedTreasureLoot);
        put(structures[2], desertPyramidLoot);
        put(structures[3], endCityLoot);
        put(structures[4], netherFortressLoot);
        put(structures[5], iglooLoot);
        put(structures[6], junglePyramidLoot);
        put(structures[7], oceanRuinLoot);
        put(structures[8], pillagerOutpostLoot);
        put(structures[9], ruinedPortalLoot);
        put(structures[10], shipwreckLoot);
        put(structures[11], strongholdLoot);
        put(structures[12], mineshaftLoot);
        put(structures[13], villageLoot);
        put(structures[14], woodlandMansionLoot);
        put(structures[15], monsterRoomLoot);
    }};

    public static String[] getStructures() {
        return structures;
    }

    public static HashSet<Block> getPlacedBlocks() {
        return placedBlocks;
    }

    public static void setSelectedStructure(String selectedStructure) {
        AllAndOnlyChests.selectedStructure = selectedStructure;
    }

    public static HashMap<String, Boolean> getStructureProgress() {
        return structureProgress;
    }

    public static String getSelectedStructure() {
        return selectedStructure;
    }

    public static HashMap<Material, Boolean> getWoodlandMansionLoot() {
        return woodlandMansionLoot;
    }

    public static HashMap<Material, Boolean> getMonsterRoomLoot() {
        return monsterRoomLoot;
    }

    public static HashMap<Material, Boolean> getVillageLoot() {
        return villageLoot;
    }

    public static HashMap<Material, Boolean> getMineshaftLoot() {
        return mineshaftLoot;
    }

    public static HashMap<Material, Boolean> getStrongholdLoot() {
        return strongholdLoot;
    }

    public static HashMap<Material, Boolean> getRuinedPortalLoot() {
        return ruinedPortalLoot;
    }

    public static HashMap<Material, Boolean> getPillagerOutpostLoot() {
        return pillagerOutpostLoot;
    }

    public static HashMap<Material, Boolean> getOceanRuinLoot() {
        return oceanRuinLoot;
    }

    public static HashMap<Material, Boolean> getJunglePyramidLoot() {
        return junglePyramidLoot;
    }

    public static HashMap<Material, Boolean> getIglooLoot() {
        return iglooLoot;
    }

    public static HashMap<Material, Boolean> getNetherFortressLoot() {
        return netherFortressLoot;
    }

    public static HashMap<Material, Boolean> getAncientCityLoot() {
        return ancientCityLoot;
    }

    public static HashMap<Material, Boolean> getShipwreckLoot() {
        return shipwreckLoot;
    }

    public static HashMap<Material, Boolean> getBastionRemnantLoot() {
        return bastionRemnantLoot;
    }

    public static HashMap<Material, Boolean> getBastionRemnantEnchantedLoot() {
        return bastionRemnantEnchantedLoot;
    }

    public static HashMap<Material, Boolean> getBuriedTreasureLoot() {
        return buriedTreasureLoot;
    }

    public static HashMap<Material, Boolean> getDesertPyramidLoot() {
        return desertPyramidLoot;
    }

    public static HashMap<Material, Boolean> getEndCityLoot() {
        return endCityLoot;
    }

    public static HashMap<Material, Boolean> getTrialChambersLoot() {
        return trialChambersLoot;
    }

    public static HashMap<Material, Boolean> getTrialChambersEnchantedLoot() {
        return trialChambersEnchantedLoot;
    }

    public static HashMap<PotionType, Boolean> getTrialChambersArrowEffects() {
        return trialChambersArrowEffects;
    }

    public static HashMap<PotionType, Boolean> getTrialChambersPotions() {
        return trialChambersPotions;
    }

    public static HashMap<Material, Boolean> getLoot(String structure) {
        return structureMaterials.get(structure);
    }

    public static ArrayList<Material> getStructureMaterials(boolean filter, String structure) {
        ArrayList<Material> materials = new ArrayList<>();

        if (structureMaterials.containsKey(structure)) {
            HashMap<Material, Boolean> materialMap = structureMaterials.get(structure);
            // Could switch the first if statement, but this is more readable
            for (HashMap.Entry<Material, Boolean> entry : materialMap.entrySet()) {
                if (filter) {
                    if (!entry.getValue()) {
                        materials.add(entry.getKey());
                    }
                } else {
                    materials.add(entry.getKey());
                }
            }
        } else {
            System.out.println("Structure not found");
        }

        materials.sort(new Comparator<Material>() {
            @Override
            public int compare(Material o1, Material o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        return materials;
    }

    public static PlayerMenuUtility getPlayerMenuUtility(Player p) {
        PlayerMenuUtility playerMenuUtility;
        if (!playerMenuUtilityMap.containsKey(p)) {
            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);
            return playerMenuUtility;
        } else {
            return playerMenuUtilityMap.get(p);
        }
    }

    public static void removePlayerFromUtility(Player p) {
        playerMenuUtilityMap.remove(p);
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        loadData();

        plugin = this;
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new StructureLootListener(), this);
        getServer().getPluginManager().registerEvents(new ItemDropListener(), this);
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getCommand("structures").setExecutor(new StructuresCommand());
        getCommand("structure").setExecutor(new StructureCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveData();
    }

    public void saveData() {
        getLogger().info("Saving data...");
        getConfig().set("selectedStructure", selectedStructure);

        List<String> structures = new ArrayList<>();
        for (HashMap.Entry<String, Boolean> entry : structureProgress.entrySet()) {
            if (entry.getValue()) {
                structures.add(entry.getKey());
            }
        }
        getConfig().set("completedStructures", structures);

        List<String> collectedItems = new ArrayList<>();
        List<String> collectedEnchantedItems = new ArrayList<>();
        List<String> collectedPotions = new ArrayList<>();
        List<String> collectedArrows = new ArrayList<>();
        if (!selectedStructure.isEmpty()) {
            if (selectedStructure.equals("bastion")) {
                for (HashMap.Entry<Material, Boolean> entry : bastionRemnantLoot.entrySet()) {
                    if (entry.getValue()) {
                        collectedItems.add(entry.getKey().toString());
                    }
                }
                for (HashMap.Entry<Material, Boolean> entry : bastionRemnantEnchantedLoot.entrySet()) {
                    if (entry.getValue()) {
                        collectedEnchantedItems.add(entry.getKey().toString());
                    }
                }
            } else if (selectedStructure.equals("trial_chambers")) {
                for (HashMap.Entry<Material, Boolean> entry : trialChambersLoot.entrySet()) {
                    if (entry.getValue()) {
                        collectedItems.add(entry.getKey().toString());
                    }
                }
                for (HashMap.Entry<Material, Boolean> entry : trialChambersEnchantedLoot.entrySet()) {
                    if (entry.getValue()) {
                        collectedEnchantedItems.add(entry.getKey().toString());
                    }
                }
                for (HashMap.Entry<PotionType, Boolean> entry : trialChambersArrowEffects.entrySet()) {
                    if (entry.getValue()) {
                        collectedArrows.add(entry.getKey().toString());
                    }
                }
                for (HashMap.Entry<PotionType, Boolean> entry : trialChambersPotions.entrySet()) {
                    if (entry.getValue()) {
                        collectedPotions.add(entry.getKey().toString());
                    }
                }
            } else {
                for (HashMap.Entry<Material, Boolean> entry : structureMaterials.get(selectedStructure).entrySet()) {
                    if (entry.getValue()) {
                        collectedItems.add(entry.getKey().toString());
                    }
                }
            }
        }

        List<Map<String, Object>> serializedLocations = new ArrayList<>();
        for (Block block : placedBlocks) {
            serializedLocations.add(block.getLocation().serialize()); // Serialize each location
        }

        getConfig().set("collectedItems", collectedItems);
        getConfig().set("collectedEnchantedItems", collectedEnchantedItems);
        getConfig().set("collectedPotions", collectedPotions);
        getConfig().set("collectedArrows", collectedArrows);
        StructureScoreboard scoreboard = StructureScoreboard.getInstance();
        scoreboard.stageSaveData();
        getConfig().set("placedBlocks", serializedLocations);
        saveConfig();
    }

    private void loadData() {
        getLogger().info("Loading data...");

        selectedStructure = getConfig().getString("selectedStructure");

        for (String structure : getConfig().getStringList("completedStructures")) {
            structureProgress.replace(structure, true);
        }

        if (!selectedStructure.isEmpty()) {
            if (selectedStructure.equals("bastion")) {
                for (String material : getConfig().getStringList("collectedItems")) {
                    bastionRemnantLoot.replace(Material.valueOf(material), true);
                }
                for (String material : getConfig().getStringList("collectedEnchantedItems")) {
                    bastionRemnantEnchantedLoot.replace(Material.valueOf(material), true);
                }
            } else if (selectedStructure.equals("trial_chambers")) {
                for (String material : getConfig().getStringList("collectedItems")) {
                    trialChambersLoot.replace(Material.valueOf(material), true);
                }
                for (String material : getConfig().getStringList("collectedEnchantedItems")) {
                    trialChambersEnchantedLoot.replace(Material.valueOf(material), true);
                }
                for (String potionType : getConfig().getStringList("collectedPotions")) {
                    trialChambersPotions.replace(PotionType.valueOf(potionType), true);
                }
                for (String potionType : getConfig().getStringList("collectedArrows")) {
                    trialChambersArrowEffects.replace(PotionType.valueOf(potionType), true);
                }
            } else {
                HashMap<Material, Boolean> materials = structureMaterials.get(selectedStructure);
                for (String material : getConfig().getStringList("collectedItems")) {
                    materials.replace(Material.valueOf(material), true);
                }
            }
        }

        List<Location> locations = new ArrayList<>();
        List<Map<?, ?>> serializedLocations = getConfig().getMapList("placedBlocks");
        for (Map<?, ?> map : serializedLocations) {
            locations.add(Location.deserialize((Map<String, Object>) map));
        }
        for (Location location : locations) {
            Block block = location.getBlock();
            placedBlocks.add(block);
        }
    }

}
