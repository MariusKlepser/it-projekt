package de.hdm.team7.database;

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
}
