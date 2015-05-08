package de.hdm.team7.client.gui.views;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;
import com.google.gwt.view.client.TreeViewModel.NodeInfo;

import de.hdm.team7.client.cell.CustomCell;

public class ReportGeneratorOverview {
	public Panel getView(){
		
		/**
		   * The model that defines the nodes in the tree.
		   */
		  class CustomTreeModel implements TreeViewModel {
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
		
		    // Create Panels
			
			HorizontalPanel contentWrapper = new HorizontalPanel();
			contentWrapper.setWidth("100%");
			
			// Create materialRequirements Form
			HorizontalPanel materialRequirements = new HorizontalPanel();
			Button submitBtn = new Button("Berechnen");
			materialRequirements.add(submitBtn);
			TextBox countField = new TextBox();
			materialRequirements.add(countField);
			
			
			materialRequirements.setWidth("100%");
			materialRequirements.setCellWidth(countField,"50%");
			materialRequirements.setCellWidth(submitBtn,"50%");
			
			VerticalPanel panel = new VerticalPanel();
			panel.add(materialRequirements);
			panel.add(table);
			panel.setWidth("100%");
			
			contentWrapper.add(tree);
			contentWrapper.setCellWidth(tree,"50%");
			contentWrapper.add(panel);
			contentWrapper.setCellWidth(panel,"50%");
			
//			content = contentWrapper;
			
			table.setWidth("100%");		
			
		
		return contentWrapper;
	}
}
