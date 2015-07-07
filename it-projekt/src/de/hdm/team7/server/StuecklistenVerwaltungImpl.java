package de.hdm.team7.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.team7.server.database.BaugruppeMapper;
import de.hdm.team7.server.database.BauteilMapper;
import de.hdm.team7.server.database.BenutzerMapper;
import de.hdm.team7.server.database.EnderzeugnisMapper;
import de.hdm.team7.server.database.StuecklisteMapper;
import de.hdm.team7.server.database.ZuordnungsMapper;
import de.hdm.team7.shared.StuecklistenVerwaltung;
import de.hdm.team7.shared.geschaeftsobjekte.Baugruppe;
import de.hdm.team7.shared.geschaeftsobjekte.Bauteil;
import de.hdm.team7.shared.geschaeftsobjekte.Benutzer;
import de.hdm.team7.shared.geschaeftsobjekte.Enderzeugnis;
import de.hdm.team7.shared.geschaeftsobjekte.Geschaeftsobjekt;
import de.hdm.team7.shared.geschaeftsobjekte.Stueckliste;
import de.hdm.team7.shared.geschaeftsobjekte.ZuordnungBGBG;
import de.hdm.team7.shared.geschaeftsobjekte.ZuordnungBGBT;
import de.hdm.team7.shared.geschaeftsobjekte.ZuordnungEEBG;
import de.hdm.team7.shared.geschaeftsobjekte.ZuordnungEEBT;

@SuppressWarnings("serial")
public class StuecklistenVerwaltungImpl extends RemoteServiceServlet implements
		StuecklistenVerwaltung {

	private ArrayList<Stueckliste> stuecklistenListe = null;
	private StuecklisteMapper stuecklisteMapper = null;
	private BaugruppeMapper baugruppeMapper = null;
	private BauteilMapper bauteilMapper = null;
	private EnderzeugnisMapper enderzeugnisMapper = null;
	private BenutzerMapper benutzerMapper = null;
	private ZuordnungsMapper zuordnungsMapper = null;

	public StuecklistenVerwaltungImpl() throws IllegalArgumentException {
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
		this.stuecklisteMapper = StuecklisteMapper.stuecklisteMapper();
		this.baugruppeMapper = BaugruppeMapper.baugruppeMapper();
		this.bauteilMapper = BauteilMapper.bauteilMapper();
		this.enderzeugnisMapper = EnderzeugnisMapper.enderzeugnisMapper();
		this.benutzerMapper = BenutzerMapper.benutzerMapper();
		this.zuordnungsMapper = ZuordnungsMapper.ZuordnungsMapper();
	}

	public ArrayList<Stueckliste> getStuecklistenListe() {
		return stuecklistenListe;
	}

	public void setStuecklistenListe(ArrayList<Stueckliste> stuecklistenListe) {
		this.stuecklistenListe = stuecklistenListe;
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
	public String erstelleBaugruppe(Baugruppe baugruppe) {
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
	public String erstelleStueckliste(Stueckliste stueckliste, Baugruppe wurzelElement) {
		String message;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		message = "Start createBillOfMaterial(); ";
		message = message + "creating BOM; ";
		stueckliste.setErstellungsDatum(sdf.format(date));
		message = message + sdf.format(new Date()) + "; ";
		message = message + "BOM created; ";
		this.stuecklisteMapper.insert(stueckliste);
		message = message + this.stuecklisteMapper.getLog();
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
	public String aktualisiereStueckliste(Stueckliste stueckliste, Baugruppe wurzelElement) {
		this.stuecklisteMapper.update(stueckliste);
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
			ArrayList<Bauteil> ausgewaehlteBaugruppen,
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
			ArrayList<Bauteil> ausgewaehlteEnderzeugnisse,
			ArrayList<Bauteil> zugewiesenesEnderzeugnis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loescheBauteil(Bauteil bauteil) {
		this.bauteilMapper.delete(bauteil);
		return null;
	}

	@Override
	public String loescheBaugruppe(Baugruppe baugruppe) {
		this.baugruppeMapper.delete(baugruppe);
		return null;
	}

	@Override
	public String loescheEnderzeugnis(Enderzeugnis enderzeugnis) {
		this.enderzeugnisMapper.delete(enderzeugnis);
		return null;
	}

	@Override
	public String loescheStueckliste(Stueckliste stueckliste) {
		this.stuecklisteMapper.delete(stueckliste);
		return null;
	}

	@Override
	public String loescheBenutzer(Benutzer benutzer) {
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
	public Stueckliste holeStuecklisteAnhandId(int id)
			throws IllegalArgumentException {
		return this.stuecklisteMapper.findByKey(id);
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
	public ArrayList<Stueckliste> holeStuecklisteAnhandName(String name)
			throws IllegalArgumentException {
		return this.stuecklisteMapper.findByName(name);
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
	public ArrayList<Stueckliste> holeAlleStuecklisten()
			throws IllegalArgumentException {
		return this.stuecklisteMapper.findAll();
	}

	@Override
	public ArrayList<Benutzer> holeAlleBenutzer()
			throws IllegalArgumentException {
		return this.benutzerMapper.findAll();
	}
	
	@Override
	public ArrayList<Bauteil> holeKinderKomponentenVon(Bauteil bauteil)
			throws IllegalArgumentException {
		return this.bauteilMapper.findeKinderKomponenten(bauteil);
	}

	@Override
	public ArrayList<ZuordnungBGBG> holeKinderBaugruppenVonBaugruppe(Baugruppe elternBaugruppe)
			throws IllegalArgumentException {
		
		return this.zuordnungsMapper.findBGBGByKey(elternBaugruppe.getId());
			
	}

	@Override
	public ArrayList<ZuordnungEEBG> holeKinderBaugruppenVonEnderzeugnis(Enderzeugnis elternEnderzeugnis)
			throws IllegalArgumentException {
		// TODO shit
		return this.zuordnungsMapper.findEEBGByKey(elternEnderzeugnis.getId());
	}
	
	public ArrayList<ZuordnungBGBT> holeKinderBauteileVonBaugruppe(Baugruppe elternBaugruppe)
			throws IllegalArgumentException {
		// TODO shit
		return this.zuordnungsMapper.findBGBTByKey(elternBaugruppe.getId());
	}
	
	public ArrayList<ZuordnungEEBT> holeKinderBauteileVonEnderzeugnis(Enderzeugnis elternEnderzeugnis)
			throws IllegalArgumentException {
		// TODO shit
		return this.zuordnungsMapper.findEEBTByKey(elternEnderzeugnis.getId());
	}

	private String hatNamensduplettenBauteil(Bauteil objekt)
			throws IllegalArgumentException {
		String errMessage = "Bitte geben Sie einen anderen Namen fuer dieses Bauteil ein, da der aktuell gewaehlte Name schon vorhanden ist!";
		Boolean hatNamensdupletten;
		ArrayList<Bauteil> namensduplettenBauteil = this.bauteilMapper.findByName(objekt.getName());
		ArrayList<Baugruppe> namensduplettenBaugruppe = this.baugruppeMapper.findByName(objekt.getName());
		ArrayList<Enderzeugnis> namensduplettenEnderzeugnis = this.enderzeugnisMapper.findByName(objekt.getName());
		ArrayList<Stueckliste> namensduplettenStueckliste = this.stuecklisteMapper.findByName(objekt.getName());
		
		if(namensduplettenBauteil.size() != 0){
			hatNamensdupletten = true;
		}
		else if (namensduplettenBaugruppe.size() != 0){
			hatNamensdupletten = true;
		}
		else if (namensduplettenEnderzeugnis.size() != 0){
			hatNamensdupletten = true;
		}
		else if (namensduplettenStueckliste.size() != 0){
			hatNamensdupletten = true;
		}
		else {
			hatNamensdupletten = false;
		}
		
		if (hatNamensdupletten == true){
			return errMessage;
		} else {
			return null;
		}
	}

	private String hatNamensduplettenBaugruppe(Baugruppe objekt)
			throws IllegalArgumentException {
		String errMessage = "Bitte geben Sie einen anderen Namen fuer dieses Bauteil ein, da der aktuell gewaehlte Name schon vorhanden ist!";
		Boolean hatNamensdupletten;
		ArrayList<Bauteil> namensduplettenBauteil = this.bauteilMapper.findByName(objekt.getName());
		ArrayList<Baugruppe> namensduplettenBaugruppe = this.baugruppeMapper.findByName(objekt.getName());
		ArrayList<Enderzeugnis> namensduplettenEnderzeugnis = this.enderzeugnisMapper.findByName(objekt.getName());
		ArrayList<Stueckliste> namensduplettenStueckliste = this.stuecklisteMapper.findByName(objekt.getName());
		
		if(namensduplettenBauteil.size() != 0){
			hatNamensdupletten = true;
		}
		else if (namensduplettenBaugruppe.size() != 0){
			hatNamensdupletten = true;
		}
		else if (namensduplettenEnderzeugnis.size() != 0){
			hatNamensdupletten = true;
		}
		else if (namensduplettenStueckliste.size() != 0){
			hatNamensdupletten = true;
		}
		else {
			hatNamensdupletten = false;
		}
		if (hatNamensdupletten == true){
			return errMessage;
		} else {
			return null;
		}
	}

	private String hatNamensduplettenEnderzeugnis(Enderzeugnis objekt)
			throws IllegalArgumentException {
		String errMessage = "Bitte geben Sie einen anderen Namen fuer dieses Bauteil ein, da der aktuell gewaehlte Name schon vorhanden ist!";
		Boolean hatNamensdupletten;
		ArrayList<Bauteil> namensduplettenBauteil = this.bauteilMapper.findByName(objekt.getName());
		ArrayList<Baugruppe> namensduplettenBaugruppe = this.baugruppeMapper.findByName(objekt.getName());
		ArrayList<Enderzeugnis> namensduplettenEnderzeugnis = this.enderzeugnisMapper.findByName(objekt.getName());
		ArrayList<Stueckliste> namensduplettenStueckliste = this.stuecklisteMapper.findByName(objekt.getName());
		
		if(namensduplettenBauteil.size() != 0){
			hatNamensdupletten = true;
		}
		else if (namensduplettenBaugruppe.size() != 0){
			hatNamensdupletten = true;
		}
		else if (namensduplettenEnderzeugnis.size() != 0){
			hatNamensdupletten = true;
		}
		else if (namensduplettenStueckliste.size() != 0){
			hatNamensdupletten = true;
		}
		else {
			hatNamensdupletten = false;
		}
		if (hatNamensdupletten == true){
			return errMessage;
		} else {
			return null;
		}
	}

	private String hatNamensduplettenStueckliste(Stueckliste objekt)
			throws IllegalArgumentException {
		String errMessage = "Bitte geben Sie einen anderen Namen fuer dieses Bauteil ein, da der aktuell gewaehlte Name schon vorhanden ist!";
		Boolean hatNamensdupletten;
		ArrayList<Bauteil> namensduplettenBauteil = this.bauteilMapper.findByName(objekt.getName());
		ArrayList<Baugruppe> namensduplettenBaugruppe = this.baugruppeMapper.findByName(objekt.getName());
		ArrayList<Enderzeugnis> namensduplettenEnderzeugnis = this.enderzeugnisMapper.findByName(objekt.getName());
		ArrayList<Stueckliste> namensduplettenStueckliste = this.stuecklisteMapper.findByName(objekt.getName());
		
		if(namensduplettenBauteil.size() != 0){
			hatNamensdupletten = true;
		}
		else if (namensduplettenBaugruppe.size() != 0){
			hatNamensdupletten = true;
		}
		else if (namensduplettenEnderzeugnis.size() != 0){
			hatNamensdupletten = true;
		}
		else if (namensduplettenStueckliste.size() != 0){
			hatNamensdupletten = true;
		}
		else {
			hatNamensdupletten = false;
		}
		if (hatNamensdupletten == true){
			return errMessage;
		} else {
			return null;
		}
	}
}
