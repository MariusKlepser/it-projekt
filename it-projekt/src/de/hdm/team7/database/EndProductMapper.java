package de.hdm.team7.database;

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
}
