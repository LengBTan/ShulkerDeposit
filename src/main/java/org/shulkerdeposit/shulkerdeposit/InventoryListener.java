package org.shulkerdeposit.shulkerdeposit;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;


public class InventoryListener implements Listener {
    @EventHandler
    public void onClickItem(InventoryClickEvent e){
        if(!e.getWhoClicked().getItemOnCursor().getType().equals(Material.AIR)
        && e.getClick().isRightClick()
        && e.getWhoClicked().getInventory().contains(Material.SHULKER_BOX)){
            ItemStack item = e.getCurrentItem();
            BlockStateMeta meta = (BlockStateMeta)item.getItemMeta();
            if(meta.getBlockState() instanceof ShulkerBox){
                ShulkerBox shulker = (ShulkerBox)meta.getBlockState();

                shulker.getInventory().addItem(new ItemStack(e.getWhoClicked().getItemOnCursor()));

                meta.setBlockState(shulker);
                item.setItemMeta(meta);

                e.getWhoClicked().setItemOnCursor(null);

                e.setCancelled(true);
            }
        }
    }
}
