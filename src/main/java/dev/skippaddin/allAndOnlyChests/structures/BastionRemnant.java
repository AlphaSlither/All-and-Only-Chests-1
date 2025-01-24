package dev.skippaddin.allAndOnlyChests.structures;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class BastionRemnant extends Structure {

    private final HashMap<Material, Boolean> loot = new HashMap<>() {{
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

    private final HashMap<Material, Boolean> enchantedLoot = new HashMap<>() {{
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

    public static int getItemCount() {
        return 65;
    }

    @Override
    public String getName() {
        return "bastion";
    }

    @Override
    public @NotNull HashMap<Material, Boolean> getLoot() {
        return loot;
    }

    public HashMap<Material, Boolean> getEnchantedLoot() {
        return enchantedLoot;
    }
}
