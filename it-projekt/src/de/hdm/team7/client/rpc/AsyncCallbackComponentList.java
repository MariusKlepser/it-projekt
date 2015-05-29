package de.hdm.team7.client.rpc;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TreeItem;

import de.hdm.team7.client.ClientsideSettings;
import de.hdm.team7.client.gui.CustomStaticTree;
import de.hdm.team7.client.gui.views.TableCellTesting;
import de.hdm.team7.shared.businessObjects.Component;

public class AsyncCallbackComponentList implements AsyncCallback<ArrayList<Component>>{
	
	private TreeItem parentTreeItem;
	private TableCellTesting view;

	@Override
	public void onFailure(Throwable caught) {
		ClientsideSettings.getLogger().severe("Client: AsyncCallback Failure!");
		
	}

	@Override
	public void onSuccess(ArrayList<Component> result) {
		ClientsideSettings.getLogger().severe("Client: AsyncCallback Success!");
		Boolean isEmpty = result.isEmpty();
		Integer resultSize = result.size();
		ClientsideSettings.getLogger().info("Result: " + "Empty(T/F): " + isEmpty + ", Size: " + resultSize);
		for (Component c : result){
			this.parentTreeItem.addTextItem(c.getName());
		}
	}

	/**
	 * @return the parentTreeItem
	 */
	public TreeItem getParentTreeItem() {
		return parentTreeItem;
	}

	/**
	 * @param parentTreeItem the parentTreeItem to set
	 */
	public void setParentTreeItem(TreeItem parentTreeItem) {
		this.parentTreeItem = parentTreeItem;
	}

	/**
	 * @return the view
	 */
	public TableCellTesting getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(TableCellTesting view) {
		this.view = view;
	}
}
