package org.shulkerdeposit.shulkerdeposit;

import org.bukkit.plugin.java.JavaPlugin;

public final class ShulkerDeposit extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("ShulkerDeposit on");
        getServer().getPluginManager().registerEvents(new Deposit(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("ShulkerDeposit off");
    }
}
