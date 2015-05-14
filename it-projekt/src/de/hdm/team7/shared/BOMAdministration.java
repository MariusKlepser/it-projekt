package de.hdm.team7.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.team7.server.businessObjects.*;

@RemoteServiceRelativePath("bomadministration")
public interface BOMAdministration extends RemoteService {

	 /**
	   * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von GWT
	   * RPC zusätzlich zum No Argument Constructor der implementierenden Klasse
	   * {@link BOMAdministrationImpl} notwendig. Bitte diese Methode direkt nach der
	   * Instantiierung aufrufen.
	   * 
	   * @throws IllegalArgumentException
	   */
	  public void init() throws IllegalArgumentException;
	  
	  // Hinzuf�gen oder Abfragen von bestimmten St�cklisten f�r eine Instanz dieser BOMAdministration
	  public ArrayList<BillOfMaterial> getBOMList() throws IllegalArgumentException;
	  public void setBOMList(ArrayList<BillOfMaterial> bomList) throws IllegalArgumentException;
	  
	  /** GESCH�FTSOBJEKTE ERSTELLEN (F�R HANDLING IN APP)
	  // Eine Instanz vom Typ BusinessObject anlegen (alle anderen Gesch�ftsobjekte enthalten); 
	  // realisiert �ber optionale Parameter bei �bergabe;
	  // Eine einzige Methode zum Erstellen von Objekten (Bauteil, Baugruppe, etc.)**/
	  public void createObject(String businessObjectType, String name, String materialBezeichnung, 
			  String beschreibung, ArrayList<Component> childrenComponents);
	  
	  public BusinessObject updateObject(String businessObjectType, String name, String materialBezeichnung, 
			  String beschreibung, ArrayList<Component> childrenComponents);
	  
	  public BusinessObject deleteObject(String businessObjectType, String name, String materialBezeichnung, 
			  String beschreibung, ArrayList<Component> childrenComponents);
	  
	  // GESCH�FTSOBJEKTE AUF DB ANLEGEN
	  public void create(BusinessObject bo) throws IllegalArgumentException;
	  
	  // GESCH�FTSOBJEKTE SUCHEN/AUSLESEN
	  // TODO: flexible Erweiterung entsprechend der Benutzungsschnittstelle
	  public BusinessObject getBusinessObjectById(String businessObjectType, int id) throws IllegalArgumentException;
	  public BusinessObject getBusinessObjectByName(String businessObjectType, String name) throws IllegalArgumentException;
	  public ArrayList<BusinessObject> getAllBusinessObjects(String businessObjectType) throws IllegalArgumentException;
	  public ArrayList<BusinessObject> getChildrenObjectsOf(BusinessObject bo, String businessObjectType) throws IllegalArgumentException;

	  // GESCH�FTSOBJEKTE AKTUALISIEREN
	  public void update(BusinessObject bo) throws IllegalArgumentException;

	  // GESCH�FTSOBJEKTE L�SCHEN
	  public void delete(BusinessObject bo) throws IllegalArgumentException;
}
