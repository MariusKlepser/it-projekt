package de.hdm.team7.server.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.team7.shared.geschaeftsobjekte.Baugruppe;

public class BaugruppeMapper {

	private static BaugruppeMapper baugruppeMapper = null;
	private String log;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected BaugruppeMapper() {
	}

	public static BaugruppeMapper baugruppeMapper() {
		if (baugruppeMapper == null) {
			baugruppeMapper = new BaugruppeMapper();
		}

		return baugruppeMapper;
	}

	public Baugruppe findByKey(int id) {
		// DB-Verbindung holen
		Connection con = DBVerbindung.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt
					.executeQuery("SELECT id, name, beschreibung,aenderungsdatum,materialbezeichnung FROM baugruppe "
							+ "WHERE id=" + id + " ORDER BY name");

			/*
			 * Da id Primärschlüssel ist, kann max. nur ein Tupel
			 * zurückgegeben werden. Prüfe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				Baugruppe ca = new Baugruppe();
				ca.setId(rs.getInt("id"));
				ca.setName(rs.getString("name"));
				ca.setDescription(rs.getString("beschreibung"));
				ca.setDtAenderungsDatum(rs.getDate("aenderungsdatum"));
				ca.setMaterialBezeichnung(rs.getString("materialbezeichnung"));

				return ca;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Auslesen aller User.
	 * 
	 * @return Ein Vektor mit User-Objekten, die sämtliche User
	 *         repräsentieren. Bei evtl. Exceptions wird ein partiell
	 *         gef�llter oder ggf. auch leerer Vetor zurückgeliefert.
	 */
	public ArrayList<Baugruppe> findAll() {
		Connection con = DBVerbindung.connection();
		// Ergebnisvektor vorbereiten
		ArrayList<Baugruppe> result = new ArrayList<Baugruppe>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, name, beschreibung, aenderungsdatum, materialbezeichnung "
							+ "FROM baugruppe " + "ORDER BY name");

			// Für jeden Eintrag im Suchergebnis wird nun ein User-Objekt
			// erstellt.
			while (rs.next()) {
				Baugruppe ca = new Baugruppe();
				ca.setId(rs.getInt("id"));
				ca.setName(rs.getString("name"));
				ca.setDescription(rs.getString("beschreibung"));
				ca.setDtAenderungsDatum(rs.getDate("aenderungsdatum"));
				ca.setMaterialBezeichnung(rs.getString("materialbezeichnung"));
				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.add(ca);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvektor zurückgeben
		return result;
	}

	/**
	 * Auslesen aller User-Objekte mit gegebenem Namen
	 * 
	 * @param name
	 *            Name der User, die ausgegeben werden sollen
	 * @return Ein Vektor mit User-Objekten, die sämtliche User mit dem
	 *         gesuchten Namen repräsentieren. Bei evtl. Exceptions wird ein
	 *         partiell gefüllter oder ggf. auch leerer Vetor zurückgeliefert.
	 */
	public ArrayList<Baugruppe> findByName(String name) {
		Connection con = DBVerbindung.connection();
		ArrayList<Baugruppe> result = new ArrayList<Baugruppe>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, name, beschreibung,aenderungsdatum,materialbezeichnung "
							+ "FROM baugruppe "
							+ "WHERE name LIKE '"
							+ name
							+ "' ORDER BY name");

			// Für jeden Eintrag im Suchergebnis wird nun ein User-Objekt
			// erstellt.
			while (rs.next()) {
				Baugruppe ca = new Baugruppe();
				ca.setId(rs.getInt("id"));
				ca.setName(rs.getString("name"));
				ca.setDescription(rs.getString("beschreibung"));
				ca.setDtAenderungsDatum(rs.getDate("aenderungsdatum"));
				ca.setMaterialBezeichnung(rs.getString("materialbezeichnung"));

				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.add(ca);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvektor zurückgeben
		return result;
	}

	/**
	 * Einfügen eines <code>User</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param u
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */
	public Baugruppe insert(Baugruppe ca) {
		Connection con = DBVerbindung.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM benutzer ");

			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * u erhält den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
				 */
				ca.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO baugruppe (id, name, beschreibung, aenderungsdatum, materialbezeichnung) "
						+ "VALUES ('"
						+ ca.getId()
						+ "','"
						+ ca.getName()
						+ "','"
						+ ca.getAenderungsDatum()
						+ "','"
						+ ca.getMaterialBezeichnung()
						+ "','"
						+ ca.getName()
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
	public Baugruppe update(Baugruppe ca) {
		Connection con = DBVerbindung.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE baugruppe " + "SET name=\""
					+ ca.getName() + "\", " + "beschreibung=\""
					+ ca.getDescription() + "\" " + "aenderungsdatum=\""
					+ ca.getAenderungsDatum() + "\" "
					+ "materialbezeichnung=\"" + ca.getMaterialBezeichnung()
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
	public void delete(Baugruppe ca) {
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
