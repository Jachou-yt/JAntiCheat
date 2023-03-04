package fr.jachou.janticheat.listeners;

import fr.jachou.janticheat.managers.Managers;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;

public class AimbotDetector implements Listener {
    private Map<Player, Integer> suspiciousPlayers = new HashMap<>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (!player.isOnline()) {
            return;
        }

        Location prevLocation = event.getFrom();
        int suspiciousness = suspiciousPlayers.getOrDefault(player, 0);

        Location currLocation = event.getTo();

        double distance = currLocation.distance(prevLocation);

        if (distance > 2.0) { // Player has moved their camera too fast
            suspiciousness++;
        } else {
            suspiciousness = Math.max(suspiciousness - 1, 0);
        }

        if (suspiciousness > 5) { // Player is too suspicious, ban them
            player.kickPlayer(Managers.PREFIX + "Vous avez été kick pour aimbot.");
        } else {
            suspiciousPlayers.put(player, suspiciousness);
        }
    }
}
