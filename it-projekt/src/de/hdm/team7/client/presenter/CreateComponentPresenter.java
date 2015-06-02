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

import de.hdm.team7.client.event.component.AddComponentEvent;
import de.hdm.team7.client.event.component.ComponentUpdatedEvent;
import de.hdm.team7.client.event.component.CreateComponentCancelledEvent;
import de.hdm.team7.shared.BOMAdministrationAsync;
import de.hdm.team7.shared.businessObjects.Component;

public class CreateComponentPresenter implements Presenter {

	public interface Display {
		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		HasValue<String> getName();

		HasValue<String> getMaterialIdentifier();

		HasValue<String> getDescription();

		Widget asWidget();
	}

	private Component component;
	private final BOMAdministrationAsync bomAdministration;
	private final HandlerManager eventBus;
	private final Display display;

	public CreateComponentPresenter(BOMAdministrationAsync bomAdministration,
			HandlerManager eventBus, Display display) {
		this.bomAdministration = bomAdministration;
		this.eventBus = eventBus;
		this.component = new Component();
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
				eventBus.fireEvent(new CreateComponentCancelledEvent());
			}
		});
	}

	@Override
	public void go(final DockPanel container) {
//		container.clear();
		container.add(display.asWidget(), DockPanel.CENTER);
	}
	
	private void doSave() {
	    component.setName(display.getName().getValue());
	    component.setMaterialIdentifier(display.getMaterialIdentifier().getValue());
	    component.setDescription(display.getDescription().getValue());
	    
	    bomAdministration.createComponent(component, new AsyncCallback<Component>() {
	        public void onSuccess(Component result) {
	          eventBus.fireEvent(new AddComponentEvent());
	        }
	        public void onFailure(Throwable caught) {
	          Window.alert("Error updating contact");
	        }
	    });
	  }
}
