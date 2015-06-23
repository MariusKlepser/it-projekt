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
 * Formular fuer die Darstellung des selektierten Kunden Angelehnt an Thies &
 * Rathke
 */

public class UserForm extends VerticalPanel {
	
	BOMAdministrationAsync bomAdministration = ClientsideSettings
			.getBOMAdministration();

	User userToDisplay = null;
	BusinessObjectTreeViewModel botvm = null;

	/*
	 * Widgets, deren Inhalte variable sind, werden als Attribute angelegt.
	 */
	Label idValueLabel = new Label();
	TextBox nameTextBox = new TextBox();

	/*
	 * Im Konstruktor werden die Widgets z.T. erzeugt. Alle werden in einem
	 * Raster angeordnet, dessen Groeﬂe sich aus dem Platzbedarf der enthaltenen
	 * Widgets bestimmt.
	 */
	public UserForm() {
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

		Button deleteButton = new Button("Loeschen");
		deleteButton.addClickHandler(new DeleteClickHandler());
		boButtonsPanel.add(deleteButton);

		Button editButton = new Button("Bearbeiten");
		editButton.addClickHandler(new EditClickHandler());
		boButtonsPanel.add(editButton);
	}

	/*
	 * Click handlers und abhaengige AsyncCallback Klassen.
	 */

	/**
	 * Zum Loeschen eines Kontos wird zunaechst der Eigentuemer abgefragt, bevor im
	 * Callback eine Loeschung durchgefuehrt wird.
	 * 
	 */

	/*
	 * TODO: Edit-Methode + ClickHandler muessen eingefuegt werden
	 * TODO: Methoden, auf die im BusinessObjectTreeViewModel zugegriffen wird, muessen ergaenzt werden
	 */

	private class DeleteClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			if (userToDisplay != null) {
				bomAdministration.deleteUser(userToDisplay,
						new DeleteUserCallback(userToDisplay));
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
			User selectedUser = botvm.getSelectedUser();
			if (selectedUser == null) {
				Window.alert("kein Nutzer ausgewaehlt");
			} else {
				bomAdministration.createUser(selectedUser, null,
						new CreateUserCallback(selectedUser));
			}
		}
	}

	/*
	 * Auch hier muss nach erfolgreicher Kontoerzeugung der Kunden- und
	 * Kontobaum aktualisiert werden. Dafuer dient ein privates Attribut und der
	 * Konstruktor.
	 * 
	 * Wir benoetigen hier nur einen Parameter fuer den Kunden, da das Konto als
	 * ergebnis des asynchronen Aufrufs geliefert wird.
	 */
	public class CreateUserCallback implements AsyncCallback<String> {

		User us = null;

		CreateUserCallback(User us) {
			this.us = us;
		}

		@Override
		public void onFailure(Throwable caught) {
		}

		public void onSuccess(User us) {
			if (us != null) {
				botvm.addUser(us);
			}
		}

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub

		}
	}

	public class DeleteUserCallback implements AsyncCallback<String> {

		User us = null;

		DeleteUserCallback(User us) {
			this.us = us;
		}

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Loeschen des Nutzers ist fehlgeschlagen!");
		}

		public void onSuccess(Void result) {
			if (us != null) {
				setSelected(null);
				botvm.removeUser(us);
			}
		}

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub

		}
	}

void setSelected(User us) {
	if (us != null) {
		userToDisplay = us;
		nameTextBox.setText(userToDisplay.getName());
		idValueLabel.setText(Integer.toString(userToDisplay.getId()));
	} else {
		nameTextBox.setText("");
		idValueLabel.setText("");
	}
}

}