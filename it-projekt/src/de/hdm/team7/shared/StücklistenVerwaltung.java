package de.hdm.team7.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.team7.shared.geschäftsobjekte.*;

@RemoteServiceRelativePath("stuecklistenverwaltung")
public interface StücklistenVerwaltung extends RemoteService {

	/**
	 * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von
	 * GWT RPC zusÃ¤tzlich zum No Argument Constructor der implementierenden
	 * Klasse {@link StücklistenVerwaltungImpl} notwendig. Bitte diese Methode
	 * direkt nach der Instantiierung aufrufen.
	 * 
	 * @throws IllegalArgumentException
	 */
	public void init() throws IllegalArgumentException;

	// Hinzufï¿½gen oder Abfragen von bestimmten Stï¿½cklisten fï¿½r eine
	// Instanz dieser BOMAdministration
	public ArrayList<Stückliste> getStücklistenListe()
			throws IllegalArgumentException;

	public void setStücklistenListe(ArrayList<Stückliste> stücklistenListe)
			throws IllegalArgumentException;

	/**
	 * GESCHï¿½FTSOBJEKTE ERSTELLEN (Fï¿½R HANDLING IN APP) // Eine Instanz vom
	 * Typ BusinessObject anlegen (alle anderen Geschï¿½ftsobjekte enthalten);
	 * // realisiert ï¿½ber optionale Parameter bei ï¿½bergabe; // Eine einzige
	 * Methode zum Erstellen von Objekten (Bauteil, Baugruppe, etc.)
	 **/


	public String erstelleBauteil(Bauteil bauteil);

	public String erstelleBaugruppe(Baugruppe baugruppe,
			ArrayList<Bauteil> kinderKomponenten);

	public String erstelleEnderzeugnis(Enderzeugnis enderzeugnis,
			ArrayList<Bauteil> kinderKomponenten);

	public String erstelleStückliste(Stückliste stückliste,
			Baugruppe wurzelElement);

	public String erstelleBenutzer(Benutzer benutzer);

	public String aktualisiereBauteil(Bauteil bauteil);

	public String aktualisiereBaugruppe(Baugruppe baugruppe,
			ArrayList<Bauteil> kinderKomponenten);

	public String aktualisiereEnderzeugnis(Enderzeugnis enderzeugnis,
			ArrayList<Bauteil> kinderKomponenten);

	public String aktualisiereStückliste(Stückliste stückliste,
			Baugruppe wurzelElement);

	public String aktualisiereBenutzer(Benutzer benutzer);

	public String ordneBauteileZuBaugruppe(
			ArrayList<Bauteil> bauteile, ArrayList<Bauteil> baugruppe);

	public String ordneBauteileZuEnderzeugnis(ArrayList<Bauteil> bauteile,
			ArrayList<Bauteil> enderzeugnis);

	public String ordneBaugruppenZuBaugruppe(
			ArrayList<Bauteil> ausgewählteBaugruppen,
			ArrayList<Bauteil> zugewieseneBaugruppe);

	public String ordneBaugruppenZuEnderzeugnis(
			ArrayList<Bauteil> baugruppen,
			ArrayList<Bauteil> enderzeugnis);

	public String ordneEnderzeugnisseZuBaugruppe(
			ArrayList<Bauteil> enderzeugnisse,
			ArrayList<Bauteil> baugruppe);

	public String ordneEnderzeugnisseZuEnderzeugnis(
			ArrayList<Bauteil> ausgewählteEnderzeugnisse,
			ArrayList<Bauteil> zugewiesenesEnderzeugnis);

	public String löscheBauteil(Bauteil bauteil);

	public String löscheBaugruppe(Baugruppe baugruppe);

	public String löscheEnderzeugnis(Enderzeugnis enderzeugnis);

	public String löscheStückliste(Stückliste stückliste);

	public String löscheBenutzer(Benutzer benutzer);

	public Bauteil holeBauteilAnhandId(int id) throws IllegalArgumentException;

	public Baugruppe holeBaugruppeAnhandId(int id)
			throws IllegalArgumentException;

	public Enderzeugnis holeEnderzeugnisAnhandId(int id) throws IllegalArgumentException;

	public Stückliste holeStücklisteAnhandId(int id)
			throws IllegalArgumentException;

	public Benutzer holeBenutzerAnhandId(int id) throws IllegalArgumentException;

	public ArrayList<Bauteil> holeBauteilAnhandName(String name)
			throws IllegalArgumentException;

	public ArrayList<Baugruppe> holeBaugruppeAnhandName(String name)
			throws IllegalArgumentException;

	public ArrayList<Enderzeugnis> holeEnderzeugnisAnhandName(String name)
			throws IllegalArgumentException;

	public ArrayList<Stückliste> holeStücklisteAnhandName(String name)
			throws IllegalArgumentException;

	public ArrayList<Benutzer> holeBenutzerAnhandName(String name)
			throws IllegalArgumentException;

	public ArrayList<Bauteil> holeAlleBauteile()
			throws IllegalArgumentException;

	public ArrayList<Baugruppe> holeAlleBaugruppen()
			throws IllegalArgumentException;

	public ArrayList<Enderzeugnis> holeAlleEnderzeugnisse()
			throws IllegalArgumentException;

	public ArrayList<Stückliste> holeAlleStücklisten()
			throws IllegalArgumentException;

	public ArrayList<Benutzer> holeAlleBenutzer() throws IllegalArgumentException;

	public ArrayList<Bauteil> holeKinderKomponentenVon(Baugruppe baugruppe)
			throws IllegalArgumentException;

	public ArrayList<Bauteil> holeKinderKomponentenVon(Enderzeugnis enderzeugnis)
			throws IllegalArgumentException;
}
