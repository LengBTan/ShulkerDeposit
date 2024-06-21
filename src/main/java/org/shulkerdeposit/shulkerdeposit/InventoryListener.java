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
        ItemStack shulkerItem = e.getCurrentItem();
        ItemStack heldItem = e.getWhoClicked().getItemOnCursor();


        //don't do anything if the cursor held item is a shulker box
        if(heldItem.getItemMeta() instanceof BlockStateMeta){
            BlockStateMeta heldMeta = (BlockStateMeta) heldItem.getItemMeta();
            if(heldMeta.getBlockState() instanceof ShulkerBox){
                return;
            }
        }

        if(shulkerItem != null && shulkerItem.getType() != Material.AIR && shulkerItem.getItemMeta() instanceof BlockStateMeta){//check if valid
            BlockStateMeta shulkerMeta = (BlockStateMeta) shulkerItem.getItemMeta();

            if(!heldItem.getType().equals(Material.AIR)
                    && e.getClick().isRightClick()
                    && shulkerMeta.getBlockState() instanceof ShulkerBox){

                ShulkerBox shulker = (ShulkerBox) shulkerMeta.getBlockState();

                shulker.getInventory().addItem(new ItemStack(heldItem));

                shulkerMeta.setBlockState(shulker);
                shulkerItem.setItemMeta(shulkerMeta);

                e.getWhoClicked().setItemOnCursor(null);

                e.setCancelled(true);
            }
        }
    }
}
