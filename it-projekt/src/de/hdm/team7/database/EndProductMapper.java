package de.hdm.team7.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.team7.database.*;
import de.hdm.team7.server.businessObjects.*;

// In Anlehnung an Thies - Bankprojekt 


public class EndProductMapper {

	private static EndProductMapper endProductMapper = null;

	  /**
	   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	  protected EndProductMapper() {
	  }

	  public static EndProductMapper endProductMapper() {
	    if (endProductMapper == null) {
	      endProductMapper = new EndProductMapper();
	    }

	    return endProductMapper;
	  }
	  /**
	   * Suchen eines EndProduct mit vorgegebener ID. Da diese eindeutig ist,
	   * wird genau ein Objekt zur�ckgegeben.
	   * 
	   * @param id Primärschlüsselattribut (->DB)
	   * @return EndProduct-Objekt, das dem übergebenen Schlüssel entspricht, null bei
	   *         nicht vorhandenem DB-Tupel.
	   */
	  public EndProduct findByKey(int id) {
	    // DB-Verbindung holen
	    Connection con = DBConnection.connection();

	    try {
	      // Leeres SQL-Statement (JDBC) anlegen
	      Statement stmt = con.createStatement();

	      // Statement ausfüllen und als Query an die DB schicken
	      ResultSet rs = stmt
	          .executeQuery("SELECT id, name, beschreibung, materialbezeichnung, aenderungsdatum FROM enderzeugnis "
	              + "WHERE id=" + id + " ORDER BY name");

	      /*
	       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
	       * werden. Prüfe, ob ein Ergebnis vorliegt.
	       */
	      if (rs.next()) {
	        // Ergebnis-Tupel in Objekt umwandeln
	        EndProduct e = new EndProduct();
	        e.setId(rs.getInt("id"));
	        e.setName(rs.getString("name"));
	        e.setDescription(rs.getString("beschreibung"));
	        e.setMaterialIdentifier(rs.getString("materialbezeichnung"));
	        e.setChangeDate(rs.getDate("aenderungsdatum"));

	        return e;
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	    }

	    return null;
	  }

	  /**
	   * Auslesen aller EndProduct.
	   * 
	   * @return Ein Vektor mit EndProduct-Objekten, die sämtliche User
	   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gef�llter
	   *         oder ggf. auch leerer Vetor zurückgeliefert.
	   */
	  public Vector<EndProduct> findAll() {
	    Connection con = DBConnection.connection();
	    // Ergebnisvektor vorbereiten
	    Vector<EndProduct> result = new Vector<EndProduct>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, name, beschreibung, materialbezeichnung, aenderungsdatum "
	              + "FROM enderzeugnis" + " ORDER BY name");

	      // Für jeden Eintrag im Suchergebnis wird nun ein EndProject-Objekt
	      // erstellt.
	      while (rs.next()) {
	        EndProduct e = new EndProduct();
	        e.setId(rs.getInt("id"));
	        e.setName(rs.getString("name"));
	        e.setDescription(rs.getString("beschreibung"));
	        e.setMaterialIdentifier(rs.getString("materialbezeichnung"));
	        e.setChangeDate(rs.getDate("aenderungsdatum"));


	        // Hinzufügen des neuen Objekts zum Ergebnisvektor
	        result.addElement(e);
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    // Ergebnisvektor zurückgeben
	    return result;
	  }

	  /**
	   * Auslesen aller EndProduct-Objekte mit gegebenem Namen
	   * 
	   * @param name Name der Endprodukte, die ausgegeben werden sollen
	   * @return Ein Vektor mit EndProject-Objekten, die sämtliche Endprodukte mit dem
	   *         gesuchten Namen repräsentieren. Bei evtl. Exceptions wird ein
	   *         partiell gefüllter oder ggf. auch leerer Vetor zurückgeliefert.
	   */
	  public Vector<EndProduct> findByName(String name) {
	    Connection con = DBConnection.connection();
	    Vector<EndProduct> result = new Vector<EndProduct>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, name, beschreibung, materialbezeichnung, aenderungsdatum "
	          + "FROM enderzeugnis " + "WHERE name LIKE '" + name
	          + "' ORDER BY name");

	      // Für jeden Eintrag im Suchergebnis wird nun ein EndProduct-Objekt
	      // erstellt.
	      while (rs.next()) {
	    	  EndProduct e = new EndProduct();
		        e.setId(rs.getInt("id"));
		        e.setName(rs.getString("name"));
		        e.setDescription(rs.getString("beschreibung"));
		        e.setMaterialIdentifier(rs.getString("materialbezeichnung"));
		        e.setChangeDate(rs.getDate("aenderungsdatum"));

	        // Hinzufügen des neuen Objekts zum Ergebnisvektor
	        result.addElement(e);
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    // Ergebnisvektor zurückgeben
	    return result;
	  }

	  /**
	   * Einfügen eines <code>EndProduct</code>-Objekts in die Datenbank. Dabei wird
	   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	   * berichtigt.
	   * 
	   * @param e das zu speichernde Objekt
	   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	   *         <code>id</code>.
	   */
	  public EndProduct insert(EndProduct e) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      /*
	       * Zunächst schauen wir nach, welches der momentan höchste
	       * Primärschlüsselwert ist.
	       */
	      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
	          + "FROM enderzeugnis ");

	      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
	      if (rs.next()) {
	        /*
	         * u erhält den bisher maximalen, nun um 1 inkrementierten
	         * Primärschlüssel.
	         */
	        e.setId(rs.getInt("maxid") + 1);

	        stmt = con.createStatement();

	        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
	        stmt.executeUpdate("INSERT INTO enderzeugnis (id, name, beschreibung, materialbezeichnung, aenderungsdatum) "
	            + "VALUES (" + e.getId() + ",'" + e.getName() + ",'" + e.getDescription() + ",'" + e.getMaterialIdentifier() + ",'" + e.getChangeDate() + "')");
	      }
	    }
	    catch (SQLException e1 ){
	      e1.printStackTrace();
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
	    return e;
	  }

	  /**
	   * Wiederholtes Schreiben eines Objekts in die Datenbank.
	   * 
	   * @param u das Objekt, das in die DB geschrieben werden soll
	   * @return das als Parameter übergebene Objekt
	   */
	  public EndProduct update(EndProduct e) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE enderzeugnis " + "SET bezeichnung=\""
	          + e.getDescription() + "\", " + "name=\"" + e.getName() + "\" " + "\", " + "materialbezeichnung=\"" 
	    		  + e.getMaterialIdentifier() + "\", " + "aenderungsdatum=\"" + e.getChangeDate()
	          + "WHERE id=" + e.getId());

	    }
	    catch (SQLException e1) {
	      e1.printStackTrace();
	    }

	    // Um Analogie zu insert(EndProduct e) zu wahren, geben wir u zurück
	    return e;
	  }

	  /**
	   * Löschen der Daten eines <code>EndProduct</code>-Objekts aus der Datenbank.
	   * 
	   * @param e das aus der DB zu löschende "Objekt"
	   */
	  public void delete(EndProduct e) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM enderzeugnis " + "WHERE id=" + e.getId());
	    }
	    catch (SQLException e1) {
	      e1.printStackTrace();
	    }
	  }


}
