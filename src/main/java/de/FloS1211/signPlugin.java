package de.FloS1211;

import de.FloS1211.command.signCommandExecuter;
import de.FloS1211.command.signCommandTabComplete;
import org.bukkit.plugin.java.JavaPlugin;

public final class signPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("sign").setExecutor(new signCommandExecuter());
        getCommand("sign").setTabCompleter(new signCommandTabComplete());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
