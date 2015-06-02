package de.hdm.team7.client.event.billOfMaterial;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class CreateBillOfMaterialCancelledEvent extends GwtEvent<CreateBillOfMaterialCancelledEventHandler>{
	
	public static Type<CreateBillOfMaterialCancelledEventHandler> TYPE = new Type<CreateBillOfMaterialCancelledEventHandler>();

	@Override
	public Type<CreateBillOfMaterialCancelledEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CreateBillOfMaterialCancelledEventHandler handler) {
		handler.onCreateBillOfMaterialCancelled(this);
	}

}
