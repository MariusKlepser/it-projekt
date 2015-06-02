package de.hdm.team7.client.event.componentAssembly;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class ComponentAssemblyDeletedEvent extends GwtEvent<ComponentAssemblyDeletedEventHandler>{
	  public static Type<ComponentAssemblyDeletedEventHandler> TYPE = new Type<ComponentAssemblyDeletedEventHandler>();
	  
	  @Override
	  public Type<ComponentAssemblyDeletedEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(ComponentAssemblyDeletedEventHandler handler) {
	    handler.onComponentAssemblyDeleted(this);
	  }
	}