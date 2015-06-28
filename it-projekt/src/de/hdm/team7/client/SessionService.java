package de.hdm.team7.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("session")
public interface SessionService extends RemoteService {
	SessionId session(SessionId sessionId);
}// end class