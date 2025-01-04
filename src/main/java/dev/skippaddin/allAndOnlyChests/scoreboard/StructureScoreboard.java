package dev.skippaddin.allAndOnlyChests.scoreboard;

import dev.skippaddin.allAndOnlyChests.AllAndOnlyChests;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;


public class StructureScoreboard {

    private final Scoreboard scoreboard;

    private String structure;

    private int chests;

    private int collected;

    private int max;

    private StructureScoreboard() {
        String structure = AllAndOnlyChests.getPlugin().getConfig().getString("scoreboardStructure");
        if (structure != null) {
            this.structure = structure;
        } else {
            this.structure = "None";
        }

        this.chests = AllAndOnlyChests.getPlugin().getConfig().getInt("chests");

        this.collected = AllAndOnlyChests.getPlugin().getConfig().getInt("collected");

        this.max = AllAndOnlyChests.getPlugin().getConfig().getInt("max");

        ScoreboardManager manager = Bukkit.getScoreboardManager();

        this.scoreboard = manager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("challenge", Criteria.DUMMY, Component.text("Structure: ", NamedTextColor.GOLD).append(Component.text(this.structure, NamedTextColor.WHITE)));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        objective.getScore(ChatColor.YELLOW + "Items: " + ChatColor.WHITE + collected + "/" + max).setScore(2);
        objective.getScore(ChatColor.YELLOW + "Chests: " + ChatColor.WHITE + chests).setScore(1);
        if (max != 0 && max == collected) {
            objective.getScore(ChatColor.GOLD + "COMPLETED!").setScore(0);
        }
    }

    private static class SingletonHelper {
        private static final StructureScoreboard INSTANCE = new StructureScoreboard();
    }

    public static StructureScoreboard getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public void displayScoreboard(Player player) {
        player.setScoreboard(scoreboard);
    }

    public void updateStructure(String structure, int max) {
        scoreboard.clearSlot(DisplaySlot.SIDEBAR);

        this.structure = structure;
        scoreboard.getObjective("challenge").unregister();
        Objective objective = scoreboard.registerNewObjective("challenge", Criteria.DUMMY, Component.text("Structure: ", NamedTextColor.GOLD).append(Component.text(this.structure, NamedTextColor.WHITE)));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        this.collected = 0;
        this.max = max;
        objective.getScore(ChatColor.YELLOW + "Items: " + ChatColor.WHITE + this.collected + "/" + this.max).setScore(2);;

        this.chests = 0;
        objective.getScore(ChatColor.YELLOW + "Chests: " + ChatColor.WHITE + chests).setScore(1);
    }

    public void updateItems(int collected) {
        Objective objective = scoreboard.getObjective(DisplaySlot.SIDEBAR);
        objective.getScore(ChatColor.YELLOW + "Items: " + ChatColor.WHITE + this.collected + "/" + this.max).resetScore();
        this.collected += collected;
        objective.getScore(ChatColor.YELLOW + "Items: " + ChatColor.WHITE + this.collected + "/" + max).setScore(2);

        if (this.collected >= this.max) {
            objective.getScore(ChatColor.GOLD + "COMPLETED!").setScore(0);
        }
    }

    public void updateChests() {
        Objective objective = scoreboard.getObjective(DisplaySlot.SIDEBAR);
        objective.getScore(ChatColor.YELLOW + "Chests: " + ChatColor.WHITE + this.chests).resetScore();
        this.chests++;
        objective.getScore(ChatColor.YELLOW + "Chests: " + ChatColor.WHITE + this.chests).setScore(1);
    }

    public void complete() {
        Objective objective = scoreboard.getObjective(DisplaySlot.SIDEBAR);
        objective.getScore(ChatColor.GOLD + "COMPLETED!").setScore(0);
    }

    public void stageSaveData() {
        FileConfiguration configuration = AllAndOnlyChests.getPlugin().getConfig();
        configuration.set("scoreboardStructure", structure);
        configuration.set("chests", chests);
        configuration.set("collected", collected);
        configuration.set("max", max);
    }
}
