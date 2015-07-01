package de.hdm.team7.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {
//	void login(User user, AsyncCallback<String> asyncCallback);
	public void login(String requestUri, AsyncCallback<LoginInfo> asyncCallback);
}// end interface
