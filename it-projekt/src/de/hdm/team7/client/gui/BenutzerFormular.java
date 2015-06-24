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
import de.hdm.team7.shared.StuecklistenVerwaltungAsync;
import de.hdm.team7.shared.geschaeftsobjekte.*;
import de.hdm.thies.bankProjekt.client.gui.CustomerAccountsTreeViewModel;
import de.hdm.thies.bankProjekt.client.gui.CustomerForm.SaveCallback;

/**
 * Formular für die Darstellung des selektierten Kunden Angelehnt an Thies &
 * Rathke
 */

public class BenutzerFormular extends VerticalPanel {
	
	StuecklistenVerwaltungAsync stuecklistenVerwaltung = ClientEinstellungen
			.getStuecklistenVerwaltung();

	Benutzer benutzerDarstellung = null;
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
	public BenutzerFormular() {
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
	 */

	private class DeleteClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			if (benutzerDarstellung != null) {
				stuecklistenVerwaltung.loescheBenutzer(benutzerDarstellung,
						new LoescheBenutzerCallback(benutzerDarstellung));
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
			Benutzer selektierterBenutzer = botvm.getSelektierterBenutzer();
			if (selektierterBenutzer == null) {
				Window.alert("kein Benutzer ausgewählt");
			} else {
				stuecklistenVerwaltung.erstelleBenutzer(selektierterBenutzer,
						new ErstelleBenutzerCallback(selektierterBenutzer));
			}
		}
	}
	
	private class EditClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			if (benutzerDarstellung != null) {
				benutzerDarstellung.setzeName(nameTextBox.getText());
				stuecklistenVerwaltung.speichere(benutzerDarstellung, new SpeicherCallback());
			} else {
				Window.alert("kein Benutzer ausgewählt");
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
			botvm.aktualisiereBenutzer(benutzerDarstellung);
		}
	}

	public class ErstelleBenutzerCallback implements AsyncCallback<String> {

		Benutzer b = null;

		ErstelleBenutzerCallback(Benutzer b) {
			this.b = b;
		}

		@Override
		public void onFailure(Throwable caught) {
		}

		public void onSuccess(Benutzer b) {
			if (b != null) {
				botvm.fuegeBenutzerHinzu(b);
			}
		}

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub

		}
	}

	public class LoescheBenutzerCallback implements AsyncCallback<String> {

		Benutzer b = null;

		LoescheBenutzerCallback(Benutzer b) {
			this.b = b;
		}

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Löschen des Benutzers ist fehlgeschlagen!");
		}

		public void onSuccess(Void result) {
			if (b != null) {
				setzeSelektiert(null);
				botvm.entferneBenutzer(b);
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

void setzeSelektiert(Benutzer b) {
	if (b != null) {
		benutzerDarstellung = b;
		nameTextBox.setText(benutzerDarstellung.getName());
		idValueLabel.setText(Integer.toString(benutzerDarstellung.getId()));
	} else {
		nameTextBox.setText("");
		idValueLabel.setText("");
	}
}

}