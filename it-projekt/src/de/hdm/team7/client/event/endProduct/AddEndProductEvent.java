package de.hdm.team7.client.event.endProduct;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class AddEndProductEvent extends GwtEvent<AddEndProductEventHandler> {

	public static Type<AddEndProductEventHandler> TYPE = new Type<AddEndProductEventHandler>();
	
	@Override
	public Type<AddEndProductEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddEndProductEventHandler handler) {
	    handler.onAddEndProduct(this);
	}

}
