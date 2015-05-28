package de.hdm.team7.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.team7.server.ServersideSettings;
import de.hdm.team7.server.database.*;
import de.hdm.team7.shared.BOMAdministration;
import de.hdm.team7.shared.businessObjects.*;

@SuppressWarnings("serial")
public class BOMAdministrationImpl extends RemoteServiceServlet implements
		BOMAdministration {

	private ArrayList<BillOfMaterial> bomList = null;
	private BillOfMaterialMapper bomMapper = null;
	private ComponentAssemblyMapper compAssMapper = null;
	private ComponentMapper compMapper = null;
	private EndProductMapper endProductMapper = null;
	private UserMapper userMapper = null;

	public BOMAdministrationImpl() throws IllegalArgumentException {
		/*
		 * Eine weitergehende Funktion muss der No-Argument-Constructor nicht
		 * haben. Er muss einfach vorhanden sein.
		 */
	}

	@Override
	public void init() throws IllegalArgumentException {
		/*
		 * Ganz wesentlich ist, dass die BOMAdministration einen vollst�ndigen
		 * Satz von Mappern besitzt, mit deren Hilfe sie dann mit der Datenbank
		 * kommunizieren kann.
		 */
		this.bomMapper = BillOfMaterialMapper.bomMapper();
		this.compAssMapper = ComponentAssemblyMapper.componentAssemblyMapper();
		this.compMapper = ComponentMapper.componentMapper();
		this.endProductMapper = EndProductMapper.endProductMapper();
		this.userMapper = UserMapper.userMapper();
	}

	public ArrayList<BillOfMaterial> getBOMList() {
		return bomList;
	}

	public void setBOMList(ArrayList<BillOfMaterial> bomList) {
		this.bomList = bomList;
	}

	public String createComponent(Component component) {
		String message;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		message = "Start createComponent(); ";
		message = message + "creating Component; ";
		component.setChangeDate(sdf.format(date));
		message = message + sdf.format(new Date()) + "; ";
		message = message + "Component created; ";
		this.compMapper.insert(component);
		message = message + this.compMapper.getLog();
		message = message + "Component sent to Mapper";
		return message;
	}

	@Override
	public String createComponentAssembly(ComponentAssembly compAssembly,
			ArrayList<Component> childrenComponents) {
		String message;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		message = "Start createComponentAssembly(); ";
		message = message + "creating ComponentAssembly; ";
		compAssembly.setChangeDate(sdf.format(date));
		message = message + sdf.format(new Date()) + "; ";
		message = message + "ComponentAssembly created; ";
		this.compAssMapper.insert(compAssembly);
		message = message + this.compAssMapper.getLog();
		message = message + "ComponentAssembly sent to Mapper";
		return message;
	}

	@Override
	public String createEndProduct(EndProduct endproduct,
			ArrayList<Component> childrenComponents) {
		String message;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		message = "Start createEndProduct(); ";
		message = message + "creating EndProduct; ";
		endproduct.setChangeDate(sdf.format(date));
		message = message + sdf.format(new Date()) + "; ";
		message = message + "EndProduct created; ";
		this.endProductMapper.insert(endproduct);
		message = message + this.endProductMapper.getLog();
		message = message + "EndProduct sent to Mapper";
		return message;
	}

	@Override
	public String createBillOfMaterial(BillOfMaterial bom,
			ComponentAssembly rootElement) {
		String message;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		message = "Start createBillOfMaterial(); ";
		message = message + "creating BOM; ";
		bom.setCreationDate(sdf.format(date));
		message = message + sdf.format(new Date()) + "; ";
		message = message + "BOM created; ";
		this.bomMapper.insert(bom);
		message = message + this.bomMapper.getLog();
		message = message + "BOM sent to Mapper";
		return message;
	}

	@Override
	public String createUser(User user) {
		String message;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		message = "Start createUser(); ";
		message = message + "creating User; ";
//		user.setChangeDate(sdf.format(date));
		message = message + sdf.format(new Date()) + "; ";
		message = message + "User created; ";
		this.userMapper.insert(user);
		message = message + this.userMapper.getLog();
		message = message + "User sent to Mapper";
		return message;
	}

	@Override
	public String updateComponent(Component component) {
		this.compMapper.update(component);
		return null;
	}

	@Override
	public String updateComponentAssembly(ComponentAssembly compAssembly,
			ArrayList<Component> childrenComponents) {
		this.compAssMapper.update(compAssembly);
		return null;
	}

	@Override
	public String updateEndProduct(EndProduct endproduct,
			ArrayList<Component> childrenComponents) {
		this.endProductMapper.update(endproduct);
		return null;
	}

	@Override
	public String updateBillOfMaterial(BillOfMaterial bom,
			ComponentAssembly rootElement) {
		this.bomMapper.update(bom);
		return null;
	}

	@Override
	public String updateUser(User user) {
		this.userMapper.update(user);
		return null;
	}

	@Override
	public String assignComponentToComponentAssembly(
			ArrayList<Component> components, ArrayList<Component> compAssemblies) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String assignComponentToEndProduct(ArrayList<Component> components,
			ArrayList<Component> endProducts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String assignComponentAssemblyToComponentAssembly(
			ArrayList<Component> selectedCompAssemblies,
			ArrayList<Component> assignableCompAssemblies) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String assignComponentAssemblyToEndProduct(
			ArrayList<Component> compAssemblies,
			ArrayList<Component> endProducts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String assignEndProductToComponentAssembly(
			ArrayList<Component> endProducts,
			ArrayList<Component> compAssemblies) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String assignEndProductToEndProduct(
			ArrayList<Component> selectedEndProducts,
			ArrayList<Component> assignableEndProducts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteComponent(Component component) {
		this.compMapper.delete(component);
		return null;
	}

	@Override
	public String deleteComponentAssembly(ComponentAssembly compAssembly) {
		this.compAssMapper.delete(compAssembly);
		return null;
	}

	@Override
	public String deleteEndProduct(EndProduct endproduct) {
		this.endProductMapper.delete(endproduct);
		return null;
	}

	@Override
	public String deleteBillOfMaterial(BillOfMaterial bom) {
		this.bomMapper.delete(bom);
		return null;
	}

	@Override
	public String deleteUser(User user) {
		this.userMapper.delete(user);
		return null;
	}

	@Override
	public Component getComponentById(int id) throws IllegalArgumentException {
		return this.compMapper.findByKey(id);
	}

	@Override
	public ComponentAssembly getComponentAssemblyById(int id)
			throws IllegalArgumentException {
		return this.compAssMapper.findByKey(id);
	}

	@Override
	public EndProduct getEndProductById(int id) throws IllegalArgumentException {
		return this.endProductMapper.findByKey(id);
	}

	@Override
	public BillOfMaterial getBillOfMaterialById(int id)
			throws IllegalArgumentException {
		return this.bomMapper.findByKey(id);
	}

	@Override
	public User getUserById(int id) throws IllegalArgumentException {
		return this.userMapper.findByKey(id);
	}

	@Override
	public ArrayList<Component> getComponentByName(String name)
			throws IllegalArgumentException {
		return this.compMapper.findByName(name);
	}

	@Override
	public ArrayList<ComponentAssembly> getComponentAssemblyByName(String name)
			throws IllegalArgumentException {
		return this.compAssMapper.findByName(name);
	}

	@Override
	public ArrayList<EndProduct> getEndProductByName(String name)
			throws IllegalArgumentException {
		return this.endProductMapper.findByName(name);
	}

	@Override
	public ArrayList<BillOfMaterial> getBillOfMaterialByName(String name)
			throws IllegalArgumentException {
		return this.bomMapper.findByName(name);
	}

	@Override
	public ArrayList<User> getUserByName(String name)
			throws IllegalArgumentException {
		return this.userMapper.findByName(name);
	}

	@Override
	public ArrayList<Component> getAllComponents()
			throws IllegalArgumentException {
		return this.compMapper.findAll();
	}

	@Override
	public ArrayList<ComponentAssembly> getAllComponentAssemblies()
			throws IllegalArgumentException {
		return this.compAssMapper.findAll();
	}

	@Override
	public ArrayList<EndProduct> getAllEndProducts()
			throws IllegalArgumentException {
		return this.endProductMapper.findAll();
	}

	@Override
	public ArrayList<BillOfMaterial> getAllBillsOfMaterial()
			throws IllegalArgumentException {
		return this.bomMapper.findAll();
	}

	@Override
	public ArrayList<User> getAllUsers() throws IllegalArgumentException {
		return this.userMapper.findAll();
	}

	@Override
	public ArrayList<Component> getChildrenObjectsOf(ComponentAssembly compAss)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Component> getChildrenObjectsOf(EndProduct endproduct)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	// public String createObject(String businessObjectType, String name,
	// String materialBezeichnung, String beschreibung,
	// ArrayList<Component> childrenComponents) {
	// String message;
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	// Date date = new Date();
	//
	// message = "Start createObject(); ";
	// // if(businessObjectType == "bauteil")
	// // {
	// // System.out.println("Server: Received Data");
	// // GWT.log("Server: Received Data!");
	// Component comp = new Component();
	// message = message + "creating Object; ";
	// comp.setName(name);
	// comp.setMaterialIdentifier(materialBezeichnung);
	// comp.setDescription(beschreibung);
	// comp.setChangeDate(sdf.format(date));
	// message = message + sdf.format(new Date()) + "; ";
	// // GWT.log("Server: Sending Data to Mapper");
	// // final Logger rootLogger = Logger.getLogger("");
	// // rootLogger.log(Level.SEVERE, "Server");
	// message = message + "Object created; ";
	// this.compMapper.insert(comp);
	// message = message + this.compMapper.getLog();
	// // GWT.log("Server: Data sent to Mapper!");
	// message = message + "Object sent to Mapper";
	// // }
	// return message;
	// }

	// @Override
	// public BusinessObject updateObject(String businessObjectType, String
	// name,
	// String materialBezeichnung, String beschreibung,
	// ArrayList<Component> childrenComponents) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public BusinessObject deleteObject(String businessObjectType, String
	// name,
	// String materialBezeichnung, String beschreibung,
	// ArrayList<Component> childrenComponents) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public BusinessObject getBusinessObjectById(String businessObjectType,
	// int id) throws IllegalArgumentException {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public BusinessObject getBusinessObjectByName(String businessObjectType,
	// String name) throws IllegalArgumentException {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public ArrayList<BusinessObject> getAllBusinessObjects(
	// String businessObjectType) throws IllegalArgumentException {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public ArrayList<BusinessObject> getChildrenObjectsOf(BusinessObject bo,
	// String businessObjectType) throws IllegalArgumentException {
	// // TODO Auto-generated method stub
	// return null;
	// }
}
