package dev.skippaddin.allAndOnlyChests.structures;

import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Material;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class TrialChambers extends Structure {

    private final HashMap<Material, Boolean> loot = new HashMap<>() {{
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

    private final HashMap<PotionType, Boolean> arrowEffects = new HashMap<>() {{
        put(PotionType.POISON, false);
        put(PotionType.SLOWNESS, false);
    }};

    private final HashMap<PotionType, Boolean> potions = new HashMap<>() {{
        put(PotionType.REGENERATION, false);
        put(PotionType.STRENGTH, false);
        put(PotionType.SWIFTNESS, false);
    }};

    private final Pair<Material, Boolean> enchantedLoot = Pair.of(Material.DIAMOND_AXE, false);

    public static int getItemCount() {
        return 64;
    }

    @Override
    public String getName() {
        return "trial_chambers";
    }

    @Override
    public @NotNull HashMap<Material, Boolean> getLoot() {
        return loot;
    }

    public HashMap<PotionType, Boolean> getArrowEffects() {
        return arrowEffects;
    }

    public HashMap<PotionType, Boolean> getPotions() {
        return potions;
    }

    public Pair<Material, Boolean> getEnchantedLoot() {
        return enchantedLoot;
    }
}
