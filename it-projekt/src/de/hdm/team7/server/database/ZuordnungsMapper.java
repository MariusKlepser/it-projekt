package de.hdm.team7.server.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdm.team7.shared.geschaeftsobjekte.Baugruppe;
import de.hdm.team7.shared.geschaeftsobjekte.Bauteil;
import de.hdm.team7.shared.geschaeftsobjekte.ZuordnungBGBG;
import de.hdm.team7.shared.geschaeftsobjekte.ZuordnungBGBT;
import de.hdm.team7.shared.geschaeftsobjekte.ZuordnungEEBG;
import de.hdm.team7.shared.geschaeftsobjekte.ZuordnungEEBT;
import de.hdm.team7.shared.geschaeftsobjekte.Zuordnung;


public class ZuordnungsMapper {

	private static ZuordnungsMapper ZuordnungsMapper = null;
	private String log;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected ZuordnungsMapper() {
	}

	public static ZuordnungsMapper ZuordnungsMapper() {
		if (ZuordnungsMapper == null) {
			ZuordnungsMapper = new ZuordnungsMapper();
		}

		return ZuordnungsMapper;
	}

	public ArrayList<Baugruppe> findBGBGByKey(int elternBaugruppenId) {
		// DB-Verbindung holen
		Connection con = DBVerbindung.connection();
		ArrayList<Baugruppe> result = new ArrayList<Baugruppe>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt
					.executeQuery("SELECT zuordnungsID, baugruppeID, baugruppe2ID, menge, aenderungsdatum FROM z_baugruppeBaugruppe "
							+ "WHERE baugruppeID=" + elternBaugruppenId + " ORDER BY name");

			/*
			 * Da id Primärschlüssel ist, kann max. nur ein Tupel
			 * zurückgegeben werden. Prüfe, ob ein Ergebnis vorliegt.
			 */
			while (rs.next()) {
				Statement statement = con.createStatement();
				
				ResultSet rslt = statement.executeQuery("SELECT id, name, beschreibung,aenderungsdatum,materialbezeichnung FROM baugruppe "
								+ "WHERE id=" + rs.getInt("baugruppe2ID") + " ORDER BY name");
					// Ergebnis-Tupel in Objekt umwandeln
					Baugruppe ca = new Baugruppe();
					ca.setId(rslt.getInt("id"));
					ca.setName(rslt.getString("name"));
					ca.setDescription(rslt.getString("beschreibung"));
					ca.setDtAenderungsDatum(rslt.getDate("aenderungsdatum"));
					ca.setMaterialBezeichnung(rslt.getString("materialbezeichnung"));

					result.add(ca);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return result;
	}
	
	public ArrayList<Bauteil> findBGBTByKey(int elternBaugruppenId) {
		// DB-Verbindung holen
		Connection con = DBVerbindung.connection();
		ArrayList<Bauteil> result = new ArrayList<Bauteil>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt
					.executeQuery("SELECT zuordnungsID, baugruppeID, bauteilID, menge, aenderungsdatum FROM z_baugruppeBauteil "
							+ "WHERE baugruppeID=" + elternBaugruppenId + " ORDER BY name");

			/*
			 * Da id Primärschlüssel ist, kann max. nur ein Tupel
			 * zurückgegeben werden. Prüfe, ob ein Ergebnis vorliegt.
			 */
			while (rs.next()) {
				Statement statement = con.createStatement();
				
				ResultSet rslt = statement.executeQuery("SELECT id, name, beschreibung,aenderungsdatum,materialbezeichnung FROM bauteil "
								+ "WHERE id=" + rs.getInt("baugruppeID") + " ORDER BY name");
					// Ergebnis-Tupel in Objekt umwandeln
					Bauteil ca = new Bauteil();
					ca.setId(rslt.getInt("id"));
					ca.setName(rslt.getString("name"));
					ca.setDescription(rslt.getString("beschreibung"));
					ca.setDtAenderungsDatum(rslt.getDate("aenderungsdatum"));
					ca.setMaterialBezeichnung(rslt.getString("materialbezeichnung"));

					result.add(ca);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return result;
	}
	
	public ArrayList<Baugruppe> findEEBGByKey(int elternEnderzeugnisId) {
		// DB-Verbindung holen
		Connection con = DBVerbindung.connection();
		ArrayList<Baugruppe> result = new ArrayList<Baugruppe>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt
					.executeQuery("SELECT zuordnungsID, enderzeugnisID, baugruppeID, menge, aenderungsdatum FROM z_enderzeugnisBaugruppe "
							+ "WHERE enderzeugnisID=" + elternEnderzeugnisId + " ORDER BY name");

			/*
			 * Da id Primärschlüssel ist, kann max. nur ein Tupel
			 * zurückgegeben werden. Prüfe, ob ein Ergebnis vorliegt.
			 */
			while (rs.next()) {
				Statement statement = con.createStatement();
				
			ResultSet rslt = statement.executeQuery("SELECT id, name, beschreibung,aenderungsdatum,materialbezeichnung FROM baugruppe "
							+ "WHERE id=" + rs.getInt("baugruppeID") + " ORDER BY name");
				// Ergebnis-Tupel in Objekt umwandeln
				Baugruppe ca = new Baugruppe();
				ca.setId(rslt.getInt("id"));
				ca.setName(rslt.getString("name"));
				ca.setDescription(rslt.getString("beschreibung"));
				ca.setDtAenderungsDatum(rslt.getDate("aenderungsdatum"));
				ca.setMaterialBezeichnung(rslt.getString("materialbezeichnung"));

				result.add(ca);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return result;
	}
	
	public ArrayList<Bauteil> findEEBTByKey(int elternEnderzeugnisId) {
		// DB-Verbindung holen
		Connection con = DBVerbindung.connection();
		ArrayList<Bauteil> result = new ArrayList<Bauteil>();
		
		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt
					.executeQuery("SELECT zuordnungsID, enderzeugnisID, bauteilID, menge, aenderungsdatum FROM z_enderzeugnisBauteil "
							+ "WHERE enderzeugnisID=" + elternEnderzeugnisId + " ORDER BY name");


			while (rs.next()) {
				Statement statement = con.createStatement();
				ResultSet rslt = statement.executeQuery("SELECT id, name, beschreibung,aenderungsdatum,materialbezeichnung FROM bauteil "
						+ "WHERE id=" + rs.getInt("baugruppeID") + " ORDER BY name");
			// Ergebnis-Tupel in Objekt umwandeln
			Bauteil ca = new Bauteil();
			ca.setId(rslt.getInt("id"));
			ca.setName(rslt.getString("name"));
			ca.setDescription(rslt.getString("beschreibung"));
			ca.setDtAenderungsDatum(rslt.getDate("aenderungsdatum"));
			ca.setMaterialBezeichnung(rslt.getString("materialbezeichnung"));

			result.add(ca);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return result;
	}

	/**
	 * Auslesen aller User.
	 * 
	 * @return Ein Vektor mit User-Objekten, die sämtliche User
	 *         repräsentieren. Bei evtl. Exceptions wird ein partiell
	 *         gef�llter oder ggf. auch leerer Vetor zurückgeliefert.
	 */
	


	/**
	 * Einfügen eines <code>User</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param u
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */ // TODO hier
	public Zuordnung insert(Zuordnung ca) {
		Connection con = DBVerbindung.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM baugruppe ");

			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * u erhält den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
				 */
				ca.setId(rs.getInt("maxid") + 1);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				//ca.setAenderungsDatum(sdf.format(date));
				
				stmt = con.createStatement();

				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO baugruppe (id, name, beschreibung, aenderungsdatum, materialbezeichnung, LetzterBearbeiter) "
						+ "VALUES ("
						+ ca.getId()
						+ ",'"
					//	+ ca.getName()
						+ "','"
					//	+ ca.getDescription()
						+ "','"
					//	+ ca.getAenderungsDatum()
						+ "','"
					//	+ ca.getMaterialBezeichnung()
						+ "','"
					//	+ ca.getLetzterBearbeiter()
						+ "')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/*
		 * Rückgabe, des evtl. korrigierten Users.
		 * 
		 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		 * Objekte übergeben werden, wäre die Anpassung des User-Objekts auch
		 * ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar.
		 * Die explizite Rückgabe von c ist eher ein Stilmittel, um zu
		 * signalisieren, dass sich das Objekt evtl. im Laufe der Methode
		 * verändert hat.
		 */
		return ca;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param u
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */
	public Zuordnung update(Zuordnung ca) {
		Connection con = DBVerbindung.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE baugruppe " + "SET name=\""
				//	+ ca.getName() + "\", " + "beschreibung=\""
				//	+ ca.getDescription() + "\" " + "aenderungsdatum=\""
				//	+ ca.getAenderungsDatum() + "\" "
				//	+ "materialbezeichnung=\"" + ca.getMaterialBezeichnung()
					+ "\" " + "WHERE id=" + ca.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Um Analogie zu insert(User u) zu wahren, geben wir u zurück
		return ca;
	}

	/**
	 * Löschen der Daten eines <code>User</code>-Objekts aus der Datenbank.
	 * 
	 * @param u
	 *            das aus der DB zu löschende "Objekt"
	 */
	public void delete(Zuordnung ca) {
		Connection con = DBVerbindung.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM benutzer " + "WHERE id="
					+ ca.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getLog() {
		// TODO Auto-generated method stub
		return log;
	}

}
