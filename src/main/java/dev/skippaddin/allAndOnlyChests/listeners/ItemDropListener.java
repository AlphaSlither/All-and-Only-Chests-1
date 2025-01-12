package dev.skippaddin.allAndOnlyChests.listeners;

import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import dev.skippaddin.allAndOnlyChests.AllAndOnlyChests;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;


//Contains all events a player can receive items from
public class ItemDropListener implements Listener {


    //Prevents getting items from item frame
    @EventHandler
    public void onItemFrameDamage(EntityDamageByEntityEvent e) {
        if (!AllAndOnlyChests.isDropsAllowed() && e.getEntity() instanceof ItemFrame) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDestroyBlock(BlockDestroyEvent e) {
        if (!AllAndOnlyChests.getPlacedBlocks().remove(e.getBlock()) && !AllAndOnlyChests.isDropsAllowed()) {
            e.setWillDrop(false);
        } else {
            AllAndOnlyChests.setSaved(false);
        }
    }

    @EventHandler
    public void onHangingBreak(HangingBreakEvent e) {
        if (!AllAndOnlyChests.isDropsAllowed()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        if (!AllAndOnlyChests.getPlacedBlocks().remove(block) && !AllAndOnlyChests.isDropsAllowed()) {
            block.setType(Material.AIR);
        } else {
            AllAndOnlyChests.setSaved(false);
        }
    }

    @EventHandler
    public void onBlockDropItem(BlockDropItemEvent e) {
        BlockState blockState = e.getBlockState();
        if (blockState instanceof InventoryHolder && !(blockState instanceof Dispenser || blockState instanceof DecoratedPot)) {
            for (Item item : e.getItems()) {
                ItemStack itemStack = item.getItemStack();
                if (itemStack.hasItemMeta() && AllAndOnlyChests.getStructureMap().contains(itemStack.getItemMeta().getItemName())) {
                    e.setCancelled(true);
                    break;
                }
            }
        } else if (!AllAndOnlyChests.isDropsAllowed() && (blockState instanceof Dispenser || blockState instanceof DecoratedPot)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        AllAndOnlyChests.getPlacedBlocks().add(e.getBlockPlaced());
        AllAndOnlyChests.setSaved(false);
    }

    @EventHandler
    public void onEntityDropItem(EntityDropItemEvent e) {
        if (!AllAndOnlyChests.isDropsAllowed()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (!AllAndOnlyChests.isDropsAllowed()) {
            e.getDrops().clear();
        }
    }

    @EventHandler
    public void onCatch(PlayerFishEvent e) {
        if (!AllAndOnlyChests.isDropsAllowed() && (e.getState() == PlayerFishEvent.State.CAUGHT_ENTITY || e.getState() == PlayerFishEvent.State.CAUGHT_FISH)) {
            e.getCaught().remove();
        }
    }

    @EventHandler
    public void onExplodeEvent(BlockExplodeEvent e) {
        if (!AllAndOnlyChests.isDropsAllowed()) {
            for (Block block : e.blockList()) {
                block.setType(Material.AIR);
            }
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        if (!AllAndOnlyChests.isDropsAllowed()) {
            for (Block block : e.blockList()) {
                block.setType(Material.AIR);
            }
        }
    }


    @EventHandler
    public void onCrafterCraftItem(CrafterCraftEvent e) {
        if (!AllAndOnlyChests.isDropsAllowed() && !AllAndOnlyChests.getPlacedBlocks().contains(e.getBlock())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onLeaveDecay(LeavesDecayEvent e) {
        if (!AllAndOnlyChests.isDropsAllowed()) {
            e.getBlock().setType(Material.AIR);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (!AllAndOnlyChests.isDropsAllowed() && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = e.getClickedBlock();
            if (block != null && block.getType() == Material.CHISELED_BOOKSHELF && !AllAndOnlyChests.getPlacedBlocks().contains(block)) {
                e.setCancelled(true);
            }
        }
    }


}
