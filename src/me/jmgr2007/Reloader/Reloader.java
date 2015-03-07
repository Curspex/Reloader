package me.jmgr2007.Reloader;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Reloader extends JavaPlugin {
    private CommandExecutor CE = new ReloaderListener(this);
    private final Pl pl = new Pl(); 
    
    public void onEnable() {
    	Vars.disabled = new Val();
    	Vars.enabled = new Val();
    	Vars.loaded = new Val();
    	Vars.reloaded = new Val();
    	Vars.unloaded = new Val();
        Logger log = this.getServer().getLogger();
        log.info("[Reloader] Passing Reloader command to command handler");
        this.getCommand("reloader").setExecutor(CE);
        log.info("[Reloader] Passing Plugin command to command listener");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(pl, this);
        initialConfigCheck();
    }
    
    private void initialConfigCheck(){
        getConfig().options().copyDefaults(true);
        if(!(new File(this.getDataFolder(),"config.yml").exists())){
            this.getLogger().info("Saving default configuration file.");
            this.saveDefaultConfig();
        }
    }

}