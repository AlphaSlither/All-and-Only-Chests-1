package dev.skippaddin.allAndOnlyChests.menuSystem.menu;

import dev.skippaddin.allAndOnlyChests.AllAndOnlyChests;
import dev.skippaddin.allAndOnlyChests.menuSystem.Menu;
import dev.skippaddin.allAndOnlyChests.menuSystem.utility.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;

public class StructureItemMenu extends Menu {

    private final ItemStack item;

    private final HashMap<Material, Boolean>[] items;

    public StructureItemMenu(PlayerMenuUtility playerMenuUtility, ItemStack item, HashMap<Material, Boolean>[] items) {
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
        this.items = items;
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

        if (items.length > 40) {

        }

    }
}
