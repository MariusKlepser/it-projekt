package de.hdm.team7.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.team7.shared.businessObjects.*;

public interface BOMAdministrationAsync {

	void init(AsyncCallback<Void> callback);
	void getBOMList(AsyncCallback<ArrayList<BillOfMaterial>> callback);
	void setBOMList(ArrayList<BillOfMaterial> bomList, AsyncCallback<Void> callback) throws IllegalArgumentException;
	void createObject(String businessObjectType, String name, String materialBezeichnung, 
			  String beschreibung, ArrayList<Component> childrenComponents, AsyncCallback<String> callback);
	void updateObject(String businessObjectType, String name, String materialBezeichnung, 
			  String beschreibung, ArrayList<Component> childrenComponents, AsyncCallback<BusinessObject> callback);
	void deleteObject(String businessObjectType, String name, String materialBezeichnung, 
			  String beschreibung, ArrayList<Component> childrenComponents, AsyncCallback<BusinessObject> callback);
	void create(BusinessObject bo, AsyncCallback<Void> callback);
	void getBusinessObjectById(String businessObjectType, int id, AsyncCallback<BusinessObject> callback) throws IllegalArgumentException;
	void getBusinessObjectByName(String businessObjectType, String name, AsyncCallback<BusinessObject> callback) throws IllegalArgumentException;
	void getAllBusinessObjects(String businessObjectType, AsyncCallback<ArrayList<BusinessObject>> callback) throws IllegalArgumentException;
	void getChildrenObjectsOf(BusinessObject bo, String businessObjectType, AsyncCallback<ArrayList<BusinessObject>> callback) throws IllegalArgumentException;
	void update(BusinessObject bo, AsyncCallback<Void> callback) throws IllegalArgumentException;
	void delete(BusinessObject bo, AsyncCallback<Void> callback) throws IllegalArgumentException;
	
}
