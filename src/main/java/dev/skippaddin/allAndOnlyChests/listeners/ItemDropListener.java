package dev.skippaddin.allAndOnlyChests.listeners;

import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import dev.skippaddin.allAndOnlyChests.AllAndOnlyChests;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashSet;

//Contains all events a player can receive items from
public class ItemDropListener implements Listener {


    //Prevents getting items from item frame
    @EventHandler
    public void onItemFrameDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof ItemFrame) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDestroyBlock(BlockDestroyEvent e) {
        if (!AllAndOnlyChests.getPlacedBlocks().remove(e.getBlock())) {
            e.setWillDrop(false);
        } else {
            AllAndOnlyChests.setSaved(false);
        }
    }

    @EventHandler
    public void onHangingBreak(HangingBreakEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        if (!AllAndOnlyChests.getPlacedBlocks().remove(block)) {
            block.setType(Material.AIR);
        } else {
            AllAndOnlyChests.setSaved(false);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        AllAndOnlyChests.getPlacedBlocks().add(e.getBlockPlaced());
        AllAndOnlyChests.setSaved(false);
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
    public void onCrafterCraftItem(CrafterCraftEvent e) {
        if (!AllAndOnlyChests.getPlacedBlocks().contains(e.getBlock())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onLeaveDecay(LeavesDecayEvent e) {
        e.getBlock().setType(Material.AIR);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = e.getClickedBlock();
            if (block != null && block.getType() == Material.CHISELED_BOOKSHELF && !AllAndOnlyChests.getPlacedBlocks().contains(block)) {
                e.setCancelled(true);
            }
        }
    }


}
