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

import de.hdm.team7.client.event.componentAssembly.ComponentAssemblyUpdatedEvent;
import de.hdm.team7.client.event.componentAssembly.EditComponentAssemblyCancelledEvent;
import de.hdm.team7.shared.BOMAdministrationAsync;
import de.hdm.team7.shared.businessObjects.ComponentAssembly;

public class EditComponentAssemblyPresenter implements Presenter {

	public interface Display {
		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		HasValue<String> getName();

		HasValue<String> getMaterialIdentifier();

		HasValue<String> getDescription();

		Widget asWidget();
	}

	private ComponentAssembly componentAssembly;
	private final BOMAdministrationAsync bomAdministration;
	private final HandlerManager eventBus;
	private final Display display;

	public EditComponentAssemblyPresenter(BOMAdministrationAsync bomAdministration,
			HandlerManager eventBus, Display display) {
		this.bomAdministration = bomAdministration;
		this.eventBus = eventBus;
		this.componentAssembly = new ComponentAssembly();
		this.display = display;
		bind();
	}
	
	public EditComponentAssemblyPresenter(BOMAdministrationAsync bomAdministration, HandlerManager eventBus, Display display, int id) {
	    this.bomAdministration = bomAdministration;
	    this.eventBus = eventBus;
	    this.display = display;
	    bind();
	    
	    bomAdministration.getComponentAssemblyById(id, new AsyncCallback<ComponentAssembly>() {
	      public void onSuccess(ComponentAssembly result) {
	        componentAssembly = result;
	        EditComponentAssemblyPresenter.this.display.getName().setValue(componentAssembly.getName());
	        EditComponentAssemblyPresenter.this.display.getMaterialIdentifier().setValue(componentAssembly.getMaterialIdentifier());
	        EditComponentAssemblyPresenter.this.display.getDescription().setValue(componentAssembly.getDescription());
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
				eventBus.fireEvent(new EditComponentAssemblyCancelledEvent());
			}
		});
	}

	@Override
	public void go(final DockPanel container) {
		container.clear();
		container.add(display.asWidget());
	}
	
	private void doSave() {
	    componentAssembly.setName(display.getName().getValue());
	    componentAssembly.setMaterialIdentifier(display.getMaterialIdentifier().getValue());
	    componentAssembly.setDescription(display.getDescription().getValue());
	    
	    bomAdministration.updateComponentAssembly(componentAssembly, new AsyncCallback<ComponentAssembly>() {
	        public void onSuccess(ComponentAssembly result) {
	          eventBus.fireEvent(new ComponentAssemblyUpdatedEvent(result));
	        }
	        public void onFailure(Throwable caught) {
	          Window.alert("Error updating contact");
	        }
	    });
	  }
}