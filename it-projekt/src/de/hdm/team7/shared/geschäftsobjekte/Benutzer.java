package de.hdm.team7.shared.gesch�ftsobjekte;

public class Benutzer extends Gesch�ftsobjekt {

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
