package de.hdm.team7.client.gui.views;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.team7.server.businessObjects.Component;

public class EditView {

	public Panel getView(){
		// Initiate wrapper Panel
		Panel panel = new FlowPanel();
		
		// Start layout creation here
		HTML h1 = new HTML("<h1>Baugruppen</h1>");
		panel.add(h1);
		
		VerticalPanel vPanel = new VerticalPanel();
		
		//Formular Baugruppenname 
		HorizontalPanel hPanel1 = new HorizontalPanel();
		vPanel.add(hPanel1);

		Label name = new Label("Baugruppenname:");
		final TextBox baugruppenname = new TextBox();	
		hPanel1.add(name);
		hPanel1.add(baugruppenname);
		hPanel1.setCellWidth(name,"200px");
		
		//Formular Materialbezeichnung
		HorizontalPanel hPanel2 = new HorizontalPanel();
		vPanel.add(hPanel2);
		
		Label materialbezeichnung = new Label("Materialbezeichnung:");
		final TextBox materialbezeichnungtxt = new TextBox();	
		hPanel2.add(materialbezeichnung);
		hPanel2.add(materialbezeichnungtxt);
		hPanel2.setCellWidth(materialbezeichnung,"200px");
		
		
		
		//Formular Beschreibung
		HorizontalPanel hPanel3 = new HorizontalPanel();
		vPanel.add(hPanel3);
		
		Label beschreibung = new Label("Beschreibung:");
		final TextArea beschreibungtxt = new TextArea();
		hPanel3.add(beschreibung);
		hPanel3.add(beschreibungtxt);
		hPanel3.setCellWidth(beschreibung, "200px");
		
		//Absenden Button
		Button bauteilanlegenbtn = new Button("anlegen", new ClickHandler() {
			public void onClick(ClickEvent event) {
				String businessObjectType = "bauteil";
				String name = baugruppenname.getText();
				String materialBezeichnung = beschreibungtxt.getText();
				String beschreibung = materialbezeichnungtxt.getText();
				ArrayList<Component> childrenComponents = null;
				
				createObject();
			}
		});
		
		
		
		vPanel.add(bauteilanlegenbtn);
		
	
		panel.add(vPanel);
		// End layout creation here
		
		// IMPORTANT: always return a Panel!
		return panel;
	}
	
}