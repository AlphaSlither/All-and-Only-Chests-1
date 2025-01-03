package dev.skippaddin.allAndOnlyChests.listeners;

import dev.skippaddin.allAndOnlyChests.scoreboard.StructureScoreboard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        StructureScoreboard scoreboard = StructureScoreboard.getInstance();
        scoreboard.displayScoreboard(e.getPlayer());
    }
}
