package de.hdm.team7.client.gui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DefaultDateTimeFormatInfo;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.TreeViewModel;

import de.hdm.team7.client.ClientEinstellungen;
import de.hdm.team7.shared.StuecklistenVerwaltungAsync;
import de.hdm.team7.shared.geschaeftsobjekte.Baugruppe;
import de.hdm.team7.shared.geschaeftsobjekte.Bauteil;
import de.hdm.team7.shared.geschaeftsobjekte.Enderzeugnis;
import de.hdm.team7.shared.geschaeftsobjekte.Stueckliste;

/**
 * Formular f�r die Darstellung des selektierten Kunden Angelehnt an Thies &
 * Rathke
 */

public class StuecklistenFormular extends VerticalPanel {
	
	StuecklistenVerwaltungAsync stuecklistenVerwaltung = ClientEinstellungen.getStuecklistenVerwaltung();

	static Stueckliste stuecklistenDarstellung = null;

	/*
	 * Widgets, deren Inhalte variable sind, werden als Attribute angelegt.
	 */
	static Label idValueLabel = new Label();
	static Label aenderungsValueLabel = new Label();
	static Label wurzelKomponenteValueLabel = new Label();
	static Label komponenteLabel = new Label("Derzeitige Komponente:");
	static Label letzterBearbeiterLabel = new Label();
	Label fehlerLabelName = new Label("Bitte geben Sie einen Namen ein!");
	
	static TextBox nameTextBox = new TextBox();
	
	static ListBox komponenteListBox = new ListBox();
	
	static Button newButton = new Button("Erstellen");
	static Button editButton = new Button("Bearbeiten");
	static Button deleteButton = new Button("Loeschen");
	
	static String pattern = "yyyy-MM-dd";
	DefaultDateTimeFormatInfo info = new DefaultDateTimeFormatInfo();
	DateTimeFormat dtf = new DateTimeFormat(pattern, info) {};  // <= trick here

	
//	private ListDataProvider<Bauteil> dataProvider = new ListDataProvider<Bauteil>();
//	// Create a CellTable.
//	CellTable<Bauteil> cellTable = new CellTable<Bauteil>();
//	ListHandler<Bauteil> sortHandler = new ListHandler<Bauteil>(
//			dataProvider.getList());
//	// Add a selection model so we can select cells.
//	final SelectionModel selectionModel = new MultiSelectionModel<Bauteil>(bauteilDarstellung.KEY_PROVIDER);
//	
	
    // Create a model for the tree.
    TreeViewModel model = new CustomTreeModel();
    CellTree tree = new CellTree(model, "Item 1");
	
	
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
		
		HorizontalPanel hPanel = new HorizontalPanel();
		
		Grid boGrid = new Grid(8, 2);
		hPanel.add(boGrid);
//		this.add(hPanel);

		Label ueberschrift = new Label("Stueckliste Info");
		boGrid.setWidget(0, 0, ueberschrift);

		Label idLabel = new Label("ID:");
		boGrid.setWidget(1, 0, idLabel);
		boGrid.setWidget(1, 1, idValueLabel);

		Label nameLabel = new Label("Name:");
		boGrid.setWidget(2, 0, nameLabel);
		boGrid.setWidget(2, 1, nameTextBox);
		boGrid.setWidget(3, 1, fehlerLabelName);
		fehlerLabelName.setVisible(false);
		
		Label komponenteAuswahlLabel = new Label("Wurzelkomponente:");
		boGrid.setWidget(4, 0, komponenteAuswahlLabel);
		boGrid.setWidget(4, 1, komponenteListBox);
		boGrid.setWidget(5, 0, komponenteLabel);
		boGrid.setWidget(5, 1, wurzelKomponenteValueLabel);
		
		ladeBaugruppenInKomponenteListBox();
		
		Label aenderungsDatumLabel = new Label("Erstellungsdatum:");
		boGrid.setWidget(6, 0, aenderungsDatumLabel);
		boGrid.setWidget(6, 1, aenderungsValueLabel);

		Label letzterBearbeiter = new Label("Letzter Bearbeiter:");
		boGrid.setWidget(7, 0, letzterBearbeiter);
		boGrid.setWidget(7, 1, letzterBearbeiterLabel);

		HorizontalPanel boButtonsPanel = new HorizontalPanel();

		newButton.addClickHandler(new NewClickHandler());
		boButtonsPanel.add(newButton);

		deleteButton.addClickHandler(new DeleteClickHandler());
		boButtonsPanel.add(deleteButton);

		editButton.addClickHandler(new EditClickHandler());
		boButtonsPanel.add(editButton);
		
		ladeEnderzeugnisInKomponenteListBox();
		

		VerticalPanel vPanel = new VerticalPanel();
		
		Label cellTableBeschreibung = new Label("Bitte waehlen Sie hier die Oberkomponenten aus, denen das aktuell selektierte Bauteil untergeordnet werden soll:");
		cellTableBeschreibung.setWordWrap(true);
		cellTableBeschreibung.setWidth("50%");
		vPanel.add(cellTableBeschreibung);
		
	    // Create a model for the tree.
	    TreeViewModel model = new CustomTreeModel();
	    CellTree cellTree = new CellTree(model, null);
		
		vPanel.add(cellTree);
		hPanel.add(vPanel);
		this.add(hPanel);
		this.add(boButtonsPanel);
	}

	/*
	 * Click handlers und abh�ngige AsyncCallback Klassen.
	 */

	/**
	 * Zum L�schen eines Kontos wird zun�chst der Eigent�mer abgefragt, bevor im
	 * Callback eine L�schung durchgef�hrt wird.
	 * 
	 */
	private class DeleteClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
				stuecklistenVerwaltung.loescheStueckliste(stuecklistenDarstellung,
						new loescheStuecklisteCallback(stuecklistenDarstellung));
		}
	}

	/**
	 * Ein neues Objekt wird erzeugt.
	 * 
	 */
	private class NewClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			if (nameTextBox.getValue() == null) {
				fehlerLabelName.setVisible(true);
			} else {
				stuecklistenDarstellung.setName(nameTextBox.getText());
				Date aktuell = new Date();
				stuecklistenDarstellung.setErstellungsDatum(dtf.format(aktuell));
//				stuecklistenDarstellung.setLetzterBearbeiter(UserServiceFactory.getUserService().getCurrentUser().getEmail());
				stuecklistenVerwaltung.erstelleStueckliste(stuecklistenDarstellung, null,
						new ErstelleStuecklistenCallback(stuecklistenDarstellung));
			}
		}
	}
	
	private class EditClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			if (nameTextBox.getValue() == null) {
				fehlerLabelName.setVisible(true);
			} else {
				stuecklistenDarstellung.setName(nameTextBox.getText());
				stuecklistenVerwaltung.aktualisiereStueckliste(stuecklistenDarstellung, null,
						new bearbeiteStuecklisteCallback(stuecklistenDarstellung));
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

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub

		}
	}
	
	public class bearbeiteStuecklisteCallback implements AsyncCallback<String> {

		Stueckliste bom = null;

		bearbeiteStuecklisteCallback(Stueckliste bom) {
			this.bom = bom;
		}

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Bearbeiten der Stueckliste ist fehlgeschlagen!");
		}

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub

		}
	}

	public void setzeSelektiert(Stueckliste bom) {
		if (bom != null) {
			stuecklistenDarstellung = bom;
			ladeBaugruppenInKomponenteListBox();
			nameTextBox.setText(stuecklistenDarstellung.getName());
			idValueLabel.setText(Integer.toString(stuecklistenDarstellung
					.getId()));
			aenderungsValueLabel.setText(stuecklistenDarstellung
					.getErstellungsDatum());
			letzterBearbeiterLabel.setText(stuecklistenDarstellung
					.getLetzterBearbeiter());
			ladeEnderzeugnisInKomponenteListBox();
			komponenteLabel.setVisible(false);
			wurzelKomponenteValueLabel.setVisible(false);
			wurzelKomponenteValueLabel.setText(stuecklistenDarstellung.getWurzelKomponente().toStringList());
			
			newButton.setVisible(false);
			editButton.setVisible(true);
			deleteButton.setVisible(true);
		} else {
			nameTextBox.setText("");
			idValueLabel.setText("");
			aenderungsValueLabel.setText("");
			letzterBearbeiterLabel.setText("");
			komponenteLabel.setVisible(false);
			wurzelKomponenteValueLabel.setVisible(false);

			newButton.setVisible(true);
			editButton.setVisible(false);
			deleteButton.setVisible(false);
		}
	}
	
	private void ladeBaugruppenInKomponenteListBox(){
		stuecklistenVerwaltung.holeAlleBaugruppen(new AsyncCallback<ArrayList<Baugruppe>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ArrayList<Baugruppe> result) {
				for (Baugruppe b : result){
					komponenteListBox.addItem(b.toStringList());
				}
			}
			
		});
	}
	
	private void ladeEnderzeugnisInKomponenteListBox(){
		stuecklistenVerwaltung.holeAlleEnderzeugnisse(new AsyncCallback<ArrayList<Enderzeugnis>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			public void onSuccess(ArrayList<Enderzeugnis> result) {
				for (Enderzeugnis e : result){
					komponenteListBox.addItem(e.toStringList());
				}
			}
		});
	}
}