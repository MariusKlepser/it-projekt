package de.hdm.team7.shared.geschäftsobjekte;

public class Benutzer extends Geschäftsobjekt {

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
