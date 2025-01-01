package dev.skippaddin.allAndOnlyChests.menuSystem.menu;

import dev.skippaddin.allAndOnlyChests.AllAndOnlyChests;
import dev.skippaddin.allAndOnlyChests.menuSystem.Menu;
import dev.skippaddin.allAndOnlyChests.menuSystem.utility.CustomHeadUtility;
import dev.skippaddin.allAndOnlyChests.menuSystem.utility.PlayerMenuUtility;
import dev.skippaddin.allAndOnlyChests.menuSystem.utility.StructureItemUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class StructureItemMenu extends Menu {

    private final ItemStack item;

    private final ArrayList<ItemStack> items = new ArrayList<>();

    private int startIndex = 0;

    //Set to static so the server has to request the textures only once.
    private static final ItemStack arrowUpItem = generateArrows(ChatColor.GREEN + "Scroll up", "http://textures" +
            ".minecraft.net/texture/7d695d335e6be8cb2a34e05e18ea2d12c3b17b8166ba62d6982a643df71ffac5");

    private static final ItemStack arrowDownItem = generateArrows(ChatColor.GREEN + "Scroll Down", "http://textures" +
            ".minecraft.net/texture/437862cdc159998ed6b6fdccaaa4675867d4484db512a84c367fabf4caf60");

    public StructureItemMenu(PlayerMenuUtility playerMenuUtility, ItemStack item) {
        super(playerMenuUtility);
        ItemMeta itemMeta = item.getItemMeta();
        String lore;
        if (AllAndOnlyChests.getSelectedStructure().isEmpty()) {
            lore = ChatColor.GREEN + "Click to select.";
        } else if (AllAndOnlyChests.getSelectedStructure().equals(itemMeta.getItemName())) {
            lore = ChatColor.GOLD + "Selected.";
        } else {
            String selectedStructure = StructureItemUtility.formatString(AllAndOnlyChests.getSelectedStructure());
            lore = ChatColor.RED + selectedStructure + " selected.";
        }
        itemMeta.setLore(List.of(lore));
        item.setItemMeta(itemMeta);
        this.item = item;
    }


    private static ItemStack generateArrows(String display, String url) {
        ItemStack itemStack = CustomHeadUtility.getCustomHead(url);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(display);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public String getMenuName() {
        if (item.getItemMeta().getItemName().equals("bastion") || item.getItemMeta().getItemName().equals("woodland_mansion")) {
            String menuName = StructureItemUtility.formatString(item.getItemMeta().getItemName());
            return ChatColor.DARK_GRAY + menuName;
        }
        return item.getItemMeta().getDisplayName();
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        if (e.getView().getTopInventory().equals(e.getInventory())) {
            if (e.getSlot() == 0) {
                new StructureMenu(playerMenuUtility).open();
            } else if (e.getSlot() == 53 && e.getCurrentItem().getType() == Material.PLAYER_HEAD) {

                boolean scrollable = false;
                startIndex += 40;

                if (items.size() - startIndex > 40) {
                    scrollable = true;
                }

                int modBy = 17;
                int invPos = 9;

                for (int i = startIndex; i < items.size(); i++) {
                    if (invPos > 52) {
                        break;
                    }

                    inventory.setItem(invPos, items.get(i));

                    invPos++;

                    if (invPos % modBy == 0) {
                        modBy += 9;
                        invPos++;
                    }
                }

                if (!scrollable) {
                    ItemStack grayGlass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                    ItemMeta grayGlassMeta = grayGlass.getItemMeta();
                    grayGlassMeta.setHideTooltip(true);
                    grayGlass.setItemMeta(grayGlassMeta);
                    inventory.setItem(53, grayGlass);
                }

                while (invPos < 53) {
                    ItemStack air = new ItemStack(Material.AIR);
                    inventory.setItem(invPos, air);

                    invPos++;
                    if (invPos % modBy == 0) {
                        modBy += 9;
                        invPos++;
                    }
                }

                inventory.setItem(8, arrowUpItem);

            } else if (e.getSlot() == 8 && e.getCurrentItem().getType() == Material.PLAYER_HEAD) {
                boolean scrollable = false;
                startIndex -= 40;

                if (startIndex != 0) {
                    scrollable = true;
                }

                int modBy = 17;
                int invPos = 9;

                for (int i = startIndex; i < items.size(); i++) {
                    if (invPos > 52) {
                        break;
                    }

                    inventory.setItem(invPos, items.get(i));

                    invPos++;

                    if (invPos % modBy == 0) {
                        modBy += 9;
                        invPos++;
                    }
                }

                inventory.setItem(53, arrowDownItem);

                if (!scrollable) {
                    ItemStack grayGlass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                    ItemMeta grayGlassMeta = grayGlass.getItemMeta();
                    grayGlassMeta.setHideTooltip(true);
                    grayGlass.setItemMeta(grayGlassMeta);
                    inventory.setItem(8, grayGlass);
                }

            } else if (e.getSlot() == 4) {
                if (AllAndOnlyChests.getSelectedStructure().isEmpty()) {
                    Player player = playerMenuUtility.getOwner();
                    String itemName = item.getItemMeta().getItemName();
                    AllAndOnlyChests.setSelectedStructure(itemName);
                    player.closeInventory();
                    if (!itemName.equals("bastion") && !itemName.equals("mansion")) {
                        player.sendTitle(ChatColor.YELLOW + item.getItemMeta().getDisplayName(), ChatColor.YELLOW + "started!");
                    } else {
                        String title = StructureItemUtility.formatString(itemName);
                        player.sendTitle(ChatColor.YELLOW + title, ChatColor.YELLOW + "started!");
                    }
                    player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                }
            }
        }
    }

    @Override
    public void setMenuItems() {
        inventory.setItem(4, item);

        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta arrowMeta = arrow.getItemMeta();
        arrowMeta.setDisplayName(ChatColor.YELLOW + "Back");
        arrow.setItemMeta(arrowMeta);
        inventory.setItem(0, arrow);

        ItemStack grayGlass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta glassMeta = grayGlass.getItemMeta();
        glassMeta.setHideTooltip(true);
        grayGlass.setItemMeta(glassMeta);
        inventory.setItem(1, grayGlass);
        inventory.setItem(2, grayGlass);
        inventory.setItem(6, grayGlass);
        inventory.setItem(7, grayGlass);
        inventory.setItem(17, grayGlass);
        inventory.setItem(26, grayGlass);
        inventory.setItem(35, grayGlass);
        inventory.setItem(44, grayGlass);

        ItemStack greenGlass = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta greenGlassMeta = greenGlass.getItemMeta();
        greenGlassMeta.setHideTooltip(true);
        greenGlass.setItemMeta(greenGlassMeta);
        inventory.setItem(3, greenGlass);
        inventory.setItem(5, greenGlass);

        if (item.getItemMeta().getItemName().equals("bastion")) {
            for (HashMap.Entry<Material, Boolean> entry : AllAndOnlyChests.getBastionRemnantLoot().entrySet()) {
                if (!entry.getValue()) {
                    items.add(buildItem(entry.getKey()));
                }
            }
            for (HashMap.Entry<Material, Boolean> entry : AllAndOnlyChests.getBastionRemnantEnchantedLoot().entrySet()) {
                if (!entry.getValue()) {
                    items.add(buildEnchantedItem(entry.getKey()));
                }
            }

        } else if (item.getItemMeta().getItemName().equals("trial_chambers")) {
            for (HashMap.Entry<Material, Boolean> entry : AllAndOnlyChests.getTrialChambersLoot().entrySet()) {
                if (!entry.getValue()) {
                    items.add(buildItem(entry.getKey()));
                }
            }
            for (HashMap.Entry<Material, Boolean> entry : AllAndOnlyChests.getTrialChambersEnchantedLoot().entrySet()) {
                if (!entry.getValue()) {
                    items.add(buildEnchantedItem(entry.getKey()));
                }
            }
            for (HashMap.Entry<PotionType, Boolean> entry : AllAndOnlyChests.getTrialChambersPotions().entrySet()) {
                if (!entry.getValue()) {
                    ItemStack potion = new ItemStack(Material.POTION);
                    PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();
                    potionMeta.setBasePotionType(entry.getKey());
                    potion.setItemMeta(potionMeta);
                    items.add(potion);
                }
            }
            for (HashMap.Entry<PotionType, Boolean> entry : AllAndOnlyChests.getTrialChambersArrowEffects().entrySet()) {
                if (!entry.getValue()) {
                    ItemStack tippedArrow = new ItemStack(Material.TIPPED_ARROW);
                    PotionMeta tippedArrowMeta = (PotionMeta) tippedArrow.getItemMeta();
                    tippedArrowMeta.setBasePotionType(entry.getKey());
                    tippedArrow.setItemMeta(tippedArrowMeta);
                    items.add(tippedArrow);
                }
            }

        } else {
            for (HashMap.Entry<Material, Boolean> entry :
                    AllAndOnlyChests.structureMaterials.get(item.getItemMeta().getItemName()).entrySet()) {
                if (!entry.getValue()) {
                    items.add(buildItem(entry.getKey()));
                }
            }
        }


        items.sort(new Comparator<ItemStack>() {
            @Override
            public int compare(ItemStack o1, ItemStack o2) {
                return o1.getType().toString().compareTo(o2.getType().toString());
            }
        });

        if (items.size() > 40) {
            inventory.setItem(53, arrowDownItem);
        } else {
            inventory.setItem(53, grayGlass);
        }

        inventory.setItem(8, grayGlass);

        int modBy = 17;
        int invPos = 9;

        for (ItemStack itemStack : items) {

            if (invPos > 52) {
                break;
            }
            inventory.setItem(invPos, itemStack);

            invPos++;

            if (invPos % modBy == 0) {
                modBy += 9;
                invPos++;
            }

        }
    }

    private ItemStack buildEnchantedItem(Material material) {
        ItemStack itemStack = buildItem(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addEnchant(Enchantment.EFFICIENCY, 1, false);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack buildItem(Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        return item;
    }
}
