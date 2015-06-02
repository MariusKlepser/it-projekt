package de.hdm.team7.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.team7.client.event.billOfMaterial.BillOfMaterialUpdatedEvent;
import de.hdm.team7.client.event.billOfMaterial.EditBillOfMaterialCancelledEvent;
import de.hdm.team7.shared.BOMAdministrationAsync;
import de.hdm.team7.shared.businessObjects.BillOfMaterial;

public class EditBillOfMaterialPresenter implements Presenter {

	public interface Display {
		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		HasValue<String> getName();
		
		//TODO: Add further Table functions

		Widget asWidget();
	}

	private BillOfMaterial billOfMaterial;
	private final BOMAdministrationAsync bomAdministration;
	private final HandlerManager eventBus;
	private final Display display;

	public EditBillOfMaterialPresenter(BOMAdministrationAsync bomAdministration,
			HandlerManager eventBus, Display display) {
		this.bomAdministration = bomAdministration;
		this.eventBus = eventBus;
		this.billOfMaterial = new BillOfMaterial();
		this.display = display;
		bind();
	}
	
	public EditBillOfMaterialPresenter(BOMAdministrationAsync bomAdministration, HandlerManager eventBus, Display display, int id) {
	    this.bomAdministration = bomAdministration;
	    this.eventBus = eventBus;
	    this.display = display;
	    bind();
	    
	    bomAdministration.getBillOfMaterialById(id, new AsyncCallback<BillOfMaterial>() {
	      public void onSuccess(BillOfMaterial result) {
	        billOfMaterial = result;
	        EditBillOfMaterialPresenter.this.display.getName().setValue(billOfMaterial.getName());
	        //TODO: Add further Table functions
	      }
	      
	      public void onFailure(Throwable caught) {
	        Window.alert("Error retrieving component");
	      }
	    });
	    
	  }

	public void bind() {
		this.display.getSaveButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doSave();
			}
		});

		this.display.getCancelButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EditBillOfMaterialCancelledEvent());
			}
		});
	}

	@Override
	public void go(final DockPanel container) {
		container.clear();
		container.add(display.asWidget());
	}
	
	private void doSave() {
	    billOfMaterial.setName(display.getName().getValue());
	    //TODO: Add further Table functions
	    
	    bomAdministration.updateBillOfMaterial(billOfMaterial, new AsyncCallback<BillOfMaterial>() {
	        public void onSuccess(BillOfMaterial result) {
	          eventBus.fireEvent(new BillOfMaterialUpdatedEvent(result));
	        }
	        public void onFailure(Throwable caught) {
	          Window.alert("Error updating contact");
	        }
	    });
	  }
}