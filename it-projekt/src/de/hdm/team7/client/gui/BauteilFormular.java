package de.hdm.team7.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.team7.client.ClientEinstellungen;
import de.hdm.team7.shared.StuecklistenVerwaltungAsync;
import de.hdm.team7.shared.geschaeftsobjekte.*;

/**
 * Formular für die Darstellung des selektierten Kunden Angelehnt an Thies &
 * Rathke
 */

public class BauteilFormular extends VerticalPanel {
	
	StuecklistenVerwaltungAsync stuecklistenVerwaltung = ClientEinstellungen
			.getStuecklistenVerwaltung();

	static Bauteil bauteilDarstellung = null;
//	BusinessObjectTreeViewModel botvm = null;
	
//	public void setzeBusinessObjectTreeViewModel(BusinessObjectTreeViewModel botvm){
//		this.botvm = botvm;
//	}

	/*
	 * Widgets, deren Inhalte variable sind, werden als Attribute angelegt.
	 */
	static Label idValueLabel = new Label();
	static Label aenderungsValueLabel = new Label();
	static Label letzterBearbeiterLabel = new Label();
	static TextBox nameTextBox = new TextBox();
	static TextBox materialTextBox = new TextBox();
	static TextArea beschreibung = new TextArea();
	
	static Button newButton = new Button("Neu");
	static Button editButton = new Button("Bearbeiten");
	static Button deleteButton = new Button("Loeschen");

	/*
	 * Im Konstruktor werden die Widgets z.T. erzeugt. Alle werden in einem
	 * Raster angeordnet, dessen Größe sich aus dem Platzbedarf der enthaltenen
	 * Widgets bestimmt.
	 */
	public BauteilFormular() {
		/**
		 * Das Grid-Widget erlaubt die Anordnung anderer Widgets in einem
		 * Gitter.
		 */
//		DecoratorPanel decoPanel = new DecoratorPanel(); 
		
		Grid boGrid = new Grid(7, 2);
//		decoPanel.add(boGrid);
		this.add(boGrid);
		
		Label ueberschrift = new Label("Bauteil Info");
		boGrid.setWidget(0, 0, ueberschrift);

		Label idLabel = new Label("ID:");
		boGrid.setWidget(1, 0, idLabel);
		boGrid.setWidget(1, 1, idValueLabel);

		Label nameLabel = new Label("Name:");
		boGrid.setWidget(2, 0, nameLabel);
		boGrid.setWidget(2, 1, nameTextBox);
		
		Label materialLabel = new Label("Materialbezeichnung:");
		boGrid.setWidget(3, 0, materialLabel);
		boGrid.setWidget(3, 1, materialTextBox);
		
		Label beschreibungLabel = new Label("Beschreibung:");
		boGrid.setWidget(4, 0, beschreibungLabel);
		boGrid.setWidget(4, 1, beschreibung);
		
		Label aenderungsDatumLabel = new Label("Aenderungsdatum:");
		boGrid.setWidget(5, 0, aenderungsDatumLabel);
		boGrid.setWidget(5, 1, aenderungsValueLabel);
		
		Label letzterBearbeiter = new Label("Letzter Bearbeiter:");
		boGrid.setWidget(6, 0, letzterBearbeiter);
		boGrid.setWidget(6, 1, letzterBearbeiterLabel);

		HorizontalPanel boButtonsPanel = new HorizontalPanel();
//		newButton.addClickHandler(new NewClickHandler());
		boButtonsPanel.add(newButton);
		
		deleteButton.addClickHandler(new DeleteClickHandler());
		boButtonsPanel.add(deleteButton);

//		editButton.addClickHandler(new EditClickHandler());
		boButtonsPanel.add(editButton);
		
//		decoPanel.add(boButtonsPanel);
		this.add(boButtonsPanel);
//		this.add(decoPanel);
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
			if (bauteilDarstellung != null) {
				stuecklistenVerwaltung.loescheBauteil(bauteilDarstellung,
						new loescheBauteilCallback(bauteilDarstellung));
			} else {

			}
		}
	}

	/**
	 * Ein neues Objekt wird erzeugt.
	 * 
	 */
//	private class NewClickHandler implements ClickHandler {
//
//		@Override
//		public void onClick(ClickEvent event) {
//			Bauteil selektiertesBauteil = botvm.holeSelektiertesBauteil();
//			if (selektiertesBauteil == null) {
//				Window.alert("kein Bauteil ausgewählt");
//			} else {
//				stuecklistenVerwaltung.erstelleBauteil(selektiertesBauteil,
//						new erstelleBauteilCallback(selektiertesBauteil));
//			}
//		}
//	}

	/*
	 * Auch hier muss nach erfolgreicher Kontoerzeugung der Kunden- und
	 * Kontobaum aktualisiert werden. Dafür dient ein privates Attribut und der
	 * Konstruktor.
	 * 
	 * Wir benötigen hier nur einen Parameter für den Kunden, da das Konto als
	 * ergebnis des asynchronen Aufrufs geliefert wird.
	 */
	public class erstelleBauteilCallback implements AsyncCallback<String> {

		Bauteil comp = null;

		erstelleBauteilCallback(Bauteil comp) {
			this.comp = comp;
		}

		@Override
		public void onFailure(Throwable caught) {
		}

		public void onSuccess(Bauteil comp) {
			if (comp != null) {
//				botvm.fuegeBauteilZuBaumHinzu(comp);
			}
		}

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub

		}
	}

	public class loescheBauteilCallback implements AsyncCallback<String> {

		Bauteil comp = null;

		loescheBauteilCallback(Bauteil comp) {
			this.comp = comp;
		}

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Löschen des Bauteils ist fehlgeschlagen!");
		}

		public void onSuccess(Void result) {
			if (comp != null) {
				setzeSelektiert(null);
//				botvm.entferneBauteil(comp);
			}
		}

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub

		}
	}

public void setzeSelektiert(Bauteil comp) {
	if (comp != null) {
		bauteilDarstellung = comp;
		nameTextBox.setText(bauteilDarstellung.getName());
		idValueLabel.setText(Integer.toString(bauteilDarstellung.getId()));
		materialTextBox.setText(bauteilDarstellung.getMaterialBezeichnung());
		beschreibung.setText(bauteilDarstellung.getDescription());
		aenderungsValueLabel.setText(bauteilDarstellung.getAenderungsDatum());
//		letzterBearbeiterLabel.setText(bauteilDarstellung.);
		
		newButton.setVisible(false);
		editButton.setEnabled(true);
	} else {
		nameTextBox.setText("");
		idValueLabel.setText("");
		materialTextBox.setText("");
		beschreibung.setText("");
		aenderungsValueLabel.setText("");
//		letzterBearbeiterLabel.setText(bauteilDarstellung.);
		
		newButton.setEnabled(true);
		editButton.setVisible(false);
		deleteButton.setVisible(false);
	}
}

}