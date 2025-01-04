package dev.skippaddin.allAndOnlyChests.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StructureTabCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (sender instanceof Player p) {
            List<String> arglist = new ArrayList<>();
            if (p.hasPermission("AllAndOnlyChests.command.structure.finish") && args.length == 1) {
                arglist.add("finish");
            }
            return arglist;
        }

        return null;
    }
}
