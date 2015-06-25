package de.hdm.team7.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TreeItem;

import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.team7.client.gui.BaugruppeFormular;
import de.hdm.team7.client.gui.BauteilFormular;
import de.hdm.team7.client.gui.BenutzerFormular;
import de.hdm.team7.client.gui.BusinessObjectTreeViewModel;
import de.hdm.team7.client.gui.CustomStaticTree;
import de.hdm.team7.client.gui.EnderzeugnisFormular;
import de.hdm.team7.client.gui.StuecklistenFormular;
import de.hdm.team7.client.gui.ITProjekt.StuecklistenVerwaltungTreeResources;
import de.hdm.team7.client.rpc.AsyncCallbackComponentList;
import de.hdm.team7.shared.StuecklistenVerwaltung;
import de.hdm.team7.shared.StuecklistenVerwaltungAsync;

public class It_projekt implements EntryPoint {

	static interface StuecklistenVerwaltungTreeResources extends
			CellTree.Resources {

		// @Override

		// @Source("cellTreeClosedItem.gif")

		// ImageResource cellTreeClosedItem();

		//

		// @Override

		// @Source("cellTreeOpenItem.gif")

		// ImageResource cellTreeOpenItem();

		//

		// @Override

		// @Source("BankCellTree.css")

		// CellTree.Style cellTreeStyle();
	}

	public void onModuleLoad() {

		StuecklistenVerwaltungAsync stuecklistenVerwaltung = null;

		if (stuecklistenVerwaltung == null) {

			stuecklistenVerwaltung = ClientEinstellungen
					.getStuecklistenVerwaltung();

		}

		/*
		 * 
		 * Die Bankanwendung besteht aus einem Navigationsteil mit Baumstruktur
		 * 
		 * und einem Datenteil mit Formularen für den ausgewählten Kunden und
		 * das
		 * 
		 * ausgewählte Konto.
		 */

		EnderzeugnisFormular ef = new EnderzeugnisFormular();

		StuecklistenFormular sf = new StuecklistenFormular();

		BaugruppeFormular bgf = new BaugruppeFormular();

		BauteilFormular btf = new BauteilFormular();

		BenutzerFormular bf = new BenutzerFormular();

		BusinessObjectTreeViewModel botvm = new BusinessObjectTreeViewModel();

		/*
		 * 
		 * Die Formulare und der Kunden- und Kontobaum werden miteinander
		 * verlinkt.
		 */

		botvm.setzeBaugruppenFormular(bgf);
		bgf.setzeBusinessObjectTreeViewModel(botvm);

		botvm.setzeBauteilFormular(btf);
		btf.setzeBusinessObjectTreeViewModel(botvm);

		botvm.setzeEnderzeugnisFormular(ef);
		ef.setzeBusinessObjectTreeViewModel(botvm);

		botvm.setzeStuecklistenFormular(sf);
		sf.setzeBusinessObjectTreeViewModel(botvm);

		botvm.setzeBenutzerFormular(bf);
		bf.setzeBusinessObjectTreeViewModel(botvm);

		/*
		 * 
		 * Die Panels und der CellTree werden erzeugt und angeordnet und in das
		 * RootPanel eingefügt.
		 */

		VerticalPanel detailsPanel = new VerticalPanel();

		detailsPanel.add(bgf);

		detailsPanel.add(btf);

		detailsPanel.add(ef);

		detailsPanel.add(sf);
		
		detailsPanel.add(bf);

		CellTree.Resources stuecklistenVerwaltungTreeResource = GWT
				.create(StuecklistenVerwaltungTreeResources.class);

		CellTree cellTree = new CellTree(botvm, "Root",
				stuecklistenVerwaltungTreeResource);

		cellTree.setAnimationEnabled(true);
		
		RootPanel.get("Navigator").add(cellTree);

		RootPanel.get("Details").add(detailsPanel);
	}
}
