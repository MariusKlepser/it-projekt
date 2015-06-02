package de.hdm.team7.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.team7.shared.businessObjects.*;

public interface BOMAdministrationAsync {

	void init(AsyncCallback<Void> callback);

	void getBOMList(AsyncCallback<ArrayList<BillOfMaterial>> callback);

	void setBOMList(ArrayList<BillOfMaterial> bomList,
			AsyncCallback<Void> callback) throws IllegalArgumentException;

	/** GESCHÄFTSOBJEKTE ERSTELLEN (FÜR HANDLING IN APP) **/
	void createComponent(Component component, AsyncCallback<Component> callback);

	void createComponentAssembly(ComponentAssembly compAssembly,
			AsyncCallback<ComponentAssembly> callback);

	void createEndProduct(EndProduct endproduct,
			AsyncCallback<EndProduct> callback);

	void createBillOfMaterial(BillOfMaterial bom, AsyncCallback<BillOfMaterial> callback);

	void createUser(User user, AsyncCallback<User> callback);

	/** GESCHÄFTSOBJEKTE AKTUALISIEREN (Attribute ändern, Zuordnungen machen) **/
	void updateComponent(Component component, AsyncCallback<Component> callback);

	void updateComponentAssembly(ComponentAssembly compAssembly,
			AsyncCallback<ComponentAssembly> callback);

	void updateEndProduct(EndProduct endproduct,
			AsyncCallback<EndProduct> callback);

	void updateBillOfMaterial(BillOfMaterial bom, AsyncCallback<BillOfMaterial> callback);

	void updateUser(User user, AsyncCallback<User> callback);

//	// Zuordnungen machen
//	void assignComponentToComponentAssembly(ArrayList<Component> components,
//			ArrayList<Component> compAssemblies, AsyncCallback<String> callback);
//
//	void assignComponentToEndProduct(ArrayList<Component> components,
//			ArrayList<Component> endProducts, AsyncCallback<String> callback);
//
//	void assignComponentAssemblyToComponentAssembly(
//			ArrayList<Component> selectedCompAssemblies,
//			ArrayList<Component> assignableCompAssemblies,
//			AsyncCallback<String> callback);
//
//	void assignComponentAssemblyToEndProduct(
//			ArrayList<Component> compAssemblies,
//			ArrayList<Component> endProducts, AsyncCallback<String> callback);
//
//	void assignEndProductToComponentAssembly(ArrayList<Component> endProducts,
//			ArrayList<Component> compAssemblies, AsyncCallback<String> callback);
//
//	void assignEndProductToEndProduct(ArrayList<Component> selectedEndProducts,
//			ArrayList<Component> assignableEndProducts,
//			AsyncCallback<String> callback);

	/** GESCHÄFTSOBJEKTE LÖSCHEN **/
	void deleteComponent(Component component, AsyncCallback<ArrayList<Component>> callback);

	void deleteComponentAssembly(ComponentAssembly compAssembly,
			AsyncCallback<ArrayList<ComponentAssembly>> callback);

	void deleteEndProduct(EndProduct endproduct, AsyncCallback<ArrayList<EndProduct>> callback);

	void deleteBillOfMaterial(BillOfMaterial bom, AsyncCallback<ArrayList<BillOfMaterial>> callback);

	void deleteUser(User user, AsyncCallback<ArrayList<User>> callback);

	/** GESCHÄFTSOBJEKTE SUCHEN/AUSLESEN **/
	// TODO: flexible Erweiterung entsprechend der Benutzungsschnittstelle
	void getComponentById(int id, AsyncCallback<Component> callback)
			throws IllegalArgumentException;

	void getComponentAssemblyById(int id,
			AsyncCallback<ComponentAssembly> callback)
			throws IllegalArgumentException;

	void getEndProductById(int id, AsyncCallback<EndProduct> callback)
			throws IllegalArgumentException;

	void getBillOfMaterialById(int id, AsyncCallback<BillOfMaterial> callback)
			throws IllegalArgumentException;

	void getUserById(int id, AsyncCallback<User> callback)
			throws IllegalArgumentException;

	void getComponentByName(String name, AsyncCallback<ArrayList<Component>> callback)
			throws IllegalArgumentException;

	void getComponentAssemblyByName(String name,
			AsyncCallback<ArrayList<ComponentAssembly>> callback)
			throws IllegalArgumentException;

	void getEndProductByName(String name, AsyncCallback<ArrayList<EndProduct>> callback)
			throws IllegalArgumentException;

	void getBillOfMaterialByName(String name,
			AsyncCallback<ArrayList<BillOfMaterial>> callback)
			throws IllegalArgumentException;

	void getUserByName(String name, AsyncCallback<ArrayList<User>> callback)
			throws IllegalArgumentException;

	void getAllComponents(AsyncCallback<ArrayList<Component>> callback)
			throws IllegalArgumentException;

	void getAllComponentAssemblies(AsyncCallback<ArrayList<ComponentAssembly>> callback)
			throws IllegalArgumentException;

	void getAllEndProducts(AsyncCallback<ArrayList<EndProduct>> callback)
			throws IllegalArgumentException;

	void getAllBillsOfMaterial(AsyncCallback<ArrayList<BillOfMaterial>> callback)
			throws IllegalArgumentException;

	void getAllUsers(AsyncCallback<ArrayList<User>> callback)
			throws IllegalArgumentException;

	void getChildrenObjectsOf(ComponentAssembly compAss,
			AsyncCallback<ArrayList<Component>> callback)
			throws IllegalArgumentException;

	void getChildrenObjectsOf(EndProduct endproduct,
			AsyncCallback<ArrayList<Component>> callback)
			throws IllegalArgumentException;

}
