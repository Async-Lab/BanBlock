package club.asyncraft.banblock;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PluginListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        BlockManager blockManager = BlockManager.getInstance();
        Material type = event.getBlock().getType();
        if (blockManager.checkBlock(type)) {
            if (!player.isOp() && !player.hasPermission("banblock.place.*") && !player.hasPermission("banblock.place." + type)) {
                event.setCancelled(true);
                blockManager.sendMessage(player);
            }
        }
    }

}
