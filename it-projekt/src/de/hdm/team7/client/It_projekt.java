package de.hdm.team7.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.team7.client.gui.BaugruppeFormular;
import de.hdm.team7.client.gui.BauteilFormular;
import de.hdm.team7.client.gui.BenutzerFormular;
import de.hdm.team7.client.gui.EnderzeugnisFormular;
import de.hdm.team7.client.gui.StuecklistenFormular;
import de.hdm.team7.shared.geschaeftsobjekte.Baugruppe;
import de.hdm.team7.shared.geschaeftsobjekte.Bauteil;
import de.hdm.team7.shared.geschaeftsobjekte.Benutzer;
import de.hdm.team7.shared.geschaeftsobjekte.Enderzeugnis;
import de.hdm.team7.shared.geschaeftsobjekte.Stueckliste;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class It_projekt implements EntryPoint {

	@Override
	public void onModuleLoad() {

		final EnderzeugnisFormular ef = new EnderzeugnisFormular();
		final StuecklistenFormular sf = new StuecklistenFormular();
		final BaugruppeFormular bgf = new BaugruppeFormular();
		final BauteilFormular btf = new BauteilFormular();
		final BenutzerFormular bf = new BenutzerFormular();

		// Create a cell to render each value in the list.
		BauteilCell bauteilCell = new BauteilCell();
		BaugruppeCell baugruppeCell = new BaugruppeCell();
		EnderzeugnisCell enderzeugnisCell = new EnderzeugnisCell();
		StuecklisteCell stuecklisteCell = new StuecklisteCell();
		BenutzerCell benutzerCell = new BenutzerCell();

		// Create a CellList that uses the cell.
		final CellList<Bauteil> bauteilList = new CellList<Bauteil>(bauteilCell);
		final CellList<Baugruppe> baugruppeList = new CellList<Baugruppe>(baugruppeCell);
		final CellList<Enderzeugnis> enderzeugnisList = new CellList<Enderzeugnis>(enderzeugnisCell);
		final CellList<Stueckliste> stuecklisteList = new CellList<Stueckliste>(stuecklisteCell);
		final CellList<Benutzer> benutzerList = new CellList<Benutzer>(benutzerCell);

		// Add a selection model so we can select cells.
		final SingleSelectionModel<Bauteil> selectionModelBauteil = new SingleSelectionModel<Bauteil>();
		bauteilList.setSelectionModel(selectionModelBauteil);
		selectionModelBauteil
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						btf.setVisible(true);
						bgf.setVisible(false);
						ef.setVisible(false);
						sf.setVisible(false);
						bf.setVisible(false);
						btf.setzeSelektiert(selectionModelBauteil.getSelectedObject());
					}
				});
		final SingleSelectionModel<Baugruppe> selectionModelBaugruppe = new SingleSelectionModel<Baugruppe>();
		baugruppeList.setSelectionModel(selectionModelBaugruppe);
		selectionModelBaugruppe
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						btf.setVisible(false);
						bgf.setVisible(true);
						ef.setVisible(false);
						sf.setVisible(false);
						bf.setVisible(false);
						bgf.setzeSelektiert(selectionModelBaugruppe.getSelectedObject());
					}
				});
		final SingleSelectionModel<Enderzeugnis> selectionModelEnderzeugnis = new SingleSelectionModel<Enderzeugnis>();
		enderzeugnisList.setSelectionModel(selectionModelEnderzeugnis);
		selectionModelEnderzeugnis
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						btf.setVisible(false);
						bgf.setVisible(false);
						ef.setVisible(true);
						sf.setVisible(false);
						bf.setVisible(false);
						ef.setzeSelektiert(selectionModelEnderzeugnis.getSelectedObject());
					}
				});
		final SingleSelectionModel<Stueckliste> selectionModelStueckliste = new SingleSelectionModel<Stueckliste>();
		stuecklisteList.setSelectionModel(selectionModelStueckliste);
		selectionModelStueckliste
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						btf.setVisible(false);
						bgf.setVisible(false);
						ef.setVisible(false);
						sf.setVisible(true);
						bf.setVisible(false);
						sf.setzeSelektiert(selectionModelStueckliste.getSelectedObject());
					}
				});
		final SingleSelectionModel<Benutzer> selectionModelBenutzer = new SingleSelectionModel<Benutzer>();
		benutzerList.setSelectionModel(selectionModelBenutzer);
		selectionModelBenutzer
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						btf.setVisible(false);
						bgf.setVisible(false);
						ef.setVisible(false);
						sf.setVisible(false);
						bf.setVisible(true);
						bf.setzeSelektiert(selectionModelBenutzer.getSelectedObject());
					}
				});

		ClientEinstellungen.getStuecklistenVerwaltung().holeAlleBauteile(
				new AsyncCallback<ArrayList<Bauteil>>() {
					@Override
					public void onFailure(Throwable t) {
						ClientEinstellungen.getLogger().severe(
								"RPC HoleAlleBauteile Failure");
					}

					@Override
					public void onSuccess(ArrayList<Bauteil> bauteilListe) {
						ClientEinstellungen.getLogger().info(
								"RPC HoleAlleBauteile Success");
						String result = "";
						for (Bauteil b : bauteilListe) {
							result += b.toString();
						}
						// Set the total row count. This isn't strictly necessary, but it
						// affects
						// paging calculations, so its good habit to keep the row count up to
						// date.
						bauteilList.setRowCount(bauteilListe.size(), true);
						// Push the data into the widget.
						bauteilList.setRowData(0, bauteilListe);
						ClientEinstellungen.getLogger().info(result);
					}
				});

		ScrollPanel scrollPanelBauteil = new ScrollPanel();
		scrollPanelBauteil.add(bauteilList);
		
		ScrollPanel scrollPanelBaugruppe = new ScrollPanel();
		scrollPanelBaugruppe.add(baugruppeList);
		
		ScrollPanel scrollPanelEnderzeugnis = new ScrollPanel();
		scrollPanelEnderzeugnis.add(enderzeugnisList);
		
		ScrollPanel scrollPanelStueckliste = new ScrollPanel();
		scrollPanelStueckliste.add(stuecklisteList);
		
		ScrollPanel scrollPanelBenutzer = new ScrollPanel();
		scrollPanelBenutzer.add(benutzerList);

		// Create a stack panel containing three labels.
		StackLayoutPanel stackLayoutPanel = new StackLayoutPanel(Unit.EM);
		stackLayoutPanel.setPixelSize(400, 400);
		stackLayoutPanel.add(scrollPanelBauteil, "Bauteile", 3);
		stackLayoutPanel.add(baugruppeList, "Baugruppen", 3);
		stackLayoutPanel.add(enderzeugnisList, "Enderzeugnisse", 3);
		stackLayoutPanel.add(stuecklisteList, "Stuecklisten", 3);
		stackLayoutPanel.add(benutzerList, "Benutzer", 3);
		
		stackLayoutPanel.addSelectionHandler(new SelectionHandler<Integer>() {
		    @Override
		    public void onSelection(SelectionEvent<Integer> event) {
		    	if (event.getSelectedItem() == 0){
		    		ClientEinstellungen.getStuecklistenVerwaltung().holeAlleBauteile(
		    				new AsyncCallback<ArrayList<Bauteil>>() {
		    					@Override
		    					public void onFailure(Throwable t) {
		    						ClientEinstellungen.getLogger().severe(
		    								"RPC HoleAlleBauteile Failure");
		    					}

		    					@Override
		    					public void onSuccess(ArrayList<Bauteil> bauteilListe) {
		    						ClientEinstellungen.getLogger().info(
		    								"RPC HoleAlleBauteile Success");
		    						String result = "";
		    						for (Bauteil b : bauteilListe) {
		    							result += b.toString();
		    						}
		    						// Set the total row count. This isn't strictly necessary, but it
		    						// affects
		    						// paging calculations, so its good habit to keep the row count up to
		    						// date.
		    						bauteilList.setRowCount(bauteilListe.size(), true);
		    						// Push the data into the widget.
		    						bauteilList.setRowData(0, bauteilListe);
		    						ClientEinstellungen.getLogger().info(result);
		    					}
		    				});
		    	} else if (event.getSelectedItem() == 1){
		    		ClientEinstellungen.getStuecklistenVerwaltung().holeAlleBaugruppen(
		    				new AsyncCallback<ArrayList<Baugruppe>>() {
		    					@Override
		    					public void onFailure(Throwable t) {
		    						ClientEinstellungen.getLogger().severe(
		    								"RPC HoleAlleBaugruppen Failure");
		    					}

		    					@Override
		    					public void onSuccess(ArrayList<Baugruppe> baugruppeListe) {
		    						ClientEinstellungen.getLogger().info(
		    								"RPC HoleAlleBaugruppe Success");
		    						String result = "";
		    						for (Baugruppe b : baugruppeListe) {
		    							result += b.toString();
		    						}
		    						baugruppeList.setRowCount(baugruppeListe.size(), true);
		    						baugruppeList.setRowData(0, baugruppeListe);
		    						ClientEinstellungen.getLogger().info(result);
		    					}
		    				});
		    	} else if (event.getSelectedItem() == 2){
		    		ClientEinstellungen.getStuecklistenVerwaltung().holeAlleEnderzeugnisse(
		    				new AsyncCallback<ArrayList<Enderzeugnis>>() {
		    					@Override
		    					public void onFailure(Throwable t) {
		    						ClientEinstellungen.getLogger().severe(
		    								"RPC HoleAlleEnderzeugnisse Failure");
		    					}

		    					@Override
		    					public void onSuccess(ArrayList<Enderzeugnis> enderzeugnisListe) {
		    						ClientEinstellungen.getLogger().info(
		    								"RPC HoleAlleEnderzeugnisse Success");
		    						String result = "";
		    						for (Enderzeugnis b : enderzeugnisListe) {
		    							result += b.toString();
		    						}
		    						enderzeugnisList.setRowCount(enderzeugnisListe.size(), true);
		    						enderzeugnisList.setRowData(0, enderzeugnisListe);
		    						ClientEinstellungen.getLogger().info(result);
		    					}
		    				});
		    	} else if (event.getSelectedItem() == 3){
		    		ClientEinstellungen.getStuecklistenVerwaltung().holeAlleStuecklisten(
		    				new AsyncCallback<ArrayList<Stueckliste>>() {
		    					@Override
		    					public void onFailure(Throwable t) {
		    						ClientEinstellungen.getLogger().severe(
		    								"RPC HoleAlleStuecklisten Failure");
		    					}

		    					@Override
		    					public void onSuccess(ArrayList<Stueckliste> stuecklisteListe) {
		    						ClientEinstellungen.getLogger().info(
		    								"RPC HoleAlleStueckliste Success");
		    						String result = "";
		    						for (Stueckliste b : stuecklisteListe) {
		    							result += b.toString();
		    						}
		    						stuecklisteList.setRowCount(stuecklisteListe.size(), true);
		    						stuecklisteList.setRowData(0, stuecklisteListe);
		    						ClientEinstellungen.getLogger().info(result);
		    					}
		    				});
		    	} else if (event.getSelectedItem() == 4){
		    		ClientEinstellungen.getStuecklistenVerwaltung().holeAlleBenutzer(
		    				new AsyncCallback<ArrayList<Benutzer>>() {
		    					@Override
		    					public void onFailure(Throwable t) {
		    						ClientEinstellungen.getLogger().severe(
		    								"RPC HoleAlleBenutzer Failure");
		    					}

		    					@Override
		    					public void onSuccess(ArrayList<Benutzer> benutzerListe) {
		    						ClientEinstellungen.getLogger().info(
		    								"RPC HoleAlleBenutzer Success");
		    						String result = "";
		    						for (Benutzer b : benutzerListe) {
		    							result += b.toString();
		    						}
		    						benutzerList.setRowCount(benutzerListe.size(), true);
		    						benutzerList.setRowData(0, benutzerListe);
		    						ClientEinstellungen.getLogger().info(result);
		    					}
		    				});
		    	}

//		        int selectedWidgetIndex = stackPanel.getVisibleIndex());
		    }
		});

		VerticalPanel detailsPanel = new VerticalPanel();
		detailsPanel.add(btf);
		detailsPanel.add(bgf);
		detailsPanel.add(ef);
		detailsPanel.add(sf);
		detailsPanel.add(bf);

		btf.setVisible(false);
		bgf.setVisible(false);
		ef.setVisible(false);
		sf.setVisible(false);
		bf.setVisible(false);
		
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.add(stackLayoutPanel);
		hPanel.add(detailsPanel);

		// Add it to the root panel.
//		RootPanel.get("Navigator").add(stackPanel);
//		RootPanel.get("Details").add(detailsPanel);
		RootPanel.get("Container").add(hPanel);

	}
}
