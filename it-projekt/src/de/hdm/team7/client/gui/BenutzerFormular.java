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

public class BenutzerFormular extends VerticalPanel {
	
	BOMAdministrationAsync bomAdministration = ClientEinstellungen
			.getBOMAdministration();

	Benutzer benutzerDarstellung = null;
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
			if (benutzerDarstellung != null) {
				bomAdministration.deleteUser(benutzerDarstellung,
						new DeleteUserCallback(benutzerDarstellung));
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
			User selektierterBenutzer = botvm.waehleSelektiertenBenutzer();
			if (selektierterBenutzer == null) {
				Window.alert("kein Nutzer ausgewählt");
			} else {
				bomAdministration.erzeugeBenutzer(selektierterBenutzer, null,
						new CreateUserCallback(selektierterBenutzer));
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
	public class CreateUserCallback implements AsyncCallback<String> {

		Benutzer b = null;

		CreateUserCallback(Benutzer b) {
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

	public class DeleteUserCallback implements AsyncCallback<String> {

		Benutzer b = null;

		DeleteUserCallback(Benutzer b) {
			this.b = b;
		}

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Löschen des Nutzers ist fehlgeschlagen!");
		}

		public void onSuccess(Void result) {
			if (b != null) {
				setSelected(null);
				botvm.loescheBenutzer(b);
			}
		}

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub

		}
	}

void setSelected(Benutzer b) {
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