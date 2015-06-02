package de.hdm.team7.client.event.component;

import com.google.gwt.event.shared.EventHandler;

public interface ComponentUpdatedEventHandler extends EventHandler{
	
	void onComponentUpdated(ComponentUpdatedEvent event);
	
}
