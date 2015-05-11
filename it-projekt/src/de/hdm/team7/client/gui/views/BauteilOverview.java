package de.hdm.team7.client.gui.views;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;

public class BauteilOverview {

	public Panel getView(){
		// Initiate wrapper Panel
		Panel panel = new FlowPanel();
		
		// Start layout creation here
		HTML h1 = new HTML("<h1>Bauteile</h1>");
		panel.add(h1);
		
		HTML h2 = new HTML("<h2>Bauteil anlegen:</h2>");
		panel.add(h2);
		
		Label name = new Label("Bauteilname:");
		panel.add(name);
		TextBox bauteilname = new TextBox();
		panel.add(bauteilname);
		
		Button bauteilanlegenbtn = new Button("anlegen");
		panel.add(bauteilanlegenbtn);
		
		// End layout creation here
		
		// IMPORTANT: always return a Panel!
		return panel;
	}
	
}
