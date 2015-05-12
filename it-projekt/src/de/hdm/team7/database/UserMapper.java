package de.hdm.team7.database;

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
}
