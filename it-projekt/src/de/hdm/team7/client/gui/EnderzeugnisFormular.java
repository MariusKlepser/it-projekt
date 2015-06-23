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
import de.hdm.team7.client.gui.BaugruppeFormular.SpeicherCallback;
import de.hdm.team7.shared.StuecklistenVerwaltungAsync;
import de.hdm.team7.shared.geschaeftsobjekte.*;

/**
 * Formular f�r die Darstellung des selektierten Kunden Angelehnt an Thies &
 * Rathke
 */

public class EnderzeugnisFormular extends VerticalPanel {
	
	StuecklistenVerwaltungAsync stuecklistenVerwaltung = ClientEinstellungen
			.getStuecklistenVerwaltung();

	Enderzeugnis enderzeugnisDarstellung = null;
	BusinessObjectTreeViewModel botvm = null;

	/*
	 * Widgets, deren Inhalte variable sind, werden als Attribute angelegt.
	 */
	Label idValueLabel = new Label();
	TextBox nameTextBox = new TextBox();
	TextBox materialTextBox = new TextBox();
	TextBox beschreibungTextBox = new TextBox();
	Label datumValueLabel = new Label();

	/*
	 * Im Konstruktor werden die Widgets z.T. erzeugt. Alle werden in einem
	 * Raster angeordnet, dessen Gr��e sich aus dem Platzbedarf der enthaltenen
	 * Widgets bestimmt.
	 */
	public EnderzeugnisFormular() {
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
		
		Label beschreibungLabel = new Label("Beschreibung");
		boGrid.setWidget(2, 0, beschreibungLabel);
		boGrid.setWidget(2, 1, beschreibungTextBox);
		
		Label materialLabel = new Label("Materialbezeichnung");
		boGrid.setWidget(3, 0, materialLabel);
		boGrid.setWidget(3, 1, materialTextBox);
		
		Label datumLabel = new Label("�nderungsdatum");
		boGrid.setWidget(4, 0, datumLabel);
		boGrid.setWidget(4, 1, datumValueLabel);

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
			if (enderzeugnisDarstellung != null) {
				stuecklistenVerwaltung.loescheEnderzeugnis(enderzeugnisDarstellung,
						new loescheEnderzeugnisCallback(enderzeugnisDarstellung));
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
			Enderzeugnis selektiertesEnderzeugnis = botvm.getSelektiertesEnderzeugnis();
			if (selektiertesEnderzeugnis == null) {
				Window.alert("kein Enderzeugnis ausgew�hlt");
			} else {
				stuecklistenVerwaltung.erstelleEnderzeugnis(selektiertesEnderzeugnis, null,
						new erstelleEnderzeugnisCallback(selektiertesEnderzeugnis));
			}
		}
	}

	private class EditClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			if (enderzeugnisDarstellung != null) {
				enderzeugnisDarstellung.setzeName(nameTextBox.getText());
				enderzeugnisDarstellung.setzeBeschreibung(beschreibungTextBox.getText());
				enderzeugnisDarstellung.setzeMaterial(materialTextBox.getText());
				enderzeugnisDarstellung.setzeDatum(datumValueLabel.getText());
				stuecklistenVerwaltung.speichere(enderzeugnisDarstellung, new SpeicherCallback());
			} else {
				Window.alert("keine Baugruppe ausgew�hlt");
			}
		}
	}

	private class SpeicherCallback implements AsyncCallback<Void> {
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Die �nderung ist fehlgeschlagen!");
		}

		@Override
		public void onSuccess(Void result) {
			botvm.aktualisiereEnderzeugnis(enderzeugnisDarstellung);
		}
	}
	
	public class erstelleEnderzeugnisCallback implements AsyncCallback<String> {

		Enderzeugnis endproduct = null;

		erstelleEnderzeugnisCallback(Enderzeugnis endProduct) {
			this.endproduct = endProduct;
		}

		@Override
		public void onFailure(Throwable caught) {
		}

		public void onSuccess(Enderzeugnis endProduct) {
			if (endProduct != null) {
				botvm.fuegeEnderzeugnisHinzu(endProduct);
			}
		}

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub

		}
	}

	public class loescheEnderzeugnisCallback implements AsyncCallback<String> {

		Enderzeugnis endProduct = null;

		loescheEnderzeugnisCallback(Enderzeugnis endProduct) {
			this.endProduct = endProduct;
		}

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das L�schen des Endprodukts ist fehlgeschlagen!");
		}

		public void onSuccess(Void result) {
			if (endProduct != null) {
				setzeSelektiert(null);
				botvm.entferneEnderzeugnis(endProduct);
			}
		}

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub

		}
	}

void setzeSelektiert(Enderzeugnis endProduct) {
	if (endProduct != null) {
		enderzeugnisDarstellung = endProduct;
		nameTextBox.setText(enderzeugnisDarstellung.getName());
		idValueLabel.setText(Integer.toString(enderzeugnisDarstellung.getId()));
	} else {
		nameTextBox.setText("");
		idValueLabel.setText("");
	}
}

}