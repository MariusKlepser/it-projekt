package de.hdm.team7.client.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

import de.hdm.team7.client.ClientsideSettings;
import de.hdm.team7.shared.BOMAdministrationAsync;
import de.hdm.team7.shared.businessObjects.*;

public class BusinessObjectTreeViewModel implements TreeViewModel {

	/**
	 * Diese Implementierung des TreeViewModels sorgt fÃ¼r die Verwaltung des
	 * Kunden- und Kontenbaumes.
	 * 
	 * @author Christian Rathke
	 * 
	 */

		// private StuecklistenAnlegenFormular stuecklistenAnlegenFormular;
		// private StuecklistenBearbeitenFormular
		// stuecklistenBearbeitenFormular;
		// private StuecklistenLoeschenFormular stuecklistenLoeschenFormular;
		//
		// private BaugruppenAnlegenFormular baugruppenAnlegenFormular;
		// private BaugruppenBearbeitenFormular baugruppenBearbeitenFormular;
		// private BaugruppenLoeschenFormular baugruppenLoeschenFormular;
		//
		// private BauteilAnlegenFormular bauteilAnlegenFormular;
		// private BauteilBearbeitenFormular bauteilBearbeitenFormular;
		// private BauteilAnlegenFormular bauteilLoeschenFormular;
		//
		// private EnderzeugnisAnlegenFormular enderzeugnisAnlegenFormular;
		// private EnderzeugnisBearbeitenFormular
		// enderzeugnisBearbeitenFormular;
		// private EnderzeugnisLoeschenFormular enderzeugnisLoeschenFormular;

		private BillOfMaterial selektierteStueckliste = null;
		private ComponentAssembly selektierteBaugruppe = null;
		private Component selektiertesBauteil = null;
		private EndProduct selektiertesEnderzeugnis = null;

		private BOMAdministrationAsync bomVerwaltung = null;
		private ListDataProvider<BillOfMaterial> StuecklistenDatenProvider = null;
		private ListDataProvider<BillOfMaterial> BaugruppenDatenProvider = null;
		private ListDataProvider<BillOfMaterial> BauteilDatenProvider = null;
		private ListDataProvider<BillOfMaterial> EnderzeugnisDatenProvider = null;
		/*
		 * In dieser Map merken wir uns die ListDataProviders fÃ¼r die
		 * Kontolisten der im Kunden- und Kontobaum expandierten Kundenknoten.
		 */
		// private Map<Customer, ListDataProvider<Account>> accountDataProviders
		// = null;

		/**
		 * Bildet BusinessObjects auf eindeutige Zahlenobjekte ab, die als
		 * SchlÃ¼ssel fÃ¼r Baumknoten dienen. Dadurch werden im Selektionsmodell
		 * alle Objekte mit derselben id selektiert, wenn eines davon selektiert
		 * wird. Der SchlÃ¼ssel fÃ¼r Kundenobjekte ist eine positive, der fÃ¼r
		 * Kontenobjekte eine negative Zahl, die sich jeweils aus der id des
		 * Objektes ergibt. Dadurch kÃ¶nnen Kunden- von Kontenobjekten
		 * unterschieden werden, auch wenn sie dieselbe id haben.
		 */
		// //private class BusinessObjectKeyProvider implements
		// ProvidesKey<BusinessObject> {
		// @Override
		// public Integer getKey(BusinessObject bo) {
		// if (bo == null) {
		// return null;
		// }
		// if (bo instanceof Customer) {
		// return new Integer(bo.getId());
		// } else {
		// return new Integer(-bo.getId());
		// }
		// }
		// };

		// private BusinessObjectKeyProvider boKeyProvider = null;
		private SingleSelectionModel<BusinessObject> selectionModel = null;

		/**
		 * Nested Class fÃ¼r die Reaktion auf Selektionsereignisse. Als Folge
		 * einer Baumknotenauswahl wird je nach Typ des Business-Objekts der
		 * "selectedCustomer" bzw. das "selectedAccount" gesetzt.
		 */
		private class SelectionChangeEventHandler implements
				SelectionChangeEvent.Handler {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				BusinessObject selection = selectionModel.getSelectedObject();
				if (selection instanceof Component) {
					setzeSelektiertesBauteil((Component) selection);
				} else if (selection instanceof ComponentAssembly) {
					setzeSelektierteBaugruppe((ComponentAssembly) selection);
				} else if (selection instanceof EndProduct) {
					setzeSelektiertesEndprodukt((EndProduct) selection);
				} else if (selection instanceof BillOfMaterial) {
					setzeSelektierteStueckliste((BillOfMaterial) selection);
				}
			}
		}

		/*
		 * Im Konstruktor werden die fÃ¼r den Kunden- und Kontobaum wichtigen
		 * lokalen Variaben initialisiert.
		 */
		public BusinessObjectTreeViewModel() {
			bomVerwaltung = ClientsideSettings.getBOMAdministration();
			// boKeyProvider = new BusinessObjectKeyProvider();
			selectionModel = new SingleSelectionModel<BusinessObject>();
			selectionModel
					.addSelectionChangeHandler(new SelectionChangeEventHandler());
			// accountDataProviders = new HashMap<Customer,
			// ListDataProvider<Account>>();
		}

		// void setCustomerForm(CustomerForm cf) {
		// customerForm = cf;
		// }
		//
		// void setAccountForm(AccountForm af) {
		// accountForm = af;
		// }
		//
		// Customer getSelectedCustomer() {
		// return selectedCustomer;
		// }
		//
		// void setSelectedCustomer(Customer c) {
		// selectedCustomer = c;
		// customerForm.setSelected(c);
		// selectedAccount = null;
		// accountForm.setSelected(null);
		// }
		//
		// Account getSelectedAccount() {
		// return selectedAccount;
		// }

		// /*
		// * Wenn ein Konto ausgewÃ¤hlt wird, wird auch der ausgewÃ¤hlte Kunde
		// * angepasst.
		// */
		// void setSelectedAccount(Account a) {
		// selectedAccount = a;
		// accountForm.setSelected(a);
		//
		// if (a != null) {
		// bankVerwaltung.getCustomerById(a.getOwnerID(),
		// new AsyncCallback<Customer>() {
		// @Override
		// public void onFailure(Throwable caught) {
		// }
		//
		// @Override
		// public void onSuccess(Customer customer) {
		// selectedCustomer = customer;
		// customerForm.setSelected(customer);
		// }
		// });
		// }
		// }
		//
		// /*
		// * Wenn ein Kunde neu erzeugt wurde, wird er selektiert.
		// */
		// void addCustomer(Customer customer) {
		// customerDataProvider.getList().add(customer);
		// selectionModel.setSelected(customer, true);
		// }
		//
		// void updateCustomer(Customer customer) {
		// List<Customer> customerList = customerDataProvider.getList();
		// int i = 0;
		// for (Customer c : customerList) {
		// if (c.getId() == customer.getId()) {
		// customerList.set(i, customer);
		// break;
		// } else {
		// i++;
		// }
		// }
		// customerDataProvider.refresh();
		// }
		//
		// void removeCustomer(Customer customer) {
		// customerDataProvider.getList().remove(customer);
		// accountDataProviders.remove(customer);
		// }
		//
		// void addAccountOfCustomer(Account account, Customer customer) {
		// // falls es noch keinen Account Provider fÃ¼r diesen Customer gibt,
		// // wurde der Baumknoten noch nicht geÃ¶ffnet und wir brauchen nichts
		// tun.
		// if (!accountDataProviders.containsKey(customer)) {
		// return;
		// }
		// ListDataProvider<Account> accountsProvider = accountDataProviders
		// .get(customer);
		// if (!accountsProvider.getList().contains(account)) {
		// accountsProvider.getList().add(account);
		// }
		// selectionModel.setSelected(account, true);
		// }
		//
		// void removeAccountOfCustomer(Account account, Customer customer) {
		// // falls es keinen Account Provider fÃ¼r diesen Customer gibt,
		// // wurde der Baumknoten noch nicht geÃ¶ffnet und wir brauchen nichts
		// tun.
		// if (!accountDataProviders.containsKey(customer)) {
		// return;
		// }
		// accountDataProviders.get(customer).getList().remove(account);
		// selectionModel.setSelected(customer, true);
		// }
		//
		// /*
		// * Ein Konto in der Baumstruktur soll ersetzt werden durch ein Konto
		// mit derselben id.
		// * Dies ist sinnvoll, wenn sich die Eigenschaften eines Kontos
		// geÃ¤ndert haben und in der
		// * Baumstruktur noch ein "veraltetes" Kontoobjekt enthalten ist.
		// */
		// void updateAccount(Account a) {
		// bankVerwaltung.getCustomerById(a.getOwnerID(),
		// new UpdateAccountCallback(a));
		// }
		//
		// private class UpdateAccountCallback implements
		// AsyncCallback<Customer> {
		//
		// Account account = null;
		//
		// UpdateAccountCallback(Account a) {
		// account = a;
		// }
		//
		// @Override
		// public void onFailure(Throwable t) {
		// }
		//
		// @Override
		// public void onSuccess(Customer customer) {
		// List<Account> accountList = accountDataProviders.get(customer)
		// .getList();
		// for (int i=0; i<accountList.size(); i++) {
		// if (account.getId() == accountList.get(i).getId()) {
		// accountList.set(i, account);
		// break;
		// }
		// }
		// }
		// }

		public void setzeSelektierteStueckliste(BillOfMaterial selection) {
			// TODO Auto-generated method stub

		}

		public void setzeSelektiertesEndprodukt(EndProduct selection) {
			// TODO Auto-generated method stub

		}

		public void setzeSelektierteBaugruppe(ComponentAssembly selection) {
			// TODO Auto-generated method stub

		}

		public void setzeSelektiertesBauteil(Component selection) {
			// TODO Auto-generated method stub

		}

	@Override
	// Get the NodeInfo that provides the children of the specified value.
	public <T> NodeInfo<?> getNodeInfo(T value) {

		if (value.equals("Root")) {
			// Erzeugen eines ListDataproviders fÃ¼r Customerdaten
			ListDataProvider<BusinessObject> kategorieDatenProvider = new ListDataProvider<BusinessObject>();

			List<BusinessObject> kategorien = new ArrayList<BusinessObject>();
			kategorien.add(new Component());
			kategorien.add(new ComponentAssembly());
			kategorien.add(new EndProduct());
			kategorien.add(new BillOfMaterial());
			kategorien = kategorieDatenProvider.getList();

			// Return a node info that pairs the data with a cell.
			return new DefaultNodeInfo<BusinessObject>(kategorieDatenProvider,
					new BusinessObjectCell(), selectionModel, null);
		}

		// if (value instanceof Customer) {
		// // Erzeugen eines ListDataproviders fÃ¼r Account-Daten
		// final ListDataProvider<Account> accountsProvider = new
		// ListDataProvider<Account>();
		// accountDataProviders.put((Customer) value, accountsProvider);
		//
		// bankVerwaltung.getAccountsOf((Customer) value,
		// new AsyncCallback<Vector<Account>>() {
		// @Override
		// public void onFailure(Throwable t) {
		// }
		//
		// @Override
		// public void onSuccess(Vector<Account> accounts) {
		// for (Account a : accounts) {
		// accountsProvider.getList().add(a);
		// }
		// }
		// });
		//
		// // Return a node info that pairs the data with a cell.
		// return new DefaultNodeInfo<Account>(accountsProvider,
		// new AccountCell(), selectionModel, null);
		// }
		return null;
	}

	// Check if the specified value represents a leaf node. Leaf nodes
	// cannot be opened.
	@Override
	public boolean isLeaf(Object value) {
		// value is of type Account
		return (value instanceof BusinessObject);
	}

}
