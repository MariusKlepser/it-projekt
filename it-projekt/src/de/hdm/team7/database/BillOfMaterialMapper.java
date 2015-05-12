package de.hdm.team7.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.team7.businessObjects.*;
import de.hdm.team7.database.*;

// In Anlehnung an Thies - Bankprojekt 

public class BillOfMaterialMapper {

	private static BillOfMaterialMapper bomMapper = null;

	  /**
	   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	  protected BillOfMaterialMapper() {
	  }

	  public static BillOfMaterialMapper bomMapper() {
	    if (bomMapper == null) {
	     bomMapper = new BillOfMaterialMapper();
	    }

	    return bomMapper;
	  }
	  
	  /**
	   * Suchen einer BOM mit vorgegebener ID. Da diese eindeutig ist,
	   * wird genau ein Objekt zur�ckgegeben.
	   * 
	   * @param id Primärschlüsselattribut (->DB)
	   * @return BOM-Objekt, das dem übergebenen Schlüssel entspricht, null bei
	   *         nicht vorhandenem DB-Tupel.
	   */
	  public BillOfMaterial findByKey(int id) {
	    // DB-Verbindung holen
	    Connection con = DBConnection.connection();

	    try {
	      // Leeres SQL-Statement (JDBC) anlegen
	      Statement stmt = con.createStatement();

	      // Statement ausfüllen und als Query an die DB schicken
	      ResultSet rs = stmt
	          .executeQuery("SELECT id, name, erstellungsdatum FROM stueckliste "
	              + "WHERE id=" + id + " ORDER BY name");

	      /*
	       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
	       * werden. Prüfe, ob ein Ergebnis vorliegt.
	       */
	      if (rs.next()) {
	        // Ergebnis-Tupel in Objekt umwandeln
	        BillOfMaterial b = new BillOfMaterial();
	        b.setId(rs.getInt("id"));
	        b.setCreationDate(rs.getDate("erstellungsdatum"));
	        b.setName(rs.getString("name"));

	        return b;
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	    }

	    return null;
	  }

	  /**
	   * Auslesen aller BOMs.
	   * 
	   * @return Ein Vektor mit BOM-Objekten, die sämtliche BOMs
	   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gef�llter
	   *         oder ggf. auch leerer Vetor zurückgeliefert.
	   */
	  public Vector<BillOfMaterial> findAll() {
	    Connection con = DBConnection.connection();
	    // Ergebnisvektor vorbereiten
	    Vector<BillOfMaterial> result = new Vector<BillOfMaterial>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, name, erstellungsdatum "
	          + "FROM stueckliste " + "ORDER BY name");

	      // Für jeden Eintrag im Suchergebnis wird nun ein BOM-Objekt
	      // erstellt.
	      while (rs.next()) {
	        BillOfMaterial b = new BillOfMaterial();
	        b.setId(rs.getInt("id"));
	        b.setCreationDate(rs.getDate("erstellungsdatum"));
	        b.setName(rs.getString("name"));

	        // Hinzufügen des neuen Objekts zum Ergebnisvektor
	        result.addElement(b);
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    // Ergebnisvektor zurückgeben
	    return result;
	  }

	  /**
	   * Auslesen aller BOM-Objekte mit gegebenem Namen
	   * 
	   * @param name Name der BOMs, die ausgegeben werden sollen
	   * @return Ein Vektor mit BOM-Objekten, die sämtliche BOMs mit dem
	   *         gesuchten Namen repräsentieren. Bei evtl. Exceptions wird ein
	   *         partiell gefüllter oder ggf. auch leerer Vetor zurückgeliefert.
	   */
	  public Vector<BillOfMaterial> findByName(String name) {
	    Connection con = DBConnection.connection();
	    Vector<BillOfMaterial> result = new Vector<BillOfMaterial>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, name, erstellungsdatum "
	          + "FROM stueckliste " + "WHERE name LIKE '" + name
	          + "' ORDER BY name");

	      // Für jeden Eintrag im Suchergebnis wird nun ein User-Objekt
	      // erstellt.
	      while (rs.next()) {
	        BillOfMaterial b = new BillOfMaterial();
	        b.setId(rs.getInt("id"));
	        b.setCreationDate(rs.getDate("erstellungsdatum"));
	        b.setName(rs.getString("name"));

	        // Hinzufügen des neuen Objekts zum Ergebnisvektor
	        result.addElement(b);
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    // Ergebnisvektor zurückgeben
	    return result;
	  }

	  /**
	   * Einfügen eines <code>BOM</code>-Objekts in die Datenbank. Dabei wird
	   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	   * berichtigt.
	   * 
	   * @param b das zu speichernde Objekt
	   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	   *         <code>id</code>.
	   */
	  public BillOfMaterial insert(BillOfMaterial b) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      /*
	       * Zunächst schauen wir nach, welches der momentan höchste
	       * Primärschlüsselwert ist.
	       */
	      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
	          + "FROM stueckliste ");

	      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
	      if (rs.next()) {
	        /*
	         * u erhält den bisher maximalen, nun um 1 inkrementierten
	         * Primärschlüssel.
	         */
	        b.setId(rs.getInt("maxid") + 1);

	        stmt = con.createStatement();

	        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
	        stmt.executeUpdate("INSERT INTO stueckliste (id, name, erstellungsdatum) "
	            + "VALUES (" + b.getId() + ",'" + b.getName() + "','"
	            + b.getCreationDate() + "')");
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
	    return b;
	  }

	  /**
	   * Wiederholtes Schreiben eines Objekts in die Datenbank.
	   * 
	   * @param b das Objekt, das in die DB geschrieben werden soll
	   * @return das als Parameter übergebene Objekt
	   */
	  public BillOfMaterial update(BillOfMaterial b) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE stueckliste " + "SET name=\""
	          + b.getName() + "\", " + "erstellungsdatum=\"" + b.getCreationDate() + "\" "
	          + "WHERE id=" + b.getId());

	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    // Um Analogie zu insert(BillOfMaterial b) zu wahren, geben wir b zurück
	    return b;
	  }

	  /**
	   * Löschen der Daten eines <code>BOM</code>-Objekts aus der Datenbank.
	   * 
	   * @param b das aus der DB zu löschende "Objekt"
	   */
	  public void delete(BillOfMaterial b) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM stueckliste " + "WHERE id=" + b.getId());
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }


}
