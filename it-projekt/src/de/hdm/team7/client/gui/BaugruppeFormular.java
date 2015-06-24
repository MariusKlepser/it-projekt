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
import de.hdm.team7.client.gui.BauteilFormular.SpeicherCallback;
import de.hdm.team7.shared.StuecklistenVerwaltungAsync;
import de.hdm.team7.shared.geschaeftsobjekte.*;

/**
 * Formular für die Darstellung des selektierten Kunden Angelehnt an Thies &
 * Rathke
 */

public class BaugruppeFormular extends VerticalPanel {
	
	StuecklistenVerwaltungAsync stuecklistenVerwaltung = ClientEinstellungen
			.getStuecklistenVerwaltung();

	Baugruppe baugruppeDarstellung = null;
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
	 * Raster angeordnet, dessen Größe sich aus dem Platzbedarf der enthaltenen
	 * Widgets bestimmt.
	 */
	public BaugruppeFormular() {
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
		
		Label datumLabel = new Label("Änderungsdatum");
		boGrid.setWidget(4, 0, datumLabel);
		boGrid.setWidget(4, 1, datumValueLabel);

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
	 */

	private class DeleteClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			if (baugruppeDarstellung != null) {
				stuecklistenVerwaltung.loescheBaugruppe(baugruppeDarstellung,
						new loescheBaugruppeCallback(baugruppeDarstellung));
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
			Baugruppe selektierteBaugruppe = botvm.getSelektierteBaugruppe();
			if (selektierteBaugruppe == null) {
				Window.alert("keine Baugruppe ausgewählt");
			} else {
				stuecklistenVerwaltung.erstelleBaugruppe(selektierteBaugruppe, null,
						new erstelleBaugruppeCallback(selektierteBaugruppe));
			}
		}
	}

	private class EditClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			if (baugruppeDarstellung != null) {
				baugruppeDarstellung.setzeName(nameTextBox.getText());
				baugruppeDarstellung.setzeBeschreibung(beschreibungTextBox.getText());
				baugruppeDarstellung.setzeMaterial(materialTextBox.getText());
				baugruppeDarstellung.setzeDatum(datumValueLabel.getText());
				stuecklistenVerwaltung.speichere(baugruppeDarstellung, new SpeicherCallback());
			} else {
				Window.alert("keine Baugruppe ausgewählt");
			}
		}
	}

	private class SpeicherCallback implements AsyncCallback<Void> {
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Die Änderung ist fehlgeschlagen!");
		}

		@Override
		public void onSuccess(Void result) {
			botvm.aktualisiereBaugruppe(baugruppeDarstellung);
		}
	}
	
	public class erstelleBaugruppeCallback implements AsyncCallback<String> {

		Baugruppe compAss = null;

		erstelleBaugruppeCallback(Baugruppe compAss) {
			this.compAss = compAss;
		}

		@Override
		public void onFailure(Throwable caught) {
		}

		public void onSuccess(Baugruppe compAss) {
			if (compAss != null) {
				botvm.fuegeBaugruppeHinzu(compAss);
			}
		}

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub

		}
	}

	public class loescheBaugruppeCallback implements AsyncCallback<String> {

		Baugruppe compAss = null;

		loescheBaugruppeCallback(Baugruppe compAss) {
			this.compAss = compAss;
		}

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Löschen der Baugruppe ist fehlgeschlagen!");
		}

		public void onSuccess(Void result) {
			if (compAss != null) {
				setzeSelektiert(null);
				botvm.entferneBaugruppe(compAss);
			}
		}

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub

		}
	}
	
	
	// botvm setter
	void setCaBotvm(BusinessObjectTreeViewModel botvm) {
		this.botvm = botvm;
	}


void setzeSelektiert(Baugruppe compAss) {
	if (compAss != null) {
		baugruppeDarstellung = compAss;
		nameTextBox.setText(baugruppeDarstellung.getName());
		idValueLabel.setText(Integer.toString(baugruppeDarstellung.getId()));
	} else {
		nameTextBox.setText("");
		idValueLabel.setText("");
	}
}

}