
package com.example.maceplugin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MacePlugin extends JavaPlugin implements Listener {

    private boolean isMaceCrafted = false;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("MacePlugin Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("MacePlugin Disabled!");
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (isMaceCrafted) {
            if (event.getItem() != null && event.getItem().getType() == Material.IRON_SWORD) {
                event.getPlayer().sendMessage("Mace has already been crafted.");
            }
        }
    }

    @EventHandler
    public void onCrafting(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().contains(Material.IRON_SWORD)) {
            isMaceCrafted = true;
            player.sendMessage("You crafted the Mace! Recipe is now disabled.");
        }
    }

    @EventHandler
    public void onItemDestroy(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        if (event.getItem().getType() == Material.IRON_SWORD) {
            isMaceCrafted = false;
            player.sendMessage("Your Mace was destroyed. Recipe is now enabled again.");
        }
    }
}
