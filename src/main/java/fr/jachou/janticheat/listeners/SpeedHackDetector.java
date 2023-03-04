package fr.jachou.janticheat.listeners;

import fr.jachou.janticheat.managers.Managers;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

public class SpeedHackDetector implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        // Check if the player is using a speed hack by comparing their speed with the normal speed
        if (player.getGameMode() == GameMode.SURVIVAL && !player.isFlying() && !player.getGameMode().equals(GameMode.SPECTATOR) && !player.getAllowFlight() &&
                player.getPotionEffect(PotionEffectType.SPEED) == null && player.getPotionEffect(PotionEffectType.SLOW) == null) {
            double speed = event.getTo().distance(event.getFrom()) * 20; // Calculate the player's speed in blocks per second
            double normalSpeed = player.getWalkSpeed() * 0.23; // Calculate the normal speed in blocks per second (default walk speed is 0.2)
            if (speed > normalSpeed * 1.2) { // Add a tolerance of 20%
                player.sendMessage(Managers.PREFIX + "§Vous avez été kick pour speed hack.");
                // Optional: take further action such as teleporting the player to a jail area, banning them, or logging the incident
            }
        }
    }
}
