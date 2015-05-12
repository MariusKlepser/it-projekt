package de.hdm.team7.database;

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
}
