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

import de.hdm.team7.client.event.componentAssembly.AddComponentAssemblyEvent;
import de.hdm.team7.client.event.componentAssembly.ComponentAssemblyUpdatedEvent;
import de.hdm.team7.client.event.componentAssembly.CreateComponentAssemblyCancelledEvent;
import de.hdm.team7.shared.BOMAdministrationAsync;
import de.hdm.team7.shared.businessObjects.ComponentAssembly;

public class CreateComponentAssemblyPresenter implements Presenter {

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

	public CreateComponentAssemblyPresenter(BOMAdministrationAsync bomAdministration,
			HandlerManager eventBus, Display display) {
		this.bomAdministration = bomAdministration;
		this.eventBus = eventBus;
		this.componentAssembly = new ComponentAssembly();
		this.display = display;
		bind();
	}

	public void bind() {
		this.display.getSaveButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doSave();
			}
		});

		this.display.getCancelButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new CreateComponentAssemblyCancelledEvent());
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
	    
	    bomAdministration.createComponentAssembly(componentAssembly, new AsyncCallback<ComponentAssembly>() {
	        public void onSuccess(ComponentAssembly result) {
	          eventBus.fireEvent(new AddComponentAssemblyEvent());
	        }
	        public void onFailure(Throwable caught) {
	          Window.alert("Error updating contact");
	        }
	    });
	  }
}
