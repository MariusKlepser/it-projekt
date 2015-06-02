package de.hdm.team7.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.team7.client.gui.CustomMenuBar;
import de.hdm.team7.client.gui.HistoryHandler;

public class It_projekt implements EntryPoint {

	public void onModuleLoad() {

		HandlerManager eventBus = new HandlerManager(null);
		AppController appViewer = new AppController(
				ClientsideSettings.getBOMAdministration(), eventBus);
		// Create a three-pane layout with splitters.
		DockPanel dock = new DockPanel();
		dock.setSpacing(4);
	    dock.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
	    RootPanel rp = RootPanel.get();
	    rp.add(dock);
		appViewer.go(dock);

		// Setup HistoryHandler
		// HistoryHandler historyHandler = new HistoryHandler();
		// historyHandler.setHistoryHandler();

		// Setup Custom MenuBar
		// CustomMenuBar customMenuBar = new CustomMenuBar();
		// MenuBar menuBar = customMenuBar.getMenuBar();

		// Add MenuBar to Menu-Div
		// RootPanel menuPanel = RootPanel.get("menu");
		// menuPanel.add(menuBar);
	}
}