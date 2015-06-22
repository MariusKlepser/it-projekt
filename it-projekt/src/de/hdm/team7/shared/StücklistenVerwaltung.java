package de.hdm.team7.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.team7.shared.gesch�ftsobjekte.*;

@RemoteServiceRelativePath("stuecklistenverwaltung")
public interface St�cklistenVerwaltung extends RemoteService {

	/**
	 * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von
	 * GWT RPC zusätzlich zum No Argument Constructor der implementierenden
	 * Klasse {@link St�cklistenVerwaltungImpl} notwendig. Bitte diese Methode
	 * direkt nach der Instantiierung aufrufen.
	 * 
	 * @throws IllegalArgumentException
	 */
	public void init() throws IllegalArgumentException;

	// Hinzuf�gen oder Abfragen von bestimmten St�cklisten f�r eine
	// Instanz dieser BOMAdministration
	public ArrayList<St�ckliste> getSt�cklistenListe()
			throws IllegalArgumentException;

	public void setSt�cklistenListe(ArrayList<St�ckliste> st�cklistenListe)
			throws IllegalArgumentException;

	/**
	 * GESCH�FTSOBJEKTE ERSTELLEN (F�R HANDLING IN APP) // Eine Instanz vom
	 * Typ BusinessObject anlegen (alle anderen Gesch�ftsobjekte enthalten);
	 * // realisiert �ber optionale Parameter bei �bergabe; // Eine einzige
	 * Methode zum Erstellen von Objekten (Bauteil, Baugruppe, etc.)
	 **/


	public String erstelleBauteil(Bauteil bauteil);

	public String erstelleBaugruppe(Baugruppe baugruppe,
			ArrayList<Bauteil> kinderKomponenten);

	public String erstelleEnderzeugnis(Enderzeugnis enderzeugnis,
			ArrayList<Bauteil> kinderKomponenten);

	public String erstelleSt�ckliste(St�ckliste st�ckliste,
			Baugruppe wurzelElement);

	public String erstelleBenutzer(Benutzer benutzer);

	public String aktualisiereBauteil(Bauteil bauteil);

	public String aktualisiereBaugruppe(Baugruppe baugruppe,
			ArrayList<Bauteil> kinderKomponenten);

	public String aktualisiereEnderzeugnis(Enderzeugnis enderzeugnis,
			ArrayList<Bauteil> kinderKomponenten);

	public String aktualisiereSt�ckliste(St�ckliste st�ckliste,
			Baugruppe wurzelElement);

	public String aktualisiereBenutzer(Benutzer benutzer);

	public String ordneBauteileZuBaugruppe(
			ArrayList<Bauteil> bauteile, ArrayList<Bauteil> baugruppe);

	public String ordneBauteileZuEnderzeugnis(ArrayList<Bauteil> bauteile,
			ArrayList<Bauteil> enderzeugnis);

	public String ordneBaugruppenZuBaugruppe(
			ArrayList<Bauteil> ausgew�hlteBaugruppen,
			ArrayList<Bauteil> zugewieseneBaugruppe);

	public String ordneBaugruppenZuEnderzeugnis(
			ArrayList<Bauteil> baugruppen,
			ArrayList<Bauteil> enderzeugnis);

	public String ordneEnderzeugnisseZuBaugruppe(
			ArrayList<Bauteil> enderzeugnisse,
			ArrayList<Bauteil> baugruppe);

	public String ordneEnderzeugnisseZuEnderzeugnis(
			ArrayList<Bauteil> ausgew�hlteEnderzeugnisse,
			ArrayList<Bauteil> zugewiesenesEnderzeugnis);

	public String l�scheBauteil(Bauteil bauteil);

	public String l�scheBaugruppe(Baugruppe baugruppe);

	public String l�scheEnderzeugnis(Enderzeugnis enderzeugnis);

	public String l�scheSt�ckliste(St�ckliste st�ckliste);

	public String l�scheBenutzer(Benutzer benutzer);

	public Bauteil holeBauteilAnhandId(int id) throws IllegalArgumentException;

	public Baugruppe holeBaugruppeAnhandId(int id)
			throws IllegalArgumentException;

	public Enderzeugnis holeEnderzeugnisAnhandId(int id) throws IllegalArgumentException;

	public St�ckliste holeSt�cklisteAnhandId(int id)
			throws IllegalArgumentException;

	public Benutzer holeBenutzerAnhandId(int id) throws IllegalArgumentException;

	public ArrayList<Bauteil> holeBauteilAnhandName(String name)
			throws IllegalArgumentException;

	public ArrayList<Baugruppe> holeBaugruppeAnhandName(String name)
			throws IllegalArgumentException;

	public ArrayList<Enderzeugnis> holeEnderzeugnisAnhandName(String name)
			throws IllegalArgumentException;

	public ArrayList<St�ckliste> holeSt�cklisteAnhandName(String name)
			throws IllegalArgumentException;

	public ArrayList<Benutzer> holeBenutzerAnhandName(String name)
			throws IllegalArgumentException;

	public ArrayList<Bauteil> holeAlleBauteile()
			throws IllegalArgumentException;

	public ArrayList<Baugruppe> holeAlleBaugruppen()
			throws IllegalArgumentException;

	public ArrayList<Enderzeugnis> holeAlleEnderzeugnisse()
			throws IllegalArgumentException;

	public ArrayList<St�ckliste> holeAlleSt�cklisten()
			throws IllegalArgumentException;

	public ArrayList<Benutzer> holeAlleBenutzer() throws IllegalArgumentException;

	public ArrayList<Bauteil> holeKinderKomponentenVon(Baugruppe baugruppe)
			throws IllegalArgumentException;

	public ArrayList<Bauteil> holeKinderKomponentenVon(Enderzeugnis enderzeugnis)
			throws IllegalArgumentException;
}
