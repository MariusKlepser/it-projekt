package de.hdm.team7.client.event.component;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class ComponentDeletedEvent extends GwtEvent<ComponentDeletedEventHandler>{
	  public static Type<ComponentDeletedEventHandler> TYPE = new Type<ComponentDeletedEventHandler>();
	  
	  @Override
	  public Type<ComponentDeletedEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(ComponentDeletedEventHandler handler) {
	    handler.onComponentDeleted(this);
	  }
	}