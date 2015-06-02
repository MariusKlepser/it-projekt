package de.hdm.team7.client.event.billOfMaterial;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class EditBillOfMaterialCancelledEvent extends GwtEvent<EditBillOfMaterialCancelledEventHandler>{
	
	public static Type<EditBillOfMaterialCancelledEventHandler> TYPE = new Type<EditBillOfMaterialCancelledEventHandler>();

	@Override
	public Type<EditBillOfMaterialCancelledEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditBillOfMaterialCancelledEventHandler handler) {
	    handler.onEditBillOfMaterialCancelled(this);
	}

}
