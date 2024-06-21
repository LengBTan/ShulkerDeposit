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
        ItemStack item = e.getCurrentItem();


        if(item != null && item.getType() != Material.AIR && item.getItemMeta() instanceof BlockStateMeta){//check if valid
            BlockStateMeta meta = (BlockStateMeta)item.getItemMeta();
            if(!e.getWhoClicked().getItemOnCursor().getType().equals(Material.AIR)
                    && e.getClick().isRightClick()
                    && meta.getBlockState() instanceof ShulkerBox){
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
