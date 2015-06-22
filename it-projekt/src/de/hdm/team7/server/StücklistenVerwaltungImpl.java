package de.hdm.team7.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.team7.server.database.BaugruppeMapper;
import de.hdm.team7.server.database.BauteilMapper;
import de.hdm.team7.server.database.BenutzerMapper;
import de.hdm.team7.server.database.EnderzeugnisMapper;
import de.hdm.team7.server.database.St�cklisteMapper;
import de.hdm.team7.shared.St�cklistenVerwaltung;
import de.hdm.team7.shared.gesch�ftsobjekte.Baugruppe;
import de.hdm.team7.shared.gesch�ftsobjekte.Bauteil;
import de.hdm.team7.shared.gesch�ftsobjekte.Benutzer;
import de.hdm.team7.shared.gesch�ftsobjekte.Enderzeugnis;
import de.hdm.team7.shared.gesch�ftsobjekte.St�ckliste;

@SuppressWarnings("serial")
public class St�cklistenVerwaltungImpl extends RemoteServiceServlet implements
		St�cklistenVerwaltung {

	private ArrayList<St�ckliste> st�cklistenListe = null;
	private St�cklisteMapper st�cklisteMapper = null;
	private BaugruppeMapper baugruppeMapper = null;
	private BauteilMapper bauteilMapper = null;
	private EnderzeugnisMapper enderzeugnisMapper = null;
	private BenutzerMapper benutzerMapper = null;

	public St�cklistenVerwaltungImpl() throws IllegalArgumentException {
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
		this.st�cklisteMapper = St�cklisteMapper.st�cklisteMapper();
		this.baugruppeMapper = BaugruppeMapper.baugruppeMapper();
		this.bauteilMapper = BauteilMapper.bauteilMapper();
		this.enderzeugnisMapper = EnderzeugnisMapper.enderzeugnisMapper();
		this.benutzerMapper = BenutzerMapper.benutzerMapper();
	}

	public ArrayList<St�ckliste> getSt�cklistenListe() {
		return st�cklistenListe;
	}

	public void setSt�cklistenListe(ArrayList<St�ckliste> st�cklistenListe) {
		this.st�cklistenListe = st�cklistenListe;
	}

	public String erstelleBauteil(Bauteil bauteil) {
		String message;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		message = "Start createComponent(); ";
		message = message + "creating Component; ";
		bauteil.setAenderungsDatum(sdf.format(date));
		message = message + sdf.format(new Date()) + "; ";
		message = message + "Component created; ";
		this.bauteilMapper.insert(bauteil);
		message = message + this.bauteilMapper.getLog();
		message = message + "Component sent to Mapper";
		return message;
	}

	@Override
	public String erstelleBaugruppe(Baugruppe baugruppe,
			ArrayList<Bauteil> kinderKomponenten) {
		String message;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		message = "Start createComponentAssembly(); ";
		message = message + "creating ComponentAssembly; ";
		baugruppe.setAenderungsDatum(sdf.format(date));
		message = message + sdf.format(new Date()) + "; ";
		message = message + "ComponentAssembly created; ";
		this.baugruppeMapper.insert(baugruppe);
		message = message + this.baugruppeMapper.getLog();
		message = message + "ComponentAssembly sent to Mapper";
		return message;
	}

	@Override
	public String erstelleEnderzeugnis(Enderzeugnis enderzeugnis,
			ArrayList<Bauteil> kinderKomponenten) {
		String message;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		message = "Start createEndProduct(); ";
		message = message + "creating EndProduct; ";
		enderzeugnis.setAenderungsDatum(sdf.format(date));
		message = message + sdf.format(new Date()) + "; ";
		message = message + "EndProduct created; ";
		this.enderzeugnisMapper.insert(enderzeugnis);
		message = message + this.enderzeugnisMapper.getLog();
		message = message + "EndProduct sent to Mapper";
		return message;
	}

	@Override
	public String erstelleSt�ckliste(St�ckliste st�ckliste, Baugruppe wurzelElement) {
		String message;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		message = "Start createBillOfMaterial(); ";
		message = message + "creating BOM; ";
		st�ckliste.setErstellungsDatum(sdf.format(date));
		message = message + sdf.format(new Date()) + "; ";
		message = message + "BOM created; ";
		this.st�cklisteMapper.insert(st�ckliste);
		message = message + this.st�cklisteMapper.getLog();
		message = message + "BOM sent to Mapper";
		return message;
	}

	@Override
	public String erstelleBenutzer(Benutzer benutzer) {
		String message;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//Date date = new Date();

		message = "Start createUser(); ";
		message = message + "creating User; ";
		//benutzer.setChangeDate(sdf.format(date));
		message = message + sdf.format(new Date()) + "; ";
		message = message + "User created; ";
		this.benutzerMapper.insert(benutzer);
		message = message + this.benutzerMapper.getLog();
		message = message + "User sent to Mapper";
		return message;
	}

	@Override
	public String aktualisiereBauteil(Bauteil bauteil) {
		this.bauteilMapper.update(bauteil);
		return null;
	}

	@Override
	public String aktualisiereBaugruppe(Baugruppe baugruppe,
			ArrayList<Bauteil> kinderKomponenten) {
		this.baugruppeMapper.update(baugruppe);
		return null;
	}

	@Override
	public String aktualisiereEnderzeugnis(Enderzeugnis enderzeugnis,
			ArrayList<Bauteil> kinderKomponenten) {
		this.enderzeugnisMapper.update(enderzeugnis);
		return null;
	}

	@Override
	public String aktualisiereSt�ckliste(St�ckliste st�ckliste, Baugruppe wurzelElement) {
		this.st�cklisteMapper.update(st�ckliste);
		return null;
	}

	@Override
	public String aktualisiereBenutzer(Benutzer benutzer) {
		this.benutzerMapper.update(benutzer);
		return null;
	}

	@Override
	public String ordneBauteileZuBaugruppe(ArrayList<Bauteil> bauteile,
			ArrayList<Bauteil> baugruppe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ordneBauteileZuEnderzeugnis(ArrayList<Bauteil> bauteile,
			ArrayList<Bauteil> enderzeugnis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ordneBaugruppenZuBaugruppe(
			ArrayList<Bauteil> ausgew�hlteBaugruppen,
			ArrayList<Bauteil> zugewieseneBaugruppe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ordneBaugruppenZuEnderzeugnis(
			ArrayList<Bauteil> baugruppen, ArrayList<Bauteil> enderzeugnis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ordneEnderzeugnisseZuBaugruppe(
			ArrayList<Bauteil> enderzeugnisse, ArrayList<Bauteil> baugruppe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ordneEnderzeugnisseZuEnderzeugnis(
			ArrayList<Bauteil> ausgew�hlteEnderzeugnisse,
			ArrayList<Bauteil> zugewiesenesEnderzeugnis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String l�scheBauteil(Bauteil bauteil) {
		this.bauteilMapper.delete(bauteil);
		return null;
	}

	@Override
	public String l�scheBaugruppe(Baugruppe baugruppe) {
		this.baugruppeMapper.delete(baugruppe);
		return null;
	}

	@Override
	public String l�scheEnderzeugnis(Enderzeugnis enderzeugnis) {
		this.enderzeugnisMapper.delete(enderzeugnis);
		return null;
	}

	@Override
	public String l�scheSt�ckliste(St�ckliste st�ckliste) {
		this.st�cklisteMapper.delete(st�ckliste);
		return null;
	}

	@Override
	public String l�scheBenutzer(Benutzer benutzer) {
		this.benutzerMapper.delete(benutzer);
		return null;
	}

	@Override
	public Bauteil holeBauteilAnhandId(int id) throws IllegalArgumentException {
		return this.bauteilMapper.findByKey(id);
	}

	@Override
	public Baugruppe holeBaugruppeAnhandId(int id)
			throws IllegalArgumentException {
		return this.baugruppeMapper.findByKey(id);
	}

	@Override
	public Enderzeugnis holeEnderzeugnisAnhandId(int id)
			throws IllegalArgumentException {
		return this.enderzeugnisMapper.findByKey(id);
	}

	@Override
	public St�ckliste holeSt�cklisteAnhandId(int id)
			throws IllegalArgumentException {
		return this.st�cklisteMapper.findByKey(id);
	}

	@Override
	public Benutzer holeBenutzerAnhandId(int id)
			throws IllegalArgumentException {
		return this.benutzerMapper.findByKey(id);
	}

	@Override
	public ArrayList<Bauteil> holeBauteilAnhandName(String name)
			throws IllegalArgumentException {
		return this.bauteilMapper.findByName(name);
	}

	@Override
	public ArrayList<Baugruppe> holeBaugruppeAnhandName(String name)
			throws IllegalArgumentException {
		return this.baugruppeMapper.findByName(name);
	}

	@Override
	public ArrayList<Enderzeugnis> holeEnderzeugnisAnhandName(String name)
			throws IllegalArgumentException {
		return this.enderzeugnisMapper.findByName(name);
	}

	@Override
	public ArrayList<St�ckliste> holeSt�cklisteAnhandName(String name)
			throws IllegalArgumentException {
		return this.st�cklisteMapper.findByName(name);
	}

	@Override
	public ArrayList<Benutzer> holeBenutzerAnhandName(String name)
			throws IllegalArgumentException {
		return this.benutzerMapper.findByName(name);
	}

	@Override
	public ArrayList<Bauteil> holeAlleBauteile()
			throws IllegalArgumentException {
		return this.bauteilMapper.findAll();
	}

	@Override
	public ArrayList<Baugruppe> holeAlleBaugruppen()
			throws IllegalArgumentException {
		return this.baugruppeMapper.findAll();
	}

	@Override
	public ArrayList<Enderzeugnis> holeAlleEnderzeugnisse()
			throws IllegalArgumentException {
		return this.enderzeugnisMapper.findAll();
	}

	@Override
	public ArrayList<St�ckliste> holeAlleSt�cklisten()
			throws IllegalArgumentException {
		return this.st�cklisteMapper.findAll();
	}

	@Override
	public ArrayList<Benutzer> holeAlleBenutzer()
			throws IllegalArgumentException {
		return this.benutzerMapper.findAll();
	}

	@Override
	public ArrayList<Bauteil> holeKinderKomponentenVon(Baugruppe compAss)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Bauteil> holeKinderKomponentenVon(Enderzeugnis endproduct)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
}
