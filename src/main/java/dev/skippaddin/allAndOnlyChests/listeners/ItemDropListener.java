package dev.skippaddin.allAndOnlyChests.listeners;

import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import dev.skippaddin.allAndOnlyChests.challenge.ChallengeData;
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
        if (!ChallengeData.isDropsAllowed() && e.getEntity() instanceof ItemFrame) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDestroyBlock(BlockDestroyEvent e) {
        if (!ChallengeData.getPlacedBlocks().remove(e.getBlock()) && !ChallengeData.isDropsAllowed()) {
            e.setWillDrop(false);
        } else {
            ChallengeData.setSaved(false);
        }
    }

    @EventHandler
    public void onHangingBreak(HangingBreakEvent e) {
        if (!ChallengeData.isDropsAllowed()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        if (!ChallengeData.getPlacedBlocks().remove(block) && !ChallengeData.isDropsAllowed()) {
            block.setType(Material.AIR);
        } else {
            ChallengeData.setSaved(false);
        }
    }

    @EventHandler
    public void onBlockDropItem(BlockDropItemEvent e) {
        BlockState blockState = e.getBlockState();
        if (blockState instanceof InventoryHolder && !(blockState instanceof Dispenser || blockState instanceof DecoratedPot)) {
            for (Item item : e.getItems()) {
                ItemStack itemStack = item.getItemStack();
                if (itemStack.hasItemMeta() && ChallengeData.getStructureSet().contains(itemStack.getItemMeta().getItemName())) {
                    e.setCancelled(true);
                    break;
                }
            }
        } else if (!ChallengeData.isDropsAllowed() && (blockState instanceof Dispenser || blockState instanceof DecoratedPot)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        ChallengeData.getPlacedBlocks().add(e.getBlockPlaced());
        ChallengeData.setSaved(false);
    }

    @EventHandler
    public void onEntityDropItem(EntityDropItemEvent e) {
        if (!ChallengeData.isDropsAllowed()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (!ChallengeData.isDropsAllowed()) {
            e.getDrops().clear();
        }
    }

    @EventHandler
    public void onCatch(PlayerFishEvent e) {
        if (!ChallengeData.isDropsAllowed() && (e.getState() == PlayerFishEvent.State.CAUGHT_ENTITY || e.getState() == PlayerFishEvent.State.CAUGHT_FISH)) {
            e.getCaught().remove();
        }
    }

    @EventHandler
    public void onExplodeEvent(BlockExplodeEvent e) {
        if (!ChallengeData.isDropsAllowed()) {
            for (Block block : e.blockList()) {
                block.setType(Material.AIR);
            }
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        if (!ChallengeData.isDropsAllowed()) {
            for (Block block : e.blockList()) {
                block.setType(Material.AIR);
            }
        }
    }


    @EventHandler
    public void onCrafterCraftItem(CrafterCraftEvent e) {
        if (!ChallengeData.isDropsAllowed() && !ChallengeData.getPlacedBlocks().contains(e.getBlock())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onLeaveDecay(LeavesDecayEvent e) {
        if (!ChallengeData.isDropsAllowed()) {
            e.getBlock().setType(Material.AIR);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (!ChallengeData.isDropsAllowed() && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = e.getClickedBlock();
            if (block != null && block.getType() == Material.CHISELED_BOOKSHELF && !ChallengeData.getPlacedBlocks().contains(block)) {
                e.setCancelled(true);
            }
        }
    }


}
