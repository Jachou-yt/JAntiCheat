package fr.jachou.janticheat.listeners;

import fr.jachou.janticheat.managers.Managers;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashMap;

public class XrayDetector implements Listener {
    private final HashMap<Player, Integer> totalBlocksMined = new HashMap<>();
    private final HashMap<Player, Integer> diamondBlocksMined = new HashMap<>();
    private final double diamondThreshold = 0.01; // 1% threshold

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();

        totalBlocksMined.putIfAbsent(player, 0);
        diamondBlocksMined.putIfAbsent(player, 0);

        if (block.getType() == Material.DIAMOND_ORE) {
            diamondBlocksMined.put(player, diamondBlocksMined.get(player) + 1);
        }

        totalBlocksMined.put(player, totalBlocksMined.get(player) + 1);

        double diamondPercentage = (double) diamondBlocksMined.get(player) / totalBlocksMined.get(player);

        if (diamondPercentage >= diamondThreshold) {
            player.kickPlayer(Managers.PREFIX + "§cVous avez été kick pour X-Ray !"); // Kick the player if the threshold is reached
        }
    }

}
