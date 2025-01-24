package dev.skippaddin.allAndOnlyChests.structures;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class RuinedPortal extends Structure {

    private final HashMap<Material, Boolean> loot = new HashMap<>() {{
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

    public static int getItemCount() {
        return 25;
    }

    @Override
    public String getName() {
        return "ruined_portal";
    }

    @Override
    public @NotNull HashMap<Material, Boolean> getLoot() {
        return loot;
    }
}
