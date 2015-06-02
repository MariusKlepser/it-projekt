package de.hdm.team7.client.event.component;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class AddComponentEvent extends GwtEvent<AddComponentEventHandler> {

	public static Type<AddComponentEventHandler> TYPE = new Type<AddComponentEventHandler>();
	
	@Override
	public Type<AddComponentEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddComponentEventHandler handler) {
	    handler.onAddComponent(this);
	}

}
