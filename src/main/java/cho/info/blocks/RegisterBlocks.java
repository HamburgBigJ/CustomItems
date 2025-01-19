package cho.info.blocks;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.material.block.GenericCustomBlock;

public class RegisterBlocks {

    public Plugin plugin;

    public RegisterBlocks(Plugin plugin){
        this.plugin = plugin;
    }

    public void createBlock(int id, String name, String texture) {
        new GenericCustomBlock(plugin, name, true, new GenericCubeBlockDesign(plugin, texture, id));
    }

}
