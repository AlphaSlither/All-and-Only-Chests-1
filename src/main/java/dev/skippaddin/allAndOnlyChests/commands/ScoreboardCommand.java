package dev.skippaddin.allAndOnlyChests.commands;

import dev.skippaddin.allAndOnlyChests.scoreboard.StructureScoreboard;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ScoreboardCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (sender instanceof Player p) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("toggle")) {
                    StructureScoreboard.getInstance().toggleScoreboard(p);
                }
            } else {
                p.sendMessage(Component.text("No arguments or too many provided", NamedTextColor.RED));
            }
        }

        return true;
    }
}
