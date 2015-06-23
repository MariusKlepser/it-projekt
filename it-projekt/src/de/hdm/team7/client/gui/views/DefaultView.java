package de.hdm.team7.client.gui.views;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

public class DefaultView {

	public Panel getView(){
		// Initiate wrapper Panel
		Panel panel = new FlowPanel();
		
		// Start layout creation here
		HTML h1 = new HTML("<h1>DefaultView</h1>");
		panel.add(h1);
		
		Label lbl = new Label("<de.hdm.team7.client.gui.views.DefaultView.java>");
		panel.add(lbl);
		// End layout creation here
		
		// IMPORTANT: always return a Panel!
		return panel;
	}
	
}