package de.hdm.team7.client.event.endProduct;

import com.google.gwt.event.shared.EventHandler;

public interface EndProductUpdatedEventHandler extends EventHandler{
	
	void onEndProductUpdated(EndProductUpdatedEvent event);
	
}
