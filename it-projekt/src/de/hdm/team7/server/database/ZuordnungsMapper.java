package de.hdm.team7.server.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.team7.shared.geschaeftsobjekte.*;

public class ZuordnungsMapper {

	private static BaugruppeMapper baugruppeMapper = null;
	private String log;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected ZuordnungsMapper() {
	}

	public static BaugruppeMapper baugruppeMapper() {
		if (baugruppeMapper == null) {
			baugruppeMapper = new BaugruppeMapper();
		}

		return baugruppeMapper;
	}

	/* public Zuordnung findByKeyEEBG(int id) {
		// DB-Verbindung holen
		Connection con = DBVerbindung.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt
					.executeQuery("SELECT zuordnungsID, enderzeugnisID, baugruppeID, menge, aenderungsdatum, benutzerID FROM z_enderzeugnisBaugruppe "
							+ "WHERE id=" + id + " ORDER BY name");

			/*
			 * Da id Primärschlüssel ist, kann max. nur ein Tupel
			 * zurückgegeben werden. Prüfe, ob ein Ergebnis vorliegt.
			 */
	/*
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				ZuordnungEEBG zo = new ZuordnungEEBG();
				zo.setId(rs.getInt("zuordnungsID"));
				zo.setEnderzeugnisID(rs.getInt("enderzeugnisID"));
				zo.findeAlleKinder();
				zo.setAenderungsdatum(rs.getDate("aenderungsdatum"));
				zo.setMenge(rs.getInt("menge"));
				zo.set

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
					.executeQuery("SELECT id, name, beschreibung,aenderungsdatum,materialbezeichnung"
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
	public ZuordnungBGBG einfuegen(ZuordnungBGBG zBGBG) {
		Connection con = DBVerbindung.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(zuordnungsID) AS maxid "
					+ "FROM z_baugruppeBaugruppe ");

			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * u erhält den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
				 */
				zBGBG.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO z_baugruppeBaugruppe (zuordnungsID, baugruppeID, baugruppe2ID, menge, aenderungsdatum, benutzerID) "
						+ "VALUES ("
						+ zBGBG.getId()
						+ ",'"
						+ zBGBG.getBaugruppeID()
						+ "','"
						+ zBGBG.getBaugruppe2ID()
						+ ",'"
						+ zBGBG.getMenge()
						+ ",'"
						+ zBGBG.getAenderungsdatum()
						+ "','"
						+ zBGBG.getAenderer()
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
		return zBGBG;
	}

	public ZuordnungBGBT einfuegen(ZuordnungBGBT zBGBT) {
		Connection con = DBVerbindung.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(zuordnungsID) AS maxid "
					+ "FROM z_baugruppeBauteil ");

			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * u erhält den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
				 */
				zBGBT.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO z_baugruppeBaugruppe (zuordnungsID, baugruppeID, bauteilID, menge, aenderungsdatum, benutzerID) "
						+ "VALUES ("
						+ zBGBT.getId()
						+ ",'"
						+ zBGBT.getBaugruppeID()
						+ "','"
						+ zBGBT.getBauteilID()
						+ ",'"
						+ zBGBT.getMenge()
						+ ",'"
						+ zBGBT.getAenderungsdatum()
						+ "','"
						+ zBGBT.getAenderer()
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
		return zBGBT;
	}
	
	public ZuordnungEEBG einfuegen(ZuordnungEEBG zEEBG) {
		Connection con = DBVerbindung.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(zuordnungsID) AS maxid "
					+ "FROM z_enderzeugnisBaugruppe ");

			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * u erhält den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
				 */
				zEEBG.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO z_baugruppeBaugruppe (zuordnungsID, baugruppeID, bauteilID, menge, aenderungsdatum, benutzerID) "
						+ "VALUES ("
						+ zEEBG.getId()
						+ ",'"
						+ zEEBG.getEnderzeugnisID()
						+ "','"
						+ zEEBG.getBaugruppeID()
						+ ",'"
						+ zEEBG.getMenge()
						+ ",'"
						+ zEEBG.getAenderungsdatum()
						+ "','"
						+ zEEBG.getAenderer()
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
		return zEEBG;
	}
	
	
	public ZuordnungEEBT einfuegen(ZuordnungEEBT zEEBT) {
		Connection con = DBVerbindung.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(zuordnungsID) AS maxid "
					+ "FROM z_enderzeugnisBauteil ");

			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * u erhält den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
				 */
				zEEBT.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO z_baugruppeBaugruppe (zuordnungsID, baugruppeID, bauteilID, menge, aenderungsdatum, benutzerID) "
						+ "VALUES ("
						+ zEEBT.getId()
						+ ",'"
						+ zEEBT.getEnderzeugnisID()
						+ "','"
						+ zEEBT.getBauteilID()
						+ ",'"
						+ zEEBT.getMenge()
						+ ",'"
						+ zEEBT.getAenderungsdatum()
						+ "','"
						+ zEEBT.getAenderer()
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
		return zEEBT;
	}

	
	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param u
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */
	public ZuordnungBGBG aktualisiere(ZuordnungBGBG zBGBG) {
		Connection con = DBVerbindung.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE z_baugruppeBaugruppe " + "SET baugruppeID=\""
					+ zBGBG.getBaugruppeID() + "\" " + "baugruppe2ID=\""
					+ zBGBG.getBaugruppe2ID() + "\" "
					+ "menge=\"" + zBGBG.getMenge() + "\" "
					+ "aenderungsdatum=\"" + zBGBG.getAenderungsdatum()
					+ "\" "
					+ "benutzerID=\"" + zBGBG.getAenderer()
					+ "\" " + "WHERE id=" + zBGBG.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return zBGBG;
	}

	public ZuordnungBGBT aktualisiere(ZuordnungBGBT zBGBT) {
		Connection con = DBVerbindung.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE z_baugruppeBauteil " + "SET baugruppeID=\""
					+ zBGBT.getBaugruppeID() + "\" " + "bauteilID=\""
					+ zBGBT.getBauteilID() + "\" "
					+ "menge=\"" + zBGBT.getMenge() + "\" "
					+ "aenderungsdatum=\"" + zBGBT.getAenderungsdatum()
					+ "\" "
					+ "benutzerID=\"" + zBGBT.getAenderer()
					+ "\" " + "WHERE id=" + zBGBT.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return zBGBT;
	}
	
	public ZuordnungEEBG aktualisiere(ZuordnungEEBG zEEBG) {
		Connection con = DBVerbindung.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE z_enderzeugnisBaugruppe " + "SET enderzeugnisID=\""
					+ zEEBG.getEnderzeugnisID() + "\" " + "baugruppeID=\""
					+ zEEBG.getBaugruppeID() + "\" "
					+ "menge=\"" + zEEBG.getMenge() + "\" "
					+ "aenderungsdatum=\"" + zEEBG.getAenderungsdatum()
					+ "\" "
					+ "benutzerID=\"" + zEEBG.getAenderer()
					+ "\" " + "WHERE id=" + zEEBG.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return zEEBG;
	}

	public ZuordnungEEBT aktualisiere(ZuordnungEEBT zEEBT) {
		Connection con = DBVerbindung.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE z_enderzeugnisBauteil " + "SET enderzeugnisID=\""
					+ zEEBT.getEnderzeugnisID() + "\" " + "bauteilID=\""
					+ zEEBT.getBauteilID() + "\" "
					+ "menge=\"" + zEEBT.getMenge() + "\" "
					+ "aenderungsdatum=\"" + zEEBT.getAenderungsdatum()
					+ "\" "
					+ "benutzerID=\"" + zEEBT.getAenderer()
					+ "\" " + "WHERE id=" + zEEBT.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return zEEBT;
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
