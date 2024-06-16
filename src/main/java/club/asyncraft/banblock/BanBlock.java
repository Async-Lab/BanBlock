package club.asyncraft.banblock;

import org.bukkit.plugin.java.JavaPlugin;

public final class BanBlock extends JavaPlugin {

    public static BanBlock instance;

    @Override
    public void onEnable() {
        instance = this;
        initConfig();
        getServer().getPluginCommand("banblock").setExecutor(new PluginCommand());
        getServer().getPluginManager().registerEvents(new PluginListener(),this);
        sendInfo("BanBlock » Plugin Enabled");
    }

    public void initConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        reloadConfig();
        BlockManager.getInstance().loadConfig(getConfig());
    }

    private void sendInfo(String s) {
        getServer().getConsoleSender().sendMessage(s.replace("&","§"));
    }

}
