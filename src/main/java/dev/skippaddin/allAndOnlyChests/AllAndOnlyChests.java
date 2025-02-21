package dev.skippaddin.allAndOnlyChests;

import dev.skippaddin.allAndOnlyChests.challenge.ChallengeData;
import dev.skippaddin.allAndOnlyChests.commands.*;
import dev.skippaddin.allAndOnlyChests.listeners.*;
import dev.skippaddin.allAndOnlyChests.menuSystem.utility.PlayerMenuUtility;
import dev.skippaddin.allAndOnlyChests.scoreboard.StructureScoreboard;
import dev.skippaddin.allAndOnlyChests.structures.BastionRemnant;
import dev.skippaddin.allAndOnlyChests.structures.TrialChambers;
import dev.skippaddin.allAndOnlyChests.structures.utility.StructureUtility;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

/**
 * Main class getting initialized by the Server when loading the plugin
 */
public final class AllAndOnlyChests extends JavaPlugin implements Listener {

    /**
     * Plugin instance to get access to non-static fields and methods
     */
    private static AllAndOnlyChests plugin;

    /**
     * Utility Map to store PlayerMenuUtility for loading Menus for specific players
     */
    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    /**
     * Returns a PlayerMenuUtility for a player. Generates one if it does not yet exist and adds it to the
     * playerMenuUtilityMap
     * @param p the Player instance
     * @return a PlayerUtilityMap to pass to Menus.
     */
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

    /**
     * Removes a PlayerMenuUtility from the map
     * @param p the player instance
     */
    public static void removePlayerFromUtility(Player p) {
        playerMenuUtilityMap.remove(p);
    }

    /**
     * @return The instance of the plugin
     */
    public static AllAndOnlyChests getPlugin() {
        return plugin;
    }

    /**
     * Startup logic of the Plugin.
     * Saves the predefined config if it does not exist.
     * Registers EventListeners and CommandExecutors
     */
    @Override
    public void onEnable() {
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
        getCommand("structure").setTabCompleter(new StructureTabCompleter());
        getCommand("scoreboard").setExecutor(new ScoreboardCommand());
        getCommand("scoreboard").setTabCompleter(new ScoreboardTabCompleter());
        getCommand("save").setExecutor(new SaveCommand());
        getCommand("drops").setExecutor(new ItemDropsCommand());
        getCommand("drops").setTabCompleter(new ItemDropsTabCompleter());

        new BukkitRunnable() {
            @Override
            public void run() {
                saveData();
            }
        }.runTaskTimer(this, 6000, 6000);
    }

    /**
     * Shutdown logic.
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveData();
    }

    /**
     * Saves the data to the config. No database used, because the data is pretty limited.
     */
    public void saveData() {
        getLogger().info("Saving data...");
        if (!ChallengeData.getSaved()) {
            getConfig().set("selectedStructure", ChallengeData.getSelectedStructure().getName());
            getConfig().set("dropsAllowed", ChallengeData.isDropsAllowed());

            List<String> structures = new ArrayList<>();
            for (HashMap.Entry<String, Boolean> entry : ChallengeData.getStructureProgress().entrySet()) {
                if (entry.getValue()) {
                    structures.add(entry.getKey());
                }
            }
            getConfig().set("completedStructures", structures);

            List<String> collectedItems = new ArrayList<>();
            List<String> collectedEnchantedItems = new ArrayList<>();
            List<String> collectedPotions = new ArrayList<>();
            List<String> collectedArrows = new ArrayList<>();
            if (!ChallengeData.getSelectedStructure().getName().isEmpty()) {
                if (ChallengeData.getSelectedStructure().getName().equals("bastion")) {
                    BastionRemnant bastionRemnant = (BastionRemnant) ChallengeData.getSelectedStructure();
                    for (HashMap.Entry<Material, Boolean> entry : bastionRemnant.getLoot().entrySet()) {
                        if (entry.getValue()) {
                            collectedItems.add(entry.getKey().toString());
                        }
                    }
                    for (HashMap.Entry<Material, Boolean> entry : bastionRemnant.getEnchantedLoot().entrySet()) {
                        if (entry.getValue()) {
                            collectedEnchantedItems.add(entry.getKey().toString());
                        }
                    }
                } else if (ChallengeData.getSelectedStructure().getName().equals("trial_chambers")) {
                    TrialChambers trialChambers = (TrialChambers) ChallengeData.getSelectedStructure();
                    for (HashMap.Entry<Material, Boolean> entry : trialChambers.getLoot().entrySet()) {
                        if (entry.getValue()) {
                            collectedItems.add(entry.getKey().toString());
                        }
                    }
                    if (trialChambers.getEnchantedLoot().getValue()) {
                        collectedEnchantedItems.add(trialChambers.getEnchantedLoot().getKey().toString());
                    }
                    for (HashMap.Entry<PotionType, Boolean> entry : trialChambers.getArrowEffects().entrySet()) {
                        if (entry.getValue()) {
                            collectedArrows.add(entry.getKey().toString());
                        }
                    }
                    for (HashMap.Entry<PotionType, Boolean> entry : trialChambers.getPotions().entrySet()) {
                        if (entry.getValue()) {
                            collectedPotions.add(entry.getKey().toString());
                        }
                    }
                } else {
                    for (HashMap.Entry<Material, Boolean> entry : ChallengeData.getSelectedStructure().getLoot().entrySet()) {
                        if (entry.getValue()) {
                            collectedItems.add(entry.getKey().toString());
                        }
                    }
                }
            }

            List<Map<String, Object>> serializedLocations = new ArrayList<>();
            for (Block block : ChallengeData.getPlacedBlocks()) {
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

            ChallengeData.setSaved(true);
            getLogger().info("Data saved");
        } else {
            getLogger().info("Data already saved after recent changes");
        }
    }

    /**
     * Loads the data from the config.
     */
    private void loadData() {
        getLogger().info("Loading data...");

        ChallengeData.setSelectedStructure(StructureUtility.getStructure(getConfig().getString("selectedStructure")));

        if (getConfig().contains("dropsAllowed")) {
            ChallengeData.setDropsAllowed(getConfig().getBoolean("dropsAllowed"));
        }

        for (String structure : getConfig().getStringList("completedStructures")) {
            ChallengeData.getStructureProgress().replace(structure, true);
        }

        if (!ChallengeData.getSelectedStructure().getName().isEmpty()) {
            if (ChallengeData.getSelectedStructure() instanceof BastionRemnant bastionRemnant) {
                for (String material : getConfig().getStringList("collectedItems")) {
                    bastionRemnant.getLoot().replace(Material.valueOf(material), true);
                }
                for (String material : getConfig().getStringList("collectedEnchantedItems")) {
                    bastionRemnant.getEnchantedLoot().replace(Material.valueOf(material), true);
                }
            } else if (ChallengeData.getSelectedStructure() instanceof TrialChambers trialChambers) {
                for (String material : getConfig().getStringList("collectedItems")) {
                    trialChambers.getLoot().replace(Material.valueOf(material), true);
                }
                if (getConfig().contains("collectedEnchantedItems")) {
                    trialChambers.getEnchantedLoot().setValue(true);
                }
                for (String potionType : getConfig().getStringList("collectedPotions")) {
                    trialChambers.getPotions().replace(PotionType.valueOf(potionType), true);
                }
                for (String potionType : getConfig().getStringList("collectedArrows")) {
                    trialChambers.getArrowEffects().replace(PotionType.valueOf(potionType), true);
                }
            } else {
                HashMap<Material, Boolean> materials = ChallengeData.getSelectedStructure().getLoot();
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
            ChallengeData.getPlacedBlocks().add(block);
        }
    }

}
