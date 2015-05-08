package de.hdm.team7.client.gui;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class ViewHandler {
	private final RootPanel rootPanel;
	
	// Init RootPanel with Target
	public ViewHandler(String target){
		this.rootPanel = RootPanel.get(target);
	}

	// Clear RootPanel
	public void clearRootPanel(){
		this.rootPanel.clear();
	}
	
	// Set RootPanel Widget
	public void setRootPanelContent(Widget w){
		this.rootPanel.clear();
		this.rootPanel.add(w);
	}
}
