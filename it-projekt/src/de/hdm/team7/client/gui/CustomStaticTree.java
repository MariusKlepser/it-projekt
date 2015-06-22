package de.hdm.team7.client.gui;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

import de.hdm.team7.client.ClientEinstellungen;
import de.hdm.team7.client.rpc.AsyncCallbackComponentList;
import de.hdm.team7.shared.geschaeftsobjekte.Baugruppe;
import de.hdm.team7.shared.geschaeftsobjekte.Bauteil;
import de.hdm.team7.shared.geschaeftsobjekte.Enderzeugnis;
import de.hdm.team7.shared.geschaeftsobjekte.Stueckliste;

public class CustomStaticTree extends Tree {

	private static ArrayList<Bauteil> components = new ArrayList<Bauteil>();
	private static ArrayList<Baugruppe> componentAssemblies = new ArrayList<Baugruppe>();
	private static ArrayList<Enderzeugnis> endProducts = new ArrayList<Enderzeugnis>();
	private static ArrayList<Stueckliste> billOfMaterials = new ArrayList<Stueckliste>();

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
		ClientEinstellungen.getStuecklistenVerwaltung().holeAlleBauteile(
				componentListCallback);
	}
}
