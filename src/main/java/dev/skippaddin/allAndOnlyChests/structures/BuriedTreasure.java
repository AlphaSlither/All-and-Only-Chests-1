package dev.skippaddin.allAndOnlyChests.structures;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class BuriedTreasure extends Structure {

    private final HashMap<Material, Boolean> loot = new HashMap<>() {{
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

    public static int getItemCount() {
        return 12;
    }

    @Override
    public String getName() {
        return "buried_treasure";
    }

    @Override
    public @NotNull HashMap<Material, Boolean> getLoot() {
        return loot;
    }
}
