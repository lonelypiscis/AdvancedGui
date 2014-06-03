package lonelypiscis.advancedgui.tellraw;

public class HoverEvent {
	public String action, value;
	
	public HoverEvent(String act, String val) {
		action = act;
		value = val;
	}
	
	public void setAction(String act) {
		action = act;
	}
	
	public void setValue(String val) {
		value = val;
	}
}
