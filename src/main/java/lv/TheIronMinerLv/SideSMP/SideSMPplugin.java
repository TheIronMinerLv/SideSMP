package lv.TheIronMinerLv.SideSMP;

import org.bukkit.plugin.java.JavaPlugin;

import lv.TheIronMinerLv.SideSMP.listeners.ChatListener;
import lv.TheIronMinerLv.SideSMP.listeners.JoinListener;
import lv.TheIronMinerLv.SideSMP.listeners.CommandSendListener;

public class SideSMPplugin extends JavaPlugin {

    private static SideSMPplugin instance;

    public static SideSMPplugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new CommandSendListener(), this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getLogger().info("Plugin is enabled!");
    }
    @Override
    public void onDisable() {
        getLogger().info("Plugin is disabled!");
    }
}
