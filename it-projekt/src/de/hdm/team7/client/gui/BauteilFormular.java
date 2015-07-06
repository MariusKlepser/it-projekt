package de.hdm.team7.client.gui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel;

import de.hdm.team7.client.ClientEinstellungen;
import de.hdm.team7.client.LoginInfo;
import de.hdm.team7.shared.StuecklistenVerwaltungAsync;
import de.hdm.team7.shared.geschaeftsobjekte.Baugruppe;
import de.hdm.team7.shared.geschaeftsobjekte.Bauteil;
import de.hdm.team7.shared.geschaeftsobjekte.Enderzeugnis;
import de.hdm.team7.shared.geschaeftsobjekte.Geschaeftsobjekt;

/**
 * Formular für die Darstellung des selektierten Kunden Angelehnt an Thies &
 * Rathke
 */

public class BauteilFormular extends VerticalPanel {

	StuecklistenVerwaltungAsync stuecklistenVerwaltung = ClientEinstellungen
			.getStuecklistenVerwaltung();
	
	static CustomStackLayoutPanel cslp = null;
	static Label statusLabel = null;

	static Bauteil bauteilDarstellung = null;
	static LoginInfo loginInfo = null;

	/*
	 * Widgets, deren Inhalte variable sind, werden als Attribute angelegt.
	 */
	static Label idValueLabel = new Label();
	static Label aenderungsValueLabel = new Label();
	static Label letzterBearbeiterLabel = new Label();
	static Label aenderungsDatumLabel = new Label("Aenderungsdatum:");
	static Label letzterBearbeiter = new Label("Letzter Bearbeiter:");
	
	Label fehlerLabelName = new Label("Bitte geben Sie einen Namen ein!");
	Label fehlerLabelMaterial = new Label("Bitte geben Sie ein Material ein!");
	Label fehlerLabelBeschreibung = new Label(
			"Bitte geben Sie eine Beschreibung ein!");

	static TextBox nameTextBox = new TextBox();
	static TextBox materialTextBox = new TextBox();
	static TextArea beschreibung = new TextArea();

	static Button newButton = new Button("Erstellen");
	static Button editButton = new Button("Aktualisieren");
	static Button deleteButton = new Button("Loeschen");
	
	Set<Bauteil> selektierteObjekte = null;

	private ListDataProvider<Bauteil> dataProvider = new ListDataProvider<Bauteil>();
	// Create a CellTable.
	CellTable<Bauteil> cellTable = new CellTable<Bauteil>();
	ListHandler<Bauteil> sortHandler = new ListHandler<Bauteil>(
			dataProvider.getList());
	// Add a selection model so we can select cells.
	final SelectionModel selectionModel = new MultiSelectionModel<Bauteil>(bauteilDarstellung.KEY_PROVIDER);
	
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

		HorizontalPanel hPanel = new HorizontalPanel();
		
		Grid boGrid = new Grid(10, 3);
		hPanel.add(boGrid);

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

		boGrid.setWidget(8, 0, aenderungsDatumLabel);
		boGrid.setWidget(8, 1, aenderungsValueLabel);

		boGrid.setWidget(9, 0, letzterBearbeiter);
		boGrid.setWidget(9, 1, letzterBearbeiterLabel);

		HorizontalPanel boButtonsPanel = new HorizontalPanel();

		newButton.addClickHandler(new NewClickHandler());
		boButtonsPanel.add(newButton);

		deleteButton.addClickHandler(new DeleteClickHandler());
		boButtonsPanel.add(deleteButton);

		editButton.addClickHandler(new EditClickHandler());
		boButtonsPanel.add(editButton);
		
		Button zeigeObjekte = new Button();
		zeigeObjekte.addClickHandler(new ZeigeObjekteClickHandler());
		boButtonsPanel.add(zeigeObjekte);

		cellTable.setWidth("55%", true);

		// Do not refresh the headers and footers every time the data is
		// updated.
		cellTable.setAutoHeaderRefreshDisabled(true);
		
//		stuecklistenVerwaltung.holeAlleBaugruppen(new AsyncCallback<ArrayList<Baugruppe>>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void onSuccess(ArrayList<Baugruppe> result) {
//				for (Baugruppe b : result){
//					zuordnungsListe.add(b);
//				}
//				
//			}
//		});
		
		stuecklistenVerwaltung.holeAlleBauteile(new AsyncCallback<ArrayList<Bauteil>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ArrayList<Bauteil> result) {
				for (Bauteil b : result){
					dataProvider.getList().add(b);
				}
				
			}
		});

		// Attach a column sort handler to the ListDataProvider to sort the
		// list.
		cellTable.addColumnSortHandler(sortHandler);
		cellTable.setSelectionModel(selectionModel,
				DefaultSelectionEventManager.<Bauteil> createCheckboxManager());

		// Initialize the columns.
		initTableColumns(selectionModel, sortHandler);

		// Add the CellList to the adapter in the database.
		addDataDisplay(cellTable);
		
//		stuecklistenVerwaltung.holeAlleEnderzeugnisse(new AsyncCallback<ArrayList<Enderzeugnis>>(){
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			public void onSuccess(ArrayList<Enderzeugnis> result) {
//				for (Enderzeugnis e : result){
//					zuordnungsListe.add(e);
//				}
//			}
//			
//		});
		
		VerticalPanel vPanel = new VerticalPanel();
		Label cellTableBeschreibung = new Label("Bitte waehlen Sie hier die Oberkomponenten aus, denen das aktuell selektierte Bauteil untergeordnet werden soll:");
		cellTableBeschreibung.setWordWrap(true);
		cellTableBeschreibung.setWidth("50%");
		vPanel.add(cellTableBeschreibung);
//		ScrollPanel sPanel = new ScrollPanel();
//		sPanel.add(cellTable);
		
		// Create a Pager to control the table.
	    SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
	    SimplePager pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
	    pager.setDisplay(cellTable);
		
		vPanel.add(cellTable);
		vPanel.add(pager);
		hPanel.add(vPanel);
		this.add(hPanel);
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
	
	private class ZeigeObjekteClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			selektierteObjekte = ((MultiSelectionModel<Bauteil>) selectionModel).getSelectedSet();
			for (Bauteil b : selektierteObjekte){
				ClientEinstellungen.getLogger().info("Name: " + b.getName() + ", Menge: " + b.getMenge() + ", Objekt: " + b.getClass());
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
			ClientEinstellungen.getLogger().info("BauteilFormular: onClick Anfang");
			final Bauteil temp = new Bauteil();
			final String bauteilname = nameTextBox.getText().toUpperCase()
					.trim();
			final String materialbezeichnung = materialTextBox.getText()
					.toUpperCase().trim();
			final String beschreibung1 = beschreibung.getText()
					.toUpperCase().trim();
			nameTextBox.setFocus(true);
			if (!bauteilname.matches("^[0-9A-Z ]{0,30}$")) {
				Window.alert("Bitte geben Sie etwas in das Feld (Name) ein und verwenden dabei nur Buchstaben und Zahlen.");
				    nameTextBox.selectAll();
			}
			if (!materialbezeichnung.matches("^[0-9A-Z ]{0,30}$")) {
				Window.alert("Bitte geben Sie etwas in das Feld (Materialbezeichnung) ein und verwenden dabei nur Buchstaben und Zahlen.");
			    	materialTextBox.selectAll();
			} 
			if (!beschreibung1.matches("^[0-9A-Z ]{0,30}$")) {
				Window.alert("Bitte geben Sie etwas in das Feld (Beschreibung) ein und verwenden dabei nur Buchstaben und Zahlen.");
			    	beschreibung.selectAll();
			}
			temp.setName(nameTextBox.getText());
			ClientEinstellungen.getLogger().info("BauteilFormular: Name gesetzt");
			ClientEinstellungen.getLogger().info("BauteilFormular: Temp: " + temp.getName() + ", " + temp.getClass());
			ClientEinstellungen.getLogger().info(
					"BauteilFormular: keine Namensdupletten gefunden");
			temp.setMaterialBezeichnung(materialTextBox.getText());
			ClientEinstellungen.getLogger().info(
					"BauteilFormular: Material gesetzt");
			temp.setDescription(beschreibung.getText());
			ClientEinstellungen.getLogger().info(
					"BauteilFormular: Beschreibung gesetzt");
			temp.setLetzterBearbeiter(loginInfo.getEmailAddress());
			ClientEinstellungen.getLogger().info(
					"BauteilFormular: " + loginInfo.getEmailAddress());
			ClientEinstellungen.getLogger().info(
					"BauteilFormular: Felddaten setzen - abgeschlossen");
			selektierteObjekte = ((MultiSelectionModel<Bauteil>) selectionModel).getSelectedSet();
			bauteilDarstellung = temp;
			ArrayList<Bauteil> tempList = new ArrayList<Bauteil>();
			for (Bauteil b : selektierteObjekte){
				tempList.add(b);
			}
			stuecklistenVerwaltung.erstelleBauteil(bauteilDarstellung, tempList,
					new erstelleBauteilCallback(bauteilDarstellung));
			cslp.ladeBauteilListNeu();
		}
	}

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
				bauteilDarstellung.setLetzterBearbeiter(loginInfo.getEmailAddress());	
				Set<Bauteil> selektierteObjekte = ((MultiSelectionModel<Bauteil>) selectionModel).getSelectedSet();
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
			ClientEinstellungen.getLogger().severe("Fehler: " + caught);
			statusLabel.setText("Das Erstellen des Bauteils ist fehlgeschlagen!");
		}

		@Override
		public void onSuccess(String result) {
			ClientEinstellungen.getLogger().info(result);
			statusLabel.setText("Das Bauteil wurde erstellt!");
		}
	}

	public class loescheBauteilCallback implements AsyncCallback<String> {

		Bauteil comp = null;

		loescheBauteilCallback(Bauteil comp) {
			this.comp = comp;
		}

		@Override
		public void onFailure(Throwable caught) {
			statusLabel.setText("Das Löschen des Bauteils ist fehlgeschlagen!");
		}

		@Override
		public void onSuccess(String result) {
			statusLabel.setText("Das Bauteil wurde geloescht");

		}
	}

	public class bearbeiteBauteilCallback implements AsyncCallback<String> {

		Bauteil comp = null;

		bearbeiteBauteilCallback(Bauteil comp) {
			this.comp = comp;
		}

		@Override
		public void onFailure(Throwable caught) {
			statusLabel.setText("Das Bearbeiten des Bauteils ist fehlgeschlagen!");
		}

		@Override
		public void onSuccess(String result) {
			statusLabel.setText("Das Bauteil wurde geaendert");
		}
	}

	public void setzeSelektiert(Bauteil comp, LoginInfo loginInfo) {
		if (comp != null) {
			bauteilDarstellung = comp;
			BauteilFormular.loginInfo = loginInfo;
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
			BauteilFormular.loginInfo = loginInfo;
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

	/**
	 * Add a display to the database. The current range of interest of the
	 * display will be populated with data.
	 * 
	 * @param display
	 *            a {@Link HasData}.
	 */
	public void addDataDisplay(HasData<Bauteil> display) {
		dataProvider.addDataDisplay(display);
	}

	/**
	 * Refresh all displays.
	 */
	public void refreshDisplays() {
		dataProvider.refresh();
	}
	
	 /**
	   * Add the columns to the table.
	   */
	  private void initTableColumns(
	      final SelectionModel<Bauteil> selectionModel,
	      ListHandler<Bauteil> sortHandler) {
	    // Checkbox column. This table will uses a checkbox column for selection.
	    // Alternatively, you can call cellTable.setSelectionEnabled(true) to enable
	    // mouse selection.
	    Column<Bauteil, Boolean> checkColumn = new Column<Bauteil, Boolean>(
	        new CheckboxCell(true, false)) {
	      @Override
	      public Boolean getValue(Bauteil object) {
	        // Get the value from the selection model.
	        return selectionModel.isSelected(object);
	      }
	    };
	    cellTable.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
	    cellTable.setColumnWidth(checkColumn, 40, Unit.PX);

	    // Name des Geschaeftsobjekts.
	    Column<Bauteil, String> nameColumn = new Column<Bauteil, String>(
	        new TextCell()) {
	      @Override
	      public String getValue(Bauteil object) {
	        return object.getName();
	      }
	    };
	    nameColumn.setSortable(true);
	    nameColumn.setDefaultSortAscending(true);
	    sortHandler.setComparator(nameColumn, new Comparator<Bauteil>() {
	      @Override
	      public int compare(Bauteil o1, Bauteil o2) {
	        return o1.getName().compareTo(o2.getName());
	      }
	    });
	    cellTable.addColumn(nameColumn, "Name");
	    cellTable.setColumnWidth(nameColumn, 60, Unit.PCT);
	    
	    // Typ des Geschaeftsobjekts.
	    Column<Bauteil, String> typeColumn = new Column<Bauteil, String>(
	        new TextCell()) {
	      @Override
	      public String getValue(Bauteil object) {
	    	  if (object instanceof Bauteil){
	    		  return "Bauteil";
	    	  }
	    	  else {
	    		  return "Enderzeugnis";
	    	  }
	        
	      }
	    };
	    typeColumn.setSortable(false);
	    cellTable.addColumn(typeColumn, "Typ");
	    cellTable.setColumnWidth(typeColumn, 25, Unit.PCT);

	    // Menge des Geschaeftsobjekts.
	    Column<Bauteil, String> mengeColumn = new Column<Bauteil, String>(
	        new EditTextCell()) {
	      @Override
	      public String getValue(Bauteil object) {
	        return object.getMenge();
	      }
	    };
	    mengeColumn.setSortable(false);
	    cellTable.addColumn(mengeColumn, "Menge");
	    mengeColumn.setFieldUpdater(new FieldUpdater<Bauteil, String>() {
	      @Override
	      public void update(int index, Bauteil object, String value) {
	        // Called when the user changes the value.
	        object.setMenge(value);
	        refreshDisplays();
	      }
	    });
	    cellTable.setColumnWidth(mengeColumn, 15, Unit.PCT);
	  }
	  
	  public void setzeCustomStackLayoutPanel(CustomStackLayoutPanel cslp){
		  BauteilFormular.cslp = cslp;
	  }
	  
	  public void setzeStatusLabel(Label statsLabel){
		  BauteilFormular.statusLabel = statsLabel;
	  }

}
