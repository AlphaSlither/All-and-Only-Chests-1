package dev.skippaddin.allAndOnlyChests.listeners;

import dev.skippaddin.allAndOnlyChests.AllAndOnlyChests;
import dev.skippaddin.allAndOnlyChests.menuSystem.Menu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.*;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

//Contains all events a player can receive items from
public class ItemListener implements Listener {

    private final HashMap<Player, InventoryHolder> lastOpened = new HashMap<>();

    private final HashSet<InventoryType> excludedInventories = new HashSet<>() {{
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
    }};

    //Prevents getting items from item frame
    @EventHandler
    public void onItemFrameDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof ItemFrame) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onHangingBreak(HangingBreakEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        if (block.getType() == Material.FARMLAND) {
            Block relative = block.getRelative(0, 1, 0);
            if (relative.getType() != Material.AIR && relative.getType() != Material.CAVE_AIR && relative.getType() != Material.VOID_AIR && !AllAndOnlyChests.getPlacedBlocks().remove(relative)) {
                relative.setType(Material.AIR);
            }
        }
        if (!AllAndOnlyChests.getPlacedBlocks().remove(block)) {
            block.setType(Material.AIR);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        AllAndOnlyChests.getPlacedBlocks().add(e.getBlockPlaced());
    }

    @EventHandler
    public void onEntityDropItem(EntityDropItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        e.getDrops().clear();
    }

    @EventHandler
    public void onCatch(PlayerFishEvent e) {
        if (e.getState() == PlayerFishEvent.State.CAUGHT_ENTITY || e.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            e.getCaught().remove();
        }
    }

    @EventHandler
    public void onExplodeEvent(BlockExplodeEvent e) {
        for (Block block : e.blockList()) {
            block.setType(Material.AIR);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        for (Block block : e.blockList()) {
            block.setType(Material.AIR);
        }
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {
        InventoryHolder holder = e.getInventory().getHolder();
        if (holder != null) {
            if (holder instanceof Menu || (holder instanceof BlockState blockState && AllAndOnlyChests.getPlacedBlocks().contains(blockState.getBlock())) || excludedInventories.contains(e.getInventory().getType()) || Arrays.stream(e.getInventory().getStorageContents()).noneMatch(i -> i != null && i.hasItemMeta() && Arrays.stream(AllAndOnlyChests.getStructures()).anyMatch(s -> s.equals(i.getItemMeta().getItemName())))) {
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
                    if (lastOpened.containsKey((Player) e.getPlayer())) {
                        lastOpened.replace((Player) e.getPlayer(), e.getInventory().getHolder());
                    } else {
                        lastOpened.put((Player) e.getPlayer(), e.getInventory().getHolder());
                    }
                    e.getInventory().remove(structureItem);
                    HashMap<Material, Boolean> structureMats =
                            AllAndOnlyChests.getLoot(AllAndOnlyChests.getSelectedStructure());
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
                    if (lastOpened.containsKey((Player) e.getPlayer())) {
                        lastOpened.replace((Player) e.getPlayer(), e.getInventory().getHolder());
                    } else {
                        lastOpened.put((Player) e.getPlayer(), e.getInventory().getHolder());
                    }
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
                } else if (structureItem != null) {

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
    public void onCrafterCraftItem(CrafterCraftEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onLeaveDecay(LeavesDecayEvent e) {
        e.getBlock().setType(Material.AIR);
    }

    @EventHandler
    public void onDispense(BlockDispenseEvent e) {
        System.out.println("BlockDispenseEvent triggered");
    }

    @EventHandler
    public void onDispenseLoot(BlockDispenseLootEvent e) {
        if (e.getBlock().getType() == Material.VAULT || e.getBlock().getType() == Material.TRIAL_SPAWNER) {

        }
        System.out.println("Dispensed loot");
    }

    @EventHandler
    public void onLootGenerate(LootGenerateEvent e) {
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
