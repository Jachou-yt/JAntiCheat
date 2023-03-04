package fr.jachou.janticheat.listeners;

import fr.jachou.janticheat.managers.Managers;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class StrenghtHackDetector implements Listener {
    private HashMap<Player, Integer> damageCounter;

    public StrenghtHackDetector() {
        this.damageCounter = new HashMap<Player, Integer>();
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            int totalDamage = 0;
            for (PotionEffect effect : player.getActivePotionEffects()) {
                if (effect.getType().equals(PotionEffectType.INCREASE_DAMAGE)) {
                    totalDamage += (effect.getAmplifier() + 1) * 3; // ajouter des dégâts en fonction de l'amplificateur de l'effet
                }
            }
            for (Enchantment enchant : player.getInventory().getItemInMainHand().getEnchantments().keySet()) {
                if (enchant.equals("DIG_SPEED")) {
                    totalDamage += player.getInventory().getItemInMainHand().getEnchantmentLevel(enchant) + 1; // ajouter des dégâts en fonction du niveau de l'enchantement
                }
            }
            totalDamage += event.getDamage(); // ajouter les dégâts de base

            if (damageCounter.containsKey(player)) {
                int previousDamage = damageCounter.get(player);
                if (totalDamage > previousDamage * 2) {
                    // Kick le joueur pour triche de force
                    player.kickPlayer(Managers.PREFIX + "Vous avez été kické pour triche de force.");
                } else {
                    damageCounter.put(player, totalDamage);
                }
            } else {
                damageCounter.put(player, totalDamage);
            }
        }
    }
}
