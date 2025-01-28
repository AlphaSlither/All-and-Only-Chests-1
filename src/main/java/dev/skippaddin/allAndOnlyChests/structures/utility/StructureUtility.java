package dev.skippaddin.allAndOnlyChests.structures.utility;

import dev.skippaddin.allAndOnlyChests.structures.*;

public final class StructureUtility {

    private StructureUtility() {}

    public static Structure getStructure(String structureName) {
        return switch (structureName) {
            case "ancient_city" -> new AncientCity();
            case "buried_treasure" -> new BuriedTreasure();
            case "desert_pyramid" -> new DesertPyramid();
            case "end_city" -> new EndCity();
            case "nether_bridge" -> new NetherFortress();
            case "igloo" -> new Igloo();
            case "jungle_temple" -> new JunglePyramid();
            case "underwater_ruin" -> new OceanRuin();
            case "pillager_outpost" -> new PillagerOutpost();
            case "ruined_portal" -> new RuinedPortal();
            case "shipwreck" -> new Shipwreck();
            case "stronghold" -> new Stronghold();
            case "mineshaft" -> new Mineshaft();
            case "village" -> new Village();
            case "woodland_mansion" -> new WoodlandMansion();
            case "simple_dungeon" -> new MonsterRoom();
            case "bastion" -> new BastionRemnant();
            case "trial_chambers" -> new TrialChambers();
            default -> new EmptyStructure();
        };
    }

}
