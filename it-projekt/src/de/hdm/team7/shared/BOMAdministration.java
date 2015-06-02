package de.hdm.team7.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.team7.shared.businessObjects.*;

@RemoteServiceRelativePath("bomadministration")
public interface BOMAdministration extends RemoteService {

	/**
	 * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von
	 * GWT RPC zusÃ¤tzlich zum No Argument Constructor der implementierenden
	 * Klasse {@link BOMAdministrationImpl} notwendig. Bitte diese Methode
	 * direkt nach der Instantiierung aufrufen.
	 * 
	 * @throws IllegalArgumentException
	 */
	public void init() throws IllegalArgumentException;

	// Hinzufügen oder Abfragen von bestimmten Stücklisten für eine Instanz
	// dieser BOMAdministration
	public ArrayList<BillOfMaterial> getBOMList()
			throws IllegalArgumentException;

	public void setBOMList(ArrayList<BillOfMaterial> bomList)
			throws IllegalArgumentException;

	/** GESCHÄFTSOBJEKTE ERSTELLEN (FÜR HANDLING IN APP) **/
	public Component createComponent(Component component);

	public ComponentAssembly createComponentAssembly(ComponentAssembly compAssembly);

	public EndProduct createEndProduct(EndProduct endproduct);

	public BillOfMaterial createBillOfMaterial(BillOfMaterial bom);

	public User createUser(User user);

	/** GESCHÄFTSOBJEKTE AKTUALISIEREN (Attribute ändern, Zuordnungen machen) **/
	public Component updateComponent(Component component);

	public ComponentAssembly updateComponentAssembly(ComponentAssembly compAssembly);

	public EndProduct updateEndProduct(EndProduct endproduct);

	public BillOfMaterial updateBillOfMaterial(BillOfMaterial bom);

	public User updateUser(User user);

//	// Zuordnungen machen
//	public String assignComponentToComponentAssembly(
//			ArrayList<Component> components, ArrayList<Component> compAssemblies);
//
//	public String assignComponentToEndProduct(ArrayList<Component> components,
//			ArrayList<Component> endProducts);
//
//	public String assignComponentAssemblyToComponentAssembly(
//			ArrayList<Component> selectedCompAssemblies,
//			ArrayList<Component> assignableCompAssemblies);
//
//	public String assignComponentAssemblyToEndProduct(
//			ArrayList<Component> compAssemblies,
//			ArrayList<Component> endProducts);
//
//	public String assignEndProductToComponentAssembly(
//			ArrayList<Component> endProducts,
//			ArrayList<Component> compAssemblies);
//
//	public String assignEndProductToEndProduct(
//			ArrayList<Component> selectedEndProducts,
//			ArrayList<Component> assignableEndProducts);

	/** GESCHÄFTSOBJEKTE LÖSCHEN **/
	public ArrayList<Component> deleteComponent(Component component);

	public ArrayList<ComponentAssembly> deleteComponentAssembly(ComponentAssembly compAssembly);

	public ArrayList<EndProduct> deleteEndProduct(EndProduct endproduct);

	public ArrayList<BillOfMaterial> deleteBillOfMaterial(BillOfMaterial bom);

	public ArrayList<User> deleteUser(User user);

	/** GESCHÄFTSOBJEKTE SUCHEN/AUSLESEN **/
	// TODO: flexible Erweiterung entsprechend der Benutzungsschnittstelle
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
}
