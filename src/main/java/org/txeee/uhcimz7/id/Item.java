package org.txeee.uhcimz7.id;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public abstract class Item {
    public static ItemStack head(Player player) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();

        headMeta.setOwningPlayer(player);

        head.setItemMeta(headMeta);

        return head;
    }
}
