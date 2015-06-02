package de.hdm.team7.client.event.componentAssembly;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class EditComponentAssemblyCancelledEvent extends GwtEvent<EditComponentAssemblyCancelledEventHandler>{
	
	public static Type<EditComponentAssemblyCancelledEventHandler> TYPE = new Type<EditComponentAssemblyCancelledEventHandler>();

	@Override
	public Type<EditComponentAssemblyCancelledEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditComponentAssemblyCancelledEventHandler handler) {
	    handler.onEditComponentAssemblyCancelled(this);
	}

}
