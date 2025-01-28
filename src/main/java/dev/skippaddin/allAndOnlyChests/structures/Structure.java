package dev.skippaddin.allAndOnlyChests.structures;

import org.bukkit.Material;

import java.util.HashMap;

public abstract class Structure {

    public abstract String getName();

    public abstract HashMap<Material, Boolean> getLoot();

}
