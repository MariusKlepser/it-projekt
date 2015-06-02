package de.hdm.team7.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import com.google.gwt.view.client.TreeViewModel.DefaultNodeInfo;
import com.google.gwt.view.client.TreeViewModel.NodeInfo;

import de.hdm.team7.shared.BOMAdministrationAsync;
import de.hdm.team7.shared.businessObjects.BillOfMaterial;
import de.hdm.team7.shared.businessObjects.Component;
import de.hdm.team7.shared.businessObjects.ComponentAssembly;
import de.hdm.team7.shared.businessObjects.EndProduct;

public class BusinessObjectTreePresenter implements Presenter {
	
	private List<Component> components;
	private List<String> componentsString;
	private List<ComponentAssembly> componentAssemblies;
	private List<EndProduct> endproducts;
	private List<BillOfMaterial> billOfMaterials;
	
	public interface Display {
	    void setTreeViewModel(TreeViewModel model);
	    Widget asWidget();
	  }
	  
	  private final BOMAdministrationAsync bom;
	  private final HandlerManager eventBus;
	  private final Display display;
	  
	  public BusinessObjectTreePresenter(BOMAdministrationAsync bom, HandlerManager eventBus, Display view) {
	    this.bom = bom;
	    this.eventBus = eventBus;
	    this.display = view;
	  }
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	// Create a cell to display a component.
	Cell<String> categoryCell = new AbstractCell<String>() {
		@Override
		public void render(Context context, String value, SafeHtmlBuilder sb) {
			if (value != null) {
				sb.appendEscaped(value);
			}
		}
	};
	
	Cell<Component> componentCell = new AbstractCell<Component>() {
		public void render(Context context, Component value,
				SafeHtmlBuilder sb) {
			if (value != null) {
				sb.appendEscaped(value.getName());
			}
		}
	};
	
	Cell<ComponentAssembly> componentAssemblyCell = new AbstractCell<ComponentAssembly>() {
		public void render(Context context, ComponentAssembly value,
				SafeHtmlBuilder sb) {
			if (value != null) {
				sb.appendEscaped(value.getName());
			}
		}
	};
	
	Cell<EndProduct> endProductCell = new AbstractCell<EndProduct>() {
		public void render(Context context, EndProduct value,
				SafeHtmlBuilder sb) {
			if (value != null) {
				sb.appendEscaped(value.getName());
			}
		}
	};
	
	Cell<BillOfMaterial> billOfMaterialCell = new AbstractCell<BillOfMaterial>() {
		public void render(Context context, BillOfMaterial value,
				SafeHtmlBuilder sb) {
			if (value != null) {
				sb.appendEscaped(value.getName());
			}
		}
	};
	
	//////////////////////////////////////////////////////////////////////////////////////
	
	public class CustomTreeModel implements TreeViewModel {
		
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

				// Return a node info that pairs the data provider and the cell.
				return new DefaultNodeInfo<String>(categoryDataProvider, categoryCell);
			} 
			else if (value instanceof String && value == "Bauteile") {
				// LEVEL 1.
				// We want the children of the category "Bauteile". Return all components.
				ListDataProvider<String> componentDataProvider = new ListDataProvider<String>();
				
				fetchComponents();
				componentsString = componentDataProvider.getList();
				
				return new DefaultNodeInfo<String>(componentDataProvider, new TextCell(), selectionModel, null);
			}
			else if (value instanceof String && value == "Baugruppen") {
				// LEVEL 1.
				// We want the children of the category "Baugruppen". Return all componentAssemblies.
				ListDataProvider<ComponentAssembly> componentAssemblyDataProvider = new ListDataProvider<ComponentAssembly>();
				
				return new DefaultNodeInfo<ComponentAssembly>(componentAssemblyDataProvider, componentAssemblyCell);
			}
			else if (value instanceof String && value == "Enderzeugnisse") {
				// LEVEL 1.
				// We want the children of the category "Enderzeugnisse". Return all endproducts.
				ListDataProvider<EndProduct> endProductDataProvider = new ListDataProvider<EndProduct>();
				
				return new DefaultNodeInfo<EndProduct>(endProductDataProvider, endProductCell);
			}
			else if (value instanceof String && value == "Stuecklisten") {
				// LEVEL 1.
				// We want the children of the category "Stuecklisten". Return all BillOfMaterials.
				ListDataProvider<BillOfMaterial> billOfMaterialDataProvider = new ListDataProvider<BillOfMaterial>();
				
				return new DefaultNodeInfo<BillOfMaterial>(billOfMaterialDataProvider, billOfMaterialCell);
			}
//			else if (value instanceof Playlist) {
//				// LEVEL 2 - LEAF.
//				// We want the children of the playlist. Return the songs.
//				ListDataProvider<String> dataProvider = new ListDataProvider<String>(
//						((Playlist) value).getSongs());
//
//				// Use the shared selection model.
//				return new DefaultNodeInfo<String>(dataProvider, new TextCell(),
//						selectionModel, null);
//			}

			return null;
		}

		@Override
		public boolean isLeaf(Object value) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
	
	///////////////////////////////////////////////////////////////////////////////////////

		@Override
		public void go(DockPanel container) {
			container.add(display.asWidget(), DockPanel.WEST);
		}

		public void fetchComponents() {
			 bom.getAllComponents(new AsyncCallback<ArrayList<Component>>() {
			      public void onSuccess(ArrayList<Component> result) {
			          components = result;
			          sortComponents();
			          List<String> data = new ArrayList<String>();

			          for (int i = 0; i < result.size(); ++i) {
			            data.add(components.get(i).getName() + " (" + components.get(i).getId() + ")");
			          }
			          
			          componentsString = data;
			      }
			      
			      public void onFailure(Throwable caught) {
			        Window.alert("Error fetching contact details");
			      }
			    });
		}

		protected void sortComponents() {
			// Yes, we could use a more optimized method of sorting, but the 
		    //  point is to create a test case that helps illustrate the higher
		    //  level concepts used when creating MVP-based applications. 
		    //
		    for (int i = 0; i < components.size(); ++i) {
		      for (int j = 0; j < components.size() - 1; ++j) {
		        if (components.get(j).getName().compareToIgnoreCase(components.get(j + 1).getName()) >= 0) {
		          Component tmp = components.get(j);
		          components.set(j, components.get(j + 1));
		          components.set(j + 1, tmp);
		        }
		      }
		    }
		}
}
