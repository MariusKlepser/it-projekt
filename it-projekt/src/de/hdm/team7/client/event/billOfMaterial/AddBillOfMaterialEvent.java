package de.hdm.team7.client.event.billOfMaterial;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class AddBillOfMaterialEvent extends GwtEvent<AddBillOfMaterialEventHandler> {

	public static Type<AddBillOfMaterialEventHandler> TYPE = new Type<AddBillOfMaterialEventHandler>();
	
	@Override
	public Type<AddBillOfMaterialEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddBillOfMaterialEventHandler handler) {
	    handler.onAddBillOfMaterial(this);
	}

}
