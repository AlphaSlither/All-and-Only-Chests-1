package dev.skippaddin.allAndOnlyChests.menuSystem.utility;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

public final class CustomHeadUtility {

    private CustomHeadUtility() {}

    public static ItemStack getCustomHead(String url) {
        PlayerProfile playerProfile = Bukkit.createPlayerProfile(UUID.randomUUID());
        try {
            playerProfile.getTextures().setSkin(new URI(url).toURL());
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        skullMeta.setOwnerProfile(playerProfile);
        head.setItemMeta(skullMeta);
        return head;
    }

}
