package de.hdm.team7.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.team7.shared.businessObjects.ComponentAssembly;

public class ComponentAssemblyMapper {

	private static ComponentAssemblyMapper componentAssemblyMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected ComponentAssemblyMapper() {
	}

	public static ComponentAssemblyMapper componentAssemblyMapper() {
		if (componentAssemblyMapper == null) {
			componentAssemblyMapper = new ComponentAssemblyMapper();
		}

		return componentAssemblyMapper;
	}
	public ComponentAssembly findByKey(int id) {
	    // DB-Verbindung holen
	    Connection con = DBConnection.connection();

	    try {
	      // Leeres SQL-Statement (JDBC) anlegen
	      Statement stmt = con.createStatement();

	      // Statement ausfüllen und als Query an die DB schicken
	      ResultSet rs = stmt
	          .executeQuery("SELECT id, name, beschreibung,aenderungsdatum,materialbezeichnung FROM baugruppe "
	              + "WHERE id=" + id + " ORDER BY name");

	      /*
	       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
	       * werden. Prüfe, ob ein Ergebnis vorliegt.
	       */
	      if (rs.next()) {
	        // Ergebnis-Tupel in Objekt umwandeln
	        ComponentAssembly ca = new ComponentAssembly();
	        ca.setId(rs.getInt("id"));
	        ca.setName(rs.getString("name"));
	        ca.setDescription(rs.getString("beschreibung"));
	        ca.setChangeDate(rs.getDate("aenderungsdatum"));
	        ca.setMaterialIdentifier(rs.getString("materialbezeichnung"));

	        return ca;
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	    }

	    return null;
	  }

	  /**
	   * Auslesen aller User.
	   * 
	   * @return Ein Vektor mit User-Objekten, die sämtliche User
	   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gef�llter
	   *         oder ggf. auch leerer Vetor zurückgeliefert.
	   */
	  public Vector<ComponentAssembly> findAll() {
	    Connection con = DBConnection.connection();
	    // Ergebnisvektor vorbereiten
	    Vector<ComponentAssembly> result = new Vector<ComponentAssembly>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, name, beschreibung,aenderungsdatum,materialbezeichnung"
	          + "FROM baugruppe " + "ORDER BY name");

	      // Für jeden Eintrag im Suchergebnis wird nun ein User-Objekt
	      // erstellt.
	      while (rs.next()) {
	        ComponentAssembly ca = new ComponentAssembly();
	        ca.setId(rs.getInt("id"));
	        ca.setName(rs.getString("name"));
	        ca.setDescription(rs.getString("beschreibung"));
	        ca.setChangeDate(rs.getDate("aenderungsdatum"));
	        ca.setMaterialIdentifier(rs.getString("materialbezeichnung"));
	        // Hinzufügen des neuen Objekts zum Ergebnisvektor
	        result.addElement(ca);
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    // Ergebnisvektor zurückgeben
	    return result;
	  }

	  /**
	   * Auslesen aller User-Objekte mit gegebenem Namen
	   * 
	   * @param name Name der User, die ausgegeben werden sollen
	   * @return Ein Vektor mit User-Objekten, die sämtliche User mit dem
	   *         gesuchten Namen repräsentieren. Bei evtl. Exceptions wird ein
	   *         partiell gefüllter oder ggf. auch leerer Vetor zurückgeliefert.
	   */
	  public Vector<ComponentAssembly> findByName(String name) {
	    Connection con = DBConnection.connection();
	    Vector<ComponentAssembly> result = new Vector<ComponentAssembly>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, name, beschreibung,aenderungsdatum,materialbezeichnung "
	          + "FROM baugruppe " + "WHERE name LIKE '" + name
	          + "' ORDER BY name");

	      // Für jeden Eintrag im Suchergebnis wird nun ein User-Objekt
	      // erstellt.
	      while (rs.next()) {
	        ComponentAssembly ca = new ComponentAssembly();
	        ca.setId(rs.getInt("id"));
	        ca.setName(rs.getString("name"));
	        ca.setDescription(rs.getString("beschreibung"));
	        ca.setChangeDate(rs.getDate("aenderungsdatum"));
	        ca.setMaterialIdentifier(rs.getString("materialbezeichnung"));

	        // Hinzufügen des neuen Objekts zum Ergebnisvektor
	        result.addElement(ca);
	      }
	    }
	    catch (SQLException e) {
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
	   * @param u das zu speichernde Objekt
	   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	   *         <code>id</code>.
	   */
	  public ComponentAssembly insert(ComponentAssembly ca) {
	    Connection con = DBConnection.connection();

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
	        stmt.executeUpdate("INSERT INTO baugruppe (id, name, beschreibung,aenderungsdatum,materialbezeichnung) "
	            + "VALUES (" + ca.getId() + ",'" + ca.getName() + "','" + ca.getChangeDate() + ",'" + ca.getMaterialIdentifier() + "','"
	            + ca.getName() + "')");
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    /*
	     * Rückgabe, des evtl. korrigierten Users.
	     * 
	     * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
	     * Objekte übergeben werden, wäre die Anpassung des User-Objekts auch
	     * ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar. Die
	     * explizite Rückgabe von c ist eher ein Stilmittel, um zu signalisieren,
	     * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
	     */
	    return ca;
	  }

	  /**
	   * Wiederholtes Schreiben eines Objekts in die Datenbank.
	   * 
	   * @param u das Objekt, das in die DB geschrieben werden soll
	   * @return das als Parameter übergebene Objekt
	   */
	  public ComponentAssembly update(ComponentAssembly ca) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE baugruppe " 
	          + "SET name=\"" + ca.getName() + "\", " + "beschreibung=\"" + ca.getDescription() + "\" "
	          + "aenderungsdatum=\"" + ca.getChangeDate() + "\" " 
	          + "materialbezeichnung=\"" + ca.getMaterialIdentifier() + "\" " 
	          + "WHERE id=" + ca.getId());

	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    // Um Analogie zu insert(User u) zu wahren, geben wir u zurück
	    return ca;
	  }

	  /**
	   * Löschen der Daten eines <code>User</code>-Objekts aus der Datenbank.
	   * 
	   * @param u das aus der DB zu löschende "Objekt"
	   */
	  public void delete(ComponentAssembly ca) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM benutzer " + "WHERE id=" + ca.getId());
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }


}


