package de.hdm.team7.server.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.team7.shared.geschäftsobjekte.Stückliste;

// In Anlehnung an Thies - Bankprojekt 

public class StücklisteMapper {

	private static StücklisteMapper stücklisteMapper = null;
	private String log;

	/**
	 * GeschÃ¼tzter Konstruktor - verhindert die MÃ¶glichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected StücklisteMapper() {
	}

	public static StücklisteMapper stücklisteMapper() {
		if (stücklisteMapper == null) {
			stücklisteMapper = new StücklisteMapper();
		}

		return stücklisteMapper;
	}

	/**
	 * Suchen einer BOM mit vorgegebener ID. Da diese eindeutig ist, wird genau
	 * ein Objekt zurï¿½ckgegeben.
	 * 
	 * @param id
	 *            PrimÃ¤rschlÃ¼sselattribut (->DB)
	 * @return BOM-Objekt, das dem Ã¼bergebenen SchlÃ¼ssel entspricht, null bei
	 *         nicht vorhandenem DB-Tupel.
	 */
	public Stückliste findByKey(int id) {
		// DB-Verbindung holen
		Connection con = DBVerbindung.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfÃ¼llen und als Query an die DB schicken
			ResultSet rs = stmt
					.executeQuery("SELECT id, name, erstellungsdatum FROM stueckliste "
							+ "WHERE id=" + id + " ORDER BY name");

			/*
			 * Da id PrimÃ¤rschlÃ¼ssel ist, kann max. nur ein Tupel
			 * zurÃ¼ckgegeben werden. PrÃ¼fe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				Stückliste b = new Stückliste();
				b.setId(rs.getInt("id"));
				b.setErstellungsDatum(rs.getDate("erstellungsdatum").toString());
				b.setName(rs.getString("name"));

				return b;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Auslesen aller BOMs.
	 * 
	 * @return Ein Vektor mit BOM-Objekten, die sÃ¤mtliche BOMs reprÃ¤sentieren.
	 *         Bei evtl. Exceptions wird ein partiell gefï¿½llter oder ggf. auch
	 *         leerer Vetor zurÃ¼ckgeliefert.
	 */
	public ArrayList<Stückliste> findAll() {
		Connection con = DBVerbindung.connection();
		// Ergebnisvektor vorbereiten
		ArrayList<Stückliste> result = new ArrayList<Stückliste>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, name, erstellungsdatum "
							+ "FROM stueckliste " + "ORDER BY name");

			// FÃ¼r jeden Eintrag im Suchergebnis wird nun ein BOM-Objekt
			// erstellt.
			while (rs.next()) {
				Stückliste b = new Stückliste();
				b.setId(rs.getInt("id"));
				b.setErstellungsDatum(rs.getDate("erstellungsdatum").toString());
				b.setName(rs.getString("name"));

				// HinzufÃ¼gen des neuen Objekts zum Ergebnisvektor
				result.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvektor zurÃ¼ckgeben
		return result;
	}

	/**
	 * Auslesen aller BOM-Objekte mit gegebenem Namen
	 * 
	 * @param name
	 *            Name der BOMs, die ausgegeben werden sollen
	 * @return Ein Vektor mit BOM-Objekten, die sÃ¤mtliche BOMs mit dem
	 *         gesuchten Namen reprÃ¤sentieren. Bei evtl. Exceptions wird ein
	 *         partiell gefÃ¼llter oder ggf. auch leerer Vetor zurÃ¼ckgeliefert.
	 */
	public ArrayList<Stückliste> findByName(String name) {
		Connection con = DBVerbindung.connection();
		ArrayList<Stückliste> result = new ArrayList<Stückliste>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, name, erstellungsdatum "
							+ "FROM stueckliste " + "WHERE name LIKE '" + name
							+ "' ORDER BY name");

			// FÃ¼r jeden Eintrag im Suchergebnis wird nun ein User-Objekt
			// erstellt.
			while (rs.next()) {
				Stückliste b = new Stückliste();
				b.setId(rs.getInt("id"));
				b.setErstellungsDatum(rs.getDate("erstellungsdatum").toString());
				b.setName(rs.getString("name"));

				// HinzufÃ¼gen des neuen Objekts zum Ergebnisvektor
				result.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvektor zurÃ¼ckgeben
		return result;
	}

	/**
	 * EinfÃ¼gen eines <code>BOM</code>-Objekts in die Datenbank. Dabei wird
	 * auch der PrimÃ¤rschlÃ¼ssel des Ã¼bergebenen Objekts geprÃ¼ft und ggf.
	 * berichtigt.
	 * 
	 * @param b
	 *            das zu speichernde Objekt
	 * @return das bereits Ã¼bergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */
	public Stückliste insert(Stückliste b) {
		Connection con = DBVerbindung.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * ZunÃ¤chst schauen wir nach, welches der momentan hÃ¶chste
			 * PrimÃ¤rschlÃ¼sselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM stueckliste ");

			// Wenn wir etwas zurÃ¼ckerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * u erhÃ¤lt den bisher maximalen, nun um 1 inkrementierten
				 * PrimÃ¤rschlÃ¼ssel.
				 */
				b.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Jetzt erst erfolgt die tatsÃ¤chliche EinfÃ¼geoperation
				stmt.executeUpdate("INSERT INTO stueckliste (id, name, erstellungsdatum) "
						+ "VALUES ("
						+ b.getId()
						+ ",'"
						+ b.getName()
						+ "','"
						+ b.getErstellungsDatum() + "')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/*
		 * RÃ¼ckgabe, des evtl. korrigierten Users.
		 * 
		 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		 * Objekte Ã¼bergeben werden, wÃ¤re die Anpassung des User-Objekts auch
		 * ohne diese explizite RÃ¼ckgabe auï¿½erhalb dieser Methode sichtbar.
		 * Die explizite RÃ¼ckgabe von c ist eher ein Stilmittel, um zu
		 * signalisieren, dass sich das Objekt evtl. im Laufe der Methode
		 * verÃ¤ndert hat.
		 */
		return b;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param b
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter Ã¼bergebene Objekt
	 */
	public Stückliste update(Stückliste b) {
		Connection con = DBVerbindung.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE stueckliste " + "SET name=\""
					+ b.getName() + "\", " + "erstellungsdatum=\""
					+ b.getErstellungsDatum() + "\" " + "WHERE id=" + b.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Um Analogie zu insert(BillOfMaterial b) zu wahren, geben wir b
		// zurÃ¼ck
		return b;
	}

	/**
	 * LÃ¶schen der Daten eines <code>BOM</code>-Objekts aus der Datenbank.
	 * 
	 * @param b
	 *            das aus der DB zu lÃ¶schende "Objekt"
	 */
	public void delete(Stückliste b) {
		Connection con = DBVerbindung.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM stueckliste " + "WHERE id="
					+ b.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getLog() {
		// TODO Auto-generated method stub
		return log;
	}

}
