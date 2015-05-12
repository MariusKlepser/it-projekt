package de.hdm.team7.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.team7.businessObjects.Component;

public class ComponentMapper {

	private static ComponentMapper componentMapper = null;

	  /**
	   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	  protected ComponentMapper() {
	  }

	  public static ComponentMapper componentMapper() {
	    if (componentMapper == null) {
	      componentMapper = new ComponentMapper();
	    }

	    return componentMapper;
	  }
	  
	  /**
	   * Suchen eines Components mit vorgegebener ID. Da diese eindeutig ist,
	   * wird genau ein Objekt zur�ckgegeben.
	   * 
	   * @param id Primärschlüsselattribut (->DB)
	   * @return Component-Objekt, das dem übergebenen Schlüssel entspricht, null bei
	   *         nicht vorhandenem DB-Tupel.
	   */
	  public Component findByKey(int id) {
	    // DB-Verbindung holen
	    Connection con = DBConnection.connection();

	    try {
	      // Leeres SQL-Statement (JDBC) anlegen
	      Statement stmt = con.createStatement();

	      // Statement ausfüllen und als Query an die DB schicken
	      ResultSet rs = stmt
	          .executeQuery("SELECT id, name, beschreibung, aenderungsdatum, materialbezeichnung FROM bauteil "
	              + "WHERE id=" + id + " ORDER BY name");

	      /*
	       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
	       * werden. Prüfe, ob ein Ergebnis vorliegt.
	       */
	      if (rs.next()) {
	        // Ergebnis-Tupel in Objekt umwandeln
	        Component u = new Component();
	        u.setId(rs.getInt("id"));
	        u.setName(rs.getString("name"));
	        u.setDescription(rs.getString("beschreibung"));
	        u.setMaterialIdentifier(rs.getString("materialbezeichnung"));
	        u.setChangeDate(rs.getDate("date"));
	        
	        return u;
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	    }

	    return null;
	  }

	  /**
	   * Auslesen aller Component.
	   * 
	   * @return Ein Vektor mit Component-Objekten, die sämtliche Component
	   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gef�llter
	   *         oder ggf. auch leerer Vetor zurückgeliefert.
	   */
	  public Vector<Component> findAll() {
	    Connection con = DBConnection.connection();
	    // Ergebnisvektor vorbereiten
	    Vector<Component> result = new Vector<Component>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, name, beschreibung, aenderungsdatum, materialbezeichnung "
	          + "FROM bauteil " + "ORDER BY name");

	      // Für jeden Eintrag im Suchergebnis wird nun ein Component-Objekt
	      // erstellt.
	      while (rs.next()) {
	        Component u = new Component();
	        u.setId(rs.getInt("id"));
	        u.setName(rs.getString("name"));
	        u.setDescription(rs.getString("beschreibung"));
	        u.setMaterialIdentifier(rs.getString("materialbezeichnung"));
	        u.setChangeDate(rs.getDate("date"));

	        // Hinzufügen des neuen Objekts zum Ergebnisvektor
	        result.addElement(u);
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    // Ergebnisvektor zurückgeben
	    return result;
	  }

	  /**
	   * Auslesen aller Component-Objekte mit gegebenem Namen
	   * 
	   * @param name Name der Component, die ausgegeben werden sollen
	   * @return Ein Vektor mit Component-Objekten, die sämtliche Component mit dem
	   *         gesuchten Namen repräsentieren. Bei evtl. Exceptions wird ein
	   *         partiell gefüllter oder ggf. auch leerer Vetor zurückgeliefert.
	   */
	  public Vector<Component> findByName(String name) {
	    Connection con = DBConnection.connection();
	    Vector<Component> result = new Vector<Component>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, name, beschreibung, aenderungsdatum, materialbezeichnung "
	          + "FROM bauteil " + "WHERE name LIKE '" + name
	          + "' ORDER BY name");

	      // Für jeden Eintrag im Suchergebnis wird nun ein Component-Objekt
	      // erstellt.
	      while (rs.next()) {
	        Component u = new Component();
	        u.setId(rs.getInt("id"));
	        u.setName(rs.getString("name"));
	        u.setDescription(rs.getString("beschreibung"));
	        u.setMaterialIdentifier(rs.getString("materialbezeichnung"));
	        u.setChangeDate(rs.getDate("date"));

	        // Hinzufügen des neuen Objekts zum Ergebnisvektor
	        result.addElement(u);
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    // Ergebnisvektor zurückgeben
	    return result;
	  }

	  /**
	   * Einfügen eines <code>Component</code>-Objekts in die Datenbank. Dabei wird
	   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	   * berichtigt.
	   * 
	   * @param u das zu speichernde Objekt
	   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	   *         <code>id</code>.
	   */
	  public Component insert(Component u) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      /*
	       * Zunächst schauen wir nach, welches der momentan höchste
	       * Primärschlüsselwert ist.
	       */
	      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
	          + "FROM bauteil ");

	      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
	      if (rs.next()) {
	        /*
	         * u erhält den bisher maximalen, nun um 1 inkrementierten
	         * Primärschlüssel.
	         */
	        u.setId(rs.getInt("maxid") + 1);

	        stmt = con.createStatement();

	        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
	        stmt.executeUpdate("INSERT INTO bauteil (id, name, beschreibung, aenderungsdatum, materialbezeichnung) "
	            + "VALUES (" + u.getId() + ",'" + u.getName() + "','" + u.getDescription() + "','" + u.getChangeDate() + "','"
	            + u.getMaterialIdentifier() + "')");
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    /*
	     * Rückgabe, des evtl. korrigierten Components.
	     * 
	     * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
	     * Objekte übergeben werden, wäre die Anpassung des Component-Objekts auch
	     * ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar. Die
	     * explizite Rückgabe von c ist eher ein Stilmittel, um zu signalisieren,
	     * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
	     */
	    return u;
	  }

	  /**
	   * Wiederholtes Schreiben eines Objekts in die Datenbank.
	   * 
	   * @param u das Objekt, das in die DB geschrieben werden soll
	   * @return das als Parameter übergebene Objekt
	   */
	  public Component update(Component u) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE bauteil " + "SET name=\""
	          + u.getName() + "\", " + "name=\"" + u.getName() + "\", " + "beschreibung=\"" + u.getDescription()
	          + "\", " + "aenderungsDatum=\"" + u.getChangeDate() + "\", " + "materialBezeichnung=\"" + u.getMaterialIdentifier() + "\" "
	          + "WHERE id=" + u.getId());

	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    // Um Analogie zu insert(Component u) zu wahren, geben wir u zurück
	    return u;
	  }

	  /**
	   * Löschen der Daten eines <code>Component</code>-Objekts aus der Datenbank.
	   * 
	   * @param u das aus der DB zu löschende "Objekt"
	   */
	  public void delete(Component u) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM bauteil " + "WHERE id=" + u.getId());
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }

}
