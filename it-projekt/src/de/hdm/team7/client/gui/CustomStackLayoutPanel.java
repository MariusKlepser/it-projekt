package de.hdm.team7.client.gui;

import java.util.ArrayList;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.team7.client.BaugruppeCell;
import de.hdm.team7.client.BauteilCell;
import de.hdm.team7.client.BenutzerCell;
import de.hdm.team7.client.ClientEinstellungen;
import de.hdm.team7.client.EnderzeugnisCell;
import de.hdm.team7.client.LoginInfo;
import de.hdm.team7.client.StuecklisteCell;
import de.hdm.team7.shared.geschaeftsobjekte.Baugruppe;
import de.hdm.team7.shared.geschaeftsobjekte.Bauteil;
import de.hdm.team7.shared.geschaeftsobjekte.Benutzer;
import de.hdm.team7.shared.geschaeftsobjekte.Enderzeugnis;
import de.hdm.team7.shared.geschaeftsobjekte.Stueckliste;

public class CustomStackLayoutPanel extends StackLayoutPanel {

	// Create a cell to render each value in the list.
	BauteilCell bauteilCell = new BauteilCell();
	BaugruppeCell baugruppeCell = new BaugruppeCell();
	EnderzeugnisCell enderzeugnisCell = new EnderzeugnisCell();
	StuecklisteCell stuecklisteCell = new StuecklisteCell();
	BenutzerCell benutzerCell = new BenutzerCell();

	// Create a CellList that uses the cell.
	final CellList<Bauteil> bauteilList = new CellList<Bauteil>(bauteilCell);
	final CellList<Baugruppe> baugruppeList = new CellList<Baugruppe>(
			baugruppeCell);
	final CellList<Enderzeugnis> enderzeugnisList = new CellList<Enderzeugnis>(
			enderzeugnisCell);
	final CellList<Stueckliste> stuecklisteList = new CellList<Stueckliste>(
			stuecklisteCell);
	final CellList<Benutzer> benutzerList = new CellList<Benutzer>(benutzerCell);

	public CustomStackLayoutPanel(Unit unit, EnderzeugnisFormular efr, StuecklistenFormular sfr, BaugruppeFormular bgfr, BauteilFormular btfr, LoginInfo loginInfoP) {
		super(unit);

		final EnderzeugnisFormular ef = efr;
		final StuecklistenFormular sf = sfr;
		final BaugruppeFormular bgf = bgfr;
		final BauteilFormular btf = btfr;
		
		final LoginInfo loginInfo = loginInfoP;
		
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
						btf.setzeSelektiert(
								selectionModelBauteil.getSelectedObject(),
								loginInfo);
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
//						 bgf.setzeSelektiert(selectionModelBaugruppe
//						 .getSelectedObject(), loginInfo);
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
						// ef.setzeSelektiert(selectionModelEnderzeugnis
						// .getSelectedObject(), loginInfo);
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
						// sf.setzeSelektiert(selectionModelStueckliste
						// .getSelectedObject(), loginInfo);
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

		this.add(scrollPanelBauteil, "Bauteile", 3);
		this.add(scrollPanelBaugruppe, "Baugruppen", 3);
		this.add(scrollPanelEnderzeugnis, "Enderzeugnisse", 3);
		this.add(scrollPanelStueckliste, "Stuecklisten", 3);

		this.addSelectionHandler(new SelectionHandler<Integer>() {
			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				if (event.getSelectedItem() == 0) {
					ClientEinstellungen.getStuecklistenVerwaltung()
							.holeAlleBauteile(
									new AsyncCallback<ArrayList<Bauteil>>() {
										@Override
										public void onFailure(Throwable t) {
											ClientEinstellungen
													.getLogger()
													.severe("RPC HoleAlleBauteile Failure");
										}

										@Override
										public void onSuccess(
												ArrayList<Bauteil> bauteilListe) {
											ClientEinstellungen
													.getLogger()
													.info("RPC HoleAlleBauteile Success");
											String result = "";
											for (Bauteil b : bauteilListe) {
												result += b.toString();
											}
											// Push the data into the widget.
											bauteilList.setRowData(0,
													bauteilListe);
											ClientEinstellungen.getLogger()
													.info(result);
										}
									});
				} else if (event.getSelectedItem() == 1) {
					ClientEinstellungen.getStuecklistenVerwaltung()
							.holeAlleBaugruppen(
									new AsyncCallback<ArrayList<Baugruppe>>() {
										@Override
										public void onFailure(Throwable t) {
											ClientEinstellungen
													.getLogger()
													.severe("RPC HoleAlleBaugruppen Failure");
										}

										@Override
										public void onSuccess(
												ArrayList<Baugruppe> baugruppeListe) {
											ClientEinstellungen
													.getLogger()
													.info("RPC HoleAlleBaugruppe Success");
											String result = "";
											for (Baugruppe b : baugruppeListe) {
												result += b.toString();
											}
											baugruppeList.setRowData(0,
													baugruppeListe);
											ClientEinstellungen.getLogger()
													.info(result);
										}
									});
				} else if (event.getSelectedItem() == 2) {
					ClientEinstellungen
							.getStuecklistenVerwaltung()
							.holeAlleEnderzeugnisse(
									new AsyncCallback<ArrayList<Enderzeugnis>>() {
										@Override
										public void onFailure(Throwable t) {
											ClientEinstellungen
													.getLogger()
													.severe("RPC HoleAlleEnderzeugnisse Failure");
										}

										@Override
										public void onSuccess(
												ArrayList<Enderzeugnis> enderzeugnisListe) {
											ClientEinstellungen
													.getLogger()
													.info("RPC HoleAlleEnderzeugnisse Success");
											String result = "";
											for (Enderzeugnis b : enderzeugnisListe) {
												result += b.toString();
											}
											enderzeugnisList.setRowData(0,
													enderzeugnisListe);
											ClientEinstellungen.getLogger()
													.info(result);
										}
									});
				} else if (event.getSelectedItem() == 3) {
					ClientEinstellungen
							.getStuecklistenVerwaltung()
							.holeAlleStuecklisten(
									new AsyncCallback<ArrayList<Stueckliste>>() {
										@Override
										public void onFailure(Throwable t) {
											ClientEinstellungen
													.getLogger()
													.severe("RPC HoleAlleStuecklisten Failure");
										}

										@Override
										public void onSuccess(
												ArrayList<Stueckliste> stuecklisteListe) {
											ClientEinstellungen
													.getLogger()
													.info("RPC HoleAlleStueckliste Success");
											String result = "";
											for (Stueckliste b : stuecklisteListe) {
												result += b.toString();
											}
											stuecklisteList.setRowData(0,
													stuecklisteListe);
											ClientEinstellungen.getLogger()
													.info(result);
										}
									});
				} else if (event.getSelectedItem() == 4) {
					ClientEinstellungen.getStuecklistenVerwaltung()
							.holeAlleBenutzer(
									new AsyncCallback<ArrayList<Benutzer>>() {
										@Override
										public void onFailure(Throwable t) {
											ClientEinstellungen
													.getLogger()
													.severe("RPC HoleAlleBenutzer Failure");
										}

										@Override
										public void onSuccess(
												ArrayList<Benutzer> benutzerListe) {
											ClientEinstellungen
													.getLogger()
													.info("RPC HoleAlleBenutzer Success");
											String result = "";
											for (Benutzer b : benutzerListe) {
												result += b.toString();
											}
											benutzerList.setRowData(0,
													benutzerListe);
											ClientEinstellungen.getLogger()
													.info(result);
										}
									});
				}
			}
		});
	}
	
	public void ladeBauteilListNeu(){
		ClientEinstellungen.getStuecklistenVerwaltung()
		.holeAlleBauteile(
				new AsyncCallback<ArrayList<Bauteil>>() {
					@Override
					public void onFailure(Throwable t) {
						ClientEinstellungen
								.getLogger()
								.severe("RPC HoleAlleBauteile Failure");
					}

					@Override
					public void onSuccess(
							ArrayList<Bauteil> bauteilListe) {
						ClientEinstellungen
								.getLogger()
								.info("RPC HoleAlleBauteile Success");
						String result = "";
						for (Bauteil b : bauteilListe) {
							result += b.toString();
						}
						// Push the data into the widget.
						bauteilList.setRowData(0,
								bauteilListe);
						ClientEinstellungen.getLogger()
								.info(result);
					}
				});
	}
	
	public void ladeBaugruppeListNeu(){
		ClientEinstellungen.getStuecklistenVerwaltung()
		.holeAlleBaugruppen(
				new AsyncCallback<ArrayList<Baugruppe>>() {
					@Override
					public void onFailure(Throwable t) {
						ClientEinstellungen
								.getLogger()
								.severe("RPC HoleAlleBaugruppen Failure");
					}

					@Override
					public void onSuccess(
							ArrayList<Baugruppe> baugruppeListe) {
						ClientEinstellungen
								.getLogger()
								.info("RPC HoleAlleBaugruppe Success");
						String result = "";
						for (Baugruppe b : baugruppeListe) {
							result += b.toString();
						}
						baugruppeList.setRowData(0,
								baugruppeListe);
						ClientEinstellungen.getLogger()
								.info(result);
					}
				});
	}
	
	public void ladeEnderzeugnisListNeu(){
		ClientEinstellungen
		.getStuecklistenVerwaltung()
		.holeAlleEnderzeugnisse(
				new AsyncCallback<ArrayList<Enderzeugnis>>() {
					@Override
					public void onFailure(Throwable t) {
						ClientEinstellungen
								.getLogger()
								.severe("RPC HoleAlleEnderzeugnisse Failure");
					}

					@Override
					public void onSuccess(
							ArrayList<Enderzeugnis> enderzeugnisListe) {
						ClientEinstellungen
								.getLogger()
								.info("RPC HoleAlleEnderzeugnisse Success");
						String result = "";
						for (Enderzeugnis b : enderzeugnisListe) {
							result += b.toString();
						}
						enderzeugnisList.setRowData(0,
								enderzeugnisListe);
						ClientEinstellungen.getLogger()
								.info(result);
					}
				});
	}
	
	public void ladeStuecklisteListNeu(){
		ClientEinstellungen
		.getStuecklistenVerwaltung()
		.holeAlleStuecklisten(
				new AsyncCallback<ArrayList<Stueckliste>>() {
					@Override
					public void onFailure(Throwable t) {
						ClientEinstellungen
								.getLogger()
								.severe("RPC HoleAlleStuecklisten Failure");
					}

					@Override
					public void onSuccess(
							ArrayList<Stueckliste> stuecklisteListe) {
						ClientEinstellungen
								.getLogger()
								.info("RPC HoleAlleStueckliste Success");
						String result = "";
						for (Stueckliste b : stuecklisteListe) {
							result += b.toString();
						}
						stuecklisteList.setRowData(0,
								stuecklisteListe);
						ClientEinstellungen.getLogger()
								.info(result);
					}
				});
	}

	public CellList<Bauteil> getBauteilList() {
		return bauteilList;
	}

	public CellList<Baugruppe> getBaugruppeList() {
		return baugruppeList;
	}

	public CellList<Enderzeugnis> getEnderzeugnisList() {
		return enderzeugnisList;
	}

	public CellList<Stueckliste> getStuecklisteList() {
		return stuecklisteList;
	}

}
