package dev.skippaddin.allAndOnlyChests.structures;

import org.bukkit.Material;

import java.util.HashMap;

public class EmptyStructure extends Structure {

    @Override
    public String getName() {
        return "";
    }

    @Override
    public HashMap<Material, Boolean> getLoot() {
        return null;
    }
}
