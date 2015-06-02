package de.hdm.team7.client.event.endProduct;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class EditEndProductCancelledEvent extends GwtEvent<EditEndProductCancelledEventHandler>{
	
	public static Type<EditEndProductCancelledEventHandler> TYPE = new Type<EditEndProductCancelledEventHandler>();

	@Override
	public Type<EditEndProductCancelledEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditEndProductCancelledEventHandler handler) {
	    handler.onEditEndProductCancelled(this);
	}

}
