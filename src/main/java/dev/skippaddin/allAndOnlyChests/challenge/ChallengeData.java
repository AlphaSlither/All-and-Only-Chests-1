package dev.skippaddin.allAndOnlyChests.challenge;

import dev.skippaddin.allAndOnlyChests.structures.Structure;
import org.bukkit.block.Block;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public final class ChallengeData {

    private ChallengeData() {}

    private static Structure selectedStructure = null;

    private static boolean saved = false;

    private static final HashSet<Block> placedBlocks = new HashSet<>();

    private static boolean dropsAllowed = false;

    // Not a HashSet because the order is important
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

    // HashSet of structures for quick access. Important for quick access in realtime evaluation when destroying blocks
    private static final HashSet<String> structureSet = new HashSet<>() {{
        this.addAll(Arrays.asList(structures));
    }};

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

    public static boolean getSaved() {
        return saved;
    }

    public static void setSaved(boolean b) {
        saved = b;
    }

    public static HashSet<Block> getPlacedBlocks() {
        return placedBlocks;
    }

    public static boolean isDropsAllowed() {
        return dropsAllowed;
    }

    public static boolean flipDropsAllowed() {
        dropsAllowed = !dropsAllowed;
        return dropsAllowed;
    }

    public static void setDropsAllowed(boolean b) {
        dropsAllowed = b;
    }

    public static String[] getStructures() {
        return structures;
    }

    public static HashSet<String> getStructureSet() {return structureSet;}

    public static HashMap<String, Boolean> getStructureProgress() {
        return structureProgress;
    }
}
