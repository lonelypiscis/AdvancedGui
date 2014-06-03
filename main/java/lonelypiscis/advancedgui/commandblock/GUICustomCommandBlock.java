package lonelypiscis.advancedgui.commandblock;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.init.Blocks;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class GUICustomCommandBlock extends GuiScreen {
	private static final Logger field_146488_a = LogManager.getLogger();
    /**
     * Text field containing the command block's command.
     */
    private GuiTextField commandTextField;
    private GuiTextField field_146486_g;
    
    public String newCommand;
    /**
     * Command block being edited.
     */
    private final CommandBlockLogic localCommandBlock;
    /**
     * "Done" button for the GUI.
     */
    private GuiButton doneBtn;
    private GuiButton cancelBtn;
    private GuiButton tildeBtn;
    private GuiButton aeBtn, oeBtn, ueBtn, szBtn;
    private GuiButton delBtn;
    private GuiButton hideBtn;
    private GuiButton tellrawBtn;
    private static final String __OBFID = "CL_00000748";

    public GUICustomCommandBlock(CommandBlockLogic p_i45032_1_)
    {
    	
        this.localCommandBlock = p_i45032_1_;
        newCommand = "";
    }
    
    

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        this.commandTextField.updateCursorCounter();
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
    	
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(this.doneBtn = new GuiButton(0, this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, I18n.format("gui.done", new Object[0])));
        this.buttonList.add(this.cancelBtn = new GuiButton(1, this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, I18n.format("gui.cancel", new Object[0])));
        this.buttonList.add(this.tildeBtn = new GuiButton(2, 25, 0, 20, 20, "~"));								// ~ - Knopf
        /*this.buttonList.add(this.aeBtn = new GuiButton(3, 25, 0, 20, 20, "�"));										// � - Knopf
        this.buttonList.add(this.delBtn = new GuiButton(4, this.width / 2 - 150 + 305, 50, 55, 20, "Delete All"));	// delete All - Knopf
        this.buttonList.add(this.oeBtn = new GuiButton(5, 50, 0, 20, 20, "�"));										// � - Knopf
        this.buttonList.add(this.ueBtn = new GuiButton(6, 75, 0, 20, 20, "�"));										// � - Knopf
        this.buttonList.add(this.szBtn = new GuiButton(7, 100, 0, 20, 20, "�"));*/									// � - Knopf
        this.buttonList.add(this.hideBtn = new GuiButton(8, 0, 0, 20, 20, "<<"));
        this.buttonList.add(this.tellrawBtn = new GuiButton(9, 0, this.height-21, 50, 20, "Wizard"));
        this.commandTextField = new GuiTextField(this.fontRendererObj, this.width / 2 - 150, 50, 300, 20);
        this.commandTextField.setMaxStringLength(32767);
        this.commandTextField.setFocused(true);
        this.commandTextField.setText(this.localCommandBlock.func_145753_i());
        this.field_146486_g = new GuiTextField(this.fontRendererObj, this.width / 2 - 150, 135, 300, 20);
        this.field_146486_g.setMaxStringLength(32767);
        this.field_146486_g.setEnabled(false);
        this.field_146486_g.setText(this.localCommandBlock.func_145753_i());
        

        
        
        if (newCommand != "") {
        	this.commandTextField.setText(newCommand);
        	newCommand = "";
        }
        	
        if (this.localCommandBlock.func_145749_h() != null)
        {
        	this.field_146486_g.setText(this.localCommandBlock.func_145749_h().getUnformattedText());
        }
        
        

        this.doneBtn.enabled = this.commandTextField.getText().trim().length() > 0;
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }
    
    public void setCommand(String cmd) {
    	newCommand = cmd;
    }
    
    public void addText(String txt) {
    	int cursPos = this.commandTextField.getCursorPosition();
    	
    	String value = this.commandTextField.getText();
    	
    	String val1 = value.substring(0, cursPos);
    	String val2 = value.substring(cursPos, value.length());
    	
    	this.commandTextField.setText(val1 + txt + val2);
    	
    	
    	this.commandTextField.setFocused(true);
    	this.commandTextField.setCursorPosition(cursPos+1);
    }

    protected void actionPerformed(GuiButton p_146284_1_)
    {
        if (p_146284_1_.enabled)
        {
            if (p_146284_1_.id == 1)
            {
                this.mc.displayGuiScreen((GuiScreen)null);
            }
            else if (p_146284_1_.id == 0)
            {
                PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());

                try
                {
                    packetbuffer.writeByte(this.localCommandBlock.func_145751_f());
                    this.localCommandBlock.func_145757_a(packetbuffer);
                    packetbuffer.writeStringToBuffer(this.commandTextField.getText());
                    this.mc.getNetHandler().addToSendQueue(new C17PacketCustomPayload("MC|AdvCdm", packetbuffer));
                }
                catch (Exception exception)
                {
                    field_146488_a.error("Couldn\'t send command block info", exception);
                }
                finally
                {
                    packetbuffer.release();
                }

                this.mc.displayGuiScreen((GuiScreen)null);
            } // ~ Knopf
            else if (p_146284_1_.id == 2) {
            	addText("~");
            } 
            else if (p_146284_1_.id == 3) {
            	addText("�");
            } // delete All Knopf
            else if (p_146284_1_.id == 4) {
            	this.commandTextField.setText("");
            	this.doneBtn.enabled = false;
            } // � Knopf
            else if (p_146284_1_.id == 5) {
            	addText("�");
            } // � Knopf
            else if (p_146284_1_.id == 6) {
            	addText("�");
            } // � Knopf
            else if (p_146284_1_.id == 7) {
            	addText("�");
            } // hide Knopf
            else if (p_146284_1_.id == 8) {
            	if (aeBtn.visible == true) {
            		setButtonsVisible(false);
            		hideBtn.displayString = ">>";
            		
            	} else {
            		setButtonsVisible(true);
            		hideBtn.displayString = "<<";
            	}
            }
            else if (p_146284_1_.id == 9) {
            	
            	FMLClientHandler.instance().displayGuiScreen(this.mc.thePlayer, new GuiTellrawWizard(this));
            }
        }
    }

    public void setButtonsVisible(boolean visible) {
    	this.tildeBtn.visible = visible;
    }
    
    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2)
    {
        this.commandTextField.textboxKeyTyped(par1, par2);
        this.field_146486_g.textboxKeyTyped(par1, par2);
        this.doneBtn.enabled = this.commandTextField.getText().trim().length() > 0;

        if (par2 != 28 && par2 != 156)
        {
            if (par2 == 1)
            {
                this.actionPerformed(this.cancelBtn);
            }
        }
        else
        {
            this.actionPerformed(this.doneBtn);
        }
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);
        this.commandTextField.mouseClicked(par1, par2, par3);
        this.field_146486_g.mouseClicked(par1, par2, par3);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
    	
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, I18n.format("advMode.setCommand", new Object[0]), this.width / 2, 24, 16777215);
        this.drawString(this.fontRendererObj, I18n.format("advMode.command", new Object[0]), this.width / 2 - 150, 37, 10526880);
        this.commandTextField.drawTextBox();
        byte b0 = 75;
        byte b1 = 0;
        FontRenderer fontrenderer = this.fontRendererObj;
        String s = I18n.format("advMode.nearestPlayer", new Object[0]);
        int i1 = this.width / 2 - 150;
        int l = b1 + 1;
        this.drawString(fontrenderer, s, i1, b0 + b1 * this.fontRendererObj.FONT_HEIGHT, 10526880);
        this.drawString(this.fontRendererObj, I18n.format("advMode.randomPlayer", new Object[0]), this.width / 2 - 150, b0 + l++ * this.fontRendererObj.FONT_HEIGHT, 10526880);
        this.drawString(this.fontRendererObj, I18n.format("advMode.allPlayers", new Object[0]), this.width / 2 - 150, b0 + l++ * this.fontRendererObj.FONT_HEIGHT, 10526880);

        if (this.field_146486_g.getText().length() > 0)
        {
            int k = b0 + l * this.fontRendererObj.FONT_HEIGHT + 20;
            this.drawString(this.fontRendererObj, I18n.format("advMode.previousOutput", new Object[0]), this.width / 2 - 150, k, 10526880);
            this.field_146486_g.drawTextBox();
        }
        
        super.drawScreen(par1, par2, par3);
    }
}
