package de.hdm.team7.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.team7.businessObjects.*;
import de.hdm.team7.database.*;
import de.hdm.team7.shared.BOMAdministration;

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
	public BusinessObject createObject(String businessObjectType, String name,
			String materialBezeichnung, String beschreibung,
			ArrayList<Component> childrenComponents) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BusinessObject updateObject(String businessObjectType, String name,
			String materialBezeichnung, String beschreibung,
			ArrayList<Component> childrenComponents) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BusinessObject deleteObject(String businessObjectType, String name,
			String materialBezeichnung, String beschreibung,
			ArrayList<Component> childrenComponents) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(BusinessObject bo) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BusinessObject getBusinessObjectById(String businessObjectType,
			int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BusinessObject getBusinessObjectByName(String businessObjectType,
			String name) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BusinessObject> getAllBusinessObjects(
			String businessObjectType) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BusinessObject> getChildrenObjectsOf(BusinessObject bo,
			String businessObjectType) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(BusinessObject bo) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BusinessObject bo) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}
}
