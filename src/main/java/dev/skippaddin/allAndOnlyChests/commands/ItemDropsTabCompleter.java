package dev.skippaddin.allAndOnlyChests.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ItemDropsTabCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (sender instanceof Player p) {
            List<String> argList = new ArrayList<>();
            if (p.hasPermission("AllAndOnlyChests.command.drops") && args.length == 1) {
                argList.add("toggle");
            }
            return argList;
        }

        return null;
    }
}
