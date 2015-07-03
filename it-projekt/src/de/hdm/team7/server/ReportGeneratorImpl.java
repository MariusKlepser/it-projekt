package de.hdm.team7.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.team7.server.database.BaugruppeMapper;
import de.hdm.team7.server.database.BauteilMapper;
import de.hdm.team7.server.database.BenutzerMapper;
import de.hdm.team7.server.database.EnderzeugnisMapper;
import de.hdm.team7.server.database.StuecklisteMapper;
import de.hdm.team7.shared.ReportGenerator;
import de.hdm.team7.shared.StuecklistenVerwaltung;
import de.hdm.team7.shared.geschaeftsobjekte.Stueckliste;

public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {
	
	private ArrayList<Stueckliste> stuecklistenListe = null;
	private StuecklisteMapper stuecklisteMapper = null;
	private BaugruppeMapper baugruppeMapper = null;
	private BauteilMapper bauteilMapper = null;
	private EnderzeugnisMapper enderzeugnisMapper = null;
	private BenutzerMapper benutzerMapper = null;
	
	public ReportGeneratorImpl() throws IllegalArgumentException {
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
	}
}
