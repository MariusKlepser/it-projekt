package de.hdm.team7.database;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.team7.businessObjects.*;

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
	  
	  public ArrayList<BillOfMaterial> findAllBOMs() {
		    Connection con = DBConnector.openConnection();

		    // Ergebnisvektor vorbereiten
		    ArrayList<BillOfMaterial> result = new ArrayList<BillOfMaterial>();

		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM accounts "
		          + " ORDER BY id");

		      // Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt erstellt.
		      while (rs.next()) {
		        BillOfMaterial bom = new BillOfMaterial();
		        //TODO: korrekte Methoden aus der Klasse BillOfMaterial ausw�hlen
//		        bom.setId(rs.getInt("id"));
//		        bom.setId(rs.getInt("owner"));

		        // Hinzufügen des neuen Objekts zum Ergebnisvektor
		        result.add(bom);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }
		    // Ergebnisvektor zurückgeben
		    return result;
		  }
}
