package de.hdm.team7.client.event.component;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

import de.hdm.team7.shared.businessObjects.Component;

public class ComponentUpdatedEvent extends
		GwtEvent<ComponentUpdatedEventHandler> {

	public static Type<ComponentUpdatedEventHandler> TYPE = new Type<ComponentUpdatedEventHandler>();
	private final Component updatedComponent;

	public ComponentUpdatedEvent(Component updatedComponent) {
		this.updatedComponent = updatedComponent;
	}

	public Component getUpdatedComponent() {
		return updatedComponent;
	}

	@Override
	public Type<ComponentUpdatedEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ComponentUpdatedEventHandler handler) {
		handler.onComponentUpdated(this);
	}

}
