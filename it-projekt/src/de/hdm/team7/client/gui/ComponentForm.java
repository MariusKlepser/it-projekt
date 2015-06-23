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

public class ComponentForm extends VerticalPanel {
	
	BOMAdministrationAsync bomAdministration = ClientsideSettings
			.getBOMAdministration();

	Component componentToDisplay = null;
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
	public ComponentForm() {
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
			if (componentToDisplay != null) {
				bomAdministration.deleteComponent(componentToDisplay,
						new deleteComponentCallback(componentToDisplay));
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
			Component selectedComponent = botvm.getSelectedComponent();
			if (selectedComponent == null) {
				Window.alert("kein Bauteil ausgewaehlt");
			} else {
				bomAdministration.createComponent(selectedComponent, null,
						new createComponentCallback(selectedComponent));
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
	public class createComponentCallback implements AsyncCallback<String> {

		Component comp = null;

		createComponentCallback(Component comp) {
			this.comp = comp;
		}

		@Override
		public void onFailure(Throwable caught) {
		}

		public void onSuccess(Component comp) {
			if (comp != null) {
				botvm.addComponent(comp);
			}
		}

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub

		}
	}

	public class deleteComponentCallback implements AsyncCallback<String> {

		Component comp = null;

		deleteComponentCallback(Component comp) {
			this.comp = comp;
		}

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Loeschen des Bauteils ist fehlgeschlagen!");
		}

		public void onSuccess(Void result) {
			if (comp != null) {
				setSelected(null);
				botvm.removeComponent(comp);
			}
		}

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub

		}
	}

void setSelected(Component comp) {
	if (comp != null) {
		componentToDisplay = comp;
		nameTextBox.setText(componentToDisplay.getName());
		idValueLabel.setText(Integer.toString(componentToDisplay.getId()));
	} else {
		nameTextBox.setText("");
		idValueLabel.setText("");
	}
}

}