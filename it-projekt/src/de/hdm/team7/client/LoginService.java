package de.hdm.team7.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
//	String login(User user);
	public LoginInfo login(String requestUri);
}// end interface
