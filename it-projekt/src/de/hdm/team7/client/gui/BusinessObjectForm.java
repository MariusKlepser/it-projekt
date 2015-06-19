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
 * Formular f�r die Darstellung des selektierten Kunden
 * Angelehnt an Thies & Rathke
 */

public class BusinessObjectForm extends VerticalPanel {
	BOMAdministrationAsync bomAdministration = ClientsideSettings
			.getBOMAdministration();
	
	BillOfMaterial bomToDisplay = null;
	Component compToDisplay = null;
	ComponentAssembly compAssToDisplay = null;
	EndProduct endProToDisplay = null;
	User userToDisplay = null;
	BusinessObjectTreeViewModel botvm = null;

	/*
	 * Widgets, deren Inhalte variable sind, werden als Attribute angelegt.
	 */
	Label idValueLabel = new Label();
	TextBox nameTextBox = new TextBox();

	/*
	 * Im Konstruktor werden die Widgets z.T. erzeugt. Alle werden in
	 * einem Raster angeordnet, dessen Gr��e sich aus dem Platzbedarf
	 * der enthaltenen Widgets bestimmt.
	 */
	public BusinessObjectForm() {
		/**
		 * Das Grid-Widget erlaubt die Anordnung anderer Widgets in einem Gitter.
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
		deleteButton.addClickHandler(new EditClickHandler());
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
	TODO: Struktur am Beispiel von L�schung/Erstellung einer BOM (andere Objekte fehlen noch)
	TODO: Instanziierung von botvm wird nicht erkannt; deleteBOMCallback wird nicht erkannt; ComponentAssembly bei Erstellung einer neuen BOM mit einbinden?
		
	
	*/

	private class DeleteClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			if (bomToDisplay != null) {
				bomAdministration.deleteBillOfMaterial(bomToDisplay,
						new deleteBOMCallback(bomToDisplay));
			} else {

			}
		}
	}

	}
	
class deleteBOMCallback implements AsyncCallback<String> {

	BillOfMaterial bom = null;

	void deleteBOMCallback(BillOfMaterial bom) {
		this.bom = bom;
	}

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Das L�schen des Objekts ist fehlgeschlagen!");
	}

	public void onSuccess(Void result) {
		if (bom != null) {
			setSelected(null);
			botvm.removeBOM(bom);
		}
	}

	/**
	 * Ein neues Objekt wird erzeugt.
	 * 
	 */
	private class NewClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			BillOfMaterial selectedBOM = botvm.getSelectedBOM();
			if (selectedBOM == null) {
				Window.alert("kein Objekt ausgew�hlt");
			} else {
				bomAdministration.createBillOfMaterial(BillOfMaterial bom,
						new CreateBOMCallback(selectedBOM));
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
	private class CreateBOMCallback implements AsyncCallback<String> {

		BillOfMaterial bom = null;

		CreateBOMCallback(BillOfMaterial bom) {
			this.bom = bom;
		}

		@Override
		public void onFailure(Throwable caught) {
			// this.showcase.append("Fehler bei der Abfrage " +
			// caught.getMessage());
		}

		public void onSuccess(BillOfMaterial bom) {
			if (account != null && customer != null) {
				botvm.addAccountOfCustomer(account, customer);
			}
		}

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub
			
		}
	}

	@Override
	public void onSuccess(String result) {
		// TODO Auto-generated method stub
		
	}
}
