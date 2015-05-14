package de.hdm.team7.server.businessObjects;

public class User extends BusinessObject {

	private static final long serialVersionUID = 1L;
	
	private String password = "";

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
