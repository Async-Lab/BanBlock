package club.asyncraft.banblock;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PluginCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player && (!sender.isOp() && !sender.hasPermission("banblock.reload"))) {
            sender.sendMessage("§c你没有权限这样做");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage("§a/bb reload 重载配置文件");
            return true;
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            BanBlock.instance.initConfig();
            sender.sendMessage("§a重载成功");
            return true;
        }
        return false;
    }
}
