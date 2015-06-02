package de.hdm.team7.client.event.component;

import com.google.gwt.event.shared.EventHandler;

public interface ComponentDeletedEventHandler extends EventHandler {
	
	void onComponentDeleted(ComponentDeletedEvent event);

}
