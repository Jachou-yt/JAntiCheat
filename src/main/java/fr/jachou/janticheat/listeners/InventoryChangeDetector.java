package fr.jachou.janticheat.listeners;

import fr.jachou.janticheat.managers.Managers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;

public class InventoryChangeDetector implements Listener {
    private Map<Player, Integer> inventoryChanges = new HashMap<>();

    @EventHandler
    public void onInventoryChange(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            int currentChanges = inventoryChanges.getOrDefault(player, 0);
            inventoryChanges.put(player, currentChanges + 1);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        inventoryChanges.remove(event.getPlayer());
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        inventoryChanges.remove(event.getPlayer());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        inventoryChanges.put(event.getPlayer(), 0);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        int currentChanges = inventoryChanges.getOrDefault(player, 0);
        if (currentChanges > 10) {
            // The player has made more than 10 inventory changes in a short period of time
            // This could indicate that they are using a hacked client to automate inventory management
            kickPlayer(player);
        }
    }

    private void kickPlayer(Player player) {
        player.kickPlayer(Managers.PREFIX + "Vous avez été kick pour changement suspect dans l'inventaire.");
    }
}
