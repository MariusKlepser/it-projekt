package de.hdm.team7.client;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -1744327211076049203L;
	private String user;
	private String passwd;

	public void setUser(String user) {
		this.user = user;
	}// end setUser

	public String getUser() {
		return user;
	}// end getUser

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}// end setPasswd

	public String getPasswd() {
		return passwd;
	}// end getPasswd
}// end class