package de.hdm.team7.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.team7.shared.businessObjects.*;

@RemoteServiceRelativePath("bomadministration")
public interface BOMAdministration extends RemoteService {

	/**
	 * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von
	 * GWT RPC zusätzlich zum No Argument Constructor der implementierenden
	 * Klasse {@link BOMAdministrationImpl} notwendig. Bitte diese Methode
	 * direkt nach der Instantiierung aufrufen.
	 * 
	 * @throws IllegalArgumentException
	 */
	public void init() throws IllegalArgumentException;

	// Hinzuf�gen oder Abfragen von bestimmten St�cklisten f�r eine
	// Instanz dieser BOMAdministration
	public ArrayList<BillOfMaterial> getBOMList()
			throws IllegalArgumentException;

	public void setBOMList(ArrayList<BillOfMaterial> bomList)
			throws IllegalArgumentException;

	/**
	 * GESCH�FTSOBJEKTE ERSTELLEN (F�R HANDLING IN APP) // Eine Instanz vom
	 * Typ BusinessObject anlegen (alle anderen Gesch�ftsobjekte enthalten);
	 * // realisiert �ber optionale Parameter bei �bergabe; // Eine einzige
	 * Methode zum Erstellen von Objekten (Bauteil, Baugruppe, etc.)
	 **/
	// public String createObject(String businessObjectType, String name, String
	// materialBezeichnung,
	// String beschreibung, ArrayList<Component> childrenComponents);

	public String createComponent(Component component);

	public String createComponentAssembly(ComponentAssembly compAssembly,
			ArrayList<Component> childrenComponents);

	public String createEndProduct(EndProduct endproduct,
			ArrayList<Component> childrenComponents);

	public String createBillOfMaterial(BillOfMaterial bom,
			ComponentAssembly rootElement);

	public String createUser(User user);

	public String updateComponent(Component component);

	public String updateComponentAssembly(ComponentAssembly compAssembly,
			ArrayList<Component> childrenComponents);

	public String updateEndProduct(EndProduct endproduct,
			ArrayList<Component> childrenComponents);

	public String updateBillOfMaterial(BillOfMaterial bom,
			ComponentAssembly rootElement);

	public String updateUser(User user);

	public String assignComponentToComponentAssembly(
			ArrayList<Component> components, ArrayList<Component> compAssemblies);

	public String assignComponentToEndProduct(ArrayList<Component> components,
			ArrayList<Component> endProducts);

	public String assignComponentAssemblyToComponentAssembly(
			ArrayList<Component> selectedCompAssemblies,
			ArrayList<Component> assignableCompAssemblies);

	public String assignComponentAssemblyToEndProduct(
			ArrayList<Component> compAssemblies,
			ArrayList<Component> endProducts);

	public String assignEndProductToComponentAssembly(
			ArrayList<Component> endProducts,
			ArrayList<Component> compAssemblies);

	public String assignEndProductToEndProduct(
			ArrayList<Component> selectedEndProducts,
			ArrayList<Component> assignableEndProducts);

	public String deleteComponent(Component component);

	public String deleteComponentAssembly(ComponentAssembly compAssembly);

	public String deleteEndProduct(EndProduct endproduct);

	public String deleteBillOfMaterial(BillOfMaterial bom);

	public String deleteUser(User user);

	public Component getComponentById(int id) throws IllegalArgumentException;

	public ComponentAssembly getComponentAssemblyById(int id)
			throws IllegalArgumentException;

	public EndProduct getEndProductById(int id) throws IllegalArgumentException;

	public BillOfMaterial getBillOfMaterialById(int id)
			throws IllegalArgumentException;

	public User getUserById(int id) throws IllegalArgumentException;

	public ArrayList<Component> getComponentByName(String name)
			throws IllegalArgumentException;

	public ArrayList<ComponentAssembly> getComponentAssemblyByName(String name)
			throws IllegalArgumentException;

	public ArrayList<EndProduct> getEndProductByName(String name)
			throws IllegalArgumentException;

	public ArrayList<BillOfMaterial> getBillOfMaterialByName(String name)
			throws IllegalArgumentException;

	public ArrayList<User> getUserByName(String name)
			throws IllegalArgumentException;

	public ArrayList<Component> getAllComponents()
			throws IllegalArgumentException;

	public ArrayList<ComponentAssembly> getAllComponentAssemblies()
			throws IllegalArgumentException;

	public ArrayList<EndProduct> getAllEndProducts()
			throws IllegalArgumentException;

	public ArrayList<BillOfMaterial> getAllBillsOfMaterial()
			throws IllegalArgumentException;

	public ArrayList<User> getAllUsers() throws IllegalArgumentException;

	public ArrayList<Component> getChildrenObjectsOf(ComponentAssembly compAss)
			throws IllegalArgumentException;

	public ArrayList<Component> getChildrenObjectsOf(EndProduct endproduct)
			throws IllegalArgumentException;

	// public BusinessObject updateObject(String businessObjectType, String
	// name, String materialBezeichnung,
	// String beschreibung, ArrayList<Component> childrenComponents);
	//
	// public BusinessObject deleteObject(String businessObjectType, String
	// name, String materialBezeichnung,
	// String beschreibung, ArrayList<Component> childrenComponents);

	// GESCH�FTSOBJEKTE AUF DB ANLEGEN
	// public void create(BusinessObject bo) throws IllegalArgumentException;

	// GESCH�FTSOBJEKTE SUCHEN/AUSLESEN
	// TODO: flexible Erweiterung entsprechend der Benutzungsschnittstelle
	// public BusinessObject getBusinessObjectById(String businessObjectType,
	// int id) throws IllegalArgumentException;
	// public BusinessObject getBusinessObjectByName(String businessObjectType,
	// String name) throws IllegalArgumentException;
	// public ArrayList<BusinessObject> getAllBusinessObjects(String
	// businessObjectType) throws IllegalArgumentException;
	// public ArrayList<BusinessObject> getChildrenObjectsOf(BusinessObject bo,
	// String businessObjectType) throws IllegalArgumentException;

	// // GESCH�FTSOBJEKTE AKTUALISIEREN
	// public void update(BusinessObject bo) throws IllegalArgumentException;
	//
	// // GESCH�FTSOBJEKTE L�SCHEN
	// public void delete(BusinessObject bo) throws IllegalArgumentException;
}
