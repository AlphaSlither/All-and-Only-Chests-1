package dev.skippaddin.allAndOnlyChests.listeners;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashSet;

public class DropItemListener implements Listener {

    private final HashSet<Block> placedBlocks = new HashSet<>();

    @EventHandler
    public void onBlockDropItem(BlockDropItemEvent e) {
        if (!placedBlocks.remove(e.getBlock())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        placedBlocks.add(e.getBlockPlaced());
    }

}
