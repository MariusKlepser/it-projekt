package de.hdm.team7.client.gui;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.MenuBar;

public class CustomMenuBar {
	// Initialize MenuBar variable
	private final MenuBar menuBar;
	
	// Create MenuBar and add options
	public CustomMenuBar() {
		this.menuBar = new MenuBar();
		this.addOptions();
	}

	public void addOptions(){
		// Set MenuBar options
		menuBar.setAutoOpen(true);
		menuBar.setWidth("100%");
		menuBar.setAnimationEnabled(true);

		// Create MenuBar items and commands
		menuBar.addItem("Bauteil", new Command() { 
			public void execute() { 
				History.newItem("Bauteil");
			}
		});
		menuBar.addItem("Baugruppe", new Command() { 
			public void execute() { 
				// Set History Item			
				History.newItem("Baugruppe");
				
			}
		});
		menuBar.addItem("Enderzeugnis", new Command() { 
			public void execute() { 
				// Set History Item
				History.newItem("Enderzeugnis");
			}
		});
		menuBar.addItem("Stückliste", new Command() { 
			public void execute() { 
				// Set History Item
				History.newItem("Stueckliste");
			}
		});
		menuBar.addSeparator();
		menuBar.addItem("Benutzer", new Command() { 
			public void execute() { 
				// Set History Item
				History.newItem("Benutzer");
			}
		});
		menuBar.addSeparator();
		menuBar.addItem("Report Generator", new Command() { 
			public void execute() { 
				// Set History Item
				History.newItem("ReportGenerator");
			}
		});	
	}

	// MenuBar getter
	public MenuBar getMenuBar() {
		return menuBar;
	}
}
