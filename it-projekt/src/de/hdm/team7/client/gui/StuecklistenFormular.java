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
import de.hdm.team7.shared.StuecklistenVerwaltung;
import de.hdm.team7.shared.StuecklistenVerwaltungAsync;
import de.hdm.team7.shared.geschaeftsobjekte.*;

/**
 * Formular f�r die Darstellung des selektierten Kunden Angelehnt an Thies &
 * Rathke
 */

public class StuecklistenFormular extends VerticalPanel {
	
	StuecklistenVerwaltungAsync stuecklistenVerwaltung = ClientEinstellungen.getStuecklistenVerwaltung();

	Stueckliste stuecklistenDarstellung = null;
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
	 * Raster angeordnet, dessen Gr��e sich aus dem Platzbedarf der enthaltenen
	 * Widgets bestimmt.
	 */
	public StuecklistenFormular() {
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
//		editButton.addClickHandler(new EditClickHandler());
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
	 * TODO: rootElement bei Erstellung einer neuen BOM muss mitgegeben werden
	 */

	private class DeleteClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			if (stuecklistenDarstellung != null) {
//				stuecklistenVerwaltung.loescheStueckliste(stuecklistenDarstellung,
//						new LoescheStuecklistenCallback(stuecklistenDarstellung));
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
			Stueckliste selektierteStueckliste = botvm.holeSelektierteStueckliste();
			if (selektierteStueckliste == null) {
				Window.alert("keine St�ckliste ausgew�hlt");
			} else {
				stuecklistenVerwaltung.erstelleStueckliste(selektierteStueckliste, null,
						new ErstelleStuecklistenCallback(selektierteStueckliste));
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
//				botvm.fuegeStuecklisteHinzu(bom);
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
			Window.alert("Das L�schen der St�ckliste ist fehlgeschlagen!");
		}

		public void onSuccess(Void result) {
			if (bom != null) {
				setzeSelektiert(null);
//				botvm.entferneStueckliste(bom);
			}
		}

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub

		}
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