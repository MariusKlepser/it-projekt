package de.hdm.team7.client.event.componentAssembly;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class AddComponentAssemblyEvent extends GwtEvent<AddComponentAssemblyEventHandler> {

	public static Type<AddComponentAssemblyEventHandler> TYPE = new Type<AddComponentAssemblyEventHandler>();
	
	@Override
	public Type<AddComponentAssemblyEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddComponentAssemblyEventHandler handler) {
	    handler.onAddComponentAssembly(this);
	}

}
