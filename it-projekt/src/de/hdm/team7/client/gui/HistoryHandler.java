package de.hdm.team7.client.gui;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

public class HistoryHandler {
	public HistoryHandler() {}

	public void setHistoryHandler() {
		// Set History ValueChangeHandler
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			public void onValueChange(ValueChangeEvent<String> event) {
				String historyToken = event.getValue();
				Panel content;
				
				if (historyToken != null) {
					// Get view based on historyToken
					ViewHandler viewHandler = new ViewHandler();
					content = viewHandler.getView(historyToken);
					
					// Set view to contentPanel (div id="content")
					RootPanel contentPanel = RootPanel.get("content");
					contentPanel.clear();
					contentPanel.add(content);
				} else {
					// historyToken null
				}
			}
		});
		
		// Fire current HistoryState to get inital historyToken
		History.fireCurrentHistoryState();
	}
}
