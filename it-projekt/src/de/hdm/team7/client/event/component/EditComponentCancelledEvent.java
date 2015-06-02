package de.hdm.team7.client.event.component;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class EditComponentCancelledEvent extends GwtEvent<EditComponentCancelledEventHandler>{
	
	public static Type<EditComponentCancelledEventHandler> TYPE = new Type<EditComponentCancelledEventHandler>();

	@Override
	public Type<EditComponentCancelledEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditComponentCancelledEventHandler handler) {
	    handler.onEditComponentCancelled(this);
	}

}
