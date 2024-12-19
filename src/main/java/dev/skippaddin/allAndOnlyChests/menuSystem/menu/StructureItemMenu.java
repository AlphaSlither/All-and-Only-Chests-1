package dev.skippaddin.allAndOnlyChests.menuSystem.menu;

import dev.skippaddin.allAndOnlyChests.AllAndOnlyChests;
import dev.skippaddin.allAndOnlyChests.menuSystem.Menu;
import dev.skippaddin.allAndOnlyChests.menuSystem.utility.CustomHeadUtility;
import dev.skippaddin.allAndOnlyChests.menuSystem.utility.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class StructureItemMenu extends Menu {

    private final ItemStack item;

    private ArrayList<Material> items;

    //Set to static so the server has to request the textures only once.
    private static final ItemStack arrowUpItem = generateArrows(ChatColor.GREEN + "Scroll up", "http://textures.minecraft.net/texture/7d695d335e6be8cb2a34e05e18ea2d12c3b17b8166ba62d6982a643df71ffac5");

    private static final ItemStack arrowDownItem = generateArrows(ChatColor.GREEN + "Scroll Down", "http://textures.minecraft.net/texture/437862cdc159998ed6b6fdccaaa4675867d4484db512a84c367fabf4caf60");

    public StructureItemMenu(PlayerMenuUtility playerMenuUtility, ItemStack item) {
        super(playerMenuUtility);
        ItemMeta itemMeta = item.getItemMeta();
        String lore;
        if (AllAndOnlyChests.getSelectedStructure().isEmpty()) {
            lore = ChatColor.GREEN + "Click to select.";
        } else if (AllAndOnlyChests.getSelectedStructure().equals(itemMeta.getItemName())) {
            lore = ChatColor.GOLD + "Selected.";
        } else {
            lore = ChatColor.RED + AllAndOnlyChests.getSelectedStructure() + " selected.";
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
        return item.getItemMeta().getDisplayName();
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

    }

    @Override
    public void setMenuItems() {
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

        if (!item.getItemMeta().getItemName().equals("bastion_remnant")) {
            items = AllAndOnlyChests.getStructureMaterials(true, item.getItemMeta().getItemName());
        } else {
            items = new ArrayList<>();
            for (HashMap.Entry<Material, Boolean> entry : AllAndOnlyChests.getBastionRemnantLoot().entrySet()) {
                if (!entry.getValue()) {
                    items.add(entry.getKey());
                }
            }
            for (HashMap.Entry<Material, Boolean> entry : AllAndOnlyChests.getBastionRemnantEnchanted().entrySet()) {
                if (!entry.getValue()) {
                    items.add(entry.getKey());
                }
            }
            items.sort(new Comparator<Material>() {
                @Override
                public int compare(Material o1, Material o2) {
                    return o1.toString().compareTo(o2.toString());
                }
            });
        }

         if (items.size() > 40) {
             inventory.setItem(8, arrowUpItem);
             inventory.setItem(53, arrowDownItem);
         } else {
             inventory.setItem(8, grayGlass);
             inventory.setItem(53, grayGlass);
         }

         int modBy = 17;
         int invPos = 9;

         for (Material material : items) {

             if (invPos > 52) {
                 break;
             }

             ItemStack itemStack = new ItemStack(material);
             inventory.setItem(invPos, itemStack);

             invPos++;

             if (invPos % modBy == 0) {
                 modBy += 9;
                 invPos++;
             }

         }
    }
}
