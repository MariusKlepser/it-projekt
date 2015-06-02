package de.hdm.team7.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

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
		 * Ganz wesentlich ist, dass die BOMAdministration einen vollständigen
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

	@Override
	public Component createComponent(Component component) {
		this.compMapper.insert(component);
		ArrayList<Component> temp = this.compMapper.findByName(component.getName());
		return temp.get(1);
	}

	@Override
	public ComponentAssembly createComponentAssembly(ComponentAssembly compAssembly) {
		this.compAssMapper.insert(compAssembly);
		ArrayList<ComponentAssembly> temp = this.compAssMapper.findByName(compAssembly.getName());
		return temp.get(1);
	}

	@Override
	public EndProduct createEndProduct(EndProduct endproduct) {
		this.endProductMapper.insert(endproduct);
		ArrayList<EndProduct> temp = this.endProductMapper.findByName(endproduct.getName());
		return temp.get(1);
	}

	@Override
	public BillOfMaterial createBillOfMaterial(BillOfMaterial bom) {
		this.bomMapper.insert(bom);
		ArrayList<BillOfMaterial> temp = this.bomMapper.findByName(bom.getName());
		return temp.get(1);
	}

	@Override
	public User createUser(User user) {
		this.userMapper.insert(user);
		ArrayList<User> temp = this.userMapper.findByName(user.getName());
		return temp.get(1);
	}

	@Override
	public Component updateComponent(Component component) {
		this.compMapper.update(component);
		return component;
	}

	@Override
	public ComponentAssembly updateComponentAssembly(ComponentAssembly compAssembly) {
		this.compAssMapper.update(compAssembly);
		return compAssembly;
	}

	@Override
	public EndProduct updateEndProduct(EndProduct endproduct) {
		this.endProductMapper.update(endproduct);
		return endproduct;
	}

	@Override
	public BillOfMaterial updateBillOfMaterial(BillOfMaterial bom) {
		this.bomMapper.update(bom);
		return bom;
	}

	@Override
	public User updateUser(User user) {
		this.userMapper.update(user);
		return user;
	}

//	@Override
//	public String assignComponentToComponentAssembly(
//			ArrayList<Component> components, ArrayList<Component> compAssemblies) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String assignComponentToEndProduct(ArrayList<Component> components,
//			ArrayList<Component> endProducts) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String assignComponentAssemblyToComponentAssembly(
//			ArrayList<Component> selectedCompAssemblies,
//			ArrayList<Component> assignableCompAssemblies) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String assignComponentAssemblyToEndProduct(
//			ArrayList<Component> compAssemblies,
//			ArrayList<Component> endProducts) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String assignEndProductToComponentAssembly(
//			ArrayList<Component> endProducts,
//			ArrayList<Component> compAssemblies) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String assignEndProductToEndProduct(
//			ArrayList<Component> selectedEndProducts,
//			ArrayList<Component> assignableEndProducts) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public ArrayList<Component> deleteComponent(Component component) {
		this.compMapper.delete(component);
		return this.getAllComponents();
	}

	@Override
	public ArrayList<ComponentAssembly> deleteComponentAssembly(ComponentAssembly compAssembly) {
		this.compAssMapper.delete(compAssembly);
		return this.getAllComponentAssemblies();
	}

	@Override
	public ArrayList<EndProduct> deleteEndProduct(EndProduct endproduct) {
		this.endProductMapper.delete(endproduct);
		return this.getAllEndProducts();
	}

	@Override
	public ArrayList<BillOfMaterial> deleteBillOfMaterial(BillOfMaterial bom) {
		this.bomMapper.delete(bom);
		return this.getAllBillsOfMaterial();
	}

	@Override
	public ArrayList<User> deleteUser(User user) {
		this.userMapper.delete(user);
		return this.getAllUsers();
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
	public ArrayList<User> getUserByName(String name) throws IllegalArgumentException {
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

}
