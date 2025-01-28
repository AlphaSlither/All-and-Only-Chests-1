package dev.skippaddin.allAndOnlyChests.commands;

import dev.skippaddin.allAndOnlyChests.AllAndOnlyChests;
import dev.skippaddin.allAndOnlyChests.challenge.ChallengeData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class ItemDropsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("AllAndOnlyChests.command.drops")) {
                if (args.length == 0) {
                    Component baseComponent = Component.text("Drops are currently: ", NamedTextColor.YELLOW);
                    if (ChallengeData.isDropsAllowed()) {
                        p.sendMessage(baseComponent.append(Component.text("ON", NamedTextColor.GREEN)));
                    } else {
                        p.sendMessage(baseComponent.append(Component.text("OFF", NamedTextColor.RED)));
                    }
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("toggle")) {
                        boolean dropsAllowed = ChallengeData.flipDropsAllowed();
                        ChallengeData.setSaved(false);
                        Component baseComponent = Component.text("Drops are now: ", NamedTextColor.YELLOW);
                        if (dropsAllowed) {
                            p.sendMessage(baseComponent.append(Component.text("ON", NamedTextColor.GREEN)));
                            p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                        } else {
                            p.sendMessage(baseComponent.append(Component.text("OFF", NamedTextColor.RED)));
                            p.playSound(p, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        }
                    } else {
                        p.sendMessage(Component.text("This is not an argument.", NamedTextColor.RED));
                    }
                }
            } else {
                p.sendMessage(Component.text("No permission.", NamedTextColor.RED));
            }
        }

        return true;
    }
}
