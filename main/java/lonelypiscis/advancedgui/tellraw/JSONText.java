package lonelypiscis.advancedgui.tellraw;

public class JSONText {
	
	//Basic Setup
	public String text, color;
	
	//Events
	public ClickEvent clickEvent;
	public HoverEvent hoverEvent;
	
	//Format
	public String bold, italic, underlined, strikethrough, obfuscated;
	
	public JSONText(String txt, String clr) {
		text = txt;
		color = clr;
		clickEvent = new ClickEvent("", "");
		hoverEvent = new HoverEvent("", "");
	}
	
	public void setBold(boolean b) {
		bold = String.valueOf(b);
	}
	
	public void setItalic(boolean i) {
		italic = String.valueOf(i);
	}
	
	public void setUnderlined(boolean u) {
		underlined = String.valueOf(u);
	}
	
	public void setStrikethrough(boolean s) {
		strikethrough = String.valueOf(s);
	}
	
	public void setObfuscated(boolean o) {
		obfuscated = String.valueOf(o);
	}
	
	@Override
	public String toString() {
	   return "DataObject [extra=" + text + ", color=" + color + "]";
	}
}
