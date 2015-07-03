package de.hdm.team7.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.team7.client.gui.StuecklistenFormular;
import de.hdm.team7.shared.geschaeftsobjekte.Stueckliste;

public class Reportgenerator implements EntryPoint {

	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label(
			"Bitte melden Sie sich mit Ihrem Google Account an, um diese Anwendung verwenden zu koennen.");
	private Anchor signInLink = new Anchor("Anmelden");
	private Anchor signOutLink = new Anchor("Abmelden");
	
	@Override
	public void onModuleLoad() {
		// Check login status using login service.
//				LoginServiceReportsAsync loginService = GWT.create(LoginServiceReports.class);
//				loginService.login(GWT.getHostPageBaseURL(),
//						new AsyncCallback<LoginInfo>() {
//							public void onFailure(Throwable error) {
//							}
//
//							public void onSuccess(LoginInfo result) {
//								loginInfo = result;
//								if (loginInfo.isLoggedIn()) {
//									if (NamespaceManager.get() == null) {
//										// Assuming there is a logged in user.
//										String namespace = UserServiceFactory.getUserService()
//												.getCurrentUser().getUserId();
//										NamespaceManager.set(namespace);
//									}
										loadReportgenerator();
//									
//								} else {
//									loadLogin();
//								}
//							}
//						});
	}
	
	private void loadLogin() {
		// Assemble login panel.
		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		
//		dropBox.addItem("Editor");
//		dropBox.addItem("Reportgenerator");
//		loginPanel.add(hinweisLabel);
//		loginPanel.add(dropBox);
		
//		dropBox.addChangeHandler(new ChangeHandler(){
//			@Override
//			public void onChange(ChangeEvent event) {
//				if (dropBox.getItemText(dropBox.getSelectedIndex()) == "Editor"){
//					stockStore.clear();
//					stockStore.setItem("Editor", "true");
//				} else {
//					stockStore.setItem("Editor", "false");
//				}
//			}
//		});
		
		// stockStore.clear();
		// stockStore.setItem("Editor", "true");
		
		loginPanel.add(signInLink);
		RootPanel.get("Container").add(loginPanel);
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
		stackLayoutPanel.setPixelSize(300, 550);
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
	
}
