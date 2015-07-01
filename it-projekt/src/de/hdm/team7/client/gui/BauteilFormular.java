package de.hdm.team7.client.gui;

//import com.google.appengine.api.users.UserServiceFactory;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.team7.client.ClientEinstellungen;
import de.hdm.team7.shared.StuecklistenVerwaltungAsync;
import de.hdm.team7.shared.geschaeftsobjekte.Bauteil;

/**
 * Formular für die Darstellung des selektierten Kunden Angelehnt an Thies &
 * Rathke
 */

public class BauteilFormular extends VerticalPanel {

	StuecklistenVerwaltungAsync stuecklistenVerwaltung = ClientEinstellungen
			.getStuecklistenVerwaltung();

	static Bauteil bauteilDarstellung = null;

	/*
	 * Widgets, deren Inhalte variable sind, werden als Attribute angelegt.
	 */
	static Label idValueLabel = new Label();
	static Label aenderungsValueLabel = new Label();
	static Label letzterBearbeiterLabel = new Label();
	Label fehlerLabelName = new Label("Bitte geben Sie einen Namen ein!");
	Label fehlerLabelMaterial = new Label("Bitte geben Sie ein Material ein!");
	Label fehlerLabelBeschreibung = new Label(
			"Bitte geben Sie eine Beschreibung ein!");

	static TextBox nameTextBox = new TextBox();
	static TextBox materialTextBox = new TextBox();
	static TextArea beschreibung = new TextArea();

	static Button newButton = new Button("Erstellen");
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

		Grid boGrid = new Grid(10, 2);
		this.add(boGrid);

		Label ueberschrift = new Label("Bauteil Info");
		boGrid.setWidget(0, 0, ueberschrift);

		Label idLabel = new Label("ID:");
		boGrid.setWidget(1, 0, idLabel);
		boGrid.setWidget(1, 1, idValueLabel);

		Label nameLabel = new Label("Name:");
		boGrid.setWidget(2, 0, nameLabel);
		boGrid.setWidget(2, 1, nameTextBox);
		boGrid.setWidget(3, 1, fehlerLabelName);
		fehlerLabelName.setVisible(false);

		Label materialLabel = new Label("Materialbezeichnung:");
		boGrid.setWidget(4, 0, materialLabel);
		boGrid.setWidget(4, 1, materialTextBox);
		boGrid.setWidget(5, 1, fehlerLabelMaterial);
		fehlerLabelMaterial.setVisible(false);

		Label beschreibungLabel = new Label("Beschreibung:");
		boGrid.setWidget(6, 0, beschreibungLabel);
		boGrid.setWidget(6, 1, beschreibung);
		boGrid.setWidget(7, 1, fehlerLabelBeschreibung);
		fehlerLabelBeschreibung.setVisible(false);

		Label aenderungsDatumLabel = new Label("Aenderungsdatum:");
		boGrid.setWidget(8, 0, aenderungsDatumLabel);
		boGrid.setWidget(8, 1, aenderungsValueLabel);

		Label letzterBearbeiter = new Label("Letzter Bearbeiter:");
		boGrid.setWidget(9, 0, letzterBearbeiter);
		boGrid.setWidget(9, 1, letzterBearbeiterLabel);

		HorizontalPanel boButtonsPanel = new HorizontalPanel();
		newButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				final String bauteilname = nameTextBox.getText().toUpperCase().trim();
				final String materialbezeichnung = materialTextBox.getText().toUpperCase().trim();
				final String beschreibung1 = beschreibung.getText().toUpperCase().trim();
				if (nameTextBox.getText() == null) {
					fehlerLabelName.setVisible(true);
				} else if (materialTextBox.getValue() == null) {
					fehlerLabelMaterial.setVisible(true);
				} else if (beschreibung.getValue() == null) {
					fehlerLabelBeschreibung.setVisible(true);
				} else if (!bauteilname.matches("^[0-9A-Z]{0,30}$")) {
				    Window.alert("Es sind nur Buchstaben und Zahlen im Bauteilname erlaubt!");
				    nameTextBox.selectAll();
				    return;
				
			    } else if (!materialbezeichnung.matches("^[0-9A-Z]{0,30}$")) {
			    	Window.alert("Es sind nur Buchstaben und Zahlen in der Materialbezeichnung erlaubt!");
			    	nameTextBox.selectAll();
			    	return;
			
			    } else if (!beschreibung1.matches("^[0-9A-Z]{0,30}$")) {
			    	Window.alert("Es sind nur Buchstaben und Zahlen in der Beschreibung erlaubt!");
			    	nameTextBox.selectAll();
			    	return;
			
			    } else{
					bauteilDarstellung.setName(nameTextBox.getText());
					bauteilDarstellung.setMaterialBezeichnung(materialTextBox
							.getText());
					bauteilDarstellung.setDescription(beschreibung.getText());
//					bauteilDarstellung.setLetzterBearbeiter(UserServiceFactory.getUserService().getCurrentUser().getEmail());
					stuecklistenVerwaltung.erstelleBauteil(bauteilDarstellung,
							new erstelleBauteilCallback(bauteilDarstellung));
				}
				
			}
			
		});
		boButtonsPanel.add(newButton);

		deleteButton.addClickHandler(new DeleteClickHandler());
		boButtonsPanel.add(deleteButton);

		editButton.addClickHandler(new EditClickHandler());
		boButtonsPanel.add(editButton);

		this.add(boButtonsPanel);
	}

	/*
	 * Click handlers und abhängige AsyncCallback Klassen.
	 */

	/**
	 * Zum Löschen eines Kontos wird zunächst der Eigentümer abgefragt, bevor im
	 * Callback eine Löschung durchgeführt wird.
	 * 
	 */

	private class DeleteClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			stuecklistenVerwaltung.loescheBauteil(bauteilDarstellung,
					new loescheBauteilCallback(bauteilDarstellung));
		}
	}

	/**
	 * Ein neues Objekt wird erzeugt.
	 * 
	 */
//	private class NewClickHandler implements ClickHandler {

//		@Override
//		public void onClick(ClickEvent event) {
//			if (nameTextBox.getValue() == null) {
//				fehlerLabelName.setVisible(true);
//			} else if (materialTextBox.getValue() == null) {
//				fehlerLabelMaterial.setVisible(true);
//			} else if (beschreibung.getValue() == null) {
//				fehlerLabelBeschreibung.setVisible(true);
//			} else {
//				bauteilDarstellung.setName(nameTextBox.getText());
//				bauteilDarstellung.setMaterialBezeichnung(materialTextBox
//						.getText());
//				bauteilDarstellung.setDescription(beschreibung.getText());
//				bauteilDarstellung.setLetzterBearbeiter(UserServiceFactory.getUserService().getCurrentUser().getEmail());
//				stuecklistenVerwaltung.erstelleBauteil(bauteilDarstellung,
//						new erstelleBauteilCallback(bauteilDarstellung));
//			}
//		}
//	}

	private class EditClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			if (nameTextBox.getValue() == null) {
				fehlerLabelName.setVisible(true);
			} else if (materialTextBox.getValue() == null) {
				fehlerLabelMaterial.setVisible(true);
			} else if (beschreibung.getValue() == null) {
				fehlerLabelBeschreibung.setVisible(true);
			} else {
				bauteilDarstellung.setName(nameTextBox.getText());
				bauteilDarstellung.setMaterialBezeichnung(materialTextBox
						.getText());
				bauteilDarstellung.setDescription(beschreibung.getText());
//				bauteilDarstellung.setLetzterBearbeiter(UserServiceFactory.getUserService().getCurrentUser().getEmail());
				stuecklistenVerwaltung.aktualisiereBauteil(bauteilDarstellung,
						new bearbeiteBauteilCallback(bauteilDarstellung));
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
	public class erstelleBauteilCallback implements AsyncCallback<String> {

		Bauteil comp = null;

		erstelleBauteilCallback(Bauteil comp) {
			this.comp = comp;
		}

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Erstellen des Bauteils ist fehlgeschlagen!");
		}

		@Override
		public void onSuccess(String result) {
			Window.alert("Das Bauteil wurde erstellt!");

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

		@Override
		public void onSuccess(String result) {
			Window.alert("Das Bauteil wurde geloescht");

		}
	}

	public class bearbeiteBauteilCallback implements AsyncCallback<String> {

		Bauteil comp = null;

		bearbeiteBauteilCallback(Bauteil comp) {
			this.comp = comp;
		}

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Bearbeiten des Bauteils ist fehlgeschlagen!");
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
			materialTextBox
					.setText(bauteilDarstellung.getMaterialBezeichnung());
			beschreibung.setText(bauteilDarstellung.getDescription());
			aenderungsValueLabel.setText(bauteilDarstellung
					.getAenderungsDatum());
			letzterBearbeiterLabel.setText(bauteilDarstellung
					.getLetzterBearbeiter());

			newButton.setVisible(false);
			editButton.setVisible(true);
			deleteButton.setVisible(true);
		} else {
			nameTextBox.setText("");
			idValueLabel.setText("");
			materialTextBox.setText("");
			beschreibung.setText("");
			aenderungsValueLabel.setText("");
			letzterBearbeiterLabel.setText("");

			newButton.setVisible(true);
			editButton.setVisible(false);
			deleteButton.setVisible(false);
		}
	}

}