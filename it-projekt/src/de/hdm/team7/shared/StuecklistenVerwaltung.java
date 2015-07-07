package de.hdm.team7.shared;

import java.util.ArrayList;
import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.team7.shared.geschaeftsobjekte.*;

@RemoteServiceRelativePath("stuecklistenverwaltung")
public interface StuecklistenVerwaltung extends RemoteService {

	/**
	 * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von
	 * GWT RPC zusätzlich zum No Argument Constructor der implementierenden
	 * Klasse {@link StuecklistenVerwaltungImpl} notwendig. Bitte diese Methode
	 * direkt nach der Instantiierung aufrufen.
	 * 
	 * @throws IllegalArgumentException
	 */
	public void init() throws IllegalArgumentException;

	// Hinzuf�gen oder Abfragen von bestimmten St�cklisten f�r eine
	// Instanz dieser BOMAdministration
	public ArrayList<Stueckliste> getStuecklistenListe()
			throws IllegalArgumentException;

	public void setStuecklistenListe(ArrayList<Stueckliste> stuecklistenListe)
			throws IllegalArgumentException;

	/**
	 * GESCH�FTSOBJEKTE ERSTELLEN (F�R HANDLING IN APP) // Eine Instanz vom
	 * Typ BusinessObject anlegen (alle anderen Gesch�ftsobjekte enthalten);
	 * // realisiert �ber optionale Parameter bei �bergabe; // Eine einzige
	 * Methode zum Erstellen von Objekten (Bauteil, Baugruppe, etc.)
	 **/


	public String erstelleBauteil(Bauteil bauteil);

	public String erstelleBaugruppe(Baugruppe baugruppe);

	public String erstelleEnderzeugnis(Enderzeugnis enderzeugnis,
			ArrayList<Bauteil> kinderKomponenten);

	public String erstelleStueckliste(Stueckliste stueckliste,
			Baugruppe wurzelElement);

	public String erstelleBenutzer(Benutzer benutzer);

	public String aktualisiereBauteil(Bauteil bauteil);

	public String aktualisiereBaugruppe(Baugruppe baugruppe,
			ArrayList<Bauteil> kinderKomponenten);

	public String aktualisiereEnderzeugnis(Enderzeugnis enderzeugnis,
			ArrayList<Bauteil> kinderKomponenten);

	public String aktualisiereStueckliste(Stueckliste stueckliste,
			Baugruppe wurzelElement);

	public String aktualisiereBenutzer(Benutzer benutzer);

	public String ordneBauteileZuBaugruppe(
			ArrayList<Bauteil> bauteile, ArrayList<Bauteil> baugruppe);

	public String ordneBauteileZuEnderzeugnis(ArrayList<Bauteil> bauteile,
			ArrayList<Bauteil> enderzeugnis);

	public String ordneBaugruppenZuBaugruppe(
			ArrayList<Bauteil> ausgewaehlteBaugruppen,
			ArrayList<Bauteil> zugewieseneBaugruppe);

	public String ordneBaugruppenZuEnderzeugnis(
			ArrayList<Bauteil> baugruppen,
			ArrayList<Bauteil> enderzeugnis);

	public String ordneEnderzeugnisseZuBaugruppe(
			ArrayList<Bauteil> enderzeugnisse,
			ArrayList<Bauteil> baugruppe);

	public String ordneEnderzeugnisseZuEnderzeugnis(
			ArrayList<Bauteil> ausgewaehlteEnderzeugnisse,
			ArrayList<Bauteil> zugewiesenesEnderzeugnis);

	public String loescheBauteil(Bauteil bauteil);

	public String loescheBaugruppe(Baugruppe baugruppe);

	public String loescheEnderzeugnis(Enderzeugnis enderzeugnis);

	public String loescheStueckliste(Stueckliste stueckliste);

	public String loescheBenutzer(Benutzer benutzer);

	public Bauteil holeBauteilAnhandId(int id) throws IllegalArgumentException;

	public Baugruppe holeBaugruppeAnhandId(int id)
			throws IllegalArgumentException;

	public Enderzeugnis holeEnderzeugnisAnhandId(int id) throws IllegalArgumentException;

	public Stueckliste holeStuecklisteAnhandId(int id)
			throws IllegalArgumentException;

	public Benutzer holeBenutzerAnhandId(int id) throws IllegalArgumentException;

	public ArrayList<Bauteil> holeBauteilAnhandName(String name)
			throws IllegalArgumentException;

	public ArrayList<Baugruppe> holeBaugruppeAnhandName(String name)
			throws IllegalArgumentException;

	public ArrayList<Enderzeugnis> holeEnderzeugnisAnhandName(String name)
			throws IllegalArgumentException;

	public ArrayList<Stueckliste> holeStuecklisteAnhandName(String name)
			throws IllegalArgumentException;

	public ArrayList<Benutzer> holeBenutzerAnhandName(String name)
			throws IllegalArgumentException;

	public ArrayList<Bauteil> holeAlleBauteile()
			throws IllegalArgumentException;

	public ArrayList<Baugruppe> holeAlleBaugruppen()
			throws IllegalArgumentException;

	public ArrayList<Enderzeugnis> holeAlleEnderzeugnisse()
			throws IllegalArgumentException;

	public ArrayList<Stueckliste> holeAlleStuecklisten()
			throws IllegalArgumentException;

	public ArrayList<Benutzer> holeAlleBenutzer() throws IllegalArgumentException;
	
	public ArrayList<Bauteil> holeKinderKomponentenVon(Bauteil bauteil)
			throws IllegalArgumentException;

	public ArrayList<Baugruppe> holeKinderBaugruppenVonBaugruppe(Baugruppe baugruppe)
			throws IllegalArgumentException;

	public ArrayList<Baugruppe> holeKinderBaugruppenVonEnderzeugnis(
			Enderzeugnis elternEnderzeugnis);
	
	public ArrayList<Bauteil> holeKinderBauteileVonBaugruppe(Baugruppe baugruppe)
			throws IllegalArgumentException;
	
	public ArrayList<Bauteil> holeKinderBauteileVonEnderzeugnis(Enderzeugnis enderzeugnis)
			throws IllegalArgumentException;
}
