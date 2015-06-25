package de.hdm.team7.client.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

import de.hdm.team7.client.ClientEinstellungen;
import de.hdm.team7.client.cell.BaugruppeCell;
import de.hdm.team7.client.cell.BauteilCell;
import de.hdm.team7.client.cell.BenutzerCell;
import de.hdm.team7.client.cell.EnderzeugnisCell;
import de.hdm.team7.client.cell.GeschaeftsobjektCell;
import de.hdm.team7.client.cell.StuecklisteCell;
import de.hdm.team7.shared.StuecklistenVerwaltungAsync;
import de.hdm.team7.shared.geschaeftsobjekte.*;

public class BusinessObjectTreeViewModel implements TreeViewModel {

	/**
	 * Diese Implementierung des TreeViewModels sorgt fÃ¼r die Verwaltung des
	 * Kunden- und Kontenbaumes.
	 * 
	 * @author Christian Rathke
	 * 
	 */

	private BenutzerFormular benutzerFormular;
	private StuecklistenFormular stuecklisteFormular;
	private BaugruppeFormular baugruppeFormular;
	private BauteilFormular bauteilFormular;
	private EnderzeugnisFormular enderzeugnisFormular;

	private Benutzer selektierterBenutzer = null;
	private Stueckliste selektierteStueckliste = null;
	private Baugruppe selektierteBaugruppe = null;
	private Bauteil selektiertesBauteil = null;
	private Enderzeugnis selektiertesEnderzeugnis = null;

	private StuecklistenVerwaltungAsync stuecklistenVerwaltung = null;

	private BusinessObjectKeyProvider boKeyProvider = null;
	private SingleSelectionModel<Category> selectionModel = null;

	private ListDataProvider<Stueckliste> stuecklistenDatenProvider = null;
	private ListDataProvider<Baugruppe> baugruppenDatenProvider = null;
	private ListDataProvider<Bauteil> bauteilDatenProvider = null;
	private ListDataProvider<Enderzeugnis> enderzeugnisDatenProvider = null;
	private ListDataProvider<Benutzer> benutzerDatenProvider = null;

	/*
	 * In dieser Map merken wir uns die ListDataProviders fÃ¼r die Kontolisten
	 * der im Kunden- und Kontobaum expandierten Kundenknoten.
	 */
	private Map<Stueckliste, ListDataProvider<Stueckliste>> stuecklistenDatenProviders = null;
	private Map<Baugruppe, ListDataProvider<Baugruppe>> baugruppenDatenProviders = null;
	private Map<Bauteil, ListDataProvider<Bauteil>> bauteilDatenProviders = null;
	private Map<Enderzeugnis, ListDataProvider<Enderzeugnis>> enderzeugnisDatenProviders = null;
	private Map<Benutzer, ListDataProvider<Benutzer>> benutzerDatenProviders = null;

	/**
	 * Bildet BusinessObjects auf eindeutige Zahlenobjekte ab, die als
	 * SchlÃ¼ssel fÃ¼r Baumknoten dienen. Dadurch werden im Selektionsmodell
	 * alle Objekte mit derselben id selektiert, wenn eines davon selektiert
	 * wird. Der SchlÃ¼ssel fÃ¼r Kundenobjekte ist eine positive, der fÃ¼r
	 * Kontenobjekte eine negative Zahl, die sich jeweils aus der id des
	 * Objektes ergibt. Dadurch kÃ¶nnen Kunden- von Kontenobjekten
	 * unterschieden werden, auch wenn sie dieselbe id haben.
	 */
	private class BusinessObjectKeyProvider implements
			ProvidesKey<Geschaeftsobjekt> {
		@Override
		public Integer getKey(Geschaeftsobjekt bo) {
			if (bo == null) {
				return null;
			}
			if (bo instanceof Benutzer) {
				return new Integer(bo.getId());
			} else {
				return new Integer(-bo.getId());
			}
		}
	};

	/**
	 * Nested Class fÃ¼r die Reaktion auf Selektionsereignisse. Als Folge
	 * einer Baumknotenauswahl wird je nach Typ des Business-Objekts der
	 * "selectedCustomer" bzw. das "selectedAccount" gesetzt.
	 */
//	private class SelectionChangeEventHandler implements SelectionChangeEvent.Handler {
//		@Override
//		public void onSelectionChange(SelectionChangeEvent event) {
//			Geschaeftsobjekt selection = selectionModel.getSelectedObject();
//			if (selection instanceof Bauteil) {
//				setSelektiertesBauteil((Bauteil) selection);
//			} else if (selection instanceof Baugruppe) {
//				setSelektierteBaugruppe((Baugruppe) selection);
//			} else if (selection instanceof Enderzeugnis) {
//				setSelektiertesEnderzeugnis((Enderzeugnis) selection);
//			} else if (selection instanceof Stueckliste) {
//				setSelektierteStueckliste((Stueckliste) selection);
//			} else if (selection instanceof Benutzer) {
//				setSelektierterBenutzer((Benutzer) selection);
//			}
//		}
//	}

	/*
	 * Im Konstruktor werden die fÃ¼r den Kunden- und Kontobaum wichtigen
	 * lokalen Variaben initialisiert.
	 */
	public BusinessObjectTreeViewModel() {
		stuecklistenVerwaltung = ClientEinstellungen
				.getStuecklistenVerwaltung();
		boKeyProvider = new BusinessObjectKeyProvider();
		selectionModel = new SingleSelectionModel<Category>();
//		selectionModel
//				.addSelectionChangeHandler(new SelectionChangeEventHandler());
		stuecklistenDatenProviders = new HashMap<Stueckliste,ListDataProvider<Stueckliste>>();
		baugruppenDatenProviders = new HashMap<Baugruppe,ListDataProvider<Baugruppe>>();
		bauteilDatenProviders = new HashMap<Bauteil,ListDataProvider<Bauteil>>();
		enderzeugnisDatenProviders = new HashMap<Enderzeugnis,ListDataProvider<Enderzeugnis>>();
		benutzerDatenProviders = new HashMap<Benutzer,ListDataProvider<Benutzer>>();
	}

	public void setzeBenutzerFormular(BenutzerFormular bf) {
		benutzerFormular = bf;
	}

	public void setzeStuecklistenFormular(StuecklistenFormular sf) {
		stuecklisteFormular = sf;
	}

	public void setzeBaugruppenFormular(BaugruppeFormular bgf) {
		baugruppeFormular = bgf;
	}

	public void setzeBauteilFormular(BauteilFormular btf) {
		bauteilFormular = btf;
	}

	public void setzeEnderzeugnisFormular(EnderzeugnisFormular ef) {
		enderzeugnisFormular = ef;
	}

	public Benutzer holeSelektiertenBenutzer() {
		return selektierterBenutzer;
	}
	
	public Stueckliste holeSelektierteStueckliste() {
		return selektierteStueckliste;
	}

	public Baugruppe holeSelektierteBaugruppe() {
		return selektierteBaugruppe;
	}

	public Bauteil holeSelektiertesBauteil() {
		return selektiertesBauteil;
	}
	
	public Enderzeugnis holeSelektiertesEnderzeugnis() {
		return selektiertesEnderzeugnis;
	}

	public void setSelektierterBenutzer(Benutzer b) {
		selektierterBenutzer = b;
		benutzerFormular.setzeSelektiert(b);
	}

	public void setSelektierteStueckliste(Stueckliste s) {
		selektierteStueckliste = s;
		stuecklisteFormular.setzeSelektiert(s);
	}
	
	public void setSelektiertesBauteil(Bauteil b) {
		selektiertesBauteil = b;
		bauteilFormular.setzeSelektiert(b);
	}

	public void setSelektierteBaugruppe(Baugruppe bg) {
		selektierteBaugruppe = bg;
		baugruppeFormular.setzeSelektiert(bg);
	}
	
	public void setSelektiertesEnderzeugnis(Enderzeugnis e) {
		selektiertesEnderzeugnis = e;
		enderzeugnisFormular.setzeSelektiert(e);
	}

	/*
	 * Wenn ein Kunde neu erzeugt wurde, wird er selektiert.
	 */
	public void fuegeBauteilZuBaumHinzu(Bauteil bauteil) {
		bauteilDatenProvider.getList().add(bauteil);
//		selectionModel.setSelected(bauteil, true);
	}
	
	public void aktualisiereBauteilInBaum(Bauteil bauteil) {
		List<Bauteil> bauteilListe = bauteilDatenProvider.getList();
		int i = 0;
		for (Bauteil b : bauteilListe) {
			if (b.getId() == bauteil.getId()) {
				bauteilListe.set(i, bauteil);
				break;
			} else {
				i++;
			}
		}
		bauteilDatenProvider.refresh();
	}

	public void entferneBauteilAusBaum(Bauteil bauteil) {
		bauteilDatenProvider.getList().remove(bauteil);
	}
	
	/*
	 * Wenn ein Kunde neu erzeugt wurde, wird er selektiert.
	 */
	public void fuegeBaugruppeZuBaumHinzu(Baugruppe baugruppe) {
		baugruppenDatenProvider.getList().add(baugruppe);
//		selectionModel.setSelected(baugruppe, true);
	}
	
	public void aktualisiereBaugruppeInBaum(Baugruppe baugruppe) {
		List<Baugruppe> baugruppeListe = baugruppenDatenProvider.getList();
		int i = 0;
		for (Baugruppe b : baugruppeListe) {
			if (b.getId() == baugruppe.getId()) {
				baugruppeListe.set(i, baugruppe);
				break;
			} else {
				i++;
			}
		}
		baugruppenDatenProvider.refresh();
	}

	public void entferneBaugruppeAusBaum(Baugruppe baugruppe) {
		baugruppenDatenProvider.getList().remove(baugruppe);
	}
	
	/*
	 * Wenn ein Kunde neu erzeugt wurde, wird er selektiert.
	 */
	public void fuegeEnderzeugnisZuBaumHinzu(Enderzeugnis enderzeugnis) {
		enderzeugnisDatenProvider.getList().add(enderzeugnis);
//		selectionModel.setSelected(enderzeugnis, true);
	}
	
	public void aktualisiereEnderzeugnisInBaum(Enderzeugnis enderzeugnis) {
		List<Enderzeugnis> enderzeugnisListe = enderzeugnisDatenProvider.getList();
		int i = 0;
		for (Enderzeugnis b : enderzeugnisListe) {
			if (b.getId() == enderzeugnis.getId()) {
				enderzeugnisListe.set(i, enderzeugnis);
				break;
			} else {
				i++;
			}
		}
		enderzeugnisDatenProvider.refresh();
	}

	public void entferneEnderzeugnisAusBaum(Enderzeugnis enderzeugnis) {
		enderzeugnisDatenProvider.getList().remove(enderzeugnis);
	}
	
	/*
	 * Wenn ein Kunde neu erzeugt wurde, wird er selektiert.
	 */
	public void fuegeStuecklisteZuBaumHinzu(Stueckliste stueckliste) {
		stuecklistenDatenProvider.getList().add(stueckliste);
//		selectionModel.setSelected(stueckliste, true);
	}
	
	public void aktualisiereStuecklisteInBaum(Stueckliste stueckliste) {
		List<Stueckliste> stuecklisteListe = stuecklistenDatenProvider.getList();
		int i = 0;
		for (Stueckliste b : stuecklisteListe) {
			if (b.getId() == stueckliste.getId()) {
				stuecklisteListe.set(i, stueckliste);
				break;
			} else {
				i++;
			}
		}
		stuecklistenDatenProvider.refresh();
	}

	public void entferneStuecklisteAusBaum(Stueckliste stueckliste) {
		stuecklistenDatenProvider.getList().remove(stueckliste);
	}
	
	/*
	 * Wenn ein Kunde neu erzeugt wurde, wird er selektiert.
	 */
	public void fuegeBenutzerZuBaumHinzu(Benutzer benutzer) {
		benutzerDatenProvider.getList().add(benutzer);
//		selectionModel.setSelected(benutzer, true);
	}
	
	public void aktualisiereBenutzerInBaum(Benutzer Benutzer) {
		List<Benutzer> benutzerListe = benutzerDatenProvider.getList();
		int i = 0;
		for (Benutzer b : benutzerListe) {
			if (b.getId() == Benutzer.getId()) {
				benutzerListe.set(i, Benutzer);
				break;
			} else {
				i++;
			}
		}
		benutzerDatenProvider.refresh();
	}

	public void entferneBenutzerAusBaum(Benutzer benutzer) {
		benutzerDatenProvider.getList().remove(benutzer);
	}

	public static class Category {

		private final String displayName;

		private Category(String displayName) {
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}
	}
	
	private static class CategoryCell extends AbstractCell<Category> {

	    @Override
	    public void render(Context context, Category value, SafeHtmlBuilder sb) {
	      if (value != null) {
	        sb.appendEscaped(value.getDisplayName());
	      }
	    }
	  }
	
	@Override
	// Get the NodeInfo that provides the children of the specified value.
	public <T> NodeInfo<?> getNodeInfo(T value) {

		if (value.equals("Root")) {
			// Erzeugen eines ListDataproviders fÃ¼r Customerdaten
			ListDataProvider<Category> kategorieDatenProvider = new ListDataProvider<Category>();
			
			List<Category> kategorien = new ArrayList<Category>();
			Category bauteil = new Category("Bauteile");
//			bauteil.setName("Bauteile");
			Category baugruppe = new Category("Baugruppen");
//			baugruppe.setName("Baugruppen");
			Category enderzeugnis = new Category("Enderzeugnisse");
//			enderzeugnis.setName("Enderzeugnisse");
			Category stueckliste = new Category("Stuecklisten");
//			stueckliste.setName("Stuecklisten");
			Category benutzer = new Category("Benutzer");
//			benutzer.setName("Benutzer");
			kategorien.add(bauteil);
			kategorien.add(baugruppe);
			kategorien.add(enderzeugnis);
			kategorien.add(stueckliste);
			kategorien.add(benutzer);
			
			for (Category c : kategorien) {
				kategorieDatenProvider.getList().add(c);
			}
			
			ClientEinstellungen.getLogger().severe("getNodeInfo Root Return Start");

			// Return a node info that pairs the data with a cell.
			return new DefaultNodeInfo<Category>(
					kategorieDatenProvider, new CategoryCell(),
					selectionModel, null);
		}

		if (value instanceof Category) {
			ClientEinstellungen.getLogger().severe("getNodeInfo: First Level Bauteile Start");
			// Erzeugen eines ListDataproviders fÃ¼r Account-Daten
			final ListDataProvider<Bauteil> bauteilDatenProvider = new ListDataProvider<Bauteil>();
			bauteilDatenProviders.put((Bauteil) value, bauteilDatenProvider);

			stuecklistenVerwaltung.holeAlleBauteile(new AsyncCallback<ArrayList<Bauteil>>() {
						@Override
						public void onFailure(Throwable t) {
							ClientEinstellungen.getLogger().severe("StkVerwaltung.holeAlleBauteile: AsyncCallback Failure!");
						}

						@Override
						public void onSuccess(ArrayList<Bauteil> bauteile) {
							for (Bauteil b : bauteile) {
								bauteilDatenProvider.getList().add(b);
							}
						}
					});

			// Return a node info that pairs the data with a cell.
//			return new DefaultNodeInfo<Bauteil>(bauteilDatenProvider,
//					new BauteilCell(), selectionModel, null);
		}
		if (value instanceof Geschaeftsobjekt && ((Geschaeftsobjekt) value).getName() == "Baugruppen") {
			// Erzeugen eines ListDataproviders fÃ¼r Account-Daten
			final ListDataProvider<Baugruppe> baugruppenDatenProvider = new ListDataProvider<Baugruppe>();
			baugruppenDatenProviders.put((Baugruppe) value, baugruppenDatenProvider);

			stuecklistenVerwaltung.holeAlleBaugruppen(new AsyncCallback<ArrayList<Baugruppe>>() {
						public void onFailure(Throwable t) {
						}

						public void onSuccess(ArrayList<Baugruppe> baugruppen) {
							for (Baugruppe b : baugruppen) {
								baugruppenDatenProvider.getList().add(b);
							}
						}
					});

			// Return a node info that pairs the data with a cell.
//			return new DefaultNodeInfo<Baugruppe>(baugruppenDatenProvider,
//					new BaugruppeCell(), selectionModel, null);
		}
		if (value instanceof Geschaeftsobjekt && ((Geschaeftsobjekt) value).getName() == "Enderzeugnisse") {
			// Erzeugen eines ListDataproviders fÃ¼r Account-Daten
			final ListDataProvider<Enderzeugnis> enderzeugnisDatenProvider = new ListDataProvider<Enderzeugnis>();
			enderzeugnisDatenProviders.put((Enderzeugnis) value, enderzeugnisDatenProvider);

			stuecklistenVerwaltung.holeAlleEnderzeugnisse(new AsyncCallback<ArrayList<Enderzeugnis>>() {
						@Override
						public void onFailure(Throwable t) {
						}

						@Override
						public void onSuccess(ArrayList<Enderzeugnis> enderzeugnisse) {
							for (Enderzeugnis e : enderzeugnisse) {
								enderzeugnisDatenProvider.getList().add(e);
							}
						}
					});

			// Return a node info that pairs the data with a cell.
//			return new DefaultNodeInfo<Enderzeugnis>(enderzeugnisDatenProvider,
//					new EnderzeugnisCell(), selectionModel, null);
		}
		if (value instanceof Geschaeftsobjekt && ((Geschaeftsobjekt) value).getName() == "Stuecklisten") {
			// Erzeugen eines ListDataproviders fÃ¼r Account-Daten
			final ListDataProvider<Stueckliste> stuecklistenDatenProvider = new ListDataProvider<Stueckliste>();
			stuecklistenDatenProviders.put((Stueckliste) value, stuecklistenDatenProvider);

			stuecklistenVerwaltung.holeAlleStuecklisten(new AsyncCallback<ArrayList<Stueckliste>>() {
						@Override
						public void onFailure(Throwable t) {
						}

						@Override
						public void onSuccess(ArrayList<Stueckliste> stuecklisten) {
							for (Stueckliste s : stuecklisten) {
								stuecklistenDatenProvider.getList().add(s);
							}
						}
					});

			// Return a node info that pairs the data with a cell.
//			return new DefaultNodeInfo<Stueckliste>(stuecklistenDatenProvider,
//					new StuecklisteCell(), selectionModel, null);
		}
		if (value instanceof Geschaeftsobjekt && ((Geschaeftsobjekt) value).getName() == "Benutzer") {
			// Erzeugen eines ListDataproviders fÃ¼r Account-Daten
			final ListDataProvider<Benutzer> benutzerDatenProvider = new ListDataProvider<Benutzer>();
			benutzerDatenProviders.put((Benutzer) value, benutzerDatenProvider);

			stuecklistenVerwaltung.holeAlleBenutzer(new AsyncCallback<ArrayList<Benutzer>>() {
						@Override
						public void onFailure(Throwable t) {
						}

						@Override
						public void onSuccess(ArrayList<Benutzer> stuecklisten) {
							for (Benutzer b : stuecklisten) {
								benutzerDatenProvider.getList().add(b);
							}
						}
					});

			// Return a node info that pairs the data with a cell.
//			return new DefaultNodeInfo<Benutzer>(benutzerDatenProvider,
//					new BenutzerCell(), selectionModel, null);
		}
		return null;
	}

	// Check if the specified value represents a leaf node. Leaf nodes
	// cannot be opened.
	@Override
	public boolean isLeaf(Object value) {
		// value is of type Account
		if (value instanceof Bauteil){
			return (value instanceof Bauteil);
		}
		else if (value instanceof Baugruppe){
			return (value instanceof Baugruppe);
		}
		else if (value instanceof Enderzeugnis){
			return (value instanceof Enderzeugnis);
		}
		else if (value instanceof Stueckliste){
			return (value instanceof Stueckliste);
		}
		else {
			return (value instanceof Benutzer);
		}
	}

}
