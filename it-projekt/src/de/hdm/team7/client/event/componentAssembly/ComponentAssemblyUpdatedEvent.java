package de.hdm.team7.client.event.componentAssembly;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

import de.hdm.team7.shared.businessObjects.ComponentAssembly;

public class ComponentAssemblyUpdatedEvent extends
		GwtEvent<ComponentAssemblyUpdatedEventHandler> {

	public static Type<ComponentAssemblyUpdatedEventHandler> TYPE = new Type<ComponentAssemblyUpdatedEventHandler>();
	private final ComponentAssembly updatedComponentAssembly;

	public ComponentAssemblyUpdatedEvent(ComponentAssembly updatedComponentAssembly) {
		this.updatedComponentAssembly = updatedComponentAssembly;
	}

	public ComponentAssembly getUpdatedComponentAssembly() {
		return updatedComponentAssembly;
	}

	@Override
	public Type<ComponentAssemblyUpdatedEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ComponentAssemblyUpdatedEventHandler handler) {
		handler.onComponentAssemblyUpdated(this);
	}

}
