package de.hdm.team7.client.gui;

import java.util.ArrayList;

import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

import de.hdm.team7.client.*;
import de.hdm.team7.client.rpc.*;
import de.hdm.team7.shared.businessObjects.*;

public class CustomStaticTree extends Tree {

	private static ArrayList<Component> components = new ArrayList<Component>();
	private static ArrayList<ComponentAssembly> componentAssemblies = new ArrayList<ComponentAssembly>();
	private static ArrayList<EndProduct> endProducts = new ArrayList<EndProduct>();
	private static ArrayList<BillOfMaterial> billOfMaterials = new ArrayList<BillOfMaterial>();

	/**
	 * @return the new tree
	 */
	public CustomStaticTree createStaticTree() {
		// Create a new tree
		CustomStaticTree staticTree = new CustomStaticTree();
		staticTree.setAnimationEnabled(true);
		TreeItem root = staticTree.addTextItem("Verwaltung");
		TreeItem components = root.addTextItem("Bauteile");
		TreeItem componentAssemblies = root.addTextItem("Baugruppen");
		TreeItem endProducts = root.addTextItem("Enderzeugnisse");
		TreeItem billOfMaterials = root.addTextItem("Stuecklisten");

		refreshComponents(components);

		return staticTree;
	}

	public void refreshComponents(TreeItem parent) {
		AsyncCallbackComponentList componentListCallback = new AsyncCallbackComponentList();
		componentListCallback.setParentTreeItem(parent);
		ClientsideSettings.getBOMAdministration().getAllComponents(
				componentListCallback);
	}
}
