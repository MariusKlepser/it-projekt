package de.hdm.team7.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;

import de.hdm.team7.client.ui.views.legacy.BaugruppeOverview;
import de.hdm.team7.client.ui.views.legacy.BauteilOverview;
import de.hdm.team7.client.ui.views.legacy.BenutzerOverview;
import de.hdm.team7.client.ui.views.legacy.DefaultView;
import de.hdm.team7.client.ui.views.legacy.EnderzeugnisOverview;
import de.hdm.team7.client.ui.views.legacy.ReportGeneratorOverview;
import de.hdm.team7.client.ui.views.legacy.StuecklisteOverview;

public class ViewHandler {
	
	public ViewHandler(){}
	
	// Load Views Based on String
	private Panel loadView(String view) {
		Panel viewContent = new FlowPanel();

		switch(view) {
		case "Bauteil":
			viewContent = new BauteilOverview().getView();
			break;
		case "Baugruppe":
			viewContent = new BaugruppeOverview().getView();
			break;
		case "Enderzeugnis":
			viewContent = new EnderzeugnisOverview().getView();
			break;
		case "Stueckliste":
			viewContent = new StuecklisteOverview().getView();
			break;
		case "Benutzer":
			viewContent = new BenutzerOverview().getView();
			break;
		case "ReportGenerator":
			viewContent = new ReportGeneratorOverview().getView();
			break;
		default:
			viewContent = new DefaultView().getView();
			break;
		}

		return viewContent;
	};
	
	// Return View
	public Panel getView(String view){
		Panel content = this.loadView(view);
		
		return content;
	}
}
