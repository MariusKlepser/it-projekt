package de.hdm.team7.client.event.componentAssembly;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class CreateComponentAssemblyCancelledEvent extends GwtEvent<CreateComponentAssemblyCancelledEventHandler>{
	
	public static Type<CreateComponentAssemblyCancelledEventHandler> TYPE = new Type<CreateComponentAssemblyCancelledEventHandler>();

	@Override
	public Type<CreateComponentAssemblyCancelledEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CreateComponentAssemblyCancelledEventHandler handler) {
		handler.onCreateComponentAssemblyCancelled(this);
	}

}
