package de.hdm.team7.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.team7.shared.businessObjects.*;

public interface BOMAdministrationAsync {

	void init(AsyncCallback<Void> callback);

	void getBOMList(AsyncCallback<ArrayList<BillOfMaterial>> callback);

	void setBOMList(ArrayList<BillOfMaterial> bomList,
			AsyncCallback<Void> callback) throws IllegalArgumentException;

	// void createObject(String businessObjectType, String name, String
	// materialBezeichnung,
	// String beschreibung, ArrayList<Component> childrenComponents,
	// AsyncCallback<String> callback);
	void createComponent(Component component, AsyncCallback<String> callback);

	void createComponentAssembly(ComponentAssembly compAssembly,
			ArrayList<Component> childrenComponents,
			AsyncCallback<String> callback);

	void createEndProduct(EndProduct endproduct,
			ArrayList<Component> childrenComponents,
			AsyncCallback<String> callback);

	void createBillOfMaterial(BillOfMaterial bom,
			ComponentAssembly rootElement, AsyncCallback<String> callback);

	void createUser(User user, AsyncCallback<String> callback);

	void updateComponent(Component component, AsyncCallback<String> callback);

	void updateComponentAssembly(ComponentAssembly compAssembly,
			ArrayList<Component> childrenComponents,
			AsyncCallback<String> callback);

	void updateEndProduct(EndProduct endproduct,
			ArrayList<Component> childrenComponents,
			AsyncCallback<String> callback);

	void updateBillOfMaterial(BillOfMaterial bom,
			ComponentAssembly rootElement, AsyncCallback<String> callback);

	void updateUser(User user, AsyncCallback<String> callback);

	void assignComponentToComponentAssembly(ArrayList<Component> components,
			ArrayList<Component> compAssemblies, AsyncCallback<String> callback);

	void assignComponentToEndProduct(ArrayList<Component> components,
			ArrayList<Component> endProducts, AsyncCallback<String> callback);

	void assignComponentAssemblyToComponentAssembly(
			ArrayList<Component> selectedCompAssemblies,
			ArrayList<Component> assignableCompAssemblies,
			AsyncCallback<String> callback);

	void assignComponentAssemblyToEndProduct(
			ArrayList<Component> compAssemblies,
			ArrayList<Component> endProducts, AsyncCallback<String> callback);

	void assignEndProductToComponentAssembly(ArrayList<Component> endProducts,
			ArrayList<Component> compAssemblies, AsyncCallback<String> callback);

	void assignEndProductToEndProduct(ArrayList<Component> selectedEndProducts,
			ArrayList<Component> assignableEndProducts,
			AsyncCallback<String> callback);

	void deleteComponent(Component component, AsyncCallback<String> callback);

	void deleteComponentAssembly(ComponentAssembly compAssembly,
			AsyncCallback<String> callback);

	void deleteEndProduct(EndProduct endproduct, AsyncCallback<String> callback);

	void deleteBillOfMaterial(BillOfMaterial bom, AsyncCallback<String> callback);

	void deleteUser(User user, AsyncCallback<String> callback);

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

	void getComponentByName(String name,
			AsyncCallback<ArrayList<Component>> callback)
			throws IllegalArgumentException;

	void getComponentAssemblyByName(String name,
			AsyncCallback<ArrayList<ComponentAssembly>> callback)
			throws IllegalArgumentException;

	void getEndProductByName(String name,
			AsyncCallback<ArrayList<EndProduct>> callback)
			throws IllegalArgumentException;

	void getBillOfMaterialByName(String name,
			AsyncCallback<ArrayList<BillOfMaterial>> callback)
			throws IllegalArgumentException;

	void getUserByName(String name, AsyncCallback<ArrayList<User>> callback)
			throws IllegalArgumentException;

	void getAllComponents(AsyncCallback<ArrayList<Component>> callback)
			throws IllegalArgumentException;

	void getAllComponentAssemblies(
			AsyncCallback<ArrayList<ComponentAssembly>> callback)
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

	// void updateObject(String businessObjectType, String name, String
	// materialBezeichnung,
	// String beschreibung, ArrayList<Component> childrenComponents,
	// AsyncCallback<BusinessObject> callback);
	// void deleteObject(String businessObjectType, String name, String
	// materialBezeichnung,
	// String beschreibung, ArrayList<Component> childrenComponents,
	// AsyncCallback<BusinessObject> callback);
	// void create(BusinessObject bo, AsyncCallback<Void> callback);
	// void getBusinessObjectById(String businessObjectType, int id,
	// AsyncCallback<BusinessObject> callback) throws IllegalArgumentException;
	// void getBusinessObjectByName(String businessObjectType, String name,
	// AsyncCallback<BusinessObject> callback) throws IllegalArgumentException;
	// void getAllBusinessObjects(String businessObjectType,
	// AsyncCallback<ArrayList<BusinessObject>> callback) throws
	// IllegalArgumentException;
	// void getChildrenObjectsOf(BusinessObject bo, String businessObjectType,
	// AsyncCallback<ArrayList<BusinessObject>> callback) throws
	// IllegalArgumentException;
	// void update(BusinessObject bo, AsyncCallback<Void> callback) throws
	// IllegalArgumentException;
	// void delete(BusinessObject bo, AsyncCallback<Void> callback) throws
	// IllegalArgumentException;

}
