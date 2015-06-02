package de.hdm.team7.client.gui;

import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import com.google.gwt.view.client.TreeViewModel.NodeInfo;

import de.hdm.team7.shared.businessObjects.BillOfMaterial;
import de.hdm.team7.shared.businessObjects.Component;
import de.hdm.team7.shared.businessObjects.ComponentAssembly;
import de.hdm.team7.shared.businessObjects.EndProduct;

/**
 * The model that defines the nodes in the tree.
 */
public class CustomTreeModel implements TreeViewModel {

	private List<Component> components;
	private List<ComponentAssembly> componentAssemblies;
	private List<EndProduct> endproducts;
	private List<BillOfMaterial> billOfMaterials;

	/**
	 * This selection model is shared across all leaf nodes. A selection model
	 * can also be shared across all nodes in the tree, or each set of child
	 * nodes can have its own instance. This gives you flexibility to determine
	 * how nodes are selected.
	 */
	private final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();

	private ListDataProvider<String> categoryDataProvider;

	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if (value == null) {
			// LEVEL 0.
			// We passed null as the root value.

			// Create a data provider that provides categories.
			categoryDataProvider = new ListDataProvider<String>();
			List<String> categoryList = categoryDataProvider.getList();
			categoryList.add("Bauteile");
			categoryList.add("Baugruppen");
			categoryList.add("Enderzeugnisse");
			categoryList.add("Stuecklisten");

			// Create a cell to display a component.
			Cell<String> categoryCell = new AbstractCell<String>() {
				@Override
				public void render(Context context,
						String value, SafeHtmlBuilder sb) {
					if (value != null) {
						sb.appendEscaped(value);
					}
				}
			};

			// Return a node info that pairs the data provider and the cell.
			return new DefaultNodeInfo<String>(categoryDataProvider, categoryCell);
		} 
//		else if (value instanceof String && value == "Bauteile") {
//			// LEVEL 1.
//			// We want the children of the category "Bauteile". Return all components.
//			ListDataProvider<Component> componentDataProvider = 
//					new ListDataProvider<Component>();
//			Cell<Playlist> cell = new AbstractCell<Playlist>() {
//				@Override
//				public void render(Context context, Playlist value,
//						SafeHtmlBuilder sb) {
//					if (value != null) {
//						sb.appendEscaped(value.getName());
//					}
//				}
//			};
//			return new DefaultNodeInfo<Playlist>(dataProvider, cell);
//		} else if (value instanceof Playlist) {
//			// LEVEL 2 - LEAF.
//			// We want the children of the playlist. Return the songs.
//			ListDataProvider<String> dataProvider = new ListDataProvider<String>(
//					((Playlist) value).getSongs());
//
//			// Use the shared selection model.
//			return new DefaultNodeInfo<String>(dataProvider, new TextCell(),
//					selectionModel, null);
//		}

		return null;
	}

	@Override
	public boolean isLeaf(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

}