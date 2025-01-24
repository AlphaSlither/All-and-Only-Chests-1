package dev.skippaddin.allAndOnlyChests.listeners;

import dev.skippaddin.allAndOnlyChests.AllAndOnlyChests;
import dev.skippaddin.allAndOnlyChests.challenge.ChallengeData;
import dev.skippaddin.allAndOnlyChests.menuSystem.Menu;
import dev.skippaddin.allAndOnlyChests.menuSystem.utility.StructureItemUtility;
import dev.skippaddin.allAndOnlyChests.scoreboard.StructureScoreboard;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockState;
import org.bukkit.block.DecoratedPot;
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


    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {

        InventoryHolder holder = e.getInventory().getHolder();
        if (holder != null) {
            if (e.getInventory().getType() == InventoryType.MERCHANT || (holder instanceof Dispenser && !ChallengeData.getPlacedBlocks().contains(((BlockState) holder).getBlock())) || (e.getInventory().getType() == InventoryType.BREWING && !ChallengeData.getPlacedBlocks().contains(((BlockState) holder).getBlock()))) {
                if (!ChallengeData.isDropsAllowed()) {
                    e.setCancelled(true);
                }
            } else if (holder instanceof Menu || (holder instanceof BlockState blockState && ChallengeData.getPlacedBlocks().contains(blockState.getBlock())) || Arrays.stream(e.getInventory().getStorageContents()).noneMatch(i -> i != null && i.hasItemMeta() && Arrays.stream(ChallengeData.getStructures()).anyMatch(s -> s.equals(i.getItemMeta().getItemName())))) {
                return;
            } else if (!AllAndOnlyChests.getSelectedStructure().isEmpty()) {
                boolean structureChest = false;
                for (ItemStack item : e.getInventory().getStorageContents()) {
                    if (item != null && item.hasItemMeta() && item.getItemMeta().getItemName().equals(AllAndOnlyChests.getSelectedStructure())) {
                        structureChest = true;
                        StructureItemUtility.processStructureItem(item);
                    }
                }
                if (structureChest) {
                    if (!AllAndOnlyChests.getSelectedStructure().equals("bastion") && !AllAndOnlyChests.getSelectedStructure().equals("trial_chambers")) {
                        HashMap<Material, Boolean> structureMats = AllAndOnlyChests.getLoot(AllAndOnlyChests.getSelectedStructure());
                        ArrayList<Component> newItems = new ArrayList<>();
                        for (ItemStack item : e.getInventory().getStorageContents()) {
                            if (item != null) {
                                Material material = item.getType();
                                Boolean found = structureMats.get(material);
                                if (found != null && !found) {
                                    structureMats.replace(material, true);
                                    newItems.add(item.displayName());
                                }
                            }
                        }
                        if (!newItems.isEmpty()) {
                            boolean allMatch = structureMats.values().stream().allMatch(b -> b);
                            progressChallenge(newItems, allMatch);
                        }
                    } else if (AllAndOnlyChests.getSelectedStructure().equals("bastion")) {
                        HashMap<Material, Boolean> bastionMats = AllAndOnlyChests.getBastionRemnantLoot();
                        HashMap<Material, Boolean> bastionEnchantedMats =
                                AllAndOnlyChests.getBastionRemnantEnchantedLoot();
                        ArrayList<Component> newItems = new ArrayList<>();
                        for (ItemStack item : e.getInventory().getStorageContents()) {
                            if (item != null) {
                                Material material = item.getType();
                                if (bastionEnchantedMats.containsKey(material) && !item.getEnchantments().isEmpty()) {
                                    Boolean found = bastionEnchantedMats.get(material);
                                    if (!found) {
                                        bastionEnchantedMats.replace(material, true);
                                        newItems.add(item.displayName());
                                    }

                                } else {
                                    Boolean found = bastionMats.get(material);
                                    if (found != null && !found) {
                                        bastionMats.replace(material, true);
                                        newItems.add(item.displayName());
                                    }
                                }
                            }
                        }
                        if (!newItems.isEmpty()) {
                            boolean allMatch =
                                    bastionMats.values().stream().allMatch(b -> b) && bastionEnchantedMats.values().stream().allMatch(b -> b);
                            progressChallenge(newItems, allMatch);
                        }
                        //Trial chambers
                    } else {
                        HashMap<Material, Boolean> trialChambersLoot = AllAndOnlyChests.getTrialChambersLoot();
                        HashMap<Material, Boolean> trialChambersEnchantedLoot =
                                AllAndOnlyChests.getTrialChambersEnchantedLoot();
                        HashMap<PotionType, Boolean> trialChambersArrows =
                                AllAndOnlyChests.getTrialChambersArrowEffects();
                        HashMap<PotionType, Boolean> trialChambersPotions = AllAndOnlyChests.getTrialChambersPotions();
                        ArrayList<Component> newItems = new ArrayList<>();

                        processTrialChambersLoot(e.getInventory().getStorageContents(), newItems, trialChambersLoot,
                                trialChambersEnchantedLoot, trialChambersArrows, trialChambersPotions);

                        if (!newItems.isEmpty()) {
                            boolean allMatch =
                                    trialChambersLoot.values().stream().allMatch(b -> b) && trialChambersEnchantedLoot.values().stream().allMatch(b -> b) && trialChambersArrows.values().stream().allMatch(b -> b) && trialChambersPotions.values().stream().allMatch(b -> b);
                            progressChallenge(newItems, allMatch);
                        }
                    }
                    ChallengeData.setSaved(false);
                } else {
                    e.setCancelled(true);
                }
            } else {
                e.setCancelled(true);
            }
        }
    }

    private void progressChallenge(ArrayList<Component> items, boolean allMatch) {
        if (allMatch) {
            ChallengeData.getStructureProgress().replace(AllAndOnlyChests.getSelectedStructure(), true);
            AllAndOnlyChests.setSelectedStructure("");
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(ChatColor.GRAY + "Found " + items.size() + " new items:");
            for (Component item : items) {
                player.sendMessage(Component.text(" ").append(item));
            }
            if (allMatch) {
                player.sendMessage(ChatColor.GOLD + "Completed structure!");
                player.playSound(player, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f);
            } else {
                player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
            }
        }
        StructureScoreboard scoreboard = StructureScoreboard.getInstance();
        scoreboard.updateItems(items.size());
        scoreboard.updateChests();
    }

    @EventHandler
    public void onLootGenerate(LootGenerateEvent e) {
        if (!(e.getInventoryHolder() instanceof Dispenser || e.getInventoryHolder() instanceof DecoratedPot)) {
            String key = e.getLootTable().getKey().toString();
            for (String structure : ChallengeData.getStructures()) {
                if (key.contains(structure)) {

                    if (!e.getLoot().isEmpty()) {
                        ItemStack item = e.getLoot().getFirst();
                        if (item.hasItemMeta()) {
                            ItemMeta itemMeta = item.getItemMeta();
                            itemMeta.setItemName(structure);
                            item.setItemMeta(itemMeta);
                        } else {
                            ItemStack newItem = new ItemStack(item.getType());
                            ItemMeta itemMeta = newItem.getItemMeta();
                            itemMeta.setItemName(structure);
                            item.setItemMeta(itemMeta);
                        }
                    }
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
                HashMap<Material, Boolean> trialChambersEnchantedLoot =
                        AllAndOnlyChests.getTrialChambersEnchantedLoot();
                HashMap<PotionType, Boolean> trialChambersArrows = AllAndOnlyChests.getTrialChambersArrowEffects();
                HashMap<PotionType, Boolean> trialChambersPotions = AllAndOnlyChests.getTrialChambersPotions();
                ArrayList<Component> newItems = new ArrayList<>();

                List<ItemStack> itemsList = e.getDispensedLoot();
                ItemStack[] items = new ItemStack[itemsList.size()];
                itemsList.toArray(items);

                processTrialChambersLoot(items, newItems, trialChambersLoot, trialChambersEnchantedLoot,
                        trialChambersArrows, trialChambersPotions);

                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(ChatColor.GRAY + "Found new items:");
                }

                boolean allMatch = false;

                if (!newItems.isEmpty()) {
                    allMatch =
                            trialChambersLoot.values().stream().allMatch(b -> b) && trialChambersEnchantedLoot.values().stream().allMatch(b -> b) && trialChambersArrows.values().stream().allMatch(b -> b) && trialChambersPotions.values().stream().allMatch(b -> b);
                    if (allMatch) {
                        ChallengeData.getStructureProgress().replace(AllAndOnlyChests.getSelectedStructure(), true);
                        AllAndOnlyChests.setSelectedStructure("");
                    }
                }

                boolean finalAllMatch = allMatch;
                StructureScoreboard scoreboard = StructureScoreboard.getInstance();
                scoreboard.updateChests();

                ChallengeData.setSaved(false);

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
                                    for (Component item : newItems) {
                                        player.sendMessage(Component.text(" ").append(item));
                                    }
                                    if (finalAllMatch) {
                                        player.sendMessage(ChatColor.GOLD + "Completed structure");
                                        player.playSound(player, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f);
                                    } else {
                                        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                                    }
                                }
                                scoreboard.updateItems(newItems.size());
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

    private void processTrialChambersLoot(ItemStack[] items, ArrayList<Component> newItems, HashMap<Material,
            Boolean> trialChambersLoot, HashMap<Material, Boolean> trialChambersEnchantedLoot, HashMap<PotionType,
            Boolean> trialChambersArrows, HashMap<PotionType, Boolean> trialChambersPotions) {

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
                        newItems.add(item.displayName());
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
                        newItems.add(item.displayName());
                    }
                } else if (!item.getEnchantments().isEmpty() && AllAndOnlyChests.getTrialChambersEnchantedLoot().containsKey(material)) {
                    Boolean found = trialChambersEnchantedLoot.get(material);
                    if (!found) {
                        trialChambersEnchantedLoot.replace(material, true);
                        newItems.add(item.displayName());
                    }
                } else {
                    Boolean found = trialChambersLoot.get(material);
                    if (found != null && !found) {
                        trialChambersLoot.replace(material, true);
                        newItems.add(item.displayName());
                    }
                }
            }
        }
    }
}
