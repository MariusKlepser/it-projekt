package de.hdm.team7.client.event.billOfMaterial;

import com.google.gwt.event.shared.EventHandler;

public interface BillOfMaterialDeletedEventHandler extends EventHandler {
	
	void onBillOfMaterialDeleted(BillOfMaterialDeletedEvent event);

}
