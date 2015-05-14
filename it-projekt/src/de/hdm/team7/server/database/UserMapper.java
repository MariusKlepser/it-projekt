package de.hdm.team7.server.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.team7.server.database.*;
import de.hdm.team7.shared.businessObjects.*;

// In Anlehnung an Thies - Bankprojekt 

public class UserMapper {

	private static UserMapper userMapper = null;

	  /**
	   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	  protected UserMapper() {
	  }

	  public static UserMapper userMapper() {
	    if (userMapper == null) {
	      userMapper = new UserMapper();
	    }

	    return userMapper;
	  }
	  
	  /**
	   * Suchen eines Users mit vorgegebener ID. Da diese eindeutig ist,
	   * wird genau ein Objekt zur�ckgegeben.
	   * 
	   * @param id Primärschlüsselattribut (->DB)
	   * @return User-Objekt, das dem übergebenen Schlüssel entspricht, null bei
	   *         nicht vorhandenem DB-Tupel.
	   */
	  public User findByKey(int id) {
	    // DB-Verbindung holen
	    Connection con = DBConnection.connection();

	    try {
	      // Leeres SQL-Statement (JDBC) anlegen
	      Statement stmt = con.createStatement();

	      // Statement ausfüllen und als Query an die DB schicken
	      ResultSet rs = stmt
	          .executeQuery("SELECT id, password, name FROM benutzer "
	              + "WHERE id=" + id + " ORDER BY name");

	      /*
	       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
	       * werden. Prüfe, ob ein Ergebnis vorliegt.
	       */
	      if (rs.next()) {
	        // Ergebnis-Tupel in Objekt umwandeln
	        User u = new User();
	        u.setId(rs.getInt("id"));
	        u.setPassword(rs.getString("password"));
	        u.setName(rs.getString("name"));

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
	   * Auslesen aller User.
	   * 
	   * @return Ein Vektor mit User-Objekten, die sämtliche User
	   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gef�llter
	   *         oder ggf. auch leerer Vetor zurückgeliefert.
	   */
	  public Vector<User> findAll() {
	    Connection con = DBConnection.connection();
	    // Ergebnisvektor vorbereiten
	    Vector<User> result = new Vector<User>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, password, name "
	          + "FROM benutzer " + "ORDER BY name");

	      // Für jeden Eintrag im Suchergebnis wird nun ein User-Objekt
	      // erstellt.
	      while (rs.next()) {
	        User u = new User();
	        u.setId(rs.getInt("id"));
	        u.setPassword(rs.getString("password"));
	        u.setName(rs.getString("name"));

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
	   * Auslesen aller User-Objekte mit gegebenem Namen
	   * 
	   * @param name Name der User, die ausgegeben werden sollen
	   * @return Ein Vektor mit User-Objekten, die sämtliche User mit dem
	   *         gesuchten Namen repräsentieren. Bei evtl. Exceptions wird ein
	   *         partiell gefüllter oder ggf. auch leerer Vetor zurückgeliefert.
	   */
	  public Vector<User> findByName(String name) {
	    Connection con = DBConnection.connection();
	    Vector<User> result = new Vector<User>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, password, name "
	          + "FROM benutzer " + "WHERE name LIKE '" + name
	          + "' ORDER BY name");

	      // Für jeden Eintrag im Suchergebnis wird nun ein User-Objekt
	      // erstellt.
	      while (rs.next()) {
	        User u = new User();
	        u.setId(rs.getInt("id"));
	        u.setPassword(rs.getString("password"));
	        u.setName(rs.getString("name"));

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
	   * Einfügen eines <code>User</code>-Objekts in die Datenbank. Dabei wird
	   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	   * berichtigt.
	   * 
	   * @param u das zu speichernde Objekt
	   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	   *         <code>id</code>.
	   */
	  public User insert(User u) {
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
	        u.setId(rs.getInt("maxid") + 1);

	        stmt = con.createStatement();

	        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
	        stmt.executeUpdate("INSERT INTO benutzer (id, password, name) "
	            + "VALUES (" + u.getId() + ",'" + u.getPassword() + "','"
	            + u.getName() + "')");
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
	    return u;
	  }

	  /**
	   * Wiederholtes Schreiben eines Objekts in die Datenbank.
	   * 
	   * @param u das Objekt, das in die DB geschrieben werden soll
	   * @return das als Parameter übergebene Objekt
	   */
	  public User update(User u) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE benutzer " + "SET password=\""
	          + u.getPassword() + "\", " + "name=\"" + u.getName() + "\" "
	          + "WHERE id=" + u.getId());

	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    // Um Analogie zu insert(User u) zu wahren, geben wir u zurück
	    return u;
	  }

	  /**
	   * Löschen der Daten eines <code>User</code>-Objekts aus der Datenbank.
	   * 
	   * @param u das aus der DB zu löschende "Objekt"
	   */
	  public void delete(User u) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM benutzer " + "WHERE id=" + u.getId());
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }


}
