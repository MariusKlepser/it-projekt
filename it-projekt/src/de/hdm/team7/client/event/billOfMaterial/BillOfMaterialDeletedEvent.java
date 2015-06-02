package de.hdm.team7.client.event.billOfMaterial;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class BillOfMaterialDeletedEvent extends GwtEvent<BillOfMaterialDeletedEventHandler>{
	  public static Type<BillOfMaterialDeletedEventHandler> TYPE = new Type<BillOfMaterialDeletedEventHandler>();
	  
	  @Override
	  public Type<BillOfMaterialDeletedEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(BillOfMaterialDeletedEventHandler handler) {
	    handler.onBillOfMaterialDeleted(this);
	  }
	}