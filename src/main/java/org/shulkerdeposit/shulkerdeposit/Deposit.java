package org.shulkerdeposit.shulkerdeposit;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;


public class Deposit implements Listener {
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

                //check if the item was added at all, maybe check before and after block state to see if it changed?
                //take a snapshot of the shulker inventory
                ItemStack oldShulker = new ItemStack(shulkerItem);

                shulker.getInventory().addItem(new ItemStack(heldItem));//attempt to add to the shulker

                //update the shulker metadata
                shulkerMeta.setBlockState(shulker);
                shulkerItem.setItemMeta(shulkerMeta);

                if(shulkerItem.isSimilar(oldShulker)){
                    e.setCancelled(true);
                    return;
                }
                else{
                    e.getWhoClicked().setItemOnCursor(null);
                    e.setCancelled(true);
                }
            }
        }
    }
}
