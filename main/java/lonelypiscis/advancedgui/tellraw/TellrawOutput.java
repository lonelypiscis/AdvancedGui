package lonelypiscis.advancedgui.tellraw;

import java.util.ArrayList;

public class TellrawOutput {
	
	public String text;
	public ArrayList<JSONText> extra;
	
	
	public TellrawOutput() {
		text = "";
		extra = new ArrayList<JSONText>();
	}
	
	public boolean addExtra(JSONText txt) {
		return extra.add(txt);
	}
	
	public void setText(String txt) {
		text = txt;
	}
}
