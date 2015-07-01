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
import de.hdm.team7.server.database.BaugruppeMapper;
import de.hdm.team7.server.database.BauteilMapper;
import de.hdm.team7.server.database.EnderzeugnisMapper;
import de.hdm.team7.shared.StuecklistenVerwaltung;
import de.hdm.team7.shared.StuecklistenVerwaltungAsync;
import de.hdm.team7.shared.geschaeftsobjekte.Baugruppe;
import de.hdm.team7.shared.geschaeftsobjekte.Bauteil;
import de.hdm.team7.shared.geschaeftsobjekte.Enderzeugnis;
import de.hdm.team7.shared.geschaeftsobjekte.ZuordnungBGBT;
import de.hdm.team7.shared.geschaeftsobjekte.ZuordnungEEBG;
import de.hdm.team7.shared.geschaeftsobjekte.ZuordnungEEBT;

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
		
		EnderzeugnisMapper eem = EnderzeugnisMapper.enderzeugnisMapper();
		BauteilMapper btm = BauteilMapper.bauteilMapper();
		BaugruppeMapper bgm = BaugruppeMapper.baugruppeMapper();
		Enderzeugnis testVentilator = new Enderzeugnis(0, "Ventilator", "Testobjekt Ventilator.", "Plastik", "01.07.2015");
		Bauteil testSchraube = new Bauteil(0, "Schraube", "Testbauteil Schraube", "Metall", "01.07.2015");
		Bauteil testMotorblock = new Bauteil(0, "Motorblock", "Testbauteil Motorblock", "Metall", "01.07.2015");
		Bauteil testKolben = new Bauteil(0, "Kolben", "Testbauteil Kolben", "Metall", "01.07.2015");
		Bauteil testVerdeck = new Bauteil(0, "Verdeck", "Testbauteil Verdeck", "Plastik", "01.07.2015");
		Baugruppe testMotor = new Baugruppe(0, "Motor", "Testbauteil Motor", "Metall", "01.07.2015");
		Baugruppe testGehaeuse = new Baugruppe(0, "Gehaeuse", "Testbauteil Gehäuse", "Plastik", "01.07.2015");
		eem.insert(testVentilator);
		btm.insert(testSchraube);
		btm.insert(testMotorblock);
		btm.insert(testKolben);
		btm.insert(testVerdeck);
		bgm.insert(testMotor);
		bgm.insert(testGehaeuse);
		ZuordnungEEBT testVentilatorSchraube = new ZuordnungEEBT(0, 4, "01.07.2015", null, testVentilator.getId(), testSchraube.getId());
		ZuordnungBGBT testMotorMotorblock = new ZuordnungBGBT(0, 1, "01.07.2015", null, testMotor.getId(), testMotorblock.getId());
		ZuordnungBGBT testMotorKolben = new ZuordnungBGBT(0, 1, "01.07.2015", null, testMotor.getId(), testKolben.getId());
		ZuordnungBGBT testGehaeuseSchraube = new ZuordnungBGBT(0, 6, "01.07.2015", null, testGehaeuse.getId(), testSchraube.getId());
		ZuordnungBGBT testGehaeuseVerdeck = new ZuordnungBGBT(0, 1, "01.07.2015", null, testGehaeuse.getId(), testVerdeck.getId());
		ZuordnungEEBG testVentilatorMotor = new ZuordnungEEBG(0, 1, "01.07.2015", null, testVentilator.getId(), testMotor.getId());
		ZuordnungEEBG testVentilatorGehaeuse = new ZuordnungEEBG(0, 1, "01.07.2015", null, testVentilator.getId(), testGehaeuse.getId());

		
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
