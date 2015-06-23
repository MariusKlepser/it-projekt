package de.hdm.team7.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.team7.client.ClientEinstellungen;

public class AsyncCallbackString implements AsyncCallback<String> {

	@Override
	public void onFailure(Throwable caught) {
		ClientEinstellungen.getLogger().severe("Client: AsyncCallback Failure!");

	}

	@Override
	public void onSuccess(String result) {
		ClientEinstellungen.getLogger().severe("Client: AsyncCallback Success!");
		ClientEinstellungen.getLogger().info(result);
	}

}
