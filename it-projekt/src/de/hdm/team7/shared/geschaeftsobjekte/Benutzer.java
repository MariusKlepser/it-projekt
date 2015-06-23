package de.hdm.team7.shared.geschaeftsobjekte;

public class Benutzer extends Geschaeftsobjekt {

	private static final long serialVersionUID = 1L;
	
	private String passwort = "";

	/**
	 * @return the password
	 */
	public String getPasswort() {
		return passwort;
	}

	/**
	 * @param passwort the password to set
	 */
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

}
