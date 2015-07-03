package de.hdm.team7.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
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
import de.hdm.team7.client.gui.CustomStackLayoutPanel;
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
//							UserService userService = UserServiceFactory.getUserService();
//							com.google.appengine.api.users.User user = userService.getCurrentUser();
//							String namespace = user.getUserId();
//							NamespaceManager.set(namespace);
							loadEditor();
						} else {
							loadLogin();
						}
					}
				});
	}

	
	
	private void loadLogin() {
		// Assemble login panel.
		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get("Container").add(loginPanel);
	}

	private void loadEditor() {
		// Set up sign out hyperlink.
	    signOutLink.setHref(loginInfo.getLogoutUrl());
		
	    final Button neuButton = new Button("Neues Element +"); 
	    final Button neuStueckliste = new Button("Neue Stueckliste");
	    final Button neuBaugruppe = new Button("Neue Baugruppe");
	    final Button neuBauteil = new Button("Neues Bauteil");
	    final Button neuEnderzeugnis = new Button("Neues Enderzeugnis");
	    
		final EnderzeugnisFormular ef = new EnderzeugnisFormular();
		final StuecklistenFormular sf = new StuecklistenFormular();
		final BaugruppeFormular bgf = new BaugruppeFormular();
		final BauteilFormular btf = new BauteilFormular();
		final BenutzerFormular bf = new BenutzerFormular();
		
		final Label statuslabel = new Label();
		statuslabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		statuslabel.setStylePrimaryName("statuslabel");
		RootPanel.get("Statuszeile").add(statuslabel);
		btf.setzeStatusLabel(statuslabel);

		final PopupPanel neuesElementPopup = new PopupPanel(true, false);
		
		final Button neuesElement = new Button("Neu >");
		neuesElement.setPixelSize(250, 25);
		neuesElement.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int left = neuesElement.getAbsoluteLeft() + 14;
				int top = neuesElement.getAbsoluteTop() + 14;
				neuesElementPopup.setPopupPosition(left, top);
				neuesElementPopup.show();
			}
		});
		
		final Button neuesBauteil = new Button("Neues Bauteil anlegen");
		neuesBauteil.setWidth("100%");
		neuesBauteil.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				btf.setVisible(true);
				bgf.setVisible(false);
				ef.setVisible(false);
				sf.setVisible(false);
				bf.setVisible(false);
				btf.setzeSelektiert(null, loginInfo);
				neuesElementPopup.hide();
			}
		});

		final Button neueBaugruppe = new Button("Neue Baugruppe anlegen");
		neueBaugruppe.setWidth("100%");
		neueBaugruppe.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				btf.setVisible(false);
				bgf.setVisible(true);
				ef.setVisible(false);
				sf.setVisible(false);
				bf.setVisible(false);
//				bgf.setzeSelektiert(null, loginInfo);
				neuesElementPopup.hide();
			}
		});
		
		final Button neuesEnderzeugnis = new Button("Neues Enderzeugnis anlegen");
		neuesEnderzeugnis.setWidth("100%");
		neuesEnderzeugnis.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				btf.setVisible(false);
				bgf.setVisible(false);
				ef.setVisible(true);
				sf.setVisible(false);
				bf.setVisible(false);
//				ef.setzeSelektiert(null, loginInfo);
				neuesElementPopup.hide();
			}
		});
		
		final Button neueStueckliste = new Button("Neue Stueckliste anlegen");
		neueStueckliste.setWidth("100%");
		neueStueckliste.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				btf.setVisible(false);
				bgf.setVisible(false);
				ef.setVisible(false);
				sf.setVisible(true);
				bf.setVisible(false);
//				sf.setzeSelektiert(null, loginInfo);
				neuesElementPopup.hide();
			}
		});
		
		VerticalPanel buttonPanel = new VerticalPanel();
		buttonPanel.add(neuesBauteil);
		buttonPanel.add(neueBaugruppe);
		buttonPanel.add(neuesEnderzeugnis);
		buttonPanel.add(neueStueckliste);
		neuesElementPopup.setWidget(buttonPanel);
		
		VerticalPanel navigation = new VerticalPanel();
		navigation.add(neuesElement);

		// Create a stack panel containing three labels.
		CustomStackLayoutPanel stackLayoutPanel = new CustomStackLayoutPanel(Unit.EM, ef, sf, bgf, btf, loginInfo);
		stackLayoutPanel.setPixelSize(250, 500);
		btf.setzeCustomStackLayoutPanel(stackLayoutPanel);
		
		navigation.add(stackLayoutPanel);

		VerticalPanel detailsPanel = new VerticalPanel();
		detailsPanel.add(btf);
		detailsPanel.add(bgf);
		detailsPanel.add(ef);
		detailsPanel.add(sf);
		detailsPanel.add(bf);
		
		neuStueckliste.setVisible(false);
		neuBaugruppe.setVisible(false);
		neuBauteil.setVisible(false);
		neuEnderzeugnis.setVisible(false);
		btf.setVisible(false);
		bgf.setVisible(false);
		ef.setVisible(false);
		sf.setVisible(false);
		bf.setVisible(false);

		HorizontalPanel bPanel = new HorizontalPanel();
		bPanel.add(neuButton);
		bPanel.add(neuStueckliste);
		bPanel.add(neuBaugruppe);
		bPanel.add(neuBauteil);
		bPanel.add(neuEnderzeugnis);
		
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.add(navigation);
		hPanel.add(detailsPanel);

		
		// Add it to the root panel.
		// RootPanel.get("Navigator").add(stackPanel);
		// RootPanel.get("Details").add(detailsPanel);
		
		Label nickname = new Label("Hallo, " + loginInfo.getNickname() + "!  ");
		HorizontalPanel hoPanel = new HorizontalPanel();
		hoPanel.setSpacing(5);
		hoPanel.add(nickname);
		hoPanel.add(signOutLink);
		RootPanel.get("LinkContainer").add(hoPanel);
		RootPanel.get("Container").add(bPanel);
		RootPanel.get("Container").add(hPanel);
	}
}
