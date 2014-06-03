package lonelypiscis.advancedgui;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = AdvancedGui.MODID, version = AdvancedGui.VERSION)
public class AdvancedGui
{
    public static final String MODID = "AdvancedGui";
    public static final String VERSION = "1.0";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("I am a Example Modsfd!");
    }
}
