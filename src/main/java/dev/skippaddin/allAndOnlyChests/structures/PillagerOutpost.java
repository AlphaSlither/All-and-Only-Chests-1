package dev.skippaddin.allAndOnlyChests.structures;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class PillagerOutpost extends Structure {

    private final HashMap<Material, Boolean> loot = new HashMap<>() {{
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

    public static int getItemCount() {
        return 13;
    }

    @Override
    public String getName() {
        return "pillager_outpost";
    }

    @Override
    public @NotNull HashMap<Material, Boolean> getLoot() {
        return loot;
    }
}
