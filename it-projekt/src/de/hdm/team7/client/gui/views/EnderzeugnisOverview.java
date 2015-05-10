package de.hdm.team7.client.gui.views;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

public class EnderzeugnisOverview {

	public Panel getView(){
		// Initiate wrapper Panel
		Panel panel = new FlowPanel();
		
		// Start layout creation here
		HTML h1 = new HTML("<h1>EnderzeugnisOverview</h1>");
		panel.add(h1);
		
		Label lbl = new Label("<de.hdm.team7.client.gui.views.EnderzeugnisOverview.java>");
		panel.add(lbl);
		// End layout creation here
		
		// IMPORTANT: always return a Panel!
		return panel;
	}
	
}