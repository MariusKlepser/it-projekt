package de.hdm.team7.client.event.billOfMaterial;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

import de.hdm.team7.shared.businessObjects.BillOfMaterial;
import de.hdm.team7.shared.businessObjects.Component;

public class BillOfMaterialUpdatedEvent extends
		GwtEvent<BillOfMaterialUpdatedEventHandler> {

	public static Type<BillOfMaterialUpdatedEventHandler> TYPE = new Type<BillOfMaterialUpdatedEventHandler>();
	private final BillOfMaterial updatedBillOfMaterial;

	public BillOfMaterialUpdatedEvent(BillOfMaterial updatedBillOfMaterial) {
		this.updatedBillOfMaterial = updatedBillOfMaterial;
	}

	public BillOfMaterial getUpdatedBillOfMaterial() {
		return updatedBillOfMaterial;
	}

	@Override
	public Type<BillOfMaterialUpdatedEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(BillOfMaterialUpdatedEventHandler handler) {
		handler.onBillOfMaterialUpdated(this);
	}

}
