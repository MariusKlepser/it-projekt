package de.hdm.team7.client.event.endProduct;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

import de.hdm.team7.shared.businessObjects.Component;
import de.hdm.team7.shared.businessObjects.EndProduct;

public class EndProductUpdatedEvent extends
		GwtEvent<EndProductUpdatedEventHandler> {

	public static Type<EndProductUpdatedEventHandler> TYPE = new Type<EndProductUpdatedEventHandler>();
	private final EndProduct updatedEndProduct;

	public EndProductUpdatedEvent(EndProduct updatedEndProduct) {
		this.updatedEndProduct = updatedEndProduct;
	}

	public EndProduct getUpdatedEndProduct() {
		return updatedEndProduct;
	}

	@Override
	public Type<EndProductUpdatedEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EndProductUpdatedEventHandler handler) {
		handler.onEndProductUpdated(this);
	}

}
