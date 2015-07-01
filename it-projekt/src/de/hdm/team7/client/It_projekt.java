package de.hdm.team7.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
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
	
	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label(
			"Bitte melden Sie sich mit Ihrem Google Account an, um diese Anwendung verwenden zu koennen.");
	private Anchor signInLink = new Anchor("Anmelden");
	private Anchor signOutLink = new Anchor("Abmelden");
	final ListBox dropBox = new ListBox(false);
	private Label hinweisLabel = new Label("Bevor Sie sich anmelden, waehlen Sie bitte hier, wo Sie sich anmelden moechten");
	private Storage stockStore = Storage.getLocalStorageIfSupported();

	@Override
	public void onModuleLoad() {
		// Check login status using login service.
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL(),
				new AsyncCallback<LoginInfo>() {
					public void onFailure(Throwable error) {
					}

					public void onSuccess(LoginInfo result) {
						loginInfo = result;
						if (loginInfo.isLoggedIn()) {
//							if (NamespaceManager.get() == null) {
//								// Assuming there is a logged in user.
//								String namespace = UserServiceFactory.getUserService()
//										.getCurrentUser().getUserId();
//								NamespaceManager.set(namespace);
//							}
							
							if (stockStore.getItem("Editor") == "true"){
								loadEditor();
							} else {
								loadReportgenerator();
							}
							
						} else {
							loadLogin();
						}
					}
				});
	}

	private void loadReportgenerator() {
		// Set up sign out hyperlink.
	    signOutLink.setHref(loginInfo.getLogoutUrl());
		
		final StuecklistenFormular sf = new StuecklistenFormular();
		StuecklisteCell stuecklisteCell = new StuecklisteCell();
		final CellList<Stueckliste> stuecklisteList = new CellList<Stueckliste>(stuecklisteCell);
		final SingleSelectionModel<Stueckliste> selectionModelStueckliste = new SingleSelectionModel<Stueckliste>();
		stuecklisteList.setSelectionModel(selectionModelStueckliste);
		selectionModelStueckliste
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						sf.setVisible(true);
						sf.setzeSelektiert(selectionModelStueckliste.getSelectedObject());
					}
				});
		ScrollPanel scrollPanelStueckliste = new ScrollPanel();
		scrollPanelStueckliste.add(stuecklisteList);
		StackLayoutPanel stackLayoutPanel = new StackLayoutPanel(Unit.EM);
		stackLayoutPanel.setPixelSize(400, 400);
		stackLayoutPanel.add(stuecklisteList, "Stuecklisten", 3);
		stackLayoutPanel.addSelectionHandler(new SelectionHandler<Integer>() {
		    @Override
		    public void onSelection(SelectionEvent<Integer> event) {
		    	if (event.getSelectedItem() == 0){
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
		    	}
		    }
		});
		
		VerticalPanel detailsPanel = new VerticalPanel();
		detailsPanel.add(sf);
		sf.setVisible(false);
		
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.add(stackLayoutPanel);
		hPanel.add(detailsPanel);

		// Add it to the root panel.
//		RootPanel.get("Navigator").add(stackPanel);
//		RootPanel.get("Details").add(detailsPanel);
		RootPanel.get("LinkContainer").add(signOutLink);
		RootPanel.get("Container").add(hPanel);
	}
	
	private void loadLogin() {
		// Assemble login panel.
		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		
		dropBox.addItem("Editor");
		dropBox.addItem("Reportgenerator");
		loginPanel.add(hinweisLabel);
		loginPanel.add(dropBox);
		
		dropBox.addChangeHandler(new ChangeHandler(){
			@Override
			public void onChange(ChangeEvent event) {
				if (dropBox.getItemText(dropBox.getSelectedIndex()) == "Editor"){
					stockStore.clear();
					stockStore.setItem("Editor", "true");
				} else {
					stockStore.setItem("Editor", "false");
				}
			}
		});
		
		loginPanel.add(signInLink);
		RootPanel.get("Container").add(loginPanel);
	}

	private void loadEditor() {
		// Set up sign out hyperlink.
	    signOutLink.setHref(loginInfo.getLogoutUrl());
		
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
		final CellList<Baugruppe> baugruppeList = new CellList<Baugruppe>(
				baugruppeCell);
		final CellList<Enderzeugnis> enderzeugnisList = new CellList<Enderzeugnis>(
				enderzeugnisCell);
		final CellList<Stueckliste> stuecklisteList = new CellList<Stueckliste>(
				stuecklisteCell);
		final CellList<Benutzer> benutzerList = new CellList<Benutzer>(
				benutzerCell);

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
						btf.setzeSelektiert(selectionModelBauteil
								.getSelectedObject());
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
						bgf.setzeSelektiert(selectionModelBaugruppe
								.getSelectedObject());
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
						ef.setzeSelektiert(selectionModelEnderzeugnis
								.getSelectedObject());
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
						sf.setzeSelektiert(selectionModelStueckliste
								.getSelectedObject());
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
						bf.setzeSelektiert(selectionModelBenutzer
								.getSelectedObject());
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
						// Set the total row count. This isn't strictly
						// necessary, but it
						// affects
						// paging calculations, so its good habit to keep the
						// row count up to
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

		final PopupPanel bauteilPopup = new PopupPanel(true, false);
		final Button neuesBauteil = new Button("Neues Bauteil");

		neuesBauteil.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				btf.setVisible(true);
				bgf.setVisible(false);
				ef.setVisible(false);
				sf.setVisible(false);
				bf.setVisible(false);
				btf.setzeSelektiert(null);
				bauteilPopup.hide();
			}
		});
		bauteilPopup.setWidget(neuesBauteil);
		final Anchor bauteilLink = new Anchor("Bauteile");

		// Open the contact info popup when the user clicks a contact
//		bauteilLink.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				// Show the popup of contact info
//				int left = bauteilLink.getAbsoluteLeft() + 14;
//				int top = bauteilLink.getAbsoluteTop() + 14;
//				bauteilPopup.setPopupPosition(left, top);
//				bauteilPopup.show();
//			}
//		});
		
		bauteilLink.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				int button = event.getNativeEvent().getButton();
				if (button == NativeEvent.BUTTON_RIGHT) {
					int left = bauteilLink.getAbsoluteLeft() + 14;
					int top = bauteilLink.getAbsoluteTop() + 14;
					bauteilPopup.setPopupPosition(left, top);
					bauteilPopup.show();
				}

			}
		});

		final PopupPanel baugruppePopup = new PopupPanel(true, false);
		final Button neueBaugruppe = new Button("Neue Baugruppe");

		neueBaugruppe.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				btf.setVisible(false);
				bgf.setVisible(true);
				ef.setVisible(false);
				sf.setVisible(false);
				bf.setVisible(false);
				bgf.setzeSelektiert(null);
				baugruppePopup.hide();
			}
		});
		baugruppePopup.setWidget(neueBaugruppe);
		final Anchor baugruppeLink = new Anchor("Baugruppen");

		// Open the contact info popup when the user clicks a contact
//		baugruppeLink.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				// Show the popup of contact info
//				int left = baugruppeLink.getAbsoluteLeft() + 14;
//				int top = baugruppeLink.getAbsoluteTop() + 14;
//				baugruppePopup.setPopupPosition(left, top);
//				baugruppePopup.show();
//			}
//		});
		
		baugruppeLink.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				int button = event.getNativeEvent().getButton();
				if (button == NativeEvent.BUTTON_RIGHT) {
					int left = baugruppeLink.getAbsoluteLeft() + 14;
					int top = baugruppeLink.getAbsoluteTop() + 14;
					baugruppePopup.setPopupPosition(left, top);
					baugruppePopup.show();
				}

			}
		});

		final PopupPanel enderzeugnisPopup = new PopupPanel(true, false);
		final Button neuesEnderzeugnis = new Button("Neues Enderzeugnis");

		neuesEnderzeugnis.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				btf.setVisible(false);
				bgf.setVisible(false);
				ef.setVisible(true);
				sf.setVisible(false);
				bf.setVisible(false);
				ef.setzeSelektiert(null);
				enderzeugnisPopup.hide();
			}
		});
		enderzeugnisPopup.setWidget(neuesEnderzeugnis);
		final Anchor enderzeugnisLink = new Anchor("Enderzeugnisse");

		// Open the contact info popup when the user clicks a contact
//		enderzeugnisLink.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				// Show the popup of contact info
//				int left = enderzeugnisLink.getAbsoluteLeft() + 14;
//				int top = enderzeugnisLink.getAbsoluteTop() + 14;
//				enderzeugnisPopup.setPopupPosition(left, top);
//				enderzeugnisPopup.show();
//			}
//		});
		
		enderzeugnisLink.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				int button = event.getNativeEvent().getButton();
				if (button == NativeEvent.BUTTON_RIGHT) {
					int left = enderzeugnisLink.getAbsoluteLeft() + 14;
					int top = enderzeugnisLink.getAbsoluteTop() + 14;
					enderzeugnisPopup.setPopupPosition(left, top);
					enderzeugnisPopup.show();
				}

			}
		});

		final PopupPanel stuecklistePopup = new PopupPanel(true, false);
		final Button neueStueckliste = new Button("Neue Stueckliste");

		neueStueckliste.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				btf.setVisible(false);
				bgf.setVisible(false);
				ef.setVisible(false);
				sf.setVisible(true);
				bf.setVisible(false);
				sf.setzeSelektiert(null);
				stuecklistePopup.hide();
			}
		});
		stuecklistePopup.setWidget(neueStueckliste);
		final Anchor stuecklisteLink = new Anchor("Stuecklisten");

		// Open the contact info popup when the user clicks a contact
//		stuecklisteLink.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				// Show the popup of contact info
//				int left = stuecklisteLink.getAbsoluteLeft() + 14;
//				int top = stuecklisteLink.getAbsoluteTop() + 14;
//				stuecklistePopup.setPopupPosition(left, top);
//				stuecklistePopup.show();
//			}
//		});
		
		stuecklisteLink.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				int button = event.getNativeEvent().getButton();
				if (button == NativeEvent.BUTTON_RIGHT) {
					int left = stuecklisteLink.getAbsoluteLeft() + 14;
					int top = stuecklisteLink.getAbsoluteTop() + 14;
					stuecklistePopup.setPopupPosition(left, top);
					stuecklistePopup.show();
				}

			}
		});

		final PopupPanel benutzerPopup = new PopupPanel(true, false);
		final Button neuerBenutzer = new Button("Neuer Benutzer");

		neuerBenutzer.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				btf.setVisible(false);
				bgf.setVisible(false);
				ef.setVisible(false);
				sf.setVisible(false);
				bf.setVisible(true);
				bf.setzeSelektiert(null);
				benutzerPopup.hide();
			}
		});
		benutzerPopup.setWidget(neuerBenutzer);
		final Anchor benutzerLink = new Anchor("Benutzer");

		// Open the contact info popup when the user clicks a contact
//		benutzerLink.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				// Show the popup of contact info
//				int left = benutzerLink.getAbsoluteLeft() + 14;
//				int top = benutzerLink.getAbsoluteTop() + 14;
//				benutzerPopup.setPopupPosition(left, top);
//				benutzerPopup.show();
//			}
//		});
		
		benutzerLink.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				int button = event.getNativeEvent().getButton();
				if (button == NativeEvent.BUTTON_RIGHT) {
					int left = benutzerLink.getAbsoluteLeft() + 14;
					int top = benutzerLink.getAbsoluteTop() + 14;
					benutzerPopup.setPopupPosition(left, top);
					benutzerPopup.show();
				}

			}
		});

		// Create a stack panel containing three labels.
		StackLayoutPanel stackLayoutPanel = new StackLayoutPanel(Unit.EM);
		stackLayoutPanel.setPixelSize(400, 400);
		stackLayoutPanel.add(scrollPanelBauteil, bauteilLink, 3);
		stackLayoutPanel.add(scrollPanelBaugruppe, baugruppeLink, 3);
		stackLayoutPanel.add(scrollPanelEnderzeugnis, enderzeugnisLink, 3);
		stackLayoutPanel.add(scrollPanelStueckliste, stuecklisteLink, 3);
		stackLayoutPanel.add(scrollPanelBenutzer, benutzerLink, 3);

		stackLayoutPanel.addSelectionHandler(new SelectionHandler<Integer>() {
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
											// Set the total row count. This
											// isn't strictly necessary, but it
											// affects
											// paging calculations, so its good
											// habit to keep the row count up to
											// date.
											bauteilList.setRowCount(
													bauteilListe.size(), true);
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
											baugruppeList.setRowCount(
													baugruppeListe.size(), true);
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
											enderzeugnisList.setRowCount(
													enderzeugnisListe.size(),
													true);
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
											stuecklisteList.setRowCount(
													stuecklisteListe.size(),
													true);
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
											benutzerList.setRowCount(
													benutzerListe.size(), true);
											benutzerList.setRowData(0,
													benutzerListe);
											ClientEinstellungen.getLogger()
													.info(result);
										}
									});
				}
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
		// RootPanel.get("Navigator").add(stackPanel);
		// RootPanel.get("Details").add(detailsPanel);
		RootPanel.get("LinkContainer").add(signOutLink);
		RootPanel.get("Container").add(hPanel);
	}
}
