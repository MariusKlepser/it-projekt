package de.hdm.team7.client.gui;

import java.util.ArrayList;
import java.util.Set;

import de.hdm.team7.client.LoginInfo;

import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.appengine.api.users.UserServiceFactory;
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
import com.google.gwt.view.client.MultiSelectionModel;

import de.hdm.team7.client.ClientEinstellungen;
import de.hdm.team7.shared.StuecklistenVerwaltungAsync;
import de.hdm.team7.shared.geschaeftsobjekte.Baugruppe;
import de.hdm.team7.shared.geschaeftsobjekte.Bauteil;


/**
 * Formular für die Darstellung des selektierten Kunden Angelehnt an Thies &
 * Rathke
 */

public class BaugruppeFormular extends VerticalPanel {

	StuecklistenVerwaltungAsync stuecklistenVerwaltung = ClientEinstellungen
			.getStuecklistenVerwaltung();

	static CustomStackLayoutPanel cslp = null;
	static Baugruppe baugruppeDarstellung = null;
	static LoginInfo loginInfo = null;
	final SelectionModel selectionModel = new MultiSelectionModel<Baugruppe>(baugruppeDarstellung.KEY_PROVIDER);
	static Label letzterBearbeiter = new Label("Letzter Bearbeiter:");
	static Label aenderungsDatumLabel = new Label("Aenderungsdatum:");
	
	Set<Baugruppe> selektierteObjekte = null;
	/*
	 * Widgets, deren Inhalte variable sind, werden als Attribute angelegt.
	 */
	Label idValueLabel = new Label();
	static Label aenderungsValueLabel = new Label();
	static Label letzterBearbeiterLabel = new Label();
	Label fehlerLabelName = new Label("Bitte geben Sie einen Namen ein!");
	Label fehlerLabelMaterial = new Label("Bitte geben Sie ein Material ein!");
	Label fehlerLabelBeschreibung = new Label(
			"Bitte geben Sie eine Beschreibung ein!");
	
	TextBox nameTextBox = new TextBox();
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
	public BaugruppeFormular() {
		/**
		 * Das Grid-Widget erlaubt die Anordnung anderer Widgets in einem
		 * Gitter.
		 */
		Grid boGrid = new Grid(10, 2);
		this.add(boGrid);

		Label ueberschrift = new Label("Baugruppe Info");
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
		this.add(boButtonsPanel);

		newButton.addClickHandler(new NewClickHandler());
		boButtonsPanel.add(newButton);

		deleteButton.addClickHandler(new DeleteClickHandler());
		boButtonsPanel.add(deleteButton);

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
				stuecklistenVerwaltung.loescheBaugruppe(baugruppeDarstellung,
						new loescheBaugruppeCallback(baugruppeDarstellung));
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
//			if (nameTextBox.getValue() == null) {
//				fehlerLabelName.setVisible(true);
//			} else if (materialTextBox.getValue() == null) {
//				fehlerLabelMaterial.setVisible(true);
//			} else if (beschreibung.getValue() == null) {
//				fehlerLabelBeschreibung.setVisible(true);
//			} else {
//				baugruppeDarstellung.setName(nameTextBox.getText());
//				baugruppeDarstellung.setMaterialBezeichnung(materialTextBox
//						.getText());
//				baugruppeDarstellung.setDescription(beschreibung.getText());
////				baugruppeDarstellung.setLetzterBearbeiter(UserServiceFactory.getUserService().getCurrentUser().getEmail());
//				stuecklistenVerwaltung.erstelleBaugruppe(baugruppeDarstellung, null,
//						new erstelleBaugruppeCallback(baugruppeDarstellung));
//			}
//		}
//	}
	
	private class NewClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			ClientEinstellungen.getLogger().info("BaugruppeFormular: onClick Anfang");
			final Baugruppe temp = new Baugruppe();
			final String baugruppenname = nameTextBox.getText().toUpperCase()
					.trim();
			final String materialbezeichnung = materialTextBox.getText()
					.toUpperCase().trim();
			final String beschreibung1 = beschreibung.getText()
					.toUpperCase().trim();

			nameTextBox.setFocus(true);
			if (!baugruppenname.matches("^[0-9A-Z]{0,30}$")) {
				Window.alert("Bitte geben Sie etwas in das Feld (Name) ein und verwenden dabei nur Buchstaben und Zahlen.");
				    nameTextBox.selectAll();
			}
			if (!materialbezeichnung.matches("^[0-9A-Z]{0,30}$")) {
				Window.alert("Bitte geben Sie etwas in das Feld (Materialbezeichnung) ein und verwenden dabei nur Buchstaben und Zahlen.");
			    	materialTextBox.selectAll();
			} 
			if (!beschreibung1.matches("^[0-9A-Z]{0,30}$")) {
				Window.alert("Bitte geben Sie etwas in das Feld (Beschreibung) ein und verwenden dabei nur Buchstaben und Zahlen.");
			    	beschreibung.selectAll();
			}
			temp.setName(nameTextBox.getText());
			ClientEinstellungen.getLogger().info("BaugruppeFormular: Name gesetzt");
			ClientEinstellungen.getLogger().info("BaugruppeFormular: Temp: " + temp.getName() + ", " + temp.getClass());
			ClientEinstellungen.getLogger().info(
					"BaugruppenFormular: keine Namensdupletten gefunden");
			temp.setMaterialBezeichnung(materialTextBox.getText());
			ClientEinstellungen.getLogger().info(
					"BaugruppenFormular: Material gesetzt");
			temp.setDescription(beschreibung.getText());
			ClientEinstellungen.getLogger().info(
					"BaugruppenFormular: Beschreibung gesetzt");
//			temp.setLetzterBearbeiter(loginInfo.getEmailAddress());
//			ClientEinstellungen.getLogger().info(
//					"BaugruppeFormular: " + loginInfo.getEmailAddress());
			ClientEinstellungen.getLogger().info(
					"BaugruppeFormular: Felddaten setzen - abgeschlossen");
			selektierteObjekte = ((MultiSelectionModel<Baugruppe>) selectionModel).getSelectedSet();
			baugruppeDarstellung = temp;
			ArrayList<Baugruppe> tempList = new ArrayList<Baugruppe>();
			for (Baugruppe b : selektierteObjekte){
				tempList.add(b);
			}
			stuecklistenVerwaltung.erstelleBaugruppe(baugruppeDarstellung,
					new erstelleBaugruppeCallback(baugruppeDarstellung));
			cslp.ladeBaugruppeListNeu();
		}
	}
	private class EditClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			final String baugruppenname = nameTextBox.getText().toUpperCase().trim();
			final String materialbezeichnung = materialTextBox.getText().toUpperCase().trim();
			final String beschreibung1 = beschreibung.getText().toUpperCase().trim();
			if (nameTextBox.getValue() == null) {
				fehlerLabelName.setVisible(true);
			} else if (materialTextBox.getValue() == null) {
				fehlerLabelMaterial.setVisible(true);
			} else if (beschreibung.getValue() == null) {
				fehlerLabelBeschreibung.setVisible(true);
				
			} else if (!baugruppenname.matches("^[0-9A-Z]{1,30}$")) {
			    Window.alert("Bitte geben Sie etwas in das Feld (Name) ein und verwenden dabei nur Buchstaben und Zahlen.");
			    nameTextBox.selectAll();
			    return;
			}  else if (!materialbezeichnung.matches("^[0-9A-Z]{1,30}$")) {
			    Window.alert("Bitte geben Sie etwas in das Feld (Materialbezeichnung) ein und verwenden dabei nur Buchstaben und Zahlen.");
			    materialTextBox.selectAll();
			    return;
			}  else if (!beschreibung1.matches("^[0-9A-Z]{1,30}$")) {
			    Window.alert("Bitte geben Sie etwas in das Feld (Beschreibung) ein und verwenden dabei nur Buchstaben und Zahlen.");
			    beschreibung.selectAll();
			    return; 
			}  else {
				baugruppeDarstellung.setName(nameTextBox.getText());
				baugruppeDarstellung.setMaterialBezeichnung(materialTextBox
						.getText());
				baugruppeDarstellung.setDescription(beschreibung.getText());
//				baugruppeDarstellung.setLetzterBearbeiter(UserServiceFactory.getUserService().getCurrentUser().getEmail());
				stuecklistenVerwaltung.aktualisiereBaugruppe(baugruppeDarstellung, null,
						new bearbeiteBaugruppeCallback(baugruppeDarstellung));
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
		Window.alert("Die Baugruppe konnte nicht erstellt werden");
		}

		@Override
		public void onSuccess(String result) {
		Window.alert("Die Baugruppe wurde angelegt!");

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

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub

		}
	}
	
	public class bearbeiteBaugruppeCallback implements AsyncCallback<String> {

		Baugruppe compAss = null;

		bearbeiteBaugruppeCallback(Baugruppe compAss) {
			this.compAss = compAss;
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

//	public void setzeSelektiert(Baugruppe compAss) {
//		if (compAss != null) {
//			baugruppeDarstellung = compAss;
//			nameTextBox.setText(baugruppeDarstellung.getName());
//			idValueLabel.setText(Integer.toString(baugruppeDarstellung.getId()));
//			materialTextBox
//					.setText(baugruppeDarstellung.getMaterialBezeichnung());
//			beschreibung.setText(baugruppeDarstellung.getDescription());
//			aenderungsValueLabel.setText(baugruppeDarstellung
//					.getAenderungsDatum());
//			letzterBearbeiterLabel.setText(baugruppeDarstellung
//					.getLetzterBearbeiter());
//			
//			newButton.setVisible(false);
//			editButton.setVisible(true);
//			deleteButton.setVisible(true);
//			
//			
//		} else {
//			nameTextBox.setText("");
//			idValueLabel.setText("");
//			materialTextBox.setText("");
//			beschreibung.setText("");
//			aenderungsValueLabel.setText("");
//			letzterBearbeiterLabel.setText("");
//
//			newButton.setVisible(true);
//			editButton.setVisible(false);
//			deleteButton.setVisible(false);
//		}
//	}
	public void setzeSelektiert(Baugruppe comp, LoginInfo loginInfo) {
		if (comp != null) {
			baugruppeDarstellung = comp;
			BaugruppeFormular.loginInfo = loginInfo;
			nameTextBox.setText(baugruppeDarstellung.getName());
			idValueLabel.setText(Integer.toString(baugruppeDarstellung.getId()));
			materialTextBox
					.setText(baugruppeDarstellung.getMaterialBezeichnung());
			beschreibung.setText(baugruppeDarstellung.getDescription());
			aenderungsValueLabel.setText(baugruppeDarstellung
					.getAenderungsDatum());
			letzterBearbeiterLabel.setText(baugruppeDarstellung
					.getLetzterBearbeiter());

			newButton.setVisible(false);
			editButton.setVisible(true);
			deleteButton.setVisible(true);
		} else {
			BaugruppeFormular.loginInfo = loginInfo;
			nameTextBox.setText("");
			idValueLabel.setText("");
			materialTextBox.setText("");
			beschreibung.setText("");
			aenderungsValueLabel.setVisible(false);
			aenderungsDatumLabel.setVisible(false);
			letzterBearbeiterLabel.setVisible(false);
			letzterBearbeiter.setVisible(false);

			newButton.setVisible(true);
			editButton.setVisible(false);
			deleteButton.setVisible(false);
		}
	}
	public void setzeCustomStackLayoutPanel(CustomStackLayoutPanel cslp){
		  BaugruppeFormular.cslp = cslp;
	  }
}

