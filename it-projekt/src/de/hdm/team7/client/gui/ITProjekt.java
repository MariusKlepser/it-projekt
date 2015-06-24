package de.hdm.team7.client.gui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.team7.client.ClientEinstellungen;
import de.hdm.team7.shared.StuecklistenVerwaltung;
import de.hdm.team7.shared.StuecklistenVerwaltungAsync;


/**
 * Entry-Point-Klasse des Projekts <b>BankProjekt</b>.
 */
public class ITProjekt implements EntryPoint {

	static interface StuecklistenVerwaltungTreeResources extends CellTree.Resources {
		
	
//		@Override
//		@Source("cellTreeClosedItem.gif")
//	    ImageResource cellTreeClosedItem();
//
//	    @Override
//		@Source("cellTreeOpenItem.gif")
//	    ImageResource cellTreeOpenItem();
//
//	    @Override
//		@Source("BankCellTree.css")
//	    CellTree.Style cellTreeStyle(); 
	}
	
	StuecklistenVerwaltungAsync stuecklistenVerwaltung = null;
	
	/**
	 * Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
	 * zusichert, benötigen wir eine Methode
	 * <code>public void onModuleLoad()</code>. Diese ist das GWT-Pendant der
	 * <code>main()</code>-Methode normaler Java-Applikationen.
	 */
	@Override
	public void onModuleLoad() {
		if (stuecklistenVerwaltung == null) {
			stuecklistenVerwaltung = ClientEinstellungen.getStuecklistenVerwaltung();
		}
		
		StuecklistenVerwaltung stuecklistenVerwaltung = new StuecklistenVerwaltung();
		stuecklistenVerwaltung.setStuecklistenListe("Stuecklisten Liste");
		bank.setStreet("Nobelstr. 10");
		bank.setZip(70569);
		bank.setCity("Stuttgart");
		bankVerwaltung.setBank(bank, new SetBankCallback());
		
		/*
		 * Die Bankanwendung besteht aus einem Navigationsteil mit Baumstruktur
		 * und einem Datenteil mit Formularen für den ausgewählten Kunden und das
		 * ausgewählte Konto.
		 */
		EnderzeugnisFormular ef = new EnderzeugnisFormular();
		StuecklistenFormular sf = new StuecklistenFormular();
		BaugruppeFormular bgf = new BaugruppeFormular();
		BauteilFormular btf = new BauteilFormular();
		BusinessObjectTreeViewModel botvm = new BusinessObjectTreeViewModel();
		
		/*
		 * Die Formulare und der Kunden- und Kontobaum werden miteinander verlinkt.
		 */
		botvm.setzeBaugruppeFormular(bgf);
		//bgf.setBusinessObjectTreeViewModel(botvm);
		
		botvm.setzeBauteilFormular(btf);
		//btf.setBusinessObjectTreeViewModel(botvm);
		
		botvm.setzeEnderzeugnisFormular(ef);
		//ef.setBusinessObjectTreeViewModel(botvm);
		
		botvm.setzeStuecklistenFormular(sf);
		//sf.setBusinessObjectTreeViewModel(botvm);
		
		/*
		 * Die Panels und der CellTree werden erzeugt und angeordnet und in das RootPanel eingefügt.
		 */
		VerticalPanel detailsPanel = new VerticalPanel();
		detailsPanel.add(bgf);
		detailsPanel.add(btf);
		detailsPanel.add(ef);
		detailsPanel.add(sf);
		
		CellTree.Resources stuecklistenVerwaltungTreeResource = GWT.create(StuecklistenVerwaltungTreeResources.class);
		CellTree cellTree = new CellTree(botvm, "Root", stuecklistenVerwaltungTreeResource);
		cellTree.setAnimationEnabled(true);
		
		RootPanel.get("Navigator").add(cellTree);
		RootPanel.get("Details").add(detailsPanel);
}
	/**
	 * Diese Nested Class wird als Callback für das Setzen des Bank-Objekts bei
	 * der BankAdministration und bei dem ReportGenerator benötigt.
	 * 
	 */
	class SetzeStuecklistenVerwaltungCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			/*
			 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message
			 * aus.
			 */
			ClientEinstellungen.getLogger().severe(
					"Setzen der Bank fehlgeschlagen!");
		}

		@Override
		public void onSuccess(Void result) {
			/*
			 * Wir erwarten diesen Ausgang, wollen aber keine Notifikation
			 * ausgeben.
			 */
		}

	}
}

