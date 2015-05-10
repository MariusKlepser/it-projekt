package de.hdm.team7.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.team7.client.gui.CustomMenuBar;
import de.hdm.team7.client.gui.HistoryHandler;

public class It_projekt implements EntryPoint {
	
	public void onModuleLoad() {		
		// Setup HistoryHandler
		HistoryHandler historyHandler = new HistoryHandler();
		historyHandler.setHistoryHandler();
		
		// Setup Custom MenuBar
		CustomMenuBar customMenuBar = new CustomMenuBar();
		MenuBar menuBar = customMenuBar.getMenuBar();
		
		// Add MenuBar to Menu-Div
		RootPanel menuPanel = RootPanel.get("menu");
		menuPanel.add(menuBar);
	
		//Einige Versuche
		Label stuecklisteAnlegen = new Label("Stückliste anlegen");
		Button check = new Button("klick mich!");
		
		
		
		RootPanel contentPanel = RootPanel.get("content");
		contentPanel.add(stuecklisteAnlegen);
		contentPanel.add(check);
		
		
		
	}

}