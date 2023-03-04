package fr.jachou.janticheat.managers;

import fr.jachou.janticheat.JAntiCheat;
import fr.jachou.janticheat.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Managers {

    private static JAntiCheat jac = JAntiCheat.getInstance();

    public static String PREFIX = "[JAntiCheat] ";

    public static void load() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + PREFIX + "Le plugin est §lload.");
        registerEvents();
    }

    public static void unload() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + PREFIX + "Le plugin est §lunload.");
    }

    private static void registerEvents() {
        jac.getServer().getPluginManager().registerEvents(new WallHackDetector(), jac);
        jac.getServer().getPluginManager().registerEvents(new SpeedHackDetector(), jac);
        jac.getServer().getPluginManager().registerEvents(new XrayDetector(), jac);
        jac.getServer().getPluginManager().registerEvents(new AimbotDetector(), jac);
        jac.getServer().getPluginManager().registerEvents(new StrenghtHackDetector(), jac);
        jac.getServer().getPluginManager().registerEvents(new InventoryChangeDetector(), jac);

    }

    private static void registerCommands() {

    }
}
