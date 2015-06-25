package de.hdm.team7.shared;

import de.hdm.team7.shared.geschaeftsobjekte.*;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("reportgenerator")
public interface ReportGenerator extends RemoteService {

	 /**
	   * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von GWT
	   * RPC zusätzlich zum No Argument Constructor der implementierenden Klasse
	   * {@link ReportGeneratorImpl} notwendig. Bitte diese Methode direkt nach der
	   * Instantiierung aufrufen.
	   * 
	   * @throws IllegalArgumentException
	   */
	  public void init() throws IllegalArgumentException;
	  
	  /**
	   * Setzen der zugeordneten St�ckliste?.
	   * 
	   * @para St�cklisten-Objekt? 
	   * @throws IllegalArgumentException
	   * 
	   * NOTWENDIG?
	   */
	  //public void setSt�ckliste(St�ckliste s) throws IllegalArgumentException;

	  /**
	   * Erstellen eines <code>AllAccountsOfCustomerReport</code>-Reports. Dieser
	   * Report-Typ stellt sÃ¤mtliche Konten eines Kunden dar.
	   * 
	   * @param s eine Referenz auf die St�ckliste bzgl. dessen der Report
	   *          erstellt werden soll.
	   * @return das fertige Reportobjekt
	   * @throws IllegalArgumentException
	   * @see St�cklisteAnzeigenReport
	   */
//	  public abstract StuecklisteAnzeigenReport erstelleStuecklistenReport(
//	      Stueckliste s) throws IllegalArgumentException;

	  // Weitere Methoden hier eintragen die notwendig sind
	  
	  
	  
	  
	}
