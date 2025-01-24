package dev.skippaddin.allAndOnlyChests.commands;

import dev.skippaddin.allAndOnlyChests.AllAndOnlyChests;
import dev.skippaddin.allAndOnlyChests.challenge.ChallengeData;
import dev.skippaddin.allAndOnlyChests.scoreboard.StructureScoreboard;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StructureCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (sender instanceof Player p) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("finish")) {
                    if (p.hasPermission("AllAndOnlyChests.command.structure.finish")) {
                        if (!AllAndOnlyChests.getSelectedStructure().isEmpty()) {
                            ChallengeData.getStructureProgress().replace(AllAndOnlyChests.getSelectedStructure(), true);
                            AllAndOnlyChests.setSelectedStructure("");
                            TextComponent textComponent = Component.text("Structure completed!", NamedTextColor.GOLD);
                            p.sendMessage(textComponent);
                            p.playSound(p, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f);
                            StructureScoreboard scoreboard = StructureScoreboard.getInstance();
                            scoreboard.complete();
                            ChallengeData.setSaved(false);
                        } else {
                            TextComponent textComponent = Component.text("No structure selected!", NamedTextColor.RED);
                            p.sendMessage(textComponent);
                        }
                    } else {
                        TextComponent textComponent = Component.text("No permissions!", NamedTextColor.RED);
                        p.sendMessage(textComponent);
                    }
                } else {
                    TextComponent textComponent = Component.text("This is not a valid argument!", NamedTextColor.RED);
                    p.sendMessage(textComponent);
                }
            } else {
                TextComponent textComponent = Component.text("No arguments or too many provided.", NamedTextColor.RED);
                p.sendMessage(textComponent);
            }
        }

        return true;
    }

}
