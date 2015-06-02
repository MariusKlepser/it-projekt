package de.hdm.team7.client.event.endProduct;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class CreateEndProductCancelledEvent extends GwtEvent<CreateEndProductCancelledEventHandler>{
	
	public static Type<CreateEndProductCancelledEventHandler> TYPE = new Type<CreateEndProductCancelledEventHandler>();

	@Override
	public Type<CreateEndProductCancelledEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CreateEndProductCancelledEventHandler handler) {
		handler.onCreateEndProductCancelled(this);
	}

}
