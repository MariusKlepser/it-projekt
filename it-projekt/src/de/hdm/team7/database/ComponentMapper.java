package de.hdm.team7.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.team7.shared.businessObjects.Component;

public class ComponentMapper {

	private static ComponentMapper componentMapper = null;

	  /**
	   * GeschÃ¼tzter Konstruktor - verhindert die MÃ¶glichkeit, mit <code>new</code>
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
	   * wird genau ein Objekt zurï¿½ckgegeben.
	   * 
	   * @param id PrimÃ¤rschlÃ¼sselattribut (->DB)
	   * @return Component-Objekt, das dem Ã¼bergebenen SchlÃ¼ssel entspricht, null bei
	   *         nicht vorhandenem DB-Tupel.
	   */
	  public Component findByKey(int id) {
	    // DB-Verbindung holen
	    Connection con = DBConnection.connection();

	    try {
	      // Leeres SQL-Statement (JDBC) anlegen
	      Statement stmt = con.createStatement();

	      // Statement ausfÃ¼llen und als Query an die DB schicken
	      ResultSet rs = stmt
	          .executeQuery("SELECT id, name, beschreibung, aenderungsdatum, materialbezeichnung FROM bauteil "
	              + "WHERE id=" + id + " ORDER BY name");

	      /*
	       * Da id PrimÃ¤rschlÃ¼ssel ist, kann max. nur ein Tupel zurÃ¼ckgegeben
	       * werden. PrÃ¼fe, ob ein Ergebnis vorliegt.
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
	   * @return Ein Vektor mit Component-Objekten, die sÃ¤mtliche Component
	   *         reprÃ¤sentieren. Bei evtl. Exceptions wird ein partiell gefï¿½llter
	   *         oder ggf. auch leerer Vetor zurÃ¼ckgeliefert.
	   */
	  public Vector<Component> findAll() {
	    Connection con = DBConnection.connection();
	    // Ergebnisvektor vorbereiten
	    Vector<Component> result = new Vector<Component>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, name, beschreibung, aenderungsdatum, materialbezeichnung "
	          + "FROM bauteil " + "ORDER BY name");

	      // FÃ¼r jeden Eintrag im Suchergebnis wird nun ein Component-Objekt
	      // erstellt.
	      while (rs.next()) {
	        Component u = new Component();
	        u.setId(rs.getInt("id"));
	        u.setName(rs.getString("name"));
	        u.setDescription(rs.getString("beschreibung"));
	        u.setMaterialIdentifier(rs.getString("materialbezeichnung"));
	        u.setChangeDate(rs.getDate("date"));

	        // HinzufÃ¼gen des neuen Objekts zum Ergebnisvektor
	        result.addElement(u);
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    // Ergebnisvektor zurÃ¼ckgeben
	    return result;
	  }

	  /**
	   * Auslesen aller Component-Objekte mit gegebenem Namen
	   * 
	   * @param name Name der Component, die ausgegeben werden sollen
	   * @return Ein Vektor mit Component-Objekten, die sÃ¤mtliche Component mit dem
	   *         gesuchten Namen reprÃ¤sentieren. Bei evtl. Exceptions wird ein
	   *         partiell gefÃ¼llter oder ggf. auch leerer Vetor zurÃ¼ckgeliefert.
	   */
	  public Vector<Component> findByName(String name) {
	    Connection con = DBConnection.connection();
	    Vector<Component> result = new Vector<Component>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, name, beschreibung, aenderungsdatum, materialbezeichnung "
	          + "FROM bauteil " + "WHERE name LIKE '" + name
	          + "' ORDER BY name");

	      // FÃ¼r jeden Eintrag im Suchergebnis wird nun ein Component-Objekt
	      // erstellt.
	      while (rs.next()) {
	        Component u = new Component();
	        u.setId(rs.getInt("id"));
	        u.setName(rs.getString("name"));
	        u.setDescription(rs.getString("beschreibung"));
	        u.setMaterialIdentifier(rs.getString("materialbezeichnung"));
	        u.setChangeDate(rs.getDate("date"));

	        // HinzufÃ¼gen des neuen Objekts zum Ergebnisvektor
	        result.addElement(u);
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    // Ergebnisvektor zurÃ¼ckgeben
	    return result;
	  }

	  /**
	   * EinfÃ¼gen eines <code>Component</code>-Objekts in die Datenbank. Dabei wird
	   * auch der PrimÃ¤rschlÃ¼ssel des Ã¼bergebenen Objekts geprÃ¼ft und ggf.
	   * berichtigt.
	   * 
	   * @param u das zu speichernde Objekt
	   * @return das bereits Ã¼bergebene Objekt, jedoch mit ggf. korrigierter
	   *         <code>id</code>.
	   */
	  public Component insert(Component u) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      /*
	       * ZunÃ¤chst schauen wir nach, welches der momentan hÃ¶chste
	       * PrimÃ¤rschlÃ¼sselwert ist.
	       */
	      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
	          + "FROM bauteil ");

	      // Wenn wir etwas zurÃ¼ckerhalten, kann dies nur einzeilig sein
	      if (rs.next()) {
	        /*
	         * u erhÃ¤lt den bisher maximalen, nun um 1 inkrementierten
	         * PrimÃ¤rschlÃ¼ssel.
	         */
	        u.setId(rs.getInt("maxid") + 1);

	        stmt = con.createStatement();

	        // Jetzt erst erfolgt die tatsÃ¤chliche EinfÃ¼geoperation
	        stmt.executeUpdate("INSERT INTO bauteil (id, name, beschreibung, aenderungsdatum, materialbezeichnung) "
	            + "VALUES (" + u.getId() + ",'" + u.getName() + "','" + u.getDescription() + "','" + u.getChangeDate() + "','"
	            + u.getMaterialIdentifier() + "')");
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    /*
	     * RÃ¼ckgabe, des evtl. korrigierten Components.
	     * 
	     * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
	     * Objekte Ã¼bergeben werden, wÃ¤re die Anpassung des Component-Objekts auch
	     * ohne diese explizite RÃ¼ckgabe auï¿½erhalb dieser Methode sichtbar. Die
	     * explizite RÃ¼ckgabe von c ist eher ein Stilmittel, um zu signalisieren,
	     * dass sich das Objekt evtl. im Laufe der Methode verÃ¤ndert hat.
	     */
	    return u;
	  }

	  /**
	   * Wiederholtes Schreiben eines Objekts in die Datenbank.
	   * 
	   * @param u das Objekt, das in die DB geschrieben werden soll
	   * @return das als Parameter Ã¼bergebene Objekt
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

	    // Um Analogie zu insert(Component u) zu wahren, geben wir u zurÃ¼ck
	    return u;
	  }

	  /**
	   * LÃ¶schen der Daten eines <code>Component</code>-Objekts aus der Datenbank.
	   * 
	   * @param u das aus der DB zu lÃ¶schende "Objekt"
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