package dev.skippaddin.allAndOnlyChests.listeners;

import dev.skippaddin.allAndOnlyChests.AllAndOnlyChests;
import dev.skippaddin.allAndOnlyChests.menuSystem.Menu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockState;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseLootEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class StructureLootListener implements Listener {

    private final HashSet<InventoryType> allowedInventories = new HashSet<>() {{
        add(InventoryType.CREATIVE);
        add(InventoryType.FURNACE);
        add(InventoryType.ANVIL);
        add(InventoryType.CRAFTING);
        add(InventoryType.BLAST_FURNACE);
        add(InventoryType.WORKBENCH);
        add(InventoryType.GRINDSTONE);
        add(InventoryType.BEACON);
        add(InventoryType.CARTOGRAPHY);
        add(InventoryType.SMOKER);
        add(InventoryType.LECTERN);
        add(InventoryType.ENDER_CHEST);
    }};

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {

        InventoryHolder holder = e.getInventory().getHolder();
        if (holder != null) {
            if (e.getInventory().getType() == InventoryType.MERCHANT || (holder instanceof Dispenser && !AllAndOnlyChests.getPlacedBlocks().contains(((BlockState) holder).getBlock())) || (e.getInventory().getType() == InventoryType.BREWING && !AllAndOnlyChests.getPlacedBlocks().contains(((BlockState) holder).getBlock()))) {
                e.setCancelled(true);
            } else if (holder instanceof Menu || (holder instanceof BlockState blockState && AllAndOnlyChests.getPlacedBlocks().contains(blockState.getBlock())) || allowedInventories.contains(e.getInventory().getType()) || Arrays.stream(e.getInventory().getStorageContents()).noneMatch(i -> i != null && i.hasItemMeta() && Arrays.stream(AllAndOnlyChests.getStructures()).anyMatch(s -> s.equals(i.getItemMeta().getItemName())))) {
                return;
            } else if (!AllAndOnlyChests.getSelectedStructure().isEmpty()) {
                ItemStack structureItem = null;
                for (ItemStack item : e.getInventory().getStorageContents()) {
                    if (item != null) {
                        ItemMeta itemMeta = item.getItemMeta();
                        if (itemMeta != null && itemMeta.getItemName().equals(AllAndOnlyChests.getSelectedStructure())) {
                            structureItem = item;
                            break;
                        }
                    }
                }
                if (structureItem != null && !AllAndOnlyChests.getSelectedStructure().equals("bastion") && !AllAndOnlyChests.getSelectedStructure().equals("trial_chambers")) {
                    e.getInventory().remove(structureItem);
                    HashMap<Material, Boolean> structureMats = AllAndOnlyChests.getLoot(AllAndOnlyChests.getSelectedStructure());
                    ArrayList<String> newItems = new ArrayList<>();
                    for (ItemStack item : e.getInventory().getStorageContents()) {
                        if (item != null) {
                            Material material = item.getType();
                            Boolean found = structureMats.get(material);
                            if (found != null && !found) {
                                structureMats.replace(material, true);
                                String[] words = material.toString().split("_");
                                StringBuilder displayNameBuilder = new StringBuilder();
                                for (String word : words) {
                                    displayNameBuilder.append(word.charAt(0)).append(word.substring(1).toLowerCase()).append(" ");
                                }
                                String displayName = displayNameBuilder.toString().strip();
                                newItems.add(displayName);
                            }
                        }
                    }
                    if (!newItems.isEmpty()) {
                        boolean allMatch = structureMats.values().stream().allMatch(b -> b);
                        progressChallenge(newItems, allMatch);
                    }
                } else if (structureItem != null && AllAndOnlyChests.getSelectedStructure().equals("bastion")) {
                    e.getInventory().remove(structureItem);
                    HashMap<Material, Boolean> bastionMats = AllAndOnlyChests.getBastionRemnantLoot();
                    HashMap<Material, Boolean> bastionEnchantedMats = AllAndOnlyChests.getBastionRemnantEnchantedLoot();
                    ArrayList<String> newItems = new ArrayList<>();
                    for (ItemStack item : e.getInventory().getStorageContents()) {
                        if (item != null) {
                            Material material = item.getType();
                            if (bastionEnchantedMats.containsKey(material) && !item.getEnchantments().isEmpty()) {
                                Boolean found = bastionEnchantedMats.get(material);
                                if (!found) {
                                    bastionEnchantedMats.replace(material, true);
                                    String[] words = material.toString().split("_");
                                    StringBuilder displayNameBuilder = new StringBuilder();
                                    for (String word : words) {
                                        displayNameBuilder.append(word.charAt(0)).append(word.substring(1).toLowerCase()).append(" ");
                                    }
                                    displayNameBuilder.append("enchanted");
                                    String displayName = displayNameBuilder.toString();
                                    newItems.add(displayName);
                                }

                            } else {
                                Boolean found = bastionMats.get(material);
                                if (found != null && !found) {
                                    bastionMats.replace(material, true);
                                    String[] words = material.toString().split("_");
                                    StringBuilder displayNameBuilder = new StringBuilder();
                                    for (String word : words) {
                                        displayNameBuilder.append(word.charAt(0)).append(word.substring(1).toLowerCase()).append(" ");
                                    }
                                    String displayName = displayNameBuilder.toString().strip();
                                    newItems.add(displayName);
                                }
                            }
                        }
                    }
                    if (!newItems.isEmpty()) {
                        boolean allMatch =
                                bastionMats.values().stream().allMatch(b -> b) && bastionEnchantedMats.values().stream().allMatch(b -> b);
                        progressChallenge(newItems, allMatch);
                    }
                    if (!newItems.isEmpty()) {
                        boolean allMatch =
                                bastionMats.values().stream().allMatch(b -> b) && bastionEnchantedMats.values().stream().allMatch(b -> b);
                        progressChallenge(newItems, allMatch);
                    }
                    //Trial chambers
                } else if (structureItem != null) {

                    e.getInventory().remove(structureItem);
                    HashMap<Material, Boolean> trialChambersLoot = AllAndOnlyChests.getTrialChambersLoot();
                    HashMap<Material, Boolean> trialChambersEnchantedLoot = AllAndOnlyChests.getTrialChambersEnchantedLoot();
                    HashMap<PotionType, Boolean> trialChambersArrows = AllAndOnlyChests.getTrialChambersArrowEffects();
                    HashMap<PotionType, Boolean> trialChambersPotions = AllAndOnlyChests.getTrialChambersPotions();
                    ArrayList<String> newItems = new ArrayList<>();

                    processTrialChambersLoot(e.getInventory().getStorageContents(), newItems, trialChambersLoot, trialChambersEnchantedLoot, trialChambersArrows, trialChambersPotions);

                    if (!newItems.isEmpty()) {
                        boolean allMatch =
                                trialChambersLoot.values().stream().allMatch(b -> b) && trialChambersEnchantedLoot.values().stream().allMatch(b -> b) && trialChambersArrows.values().stream().allMatch(b -> b) && trialChambersPotions.values().stream().allMatch(b -> b);
                        progressChallenge(newItems, allMatch);
                    }
                } else {
                    e.setCancelled(true);
                }
            } else {
                e.setCancelled(true);
            }
        }
    }

    private void progressChallenge(ArrayList<String> items, boolean allMatch) {
        if (allMatch) {
            AllAndOnlyChests.getStructureProgress().replace(AllAndOnlyChests.getSelectedStructure(), true);
            AllAndOnlyChests.setSelectedStructure("");
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(ChatColor.GRAY + "Found " + items.size() + " new items:");
            for (String item : items) {
                player.sendMessage(" [" + item + "]");
            }
            if (allMatch) {
                player.sendMessage(ChatColor.GOLD + "Completed structure!");
                player.playSound(player, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f);
            } else {
                player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
            }
        }
    }

    @EventHandler
    public void onLootGenerate(LootGenerateEvent e) {
        if (!(e.getInventoryHolder() instanceof Dispenser)) {
            String key = e.getLootTable().getKey().toString();
            for (String structure : AllAndOnlyChests.getStructures()) {
                if (key.contains(structure)) {
                    ItemStack dirt = new ItemStack(Material.DIRT);
                    ItemMeta dirtMeta = dirt.getItemMeta();
                    dirtMeta.setItemName(structure);
                    dirt.setItemMeta(dirtMeta);
                    e.getLoot().add(dirt);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onDispenseLoot(BlockDispenseLootEvent e) {
        if (e.getBlock().getType() == Material.VAULT || e.getBlock().getType() == Material.TRIAL_SPAWNER) {
            if (AllAndOnlyChests.getSelectedStructure().equals("trial_chambers")) {
                HashMap<Material, Boolean> trialChambersLoot = AllAndOnlyChests.getTrialChambersLoot();
                HashMap<Material, Boolean> trialChambersEnchantedLoot = AllAndOnlyChests.getTrialChambersEnchantedLoot();
                HashMap<PotionType, Boolean> trialChambersArrows = AllAndOnlyChests.getTrialChambersArrowEffects();
                HashMap<PotionType, Boolean> trialChambersPotions = AllAndOnlyChests.getTrialChambersPotions();
                ArrayList<String> newItems = new ArrayList<>();

                List<ItemStack> itemsList = e.getDispensedLoot();
                ItemStack[] items = new ItemStack[itemsList.size()];
                itemsList.toArray(items);

                processTrialChambersLoot(items, newItems, trialChambersLoot, trialChambersEnchantedLoot, trialChambersArrows, trialChambersPotions);

                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(ChatColor.GRAY + "Found new items:");
                }

                boolean allMatch = false;

                if (!newItems.isEmpty()) {
                    allMatch = trialChambersLoot.values().stream().allMatch(b -> b) && trialChambersEnchantedLoot.values().stream().allMatch(b -> b) && trialChambersArrows.values().stream().allMatch(b -> b) && trialChambersPotions.values().stream().allMatch(b -> b);
                    if (allMatch) {
                        AllAndOnlyChests.getStructureProgress().replace(AllAndOnlyChests.getSelectedStructure(), true);
                        AllAndOnlyChests.setSelectedStructure("");
                    }
                }

                boolean finalAllMatch = allMatch;

                new BukkitRunnable() {

                    int count = itemsList.size();

                    @Override
                    public void run() {
                        if (count == 0) {
                            if (newItems.isEmpty()) {
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    player.sendMessage(ChatColor.RED + "None");
                                }
                            } else {
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    for (String item : newItems) {
                                        player.sendMessage(" [" + item + "]");
                                    }
                                    if (finalAllMatch) {
                                        player.sendMessage(ChatColor.GOLD + "Completed structure");
                                        player.playSound(player, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f);
                                    } else {
                                        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                                    }
                                }
                            }
                            this.cancel();
                        }
                        count--;
                    }
                }.runTaskTimer(AllAndOnlyChests.getPlugin(), 20, 20);


            } else {
                e.setCancelled(true);
            }
        }
    }

    private void processTrialChambersLoot(ItemStack[] items, ArrayList<String> newItems, HashMap<Material, Boolean> trialChambersLoot, HashMap<Material, Boolean> trialChambersEnchantedLoot, HashMap<PotionType, Boolean> trialChambersArrows, HashMap<PotionType, Boolean> trialChambersPotions) {

        for (ItemStack item : items) {
            if (item != null) {
                Material material = item.getType();
                if (material == Material.POTION) {
                    PotionMeta potionMeta = (PotionMeta) item.getItemMeta();
                    PotionType potionType = potionMeta.getBasePotionType();
                    String[] splitTypes = potionType.toString().split("_");
                    if (splitTypes.length == 1) {
                        potionType = PotionType.valueOf(splitTypes[0]);
                    } else {
                        potionType = PotionType.valueOf(splitTypes[1]);
                    }
                    Boolean found = trialChambersPotions.get(potionType);
                    if (found != null && !found) {
                        trialChambersPotions.replace(potionType, true);
                        String type = potionType.toString();
                        String displayName =
                                "Potion of " + type.charAt(0) + type.substring(1).toLowerCase();
                        newItems.add(displayName);
                    }
                } else if (material == Material.TIPPED_ARROW) {
                    PotionMeta arrowMeta = (PotionMeta) item.getItemMeta();
                    PotionType arrowType = arrowMeta.getBasePotionType();
                    String[] splitTypes = arrowType.toString().split("_");
                    if (splitTypes.length == 1) {
                        arrowType = PotionType.valueOf(splitTypes[0]);
                    } else {
                        arrowType = PotionType.valueOf(splitTypes[1]);
                    }
                    Boolean found = trialChambersArrows.get(arrowType);
                    if (found != null && !found) {
                        trialChambersArrows.replace(arrowType, true);
                        String type = arrowType.toString();
                        String displayName = "Arrow of " + type.charAt(0) + type.substring(1).toLowerCase();
                        newItems.add(displayName);
                    }
                } else if (!item.getEnchantments().isEmpty() && AllAndOnlyChests.getTrialChambersEnchantedLoot().containsKey(material)) {
                    Boolean found = trialChambersEnchantedLoot.get(material);
                    if (!found) {
                        trialChambersEnchantedLoot.replace(material, true);
                        String[] words = material.toString().split("_");
                        StringBuilder displayNameBuilder = new StringBuilder();
                        for (String word : words) {
                            displayNameBuilder.append(word.charAt(0)).append(word.substring(1).toLowerCase()).append(" ");
                        }
                        displayNameBuilder.append("enchanted");
                        String displayName = displayNameBuilder.toString();
                        newItems.add(displayName);
                    }
                } else {
                    Boolean found = trialChambersLoot.get(material);
                    if (found != null && !found) {
                        trialChambersLoot.replace(material, true);
                        String[] words = material.toString().split("_");
                        StringBuilder displayNameBuilder = new StringBuilder();
                        for (String word : words) {
                            displayNameBuilder.append(word.charAt(0)).append(word.substring(1).toLowerCase()).append(" ");
                        }
                        String displayName = displayNameBuilder.toString().strip();
                        newItems.add(displayName);
                    }
                }
            }
        }
    }

}
