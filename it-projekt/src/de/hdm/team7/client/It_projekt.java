package de.hdm.team7.client;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;

import de.hdm.team7.client.cell.CustomCell;

public class It_projekt implements EntryPoint {

	  /**
	   * The model that defines the nodes in the tree.
	   */
	  private static class CustomTreeModel implements TreeViewModel {
	    /**
	     * Get the {@link NodeInfo} that provides the children of the specified
	     * value.
	     */
	    public <T> NodeInfo<?> getNodeInfo(T value) {
	      /*
	       * Create some data in a data provider. Use the parent value as a prefix
	       * for the next level.
	       */
	      ListDataProvider<String> dataProvider = new ListDataProvider<String>();
	      for (int i = 0; i < 2; i++) {
	        dataProvider.getList().add(value + "." + String.valueOf(i));
	      }
	      // Return a node info that pairs the data with a cell.
	      return new DefaultNodeInfo<String>(dataProvider, new TextCell());
	    }
	    /**
	     * Check if the specified value represents a leaf node. Leaf nodes cannot be
	     * opened.
	     */
	    public boolean isLeaf(Object value) {
	      // The maximum length of a value is ten characters.
	      return value.toString().length() > 10;
	    }
	  }
	
	
	public void onModuleLoad() {		
		// Create a model for the tree.
	    TreeViewModel model = new CustomTreeModel();
	    /*
	     * Create the tree using the model. We specify the default value of the
	     * hidden root node as "Item 1".
	     */
	    CellTree tree = new CellTree(model, "Item 1");
		
		// The list of data to display.
		List<CustomCell> CustomCellS = Arrays
				.asList(new CustomCell("John", "123 Fourth Road"), new CustomCell("Lola",
						"123 Fourth Road"),
						new CustomCell("Phil", "123 Fourth Road"), new CustomCell("Kev",
								"123 Fourth Road"), new CustomCell("Dave",
								"123 Fourth Road"), new CustomCell("Mayo",
								"123 Fourth Road"), new CustomCell("Mary",
								"222 Lancer Lane"));
		
		// Create a data provider.
		ListDataProvider<CustomCell> dataProvider = new ListDataProvider<CustomCell>();
		
		// Create a CustomCellTable.
		CellTable<CustomCell> table = new CellTable<CustomCell>();

		// Attach a column sort handler to the ListDataProvider to sort the
		// list.
		ListHandler<CustomCell> sortHandler = new ListHandler<CustomCell>(CustomCellS);
		table.addColumnSortHandler(sortHandler);

		// Create name column.
		TextColumn<CustomCell> nameColumn = new TextColumn<CustomCell>() {
			@Override
			public String getValue(CustomCell CustomCell) {
				return CustomCell.getName();
			}
		};

		nameColumn.setSortable(true);
		// nameColumn.setDefaultSortAscending(true); // Für Kevin zum testen

		// Create address column.
		TextColumn<CustomCell> addressColumn = new TextColumn<CustomCell>() {
			@Override
			public String getValue(CustomCell CustomCell) {
				return CustomCell.getAddress();
			}
		};

		addressColumn.setSortable(true);

		// Add the columns.
		table.addColumn(nameColumn, "Name");
		table.addColumn(addressColumn, "Address");


		// Connect the table to the data provider.
		dataProvider.addDataDisplay(table);

		// Add the data to the data provider, which automatically pushes it to
		// the
		// widget.
		List<CustomCell> list = dataProvider.getList();
		for (CustomCell contact : CustomCellS) {
			list.add(contact);
		}

		// Add a ColumnSortEvent.ListHandler to connect sorting to the
		// java.util.List.
		ListHandler<CustomCell> columnSortHandler = new ListHandler<CustomCell>(list);
		columnSortHandler.setComparator(nameColumn, new Comparator<CustomCell>() {
			public int compare(CustomCell o1, CustomCell o2) {
				if (o1 == o2) {
					return 0;
				}

				// Compare the name columns.
				if (o1 != null) {
					return (o2 != null) ? o1.getName().compareTo(o2.getName()) : 1;
				}
				return -1;
			}
		});
		table.addColumnSortHandler(columnSortHandler);

		// We know that the data is sorted alphabetically by default.
		table.getColumnSortList().push(nameColumn);
		
		
		 // Make a command that we will execute from all leaves.
	    Command menuCommand = new Command() {
	      public void execute() {
	        Window.alert("You selected a menu item!");
	      }
	    };
		
		// MenuBar
		MenuBar menubar = new MenuBar();
		menubar.setAutoOpen(true);
		menubar.setWidth("100%");
		menubar.setAnimationEnabled(true);
		
	    // Create the Bauteil menu
	    MenuBar fileMenu = new MenuBar(true);
	    fileMenu.setAnimationEnabled(true);
	    menubar.addItem(new MenuItem("Bauteil", fileMenu));
	    String[] fileOptions = {"View","Add","Edit","Delete"};
	    for (int i = 0; i < fileOptions.length; i++) {
	        fileMenu.addItem(fileOptions[i], menuCommand);
	    }

	    // Create the Baugruppe menu
	    MenuBar editMenu = new MenuBar(true);
	    menubar.addItem(new MenuItem("Baugruppe", editMenu));
	    String[] editOptions = {"View","Add","Edit","Delete"};
	    for (int i = 0; i < editOptions.length; i++) {
	      editMenu.addItem(editOptions[i], menuCommand);
	    }

	    // Create the Enderzeugnis menu
	    MenuBar gwtMenu = new MenuBar(true);
	    menubar.addItem(new MenuItem("Enderzeugnis", true, gwtMenu));
	    String[] gwtOptions = {"View","Add","Edit","Delete"};
	    for (int i = 0; i < gwtOptions.length; i++) {
	      gwtMenu.addItem(gwtOptions[i], menuCommand);
	    }
	    
	    // Create the Stückliste menu
	    MenuBar partsMenu = new MenuBar(true);
	    menubar.addItem(new MenuItem("Stückliste", true, partsMenu));
	    String[] partsOptions = {"View","Add","Edit","Delete"};
	    for (int i = 0; i < partsOptions.length; i++) {
	    	partsMenu.addItem(partsOptions[i], menuCommand);
	    }

	    // Create Seperator
	    menubar.addSeparator();
	    
	    // Create the Benutzer menu
	    menubar.addItem("Benutzer", menuCommand);
		
	    
	    // Create Tab
	    TabLayoutPanel p = new TabLayoutPanel(2, Unit.EM);

	    // Create Panels
		HorizontalPanel wrapper = new HorizontalPanel();
		
		HorizontalPanel materialRequirements = new HorizontalPanel();
		
		VerticalPanel panel = new VerticalPanel();
		
		// Create materialRequirements Form
		Button submitBtn = new Button("Berechnen");
		TextBox countField = new TextBox();

		materialRequirements.add(countField);
		materialRequirements.add(submitBtn);
		
		// Add Modules
		wrapper.add(tree);
		
		panel.add(materialRequirements);

		panel.add(table);
		wrapper.add(panel);
		
		
		// Set Width
		wrapper.setWidth("100%");
		panel.setWidth("100%");
		
		materialRequirements.setWidth("100%");
		materialRequirements.setCellWidth(countField,"50%");
		materialRequirements.setCellWidth(submitBtn,"50%");
		
		table.setWidth("100%");
		
		wrapper.setCellWidth(tree,"50%");
		wrapper.setCellWidth(panel,"50%");

		// Add Tabs
		p.add(wrapper, "Report Generator");
		p.add(new HTML("Create Bauteil Content<br />res"), "Form");
		p.add(new HTML("Create Stückliste Content"), "Create Stückliste");
		p.add(new HTML("View Stückliste Content"), "View Stückliste");

		// Add to RootPanel
		RootPanel rp = RootPanel.get("content");
		RootPanel menu = RootPanel.get("menu");
		RootLayoutPanel rlp = RootLayoutPanel.get();

		// Add MenuBar
		menu.add(menubar);
		
		rlp.add(p);
		rp.add(rlp);
	}

}