package de.hdm.team7.client.gui;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.team7.client.gui.views.BaugruppeOverview;
import de.hdm.team7.client.gui.views.BauteilOverview;
import de.hdm.team7.client.gui.views.BenutzerOverview;
import de.hdm.team7.client.gui.views.EnderzeugnisOverview;
import de.hdm.team7.client.gui.views.ReportGeneratorOverview;
import de.hdm.team7.client.gui.views.StuecklisteOverview;

public class CustomMenuBar {
	// Initialize MenuBar Variable
	private final MenuBar menuBar;
	
	// Create MenuBar and add Options
	public CustomMenuBar() {
		this.menuBar = new MenuBar();
		this.addOptions();
	}

	public void addOptions(){
		// Set MenuBar Options
		menuBar.setAutoOpen(true);
		menuBar.setWidth("100%");
		menuBar.setAnimationEnabled(true);

		// Create MenuBar Items and Commands
		menuBar.addItem("Bauteil", new Command() { 
			public void execute() { 
				// Get ReportGeneratorOverview View
				Widget content = new BauteilOverview().getView();
				
				// Init View Handler with Target
				ViewHandler viewHandler = new ViewHandler("content");
				// Set Root Panel Content
				viewHandler.setRootPanelContent(content);
			}
		});
		menuBar.addItem("Baugruppe", new Command() { 
			public void execute() { 
				// Get ReportGeneratorOverview View
				Widget content = new BaugruppeOverview().getView();
				
				// Init View Handler with Target
				ViewHandler viewHandler = new ViewHandler("content");
				// Set Root Panel Content
				viewHandler.setRootPanelContent(content);
			}
		});
		menuBar.addItem("Enderzeugnis", new Command() { 
			public void execute() { 
				// Get ReportGeneratorOverview View
				Widget content = new EnderzeugnisOverview().getView();
				
				// Init View Handler with Target
				ViewHandler viewHandler = new ViewHandler("content");
				// Set Root Panel Content
				viewHandler.setRootPanelContent(content);
			}
		});
		menuBar.addItem("Stückliste", new Command() { 
			public void execute() { 
				// Get ReportGeneratorOverview View
				Widget content = new StuecklisteOverview().getView();
				
				// Init View Handler with Target
				ViewHandler viewHandler = new ViewHandler("content");
				// Set Root Panel Content
				viewHandler.setRootPanelContent(content);
			}
		});
		menuBar.addSeparator();
		menuBar.addItem("Benutzer", new Command() { 
			public void execute() { 
				// Get ReportGeneratorOverview View
				Widget content = new BenutzerOverview().getView();
				
				// Init View Handler with Target
				ViewHandler viewHandler = new ViewHandler("content");
				// Set Root Panel Content
				viewHandler.setRootPanelContent(content);
			}
		});
		menuBar.addSeparator();
		menuBar.addItem("Report Generator", new Command() { 
			public void execute() { 
				// Get ReportGeneratorOverview View
				Widget content = new ReportGeneratorOverview().getView();
				
				// Init View Handler with Target
				ViewHandler viewHandler = new ViewHandler("content");
				// Set Root Panel Content
				viewHandler.setRootPanelContent(content);
			}
		});	
	}

	// MenuBar Getter
	public MenuBar getMenuBar() {
		return menuBar;
	}
}
