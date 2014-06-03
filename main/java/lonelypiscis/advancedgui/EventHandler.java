package lonelypiscis.advancedgui;

import lonelypiscis.advancedgui.commandblock.GUICustomCommandBlock;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.GuiCommandBlock;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.achievement.GuiAchievement;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class EventHandler {
	@SubscribeEvent
	public void rightClick(PlayerInteractEvent evt) {
		//System.out.println("Player " + evt.entityPlayer + " leftclicked block");
		
		if (evt.entityPlayer.worldObj.getBlock(evt.x, evt.y, evt.z) == Blocks.command_block && evt.action == Action.RIGHT_CLICK_BLOCK) {	
			TileEntity commandTile = evt.entityPlayer.worldObj.getTileEntity(evt.x, evt.y, evt.z);
			FMLClientHandler.instance().displayGuiScreen(evt.entityPlayer, new GUICustomCommandBlock(((TileEntityCommandBlock)commandTile).func_145993_a()));
			evt.setCanceled(true);
		}
		
		
		
		/*TileEntity commandTile = evt.entityPlayer.worldObj.getTileEntity(evt.x, evt.y, evt.z);
		
		FMLClientHandler.instance().displayGuiScreen(evt.entityPlayer, new GuiCommandBlock(commandTile));*/
	}
}
