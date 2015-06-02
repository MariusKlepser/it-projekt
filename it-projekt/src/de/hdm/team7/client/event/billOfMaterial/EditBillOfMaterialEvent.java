package de.hdm.team7.client.event.billOfMaterial;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class EditBillOfMaterialEvent extends GwtEvent<EditBillOfMaterialEventHandler>{
	
	public static Type<EditBillOfMaterialEventHandler> TYPE = new Type<EditBillOfMaterialEventHandler>();
	  private final String id;
	  
	  public EditBillOfMaterialEvent(String id) {
	    this.id = id;
	  }
	  
	  public String getId() { return id; }
	  
	  @Override
	  public Type<EditBillOfMaterialEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(EditBillOfMaterialEventHandler handler) {
	    handler.onEditBillOfMaterial(this);
	  }

}
