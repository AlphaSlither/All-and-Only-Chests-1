package dev.skippaddin.allAndOnlyChests.menuSystem.menu;

import dev.skippaddin.allAndOnlyChests.AllAndOnlyChests;
import dev.skippaddin.allAndOnlyChests.menuSystem.Menu;
import dev.skippaddin.allAndOnlyChests.menuSystem.utility.PlayerMenuUtility;
import dev.skippaddin.allAndOnlyChests.menuSystem.utility.StructureItemUtility;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StructureMenu extends Menu {

    private final int[] unfinishedSlots = new int[] {0,1,2,3,9,10,11,12,18,19,20,21,27,28,29,30,36,37};

    private final int[] finishedSlots = new int[] {5,6,7,8,14,15,16,17,23,24,25,26,32,33,34,35,41,42};

    public StructureMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Structures";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.GRAY_STAINED_GLASS_PANE) {
            new StructureItemMenu(playerMenuUtility, e.getCurrentItem()).open();
        }
    }

    @Override
    public void setMenuItems() {
        HashMap<String, Boolean> structures = AllAndOnlyChests.getStructures();
        List<String> unfinishedStructures = new ArrayList<>();
        for (HashMap.Entry<String, Boolean> entry : structures.entrySet()) {
            if (!entry.getValue()) {
                unfinishedStructures.add(entry.getKey());
            }
        }

        for (int i = 0; i < unfinishedStructures.size(); i++) {
            ItemStack structureItem = StructureItemUtility.getStructureItem(unfinishedStructures.get(i));
            inventory.setItem(unfinishedSlots[i], structureItem);
        }

        List<String> finishedStructures = new ArrayList<>();
        for (HashMap.Entry<String, Boolean> entry : structures.entrySet()) {
            if (entry.getValue()) {
                finishedStructures.add(entry.getKey());
            }
        }

        for (int i = 0; i < finishedStructures.size(); i++) {
            ItemStack structureItem = StructureItemUtility.getStructureItem(finishedStructures.get(i));
            inventory.setItem(finishedSlots[i], structureItem);
        }

        int invPos = 4;

        for (int i = 0; i < 6; i++) {
            ItemStack divider = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta dividerMeta = divider.getItemMeta();
            dividerMeta.setHideTooltip(true);
            divider.setItemMeta(dividerMeta);
            inventory.setItem(invPos, divider);
            invPos += 9;
        }

    }
}
