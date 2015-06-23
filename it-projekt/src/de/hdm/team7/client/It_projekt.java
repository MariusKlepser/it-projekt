package de.hdm.team7.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TreeItem;

import de.hdm.team7.client.gui.CustomStaticTree;
import de.hdm.team7.client.rpc.AsyncCallbackComponentList;

public class It_projekt implements EntryPoint {
	
	public void onModuleLoad() {		
//		// Setup HistoryHandler
//		HistoryHandler historyHandler = new HistoryHandler();
//		historyHandler.setHistoryHandler();
		
		// Setup Custom MenuBar
//		CustomMenuBar customMenuBar = new CustomMenuBar();
//		MenuBar menuBar = customMenuBar.getMenuBar();
//		
//		TableCellTesting tct = new TableCellTesting();
		
		// Add MenuBar to Menu-Div
//		RootPanel menuPanel = RootPanel.get("menu");
//		menuPanel.add(menuBar);
//		menuPanel.add(tct.asWidget());
		
		RootPanel navPanel = RootPanel.get("Navigator");
		RootPanel detailsPanel = RootPanel.get("Details");
		
//		final BusinessObjectTreeViewModel botvm = new BusinessObjectTreeViewModel();
//		navPanel.add(botvm);
//		staticTree.ensureDebugId("cwTree-staticTree");
//		ScrollPanel staticTreeWrapper = new ScrollPanel(staticTree);
//		staticTreeWrapper.ensureDebugId("cwTree-staticTree-Wrapper");

		SelectionHandler<TreeItem> handler = new SelectionHandler<TreeItem>() {

			@Override
			public void onSelection(SelectionEvent event) {
				AsyncCallbackComponentList componentListCallback = new AsyncCallbackComponentList();
				TreeItem item = (TreeItem) event.getSelectedItem();
				ClientEinstellungen.getStuecklistenVerwaltung().holeAlleBauteile(componentListCallback);
////				ClientsideSettings.getBOMAdministration().getComponentByName(
////						item.getText(), componentListCallback);
			}

		};

//		staticTree.addSelectionHandler(handler);
//		staticTreeWrapper.setWidth("100%");
//		navPanel.add(staticTreeWrapper);
	}

}
