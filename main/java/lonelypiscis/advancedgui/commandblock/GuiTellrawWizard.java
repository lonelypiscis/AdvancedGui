package lonelypiscis.advancedgui.commandblock;

import lonelypiscis.advancedgui.tellraw.JSONText;
import lonelypiscis.advancedgui.tellraw.TellrawOutput;

import org.lwjgl.input.Keyboard;

import com.google.gson.Gson;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCommandBlock;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.server.CommandBlockLogic;

public class GuiTellrawWizard extends GuiScreen {
	
	GUICustomCommandBlock cmdBlockGui;
	GuiTextField previewTextField, arrayPosTextField, textTextField, colorTextField;
	
	//Navigation Buttons
	GuiButton cancelBtn, doneBtn, btnPrev, btnNext, addExtra;
	
	//Format
	GuiButton boldBtn, italicBtn, underlBtn, stikeBtn, obfBtn;
	
	//Events
	GuiTextField clickValue, clickAction, hoverValue, hoverAction;
	
	TellrawOutput out;
	
	int pos;
	
	JSONText currentElement;
	JSONText current;
	
	public GuiTellrawWizard(GUICustomCommandBlock guiCustomCommandBlock) {
		cmdBlockGui = guiCustomCommandBlock;
		
		pos = 0;
		
		currentElement = new JSONText("", "white");
		
		out = new TellrawOutput();
		
		out.addExtra(currentElement);
	}
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(this.doneBtn = new GuiButton(0, this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, I18n.format("gui.done", new Object[0])));
        this.buttonList.add(this.cancelBtn = new GuiButton(1, this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, I18n.format("gui.cancel", new Object[0])));
        this.previewTextField = new GuiTextField(this.fontRendererObj, this.width / 2 - 150, 140, 300, 20);
        this.previewTextField.setMaxStringLength(32767);
        this.buttonList.add(this.btnPrev = new GuiButton(2, 0, 0, 20, 20, "<"));
        this.buttonList.add(this.btnNext = new GuiButton(3, 44, 0, 20, 20, ">"));
        this.arrayPosTextField = new GuiTextField(this.fontRendererObj, 22, 2, 20, 16);
        this.arrayPosTextField.setText(String.valueOf(pos));
        this.textTextField = new GuiTextField(this.fontRendererObj, this.width / 2 - 150, 33, 150, 20);
        this.buttonList.add(this.addExtra = new GuiButton(4, this.width / 2 - 4 - 100, 0, 20, 20, "+"));
        
        //Format Buttons
        this.buttonList.add(this.boldBtn = new GuiButton(5, this.width / 2 + 4, 35, 20, 20, "B"));
        this.buttonList.add(this.underlBtn = new GuiButton(6, this.width / 2 + 4 + 22, 35, 20, 20, "U"));
        this.buttonList.add(this.italicBtn = new GuiButton(7, this.width / 2 + 4 + 44, 35, 20, 20, "I"));
        this.buttonList.add(this.obfBtn = new GuiButton(8, this.width / 2 + 4 + 66, 35, 20, 20, "O"));
        this.buttonList.add(this.stikeBtn = new GuiButton(9, this.width / 2 + 4 + 88, 35, 20, 20, "S"));
        
        //Events
        this.hoverAction = new GuiTextField(this.fontRendererObj, this.width / 2 - 150, 70, 150, 20);
        this.hoverValue = new GuiTextField(this.fontRendererObj, this.width / 2 + 4, 70, 145, 20);
        
        this.clickAction = new GuiTextField(this.fontRendererObj, this.width / 2 - 150, 110, 150, 20);
        this.clickValue = new GuiTextField(this.fontRendererObj, this.width / 2 + 4, 110, 145, 20);
        
	}
	
	@Override
	public void drawScreen(int x, int y, float f)
	{
		drawDefaultBackground();
		
		this.drawCenteredString(this.fontRendererObj, "Tellraw Wizard", this.width / 2, 14, 16777215);
		this.drawString(this.fontRendererObj, "Text", this.width / 2 - 150, 23, 10526880);

		//this.drawString(this.fontRendererObj, "HoverEvent", this.width / 2 - 150 - 55, 76, 10526880);
		this.drawString(this.fontRendererObj, "ClickEvent", this.width / 2 - 150 - 55, 116, 10526880);
		this.drawString(this.fontRendererObj, "Action", this.width / 2 - 150, 60, 10526880);
		this.drawString(this.fontRendererObj, "Value", this.width / 2 + 4, 60, 10526880);
		
		//this.drawString(this.fontRendererObj, "ClickEvent", this.width / 2 - 150 - 62, 116, 10526880);
		this.drawString(this.fontRendererObj, "HoverEvent", this.width / 2 - 150 - 62, 76, 10526880);
		this.drawString(this.fontRendererObj, "Action", this.width / 2 - 150, 100, 10526880);
		this.drawString(this.fontRendererObj, "Value", this.width / 2 + 4, 100, 10526880);
		
		
		
		previewTextField.drawTextBox();
		arrayPosTextField.drawTextBox();
		textTextField.drawTextBox();
		
		hoverAction.drawTextBox();
		hoverValue.drawTextBox();
		
		clickAction.drawTextBox();
		clickValue.drawTextBox();

		super.drawScreen(x, y, f);
	}
	 
	protected void actionPerformed(GuiButton p_146284_1_)
    {
        if (p_146284_1_.enabled)
        {
            if (p_146284_1_.id == 1) {
            	//Back to Command Block Interface
            	
            	FMLClientHandler.instance().displayGuiScreen(this.mc.thePlayer, cmdBlockGui);
            }
            else if (p_146284_1_.id == 2) {
            	if (pos > 0) {
            		pos--;
            	}
            	
            	arrayPosTextField.setText(String.valueOf(pos));
            }
            else if (p_146284_1_.id == 3) {
            	if (pos < out.extra.size()-1) {
            		pos++;
            	}
            	
            	arrayPosTextField.setText(String.valueOf(pos));
            }
            else if (p_146284_1_.id == 4) {
            	out.addExtra(new JSONText("", "white"));
            	
            }
            else if (p_146284_1_.id == 0) {
            	cmdBlockGui.setCommand("/tellraw @p " + new Gson().toJson(this.out));
            	
            	FMLClientHandler.instance().displayGuiScreen(this.mc.thePlayer, cmdBlockGui);
            	
            }
            
            //bold
            else if (p_146284_1_.id == 5) {
            	
            	if (boldBtn.packedFGColour == 0) {
            		out.extra.get(pos).bold = "true";
            		boldBtn.packedFGColour = 1;
            	} else {
            		out.extra.get(pos).bold = "false";
            		boldBtn.packedFGColour = 0;
            	}
            	
            	
            }
            
            //underlined
            else if (p_146284_1_.id == 6) {
            	
            	if (underlBtn.packedFGColour == 0) {
            		out.extra.get(pos).underlined = "true";
            		underlBtn.packedFGColour = 1;
            	} else {
            		out.extra.get(pos).underlined = "false";
            		underlBtn.packedFGColour = 0;
            	}
            	
            	
            }
            
            //italic
            else if (p_146284_1_.id == 7) {
            	
            	if (italicBtn.packedFGColour == 0) {
            		out.extra.get(pos).italic = "true";
            		italicBtn.packedFGColour = 1;
            	} else {
            		out.extra.get(pos).italic = "false";
            		italicBtn.packedFGColour = 0;
            	}
            	
            	
            }
            
            //obfuscated
            else if (p_146284_1_.id == 8) {
            	
            	if (obfBtn.packedFGColour == 0) {
            		out.extra.get(pos).obfuscated = "true";
            		obfBtn.packedFGColour = 1;
            	} else {
            		out.extra.get(pos).obfuscated = "false";
            		obfBtn.packedFGColour = 0;
            	}
            	
            	
            }
            
            //strikethrough
            else if (p_146284_1_.id == 9) {
            	
            	if (stikeBtn.packedFGColour == 0) {
            		out.extra.get(pos).strikethrough = "true";
            		stikeBtn.packedFGColour = 1;
            	} else {
            		out.extra.get(pos).strikethrough = "false";
            		stikeBtn.packedFGColour = 0;
            	}
            	
            	
            }
            
            
            
            updateAllTextFields();
            
            
            
        }
        
        
    }
	
	protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);
        this.previewTextField.mouseClicked(par1, par2, par3);
        this.textTextField.mouseClicked(par1, par2, par3);
        this.clickAction.mouseClicked(par1, par2, par3);
        this.clickValue.mouseClicked(par1, par2, par3);
        this.hoverAction.mouseClicked(par1, par2, par3);
        this.hoverValue.mouseClicked(par1, par2, par3);
    }
	
	protected void keyTyped(char par1, int par2)
    {
        this.previewTextField.textboxKeyTyped(par1, par2);
        this.textTextField.textboxKeyTyped(par1, par2);
        this.clickAction.textboxKeyTyped(par1, par2);
        this.clickValue.textboxKeyTyped(par1, par2);
        this.hoverAction.textboxKeyTyped(par1, par2);
        this.hoverValue.textboxKeyTyped(par1, par2);
        
        current = out.extra.get(pos);
        
        current.text = textTextField.getText();
        current.clickEvent.action = clickAction.getText();
        current.clickEvent.value = clickValue.getText();
        current.hoverEvent.action = hoverAction.getText();
        current.hoverEvent.value = hoverValue.getText();
        
        
        
        
        
        out.extra.set(pos, current);
        
        this.previewTextField.setText("/tellraw @p " + new Gson().toJson(this.out).replaceAll("\u0027", "'"));
    }
	
	public void updateAllTextFields() {
		this.previewTextField.setText("/tellraw @p " + new Gson().toJson(this.out).replaceAll("\u0027", "'"));
		this.textTextField.setText(out.extra.get(pos).text);
	}
}
