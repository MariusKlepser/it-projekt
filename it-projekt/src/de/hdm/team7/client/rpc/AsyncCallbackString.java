package de.hdm.team7.client.rpc;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TreeItem;

import de.hdm.team7.client.ClientsideSettings;
import de.hdm.team7.shared.businessObjects.*;

public class AsyncCallbackString implements AsyncCallback <String>{
	
	@Override
	public void onFailure(Throwable caught) {
		ClientsideSettings.getLogger().severe("Client: AsyncCallback Failure!");
		
	}

	@Override
	public void onSuccess(String result) {
		ClientsideSettings.getLogger().severe("Client: AsyncCallback Success!");
		ClientsideSettings.getLogger().info(result);	
	}
	
	
}
