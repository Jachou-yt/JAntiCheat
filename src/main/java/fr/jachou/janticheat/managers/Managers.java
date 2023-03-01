package fr.jachou.janticheat.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Managers {

    public static String PREFIX = "[JAntiCheat] ";

    public static void load() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + PREFIX + "Le plugin est §lload.");
    }

    public static void unload() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + PREFIX + "Le plugin est §lunload.");
    }
}
