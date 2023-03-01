package fr.jachou.janticheat;

import fr.jachou.janticheat.listeners.WallHackDetector;
import fr.jachou.janticheat.managers.Managers;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class JAntiCheat extends JavaPlugin implements Listener {

    private static JAntiCheat instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Managers.load();

        // Events
        getServer().getPluginManager().registerEvents(this, new WallHackDetector());

        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Managers.unload();
    }
}
