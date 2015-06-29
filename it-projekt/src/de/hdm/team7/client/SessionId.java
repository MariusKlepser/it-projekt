package de.hdm.team7.client;

import java.io.Serializable;

public class SessionId implements Serializable {
	private static final long serialVersionUID = 4637358200152132934L;
	private String sessionId = "";

	public void setSessionId(String sessionId) {
		if (sessionId == null)
			this.sessionId = "";
		else
			this.sessionId = sessionId;
	}// end setSessionId

	public String getSessionId() {
		return sessionId;
	}// end getSessionId
}// end class
