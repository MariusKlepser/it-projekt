package de.hdm.team7.client.event.billOfMaterial;

import com.google.gwt.event.shared.EventHandler;

public interface BillOfMaterialUpdatedEventHandler extends EventHandler{
	
	void onBillOfMaterialUpdated(BillOfMaterialUpdatedEvent event);
	
}
