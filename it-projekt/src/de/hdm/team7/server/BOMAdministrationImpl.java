package de.hdm.team7.server;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.team7.server.ServersideSettings;
import de.hdm.team7.database.*;
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

	@Override
	public void createObject(String businessObjectType, String name,
			String materialBezeichnung, String beschreibung,
			ArrayList<Component> childrenComponents) {
		if(businessObjectType == "bauteil")
		{
			System.out.println("Server: Received Data");
			GWT.log("Server: Received Data!");
			Component comp = new Component();
			comp.setName(name);
			comp.setMaterialIdentifier(materialBezeichnung);
			comp.setDescription(beschreibung);
			GWT.log("Server: Sending Data to Mapper");
			final Logger rootLogger = Logger.getLogger("");
			rootLogger.log(Level.SEVERE, "Server");
			this.compMapper.insert(comp);
			GWT.log("Server: Data sent to Mapper!");
		}
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
