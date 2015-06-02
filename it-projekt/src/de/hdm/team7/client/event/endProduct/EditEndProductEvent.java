package de.hdm.team7.client.event.endProduct;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class EditEndProductEvent extends GwtEvent<EditEndProductEventHandler>{
	
	public static Type<EditEndProductEventHandler> TYPE = new Type<EditEndProductEventHandler>();
	  private final int id;
	  
	  public EditEndProductEvent(int id) {
	    this.id = id;
	  }
	  
	  public int getId() { return id; }
	  
	  @Override
	  public Type<EditEndProductEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(EditEndProductEventHandler handler) {
	    handler.onEditEndProduct(this);
	  }

}
