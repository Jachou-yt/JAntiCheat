package fr.jachou.janticheat.listeners;

import fr.jachou.janticheat.managers.Managers;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class WallHackDetector {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        // Check if the player is using a wallhack by comparing their view distance with the normal view distance
        if (player.getGameMode() == GameMode.SURVIVAL && !player.isFlying() && !player.getGameMode().equals(GameMode.SPECTATOR) && !player.getAllowFlight()) {
            int viewDistance = player.getClientViewDistance();
            int normalViewDistance = player.getWorld().getWorldBorder().getWarningDistance() / 16;
            if (viewDistance > normalViewDistance + 2) { // Add a tolerance of 2 blocks
                player.sendMessage(Managers.PREFIX + " §fTu as été détécté en train d'utiliser un wallhack.");
                // Optional: take further action such as teleporting the player to a jail area, banning them, or logging the incident
            }
        }
    }
}
