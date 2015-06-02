package de.hdm.team7.client.event.endProduct;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class EndProductDeletedEvent extends GwtEvent<EndProductDeletedEventHandler>{
	  public static Type<EndProductDeletedEventHandler> TYPE = new Type<EndProductDeletedEventHandler>();
	  
	  @Override
	  public Type<EndProductDeletedEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(EndProductDeletedEventHandler handler) {
	    handler.onEndProductDeleted(this);
	  }
	}