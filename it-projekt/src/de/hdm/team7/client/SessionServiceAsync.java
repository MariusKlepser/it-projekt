package de.hdm.team7.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SessionServiceAsync {
	void session(SessionId sessionId, AsyncCallback<SessionId> asyncCallback);
}// end MyServiceAsync