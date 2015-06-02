package de.hdm.team7.client.event.component;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class EditComponentEvent extends GwtEvent<EditComponentEventHandler>{
	
	public static Type<EditComponentEventHandler> TYPE = new Type<EditComponentEventHandler>();
	  private final int id;
	  
	  public EditComponentEvent(int id) {
	    this.id = id;
	  }
	  
	  public int getId() { return id; }
	  
	  @Override
	  public Type<EditComponentEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(EditComponentEventHandler handler) {
	    handler.onEditComponent(this);
	  }

}
