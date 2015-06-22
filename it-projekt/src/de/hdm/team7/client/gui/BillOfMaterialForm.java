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

import de.hdm.team7.client.ClientEinstellungen;
import de.hdm.team7.shared.StücklistenVerwaltung;
import de.hdm.team7.shared.StücklistenVerwaltungAsync;
import de.hdm.team7.shared.geschäftsobjekte.*;

/**
 * Formular für die Darstellung des selektierten Kunden Angelehnt an Thies &
 * Rathke
 */

public class BillOfMaterialForm extends VerticalPanel {
	
	StücklistenVerwaltungAsync bomAdministration = ClientEinstellungen.getStücklistenVerwaltung();

	Stückliste bomToDisplay = null;
	BusinessObjectTreeViewModel botvm = null;

	/*
	 * Widgets, deren Inhalte variable sind, werden als Attribute angelegt.
	 */
	Label idValueLabel = new Label();
	TextBox nameTextBox = new TextBox();

	/*
	 * Im Konstruktor werden die Widgets z.T. erzeugt. Alle werden in einem
	 * Raster angeordnet, dessen Größe sich aus dem Platzbedarf der enthaltenen
	 * Widgets bestimmt.
	 */
	public BillOfMaterialForm() {
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

		Button deleteButton = new Button("Löschen");
		deleteButton.addClickHandler(new DeleteClickHandler());
		boButtonsPanel.add(deleteButton);

		Button editButton = new Button("Bearbeiten");
		editButton.addClickHandler(new EditClickHandler());
		boButtonsPanel.add(editButton);
	}

	/*
	 * Click handlers und abhängige AsyncCallback Klassen.
	 */

	/**
	 * Zum Löschen eines Kontos wird zunächst der Eigentümer abgefragt, bevor im
	 * Callback eine Löschung durchgeführt wird.
	 * 
	 */

	/*
	 * TODO: Edit-Methode + ClickHandler müssen eingefügt werden
	 * TODO: Methoden, auf die im BusinessObjectTreeViewModel zugegriffen wird, müssen ergänzt werden
	 * TODO: rootElement bei Erstellung einer neuen BOM muss mitgegeben werden
	 */

	private class DeleteClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			if (bomToDisplay != null) {
				bomAdministration.löscheStückliste(bomToDisplay,
						new DeleteBOMCallback(bomToDisplay));
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
			Stückliste selectedBOM = botvm.getSelektierteStückliste();
			if (selectedBOM == null) {
				Window.alert("keine Stückliste ausgewählt");
			} else {
				bomAdministration.erstelleStückliste(selectedBOM, null,
						new CreateBOMCallback(selectedBOM));
			}
		}
	}

	/*
	 * Auch hier muss nach erfolgreicher Kontoerzeugung der Kunden- und
	 * Kontobaum aktualisiert werden. Dafür dient ein privates Attribut und der
	 * Konstruktor.
	 * 
	 * Wir benötigen hier nur einen Parameter für den Kunden, da das Konto als
	 * ergebnis des asynchronen Aufrufs geliefert wird.
	 */
	public class CreateBOMCallback implements AsyncCallback<String> {

		Stückliste bom = null;

		CreateBOMCallback(Stückliste bom) {
			this.bom = bom;
		}

		@Override
		public void onFailure(Throwable caught) {
		}

		public void onSuccess(Stückliste bom) {
			if (bom != null) {
				botvm.addBillOfMaterial(bom);
			}
		}

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub

		}
	}

	public class DeleteBOMCallback implements AsyncCallback<String> {

		Stückliste bom = null;

		DeleteBOMCallback(Stückliste bom) {
			this.bom = bom;
		}

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Löschen der Stückliste ist fehlgeschlagen!");
		}

		public void onSuccess(Void result) {
			if (bom != null) {
				setSelected(null);
				botvm.removeBOM(bom);
			}
		}

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub

		}
	}

void setSelected(Stückliste bom) {
	if (bom != null) {
		bomToDisplay = bom;
		nameTextBox.setText(bomToDisplay.getName());
		idValueLabel.setText(Integer.toString(bomToDisplay.getId()));
	} else {
		nameTextBox.setText("");
		idValueLabel.setText("");
	}
}

}