package de.hdm.team7.client.rpc;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TreeItem;

import de.hdm.team7.client.ClientEinstellungen;
import de.hdm.team7.client.gui.views.TableCellTesting;
import de.hdm.team7.shared.geschaeftsobjekte.Bauteil;

public class AsyncCallbackComponentList implements
		AsyncCallback<ArrayList<Bauteil>> {

	private TreeItem parentTreeItem;
	private TableCellTesting view;

	@Override
	public void onFailure(Throwable caught) {
		ClientEinstellungen.getLogger().severe("Client: AsyncCallback Failure!");

	}

	@Override
	public void onSuccess(ArrayList<Bauteil> result) {
		ClientEinstellungen.getLogger().severe("Client: AsyncCallback Success!");
		Boolean isEmpty = result.isEmpty();
		Integer resultSize = result.size();
		ClientEinstellungen.getLogger()
				.info("Result: " + "Empty(T/F): " + isEmpty + ", Size: "
						+ resultSize);
		for (Bauteil c : result) {
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
	 * @param parentTreeItem
	 *            the parentTreeItem to set
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
	 * @param view
	 *            the view to set
	 */
	public void setView(TableCellTesting view) {
		this.view = view;
	}
}
