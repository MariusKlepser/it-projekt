package de.hdm.team7.client.ui.views;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.team7.client.presenter.CreateComponentPresenter;

public class CreateComponentView extends Composite implements
		CreateComponentPresenter.Display {
	private final Label lblName;
	private final TextBox txtName;
	private final Label lblMaterialIdentifier;
	private final TextBox txtMaterialIdentifier;
	private final Label lblDescription;
	private final TextArea txtDescription;
	private final FlexTable detailsTable;
	private final Button saveButton;
	private final Button cancelButton;

	public CreateComponentView() {
		DecoratorPanel createComponentDecorator = new DecoratorPanel();
		createComponentDecorator.setWidth("18em");
		initWidget(createComponentDecorator);

		VerticalPanel createComponentPanel = new VerticalPanel();
		createComponentPanel.setWidth("100%");

		// Create the component list
		//
		detailsTable = new FlexTable();
		detailsTable.setCellSpacing(0);
		detailsTable.setWidth("100%");
		detailsTable.addStyleName("component-ListContainer");
		detailsTable.getColumnFormatter()
				.addStyleName(1, "add-component-input");
		txtName = new TextBox();
		txtMaterialIdentifier = new TextBox();
		txtDescription = new TextArea();
		lblName = new Label("Bauteilname:");
		lblMaterialIdentifier = new Label("Materialbezeichnung:");
		lblDescription = new Label("Bauteilbeschreibung:");
		initDetailsTable();
		createComponentPanel.add(detailsTable);

		HorizontalPanel menuPanel = new HorizontalPanel();
		saveButton = new Button("Speichern");
		cancelButton = new Button("Abbrechen");
		menuPanel.add(saveButton);
		menuPanel.add(cancelButton);
		createComponentPanel.add(menuPanel);
		createComponentDecorator.add(createComponentPanel);
	}

	private void initDetailsTable() {
		detailsTable.setWidget(0, 0, lblName);
		detailsTable.setWidget(0, 1, txtName);
		detailsTable.setWidget(1, 0, lblMaterialIdentifier);
		detailsTable.setWidget(1, 1, txtMaterialIdentifier);
		detailsTable.setWidget(2, 0, lblDescription);
		detailsTable.setWidget(2, 1, txtDescription);
		txtName.setFocus(true);
	}

	public HasValue<String> getName() {
		return txtName;
	}

	public HasValue<String> getMaterialIdentifier() {
		return txtMaterialIdentifier;
	}

	public HasValue<String> getDescription() {
		return txtDescription;
	}

	public HasClickHandlers getSaveButton() {
		return saveButton;
	}

	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}

	public Widget asWidget() {
		return this;
	}

}
