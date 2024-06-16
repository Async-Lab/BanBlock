package club.asyncraft.banblock;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlockManager {

    private static volatile BlockManager instance;

    private final Set<Material> bannedBlockSet = new HashSet<>();
    private String message = null;

    /**
     * 检查方块是否被ban
     * @param material 物品
     * @return 是否被ban
     */
    public boolean checkBlock(Material material) {
        return bannedBlockSet.contains(material);
    }

    /**
     * 检查方块是否被ban
     * 如果ban，则向玩家发送消息
     * @param material 物品
     * @param player 玩家
     * @return 是否被ban
     */
    public boolean checkBlockAndSendMsg(Material material, Player player) {
        if (bannedBlockSet.contains(material)) {
            sendMessage(player);
            return true;
        }
        return false;
    }

    public void sendMessage(Player player) {
        player.sendMessage(message.replace("&","§"));
    }

    public void loadConfig(Configuration config) {
        message = config.getString("message");
        List<String> banList = config.getStringList("ban_list");
        bannedBlockSet.clear();
        banList.forEach((name) -> {
            Material material = Material.getMaterial(name.toUpperCase());
            if (material != null) {
                bannedBlockSet.add(material);
            }
        });
    }

    public static BlockManager getInstance() {
        if (instance == null) {
            synchronized (BlockManager.class) {
                if (instance == null) {
                    instance = new BlockManager();
                }
            }
        }
        return instance;
    }

}
