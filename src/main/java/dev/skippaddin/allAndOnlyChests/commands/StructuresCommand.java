package dev.skippaddin.allAndOnlyChests.commands;

import dev.skippaddin.allAndOnlyChests.AllAndOnlyChests;
import dev.skippaddin.allAndOnlyChests.menuSystem.menu.StructureMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StructuresCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player p && p.hasPermission("AllAndOnlyChest.structures")) {
            new StructureMenu(AllAndOnlyChests.getPlayerMenuUtility(p)).open();
        }
        return true;
    }
}
