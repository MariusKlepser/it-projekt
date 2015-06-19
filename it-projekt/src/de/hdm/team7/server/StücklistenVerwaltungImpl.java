package de.hdm.team7.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.team7.server.database.BaugruppeMapper;
import de.hdm.team7.server.database.BauteilMapper;
import de.hdm.team7.server.database.BenutzerMapper;
import de.hdm.team7.server.database.EnderzeugnisMapper;
import de.hdm.team7.server.database.StücklisteMapper;
import de.hdm.team7.shared.StücklistenVerwaltung;
import de.hdm.team7.shared.geschäftsobjekte.Baugruppe;
import de.hdm.team7.shared.geschäftsobjekte.Bauteil;
import de.hdm.team7.shared.geschäftsobjekte.Benutzer;
import de.hdm.team7.shared.geschäftsobjekte.Enderzeugnis;
import de.hdm.team7.shared.geschäftsobjekte.Stückliste;

@SuppressWarnings("serial")
public class StücklistenVerwaltungImpl extends RemoteServiceServlet implements
		StücklistenVerwaltung {

	private ArrayList<Stückliste> stücklistenListe = null;
	private StücklisteMapper stücklisteMapper = null;
	private BaugruppeMapper baugruppeMapper = null;
	private BauteilMapper bauteilMapper = null;
	private EnderzeugnisMapper enderzeugnisMapper = null;
	private BenutzerMapper benutzerMapper = null;

	public StücklistenVerwaltungImpl() throws IllegalArgumentException {
		/*
		 * Eine weitergehende Funktion muss der No-Argument-Constructor nicht
		 * haben. Er muss einfach vorhanden sein.
		 */
	}

	@Override
	public void init() throws IllegalArgumentException {
		/*
		 * Ganz wesentlich ist, dass die BOMAdministration einen vollstï¿½ndigen
		 * Satz von Mappern besitzt, mit deren Hilfe sie dann mit der Datenbank
		 * kommunizieren kann.
		 */
		this.stücklisteMapper = StücklisteMapper.stücklisteMapper();
		this.baugruppeMapper = BaugruppeMapper.baugruppeMapper();
		this.bauteilMapper = BauteilMapper.bauteilMapper();
		this.enderzeugnisMapper = EnderzeugnisMapper.enderzeugnisMapper();
		this.benutzerMapper = BenutzerMapper.benutzerMapper();
	}

	public ArrayList<Stückliste> getStücklistenListe() {
		return stücklistenListe;
	}

	public void setStücklistenListe(ArrayList<Stückliste> stücklistenListe) {
		this.stücklistenListe = stücklistenListe;
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
	public String erstelleStückliste(Stückliste stückliste, Baugruppe wurzelElement) {
		String message;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		message = "Start createBillOfMaterial(); ";
		message = message + "creating BOM; ";
		stückliste.setErstellungsDatum(sdf.format(date));
		message = message + sdf.format(new Date()) + "; ";
		message = message + "BOM created; ";
		this.stücklisteMapper.insert(stückliste);
		message = message + this.stücklisteMapper.getLog();
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
	public String aktualisiereStückliste(Stückliste stückliste, Baugruppe wurzelElement) {
		this.stücklisteMapper.update(stückliste);
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
			ArrayList<Bauteil> ausgewählteBaugruppen,
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
			ArrayList<Bauteil> ausgewählteEnderzeugnisse,
			ArrayList<Bauteil> zugewiesenesEnderzeugnis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String löscheBauteil(Bauteil bauteil) {
		this.bauteilMapper.delete(bauteil);
		return null;
	}

	@Override
	public String löscheBaugruppe(Baugruppe baugruppe) {
		this.baugruppeMapper.delete(baugruppe);
		return null;
	}

	@Override
	public String löscheEnderzeugnis(Enderzeugnis enderzeugnis) {
		this.enderzeugnisMapper.delete(enderzeugnis);
		return null;
	}

	@Override
	public String löscheStückliste(Stückliste stückliste) {
		this.stücklisteMapper.delete(stückliste);
		return null;
	}

	@Override
	public String löscheBenutzer(Benutzer benutzer) {
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
	public Stückliste holeStücklisteAnhandId(int id)
			throws IllegalArgumentException {
		return this.stücklisteMapper.findByKey(id);
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
	public ArrayList<Stückliste> holeStücklisteAnhandName(String name)
			throws IllegalArgumentException {
		return this.stücklisteMapper.findByName(name);
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
	public ArrayList<Stückliste> holeAlleStücklisten()
			throws IllegalArgumentException {
		return this.stücklisteMapper.findAll();
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
