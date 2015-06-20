package de.hdm.team7.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.team7.client.ClientsideSettings;
import de.hdm.team7.shared.BOMAdministrationAsync;
import de.hdm.team7.shared.businessObjects.*;

/**
 * Formular f�r die Darstellung des selektierten Kunden Angelehnt an Thies &
 * Rathke
 */

public class EndProductForm extends VerticalPanel {
	
	BOMAdministrationAsync bomAdministration = ClientsideSettings
			.getBOMAdministration();

	EndProduct EndProductToDisplay = null;
	BusinessObjectTreeViewModel botvm = null;

	/*
	 * Widgets, deren Inhalte variable sind, werden als Attribute angelegt.
	 */
	Label idValueLabel = new Label();
	TextBox nameTextBox = new TextBox();

	/*
	 * Im Konstruktor werden die Widgets z.T. erzeugt. Alle werden in einem
	 * Raster angeordnet, dessen Gr��e sich aus dem Platzbedarf der enthaltenen
	 * Widgets bestimmt.
	 */
	public EndProductForm() {
		/**
		 * Das Grid-Widget erlaubt die Anordnung anderer Widgets in einem
		 * Gitter.
		 */
		Grid boGrid = new Grid(3, 2);
		this.add(boGrid);

		Label idLabel = new Label("ID");
		boGrid.setWidget(0, 0, idLabel);
		boGrid.setWidget(0, 1, idValueLabel);

		Label nameLabel = new Label("Name");
		boGrid.setWidget(1, 0, nameLabel);
		boGrid.setWidget(1, 1, nameTextBox);

		HorizontalPanel boButtonsPanel = new HorizontalPanel();
		this.add(boButtonsPanel);

		Button newButton = new Button("Neu");
		newButton.addClickHandler(new NewClickHandler());
		boButtonsPanel.add(newButton);

		Button deleteButton = new Button("L�schen");
		deleteButton.addClickHandler(new DeleteClickHandler());
		boButtonsPanel.add(deleteButton);

		Button editButton = new Button("Bearbeiten");
		editButton.addClickHandler(new EditClickHandler());
		boButtonsPanel.add(editButton);
	}

	/*
	 * Click handlers und abh�ngige AsyncCallback Klassen.
	 */

	/**
	 * Zum L�schen eines Kontos wird zun�chst der Eigent�mer abgefragt, bevor im
	 * Callback eine L�schung durchgef�hrt wird.
	 * 
	 */

	/*
	 * TODO: Edit-Methode + ClickHandler m�ssen eingef�gt werden
	 * TODO: Methoden, auf die im BusinessObjectTreeViewModel zugegriffen wird, m�ssen erg�nzt werden
	 */

	private class DeleteClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			if (EndProductToDisplay != null) {
				bomAdministration.deleteEndProduct(EndProductToDisplay,
						new deleteEndProductCallback(EndProductToDisplay));
			} else {

			}
		}
	}

	/**
	 * Ein neues Objekt wird erzeugt.
	 * 
	 */
	private class NewClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			EndProduct selectedEndProduct = botvm.getSelectedEndProduct();
			if (selectedEndProduct == null) {
				Window.alert("kein Endprodukt ausgew�hlt");
			} else {
				bomAdministration.createEndProduct(selectedEndProduct, null,
						new createEndProductCallback(selectedEndProduct));
			}
		}
	}

	/*
	 * Auch hier muss nach erfolgreicher Kontoerzeugung der Kunden- und
	 * Kontobaum aktualisiert werden. Daf�r dient ein privates Attribut und der
	 * Konstruktor.
	 * 
	 * Wir ben�tigen hier nur einen Parameter f�r den Kunden, da das Konto als
	 * ergebnis des asynchronen Aufrufs geliefert wird.
	 */
	public class createEndProductCallback implements AsyncCallback<String> {

		EndProduct endProduct = null;

		createEndProductCallback(EndProduct endProduct) {
			this.endProduct = endProduct;
		}

		@Override
		public void onFailure(Throwable caught) {
		}

		public void onSuccess(EndProduct endProduct) {
			if (endProduct != null) {
				botvm.addEndProduct(endProduct);
			}
		}

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub

		}
	}

	public class deleteEndProductCallback implements AsyncCallback<String> {

		EndProduct endProduct = null;

		deleteEndProductCallback(EndProduct endProduct) {
			this.endProduct = endProduct;
		}

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das L�schen des Endprodukts ist fehlgeschlagen!");
		}

		public void onSuccess(Void result) {
			if (endProduct != null) {
				setSelected(null);
				botvm.removeEndProduct(endProduct);
			}
		}

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub

		}
	}

void setSelected(EndProduct endProduct) {
	if (endProduct != null) {
		EndProductToDisplay = endProduct;
		nameTextBox.setText(EndProductToDisplay.getName());
		idValueLabel.setText(Integer.toString(EndProductToDisplay.getId()));
	} else {
		nameTextBox.setText("");
		idValueLabel.setText("");
	}
}

}