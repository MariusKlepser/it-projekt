package de.hdm.team7.shared;

import de.hdm.team7.shared.geschäftsobjekte.*;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("reportgenerator")
public interface ReportGenerator extends RemoteService {

	 /**
	   * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von GWT
	   * RPC zusÃ¤tzlich zum No Argument Constructor der implementierenden Klasse
	   * {@link ReportGeneratorImpl} notwendig. Bitte diese Methode direkt nach der
	   * Instantiierung aufrufen.
	   * 
	   * @throws IllegalArgumentException
	   */
	  public void init() throws IllegalArgumentException;
	  
	  /**
	   * Setzen der zugeordneten Stückliste?.
	   * 
	   * @para Stücklisten-Objekt? 
	   * @throws IllegalArgumentException
	   * 
	   * NOTWENDIG?
	   */
	  public void setStückliste(Stückliste s) throws IllegalArgumentException;

	  /**
	   * Erstellen eines <code>AllAccountsOfCustomerReport</code>-Reports. Dieser
	   * Report-Typ stellt sÃƒÂ¤mtliche Konten eines Kunden dar.
	   * 
	   * @param s eine Referenz auf die Stückliste bzgl. dessen der Report
	   *          erstellt werden soll.
	   * @return das fertige Reportobjekt
	   * @throws IllegalArgumentException
	   * @see StücklisteAnzeigenReport
	   */
	  public abstract StücklisteAnzeigenReport erstelleStücklistenReport(
	      Stückliste s) throws IllegalArgumentException;

	  // Weitere Methoden hier eintragen die notwendig sind
	  
	  
	  
	  
	}
