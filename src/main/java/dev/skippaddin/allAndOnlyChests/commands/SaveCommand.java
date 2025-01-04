package dev.skippaddin.allAndOnlyChests.commands;

import dev.skippaddin.allAndOnlyChests.AllAndOnlyChests;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SaveCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("AllAndOnlyChests.command.save")) {
                AllAndOnlyChests.getPlugin().saveData();
                p.sendMessage(Component.text("Data saved.", NamedTextColor.GREEN));
            } else {
                p.sendMessage(Component.text("No permission!", NamedTextColor.RED));
            }
        }

        return true;
    }
}
