package dev.skippaddin.allAndOnlyChests.listeners;

import dev.skippaddin.allAndOnlyChests.AllAndOnlyChests;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        AllAndOnlyChests.removePlayerFromUtility(e.getPlayer());
    }

}
