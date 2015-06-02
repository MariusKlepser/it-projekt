package de.hdm.team7.client.event.componentAssembly;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class EditComponentAssemblyEvent extends GwtEvent<EditComponentAssemblyEventHandler>{
	
	public static Type<EditComponentAssemblyEventHandler> TYPE = new Type<EditComponentAssemblyEventHandler>();
	  private final int id;
	  
	  public EditComponentAssemblyEvent(int id) {
	    this.id = id;
	  }
	  
	  public int getId() { return id; }
	  
	  @Override
	  public Type<EditComponentAssemblyEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(EditComponentAssemblyEventHandler handler) {
	    handler.onEditComponentAssembly(this);
	  }

}
