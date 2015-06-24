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

/**
 * Formular für die Darstellung des selektierten Kunden Angelehnt an Thies &
 * Rathke
 */

public class BaugruppeFormular extends VerticalPanel {

	StuecklistenVerwaltungAsync stuecklistenVerwaltung = ClientEinstellungen
			.getStuecklistenVerwaltung();

	Baugruppe baugruppeDarstellung = null;
	BusinessObjectTreeViewModel botvm = null;
	
	public void setzeBusinessObjectTreeViewModel(BusinessObjectTreeViewModel botvm){
		this.botvm = botvm;
	}

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
	 * TODO: Edit-Methode + ClickHandler müssen eingefügt werden TODO: Methoden,
	 * auf die im BusinessObjectTreeViewModel zugegriffen wird, müssen ergänzt
	 * werden
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
			Baugruppe selektierteBaugruppe = botvm.holeSelektierteBaugruppe();
			if (selektierteBaugruppe == null) {
				Window.alert("keine Baugruppe ausgewählt");
			} else {
				stuecklistenVerwaltung.erstelleBaugruppe(selektierteBaugruppe,
						null, new erstelleBaugruppeCallback(
								selektierteBaugruppe));
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

	void setzeSelektiert(Baugruppe compAss) {
		if (compAss != null) {
			baugruppeDarstellung = compAss;
			nameTextBox.setText(baugruppeDarstellung.getName());
			idValueLabel
					.setText(Integer.toString(baugruppeDarstellung.getId()));
		} else {
			nameTextBox.setText("");
			idValueLabel.setText("");
		}
	}

}