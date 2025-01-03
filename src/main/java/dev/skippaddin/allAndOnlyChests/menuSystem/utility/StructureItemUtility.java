package dev.skippaddin.allAndOnlyChests.menuSystem.utility;

import dev.skippaddin.allAndOnlyChests.AllAndOnlyChests;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionType;

import java.util.HashMap;
import java.util.List;

public final class StructureItemUtility {

    private StructureItemUtility() {
    }

    public static ItemStack getStructureItem(String structure) {
        switch (structure) {
            case "ancient_city":
                ItemStack ancientCityItem = new ItemStack(Material.SCULK_SHRIEKER);
                ItemMeta ancientCityItemItemMeta = ancientCityItem.getItemMeta();
                generateItemMeta(ancientCityItemItemMeta, structure, AllAndOnlyChests.getAncientCityLoot());
                ancientCityItem.setItemMeta(ancientCityItemItemMeta);
                return ancientCityItem;
            case "bastion":
                ItemStack bastionRemnantItem = new ItemStack(Material.PIGLIN_HEAD);
                ItemMeta bastionRemnantItemMeta = bastionRemnantItem.getItemMeta();
                generateItemMeta(bastionRemnantItemMeta, structure, AllAndOnlyChests.getBastionRemnantLoot());
                //Overwriting Lore because bastion remnant is special case because of non-enchanted and enchanted items
                int maxCount = AllAndOnlyChests.getBastionRemnantLoot().size();
                maxCount += AllAndOnlyChests.getBastionRemnantEnchantedLoot().size();
                if (structure.equals(AllAndOnlyChests.getSelectedStructure())) {
                    int collected = 0;
                    for (HashMap.Entry<Material, Boolean> entry : AllAndOnlyChests.getBastionRemnantLoot().entrySet()) {
                        if (entry.getValue()) {
                            collected++;
                        }
                    }
                    for (HashMap.Entry<Material, Boolean> entry :
                            AllAndOnlyChests.getBastionRemnantEnchantedLoot().entrySet()) {
                        if (entry.getValue()) {
                            collected++;
                        }
                    }
                    bastionRemnantItemMeta.lore(List.of(Component.text(collected + "/" + maxCount + " items", NamedTextColor.YELLOW)));
                } else {
                    bastionRemnantItemMeta.lore(List.of(Component.text(maxCount + " items", NamedTextColor.RED)));
                    String displayName = formatString(structure);
                    // Minecraft sets Color to Yellow automatically for piglin head
                    bastionRemnantItemMeta.displayName(Component.text(displayName, NamedTextColor.WHITE));
                }
                bastionRemnantItem.setItemMeta(bastionRemnantItemMeta);
                return bastionRemnantItem;
            case "buried_treasure":
                ItemStack buriedTreasureItem = new ItemStack(Material.IRON_SHOVEL);
                ItemMeta buriedTreasureItemMeta = buriedTreasureItem.getItemMeta();
                generateItemMeta(buriedTreasureItemMeta, structure, AllAndOnlyChests.getBuriedTreasureLoot());
                buriedTreasureItem.setItemMeta(buriedTreasureItemMeta);
                return buriedTreasureItem;
            case "desert_pyramid":
                ItemStack desertPyramidItem = new ItemStack(Material.CHISELED_SANDSTONE);
                ItemMeta desertPyramidItemMeta = desertPyramidItem.getItemMeta();
                generateItemMeta(desertPyramidItemMeta, structure, AllAndOnlyChests.getDesertPyramidLoot());
                desertPyramidItem.setItemMeta(desertPyramidItemMeta);
                return desertPyramidItem;
            case "end_city":
                ItemStack endCityItem = new ItemStack(Material.SHULKER_BOX);
                ItemMeta endCityItemMeta = endCityItem.getItemMeta();
                generateItemMeta(endCityItemMeta, structure, AllAndOnlyChests.getEndCityLoot());
                endCityItem.setItemMeta(endCityItemMeta);
                return endCityItem;
            case "nether_bridge":
                ItemStack fortressItem = new ItemStack(Material.BLAZE_ROD);
                ItemMeta fortressItemMeta = fortressItem.getItemMeta();
                generateItemMeta(fortressItemMeta, structure, AllAndOnlyChests.getNetherFortressLoot());
                if (AllAndOnlyChests.getSelectedStructure().equals(structure)) {
                    fortressItemMeta.displayName(Component.text("Fortress", NamedTextColor.YELLOW));
                } else {
                    fortressItemMeta.displayName(Component.text("Fortress"));
                }
                fortressItem.setItemMeta(fortressItemMeta);
                return fortressItem;
            case "igloo":
                ItemStack iglooItem = new ItemStack(Material.SNOW_BLOCK);
                ItemMeta iglooItemMeta = iglooItem.getItemMeta();
                generateItemMeta(iglooItemMeta, structure, AllAndOnlyChests.getIglooLoot());
                iglooItem.setItemMeta(iglooItemMeta);
                return iglooItem;
            case "jungle_temple":
                ItemStack junglePyramidItem = new ItemStack(Material.TRIPWIRE_HOOK);
                ItemMeta junglePyramidItemMeta = junglePyramidItem.getItemMeta();
                generateItemMeta(junglePyramidItemMeta, structure, AllAndOnlyChests.getJunglePyramidLoot());
                junglePyramidItem.setItemMeta(junglePyramidItemMeta);
                return junglePyramidItem;
            case "underwater_ruin":
                ItemStack oceanRuinItem = new ItemStack(Material.POLISHED_GRANITE);
                ItemMeta oceanRuinItemMeta = oceanRuinItem.getItemMeta();
                generateItemMeta(oceanRuinItemMeta, structure, AllAndOnlyChests.getOceanRuinLoot());
                if (AllAndOnlyChests.getSelectedStructure().equals(structure)) {
                    oceanRuinItemMeta.displayName(Component.text("Ocean Ruin", NamedTextColor.YELLOW));
                } else {
                    oceanRuinItemMeta.displayName(Component.text("Ocean Ruin"));
                }
                oceanRuinItem.setItemMeta(oceanRuinItemMeta);
                return oceanRuinItem;
            case "pillager_outpost":
                ItemStack pillagerOutpostItem = new ItemStack(Material.WHITE_BANNER);
                ItemMeta pillagerOutpostItemMeta = pillagerOutpostItem.getItemMeta();
                BannerMeta bannerMeta = getBannerMeta((BannerMeta) pillagerOutpostItemMeta);
                generateItemMeta(bannerMeta, structure, AllAndOnlyChests.getPillagerOutpostLoot());
                pillagerOutpostItem.setItemMeta(bannerMeta);
                return pillagerOutpostItem;
            case "ruined_portal":
                ItemStack ruinedPortalItem = new ItemStack(Material.OBSIDIAN);
                ItemMeta ruinedPortalItemMeta = ruinedPortalItem.getItemMeta();
                generateItemMeta(ruinedPortalItemMeta, structure, AllAndOnlyChests.getRuinedPortalLoot());
                ruinedPortalItem.setItemMeta(ruinedPortalItemMeta);
                return ruinedPortalItem;
            case "shipwreck":
                ItemStack shipwreckItem = new ItemStack(Material.FILLED_MAP);
                ItemMeta shipwreckItemMeta = shipwreckItem.getItemMeta();
                generateItemMeta(shipwreckItemMeta, structure, AllAndOnlyChests.getShipwreckLoot());
                shipwreckItem.setItemMeta(shipwreckItemMeta);
                return shipwreckItem;
            case "stronghold":
                ItemStack strongholdItem = new ItemStack(Material.END_PORTAL_FRAME);
                ItemMeta strongholdItemMeta = strongholdItem.getItemMeta();
                generateItemMeta(strongholdItemMeta, structure, AllAndOnlyChests.getStrongholdLoot());
                strongholdItem.setItemMeta(strongholdItemMeta);
                return strongholdItem;
            case "mineshaft":
                ItemStack mineshaftItem = new ItemStack(Material.CHEST_MINECART);
                ItemMeta mineshaftItemMeta = mineshaftItem.getItemMeta();
                generateItemMeta(mineshaftItemMeta, structure, AllAndOnlyChests.getMineshaftLoot());
                mineshaftItem.setItemMeta(mineshaftItemMeta);
                return mineshaftItem;
            case "village":
                ItemStack villageItem = new ItemStack(Material.HAY_BLOCK);
                ItemMeta villageItemMeta = villageItem.getItemMeta();
                generateItemMeta(villageItemMeta, structure, AllAndOnlyChests.getVillageLoot());
                villageItem.setItemMeta(villageItemMeta);
                return villageItem;
            case "woodland_mansion":
                ItemStack mansionItem = new ItemStack(Material.TOTEM_OF_UNDYING);
                ItemMeta mansionItemMeta = mansionItem.getItemMeta();
                generateItemMeta(mansionItemMeta, structure, AllAndOnlyChests.getWoodlandMansionLoot());
                if (!AllAndOnlyChests.getSelectedStructure().equals(structure)) {
                    String displayName = formatString(structure);
                    mansionItemMeta.customName(Component.text(displayName, NamedTextColor.WHITE));
                }
                mansionItem.setItemMeta(mansionItemMeta);
                return mansionItem;
            case "simple_dungeon":
                ItemStack monsterRoomItem = new ItemStack(Material.SPAWNER);
                ItemMeta monsterRoomItemMeta = monsterRoomItem.getItemMeta();
                generateItemMeta(monsterRoomItemMeta, structure, AllAndOnlyChests.getMonsterRoomLoot());
                monsterRoomItemMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
                if (AllAndOnlyChests.getSelectedStructure().equals(structure)) {
                    monsterRoomItemMeta.displayName(Component.text("Monster Room", NamedTextColor.YELLOW));
                } else {
                    monsterRoomItemMeta.displayName(Component.text("Monster Room"));
                }
                monsterRoomItem.setItemMeta(monsterRoomItemMeta);
                return monsterRoomItem;
            case "trial_chambers":
                ItemStack trialChambersItem = new ItemStack(Material.VAULT);
                ItemMeta trialChambersItemMeta = trialChambersItem.getItemMeta();
                generateItemMeta(trialChambersItemMeta, structure, AllAndOnlyChests.getTrialChambersLoot());
                int itemMaxCount = AllAndOnlyChests.getTrialChambersLoot().size() +
                        AllAndOnlyChests.getTrialChambersEnchantedLoot().size() +
                        AllAndOnlyChests.getTrialChambersPotions().size() +
                        AllAndOnlyChests.getTrialChambersArrowEffects().size();
                if (structure.equals(AllAndOnlyChests.getSelectedStructure())) {
                    int collected = 0;
                    for (HashMap.Entry<Material, Boolean> entry : AllAndOnlyChests.getTrialChambersLoot().entrySet()) {
                        if (entry.getValue()) {
                            collected++;
                        }
                    }
                    for (HashMap.Entry<Material, Boolean> entry : AllAndOnlyChests.getTrialChambersEnchantedLoot().entrySet()) {
                        if (entry.getValue()) {
                            collected++;
                        }
                    }
                    for (HashMap.Entry<PotionType, Boolean> entry : AllAndOnlyChests.getTrialChambersArrowEffects().entrySet()) {
                        if (entry.getValue()) {
                            collected++;
                        }
                    }
                    for (HashMap.Entry<PotionType, Boolean> entry : AllAndOnlyChests.getTrialChambersPotions().entrySet()) {
                        if (entry.getValue()) {
                            collected++;
                        }
                    }
                    trialChambersItemMeta.lore(List.of(Component.text(collected + "/" + itemMaxCount + " items", NamedTextColor.YELLOW)));
                } else {
                    trialChambersItemMeta.lore(List.of(Component.text(itemMaxCount + " items", NamedTextColor.RED)));
                }
                trialChambersItem.setItemMeta(trialChambersItemMeta);
                return trialChambersItem;
            default:
                return new ItemStack(Material.AIR);
        }
    }

    private static BannerMeta getBannerMeta(BannerMeta pillagerOutpostItemMeta) {
        pillagerOutpostItemMeta.setPatterns(List.of(new Pattern(DyeColor.CYAN, PatternType.RHOMBUS),
                new Pattern(DyeColor.LIGHT_GRAY, PatternType.STRIPE_BOTTOM), new Pattern(DyeColor.GRAY,
                        PatternType.STRIPE_CENTER), new Pattern(DyeColor.LIGHT_GRAY, PatternType.BORDER),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE), new Pattern(DyeColor.LIGHT_GRAY,
                        PatternType.HALF_HORIZONTAL), new Pattern(DyeColor.LIGHT_GRAY, PatternType.CIRCLE),
                new Pattern(DyeColor.BLACK, PatternType.BORDER)));
        pillagerOutpostItemMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        return pillagerOutpostItemMeta;
    }


    private static void generateItemMeta(ItemMeta itemMeta, String structure, HashMap<Material, Boolean> loot) {
        itemMeta.itemName(Component.text(structure));
        String displayName = formatString(structure);
        int maxCount = loot.size();
        if (structure.equals(AllAndOnlyChests.getSelectedStructure())) {
            itemMeta.customName(Component.text(displayName, NamedTextColor.YELLOW));
            itemMeta.addEnchant(Enchantment.BREACH, 1, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            int collected = 0;
            for (HashMap.Entry<Material, Boolean> entry : loot.entrySet()) {
                if (entry.getValue()) {
                    collected++;
                }
            }
            itemMeta.lore(List.of(Component.text(collected + "/" + maxCount + " items", NamedTextColor.YELLOW)));
        } else {
            itemMeta.displayName(Component.text(displayName));
            itemMeta.lore(List.of(Component.text(maxCount + " items", NamedTextColor.RED)));
        }
    }


    public static String formatString(String name) {
        String[] words = name.split("_");
        StringBuilder displayName = new StringBuilder();
        displayName.append(Character.toUpperCase(words[0].charAt(0))).append(words[0].substring(1));
        for (int i = 1; i < words.length; i++) {
            displayName.append(" ").append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1));
        }

        return displayName.toString();
    }
}
