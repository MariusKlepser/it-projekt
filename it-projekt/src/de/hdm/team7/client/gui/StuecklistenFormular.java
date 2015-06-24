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
import de.hdm.team7.client.gui.BenutzerFormular.SpeicherCallback;
import de.hdm.team7.shared.StuecklistenVerwaltung;
import de.hdm.team7.shared.StuecklistenVerwaltungAsync;
import de.hdm.team7.shared.geschaeftsobjekte.*;

/**
 * Formular für die Darstellung des selektierten Kunden Angelehnt an Thies &
 * Rathke
 */

public class StuecklistenFormular extends VerticalPanel {
	
	StuecklistenVerwaltungAsync stuecklistenVerwaltung = ClientEinstellungen.getStuecklistenVerwaltung();

	Stueckliste stuecklistenDarstellung = null;
	BusinessObjectTreeViewModel botvm = null;

	/*
	 * Widgets, deren Inhalte variable sind, werden als Attribute angelegt.
	 */
	Label idValueLabel = new Label();
	TextBox nameTextBox = new TextBox();
	Label datumValueLabel = new Label();

	/*
	 * Im Konstruktor werden die Widgets z.T. erzeugt. Alle werden in einem
	 * Raster angeordnet, dessen Größe sich aus dem Platzbedarf der enthaltenen
	 * Widgets bestimmt.
	 */
	public StuecklistenFormular() {
		/**
		 * Das Grid-Widget erlaubt die Anordnung anderer Widgets in einem
		 * Gitter.
		 */
		Grid boGrid = new Grid(4, 2);
		this.add(boGrid);

		Label idLabel = new Label("ID");
		boGrid.setWidget(0, 0, idLabel);
		boGrid.setWidget(0, 1, idValueLabel);

		Label nameLabel = new Label("Name");
		boGrid.setWidget(1, 0, nameLabel);
		boGrid.setWidget(1, 1, nameTextBox);
		
		Label datumLabel = new Label("Erstellungsdatum");
		boGrid.setWidget(2, 0, datumLabel);
		boGrid.setWidget(2, 1, datumValueLabel);

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
			if (stuecklistenDarstellung != null) {
				stuecklistenVerwaltung.loescheStueckliste(stuecklistenDarstellung,
						new LoescheStuecklistenCallback(stuecklistenDarstellung));
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
			Stueckliste selektierteStueckliste = botvm.getSelektierteStueckliste();
			if (selektierteStueckliste == null) {
				Window.alert("keine Stückliste ausgewählt");
			} else {
				stuecklistenVerwaltung.erstelleStueckliste(selektierteStueckliste, null,
						new ErstelleStuecklistenCallback(selektierteStueckliste));
			}
		}
	}

	private class EditClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			if (stuecklistenDarstellung != null) {
				stuecklistenDarstellung.setzeName(nameTextBox.getText());
				stuecklistenVerwaltung.speichere(stuecklistenDarstellung, new SpeicherCallback());
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
			botvm.aktualisiereStueckliste(stuecklistenDarstellung);
		}
	}
	
	
	public class ErstelleStuecklistenCallback implements AsyncCallback<String> {

		Stueckliste bom = null;

		ErstelleStuecklistenCallback(Stueckliste bom) {
			this.bom = bom;
		}

		@Override
		public void onFailure(Throwable caught) {
		}

		public void onSuccess(Stueckliste bom) {
			if (bom != null) {
				botvm.fuegeStuecklisteHinzu(bom);
			}
		}

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub

		}
	}

	public class loescheStuecklisteCallback implements AsyncCallback<String> {

		Stueckliste bom = null;

		loescheStuecklisteCallback(Stueckliste bom) {
			this.bom = bom;
		}

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Löschen der Stückliste ist fehlgeschlagen!");
		}

		public void onSuccess(Void result) {
			if (bom != null) {
				setzeSelektiert(null);
				botvm.entferneStueckliste(bom);
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

void setzeSelektiert(Stueckliste bom) {
	if (bom != null) {
		stuecklistenDarstellung = bom;
		nameTextBox.setText(stuecklistenDarstellung.getName());
		idValueLabel.setText(Integer.toString(stuecklistenDarstellung.getId()));
	} else {
		nameTextBox.setText("");
		idValueLabel.setText("");
	}
}

}