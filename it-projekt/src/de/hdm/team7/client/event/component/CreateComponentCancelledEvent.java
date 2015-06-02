package de.hdm.team7.client.event.component;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class CreateComponentCancelledEvent extends GwtEvent<CreateComponentCancelledEventHandler>{
	
	public static Type<CreateComponentCancelledEventHandler> TYPE = new Type<CreateComponentCancelledEventHandler>();

	@Override
	public Type<CreateComponentCancelledEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CreateComponentCancelledEventHandler handler) {
		handler.onCreateComponentCancelled(this);
	}

}
