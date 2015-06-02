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

import de.hdm.team7.client.event.endProduct.EndProductUpdatedEvent;
import de.hdm.team7.client.event.endProduct.EditEndProductCancelledEvent;
import de.hdm.team7.shared.BOMAdministrationAsync;
import de.hdm.team7.shared.businessObjects.EndProduct;

public class EditEndProductPresenter implements Presenter {

	public interface Display {
		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		HasValue<String> getName();

		HasValue<String> getMaterialIdentifier();

		HasValue<String> getDescription();

		Widget asWidget();
	}

	private EndProduct endProduct;
	private final BOMAdministrationAsync bomAdministration;
	private final HandlerManager eventBus;
	private final Display display;

	public EditEndProductPresenter(BOMAdministrationAsync bomAdministration,
			HandlerManager eventBus, Display display) {
		this.bomAdministration = bomAdministration;
		this.eventBus = eventBus;
		this.endProduct = new EndProduct();
		this.display = display;
		bind();
	}
	
	public EditEndProductPresenter(BOMAdministrationAsync bomAdministration, HandlerManager eventBus, Display display, int id) {
	    this.bomAdministration = bomAdministration;
	    this.eventBus = eventBus;
	    this.display = display;
	    bind();
	    
	    bomAdministration.getEndProductById(id, new AsyncCallback<EndProduct>() {
	      public void onSuccess(EndProduct result) {
	        endProduct = result;
	        EditEndProductPresenter.this.display.getName().setValue(endProduct.getName());
	        EditEndProductPresenter.this.display.getMaterialIdentifier().setValue(endProduct.getMaterialIdentifier());
	        EditEndProductPresenter.this.display.getDescription().setValue(endProduct.getDescription());
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
				eventBus.fireEvent(new EditEndProductCancelledEvent());
			}
		});
	}

	@Override
	public void go(final DockPanel container) {
		container.clear();
		container.add(display.asWidget());
	}
	
	private void doSave() {
	    endProduct.setName(display.getName().getValue());
	    endProduct.setMaterialIdentifier(display.getMaterialIdentifier().getValue());
	    endProduct.setDescription(display.getDescription().getValue());
	    
	    bomAdministration.updateEndProduct(endProduct, new AsyncCallback<EndProduct>() {
	        public void onSuccess(EndProduct result) {
	          eventBus.fireEvent(new EndProductUpdatedEvent(result));
	        }
	        public void onFailure(Throwable caught) {
	          Window.alert("Error updating contact");
	        }
	    });
	  }
}