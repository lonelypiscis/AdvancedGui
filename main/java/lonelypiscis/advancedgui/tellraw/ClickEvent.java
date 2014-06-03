package lonelypiscis.advancedgui.tellraw;

public class ClickEvent {
	public String action, value;
	
	public ClickEvent(String act, String val) {
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
