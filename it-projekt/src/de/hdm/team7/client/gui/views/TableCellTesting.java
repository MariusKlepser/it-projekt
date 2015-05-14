package de.hdm.team7.client.gui.views;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CompositeCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.HasCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.team7.client.DataSet;

public class TableCellTesting {

	public Panel getView(){
		// Initiate wrapper Panel
		Panel panel = new FlowPanel();
		
		// Start layout creation here
		HTML h1 = new HTML("<h1>TableCellTesting</h1>");
		panel.add(h1);
		
		// CellTable Test-Data
		final List<DataSet> DATA = Arrays.asList(
			new DataSet(0, "Motor", "bauteil"), 
			new DataSet(1, "Baum", "stueckliste"), 
			new DataSet(2, "Zitrone", "bauteil"),
			new DataSet(3, "Kischde", "enderzeugnis")
		);
		
		// Creating CellTable
		CellTable<DataSet> table = new CellTable<DataSet>();
//		DataGrid<DataSet> table = new DataGrid<DataSet>();
//		table.setAutoHeaderRefreshDisabled(true);
//		table.setEmptyTableWidget(new Label("Empty"));
		
		// Creating Id-Col
		TextColumn<DataSet> idColumn = new TextColumn<DataSet>() {
			@Override
			public String getValue(DataSet dataset) {
				return dataset.getIdString();
			}
		};
		
		// Make Id-Col sortable
		idColumn.setSortable(true);
		
		// Creating Name-Col
		TextColumn<DataSet> nameColumn = new TextColumn<DataSet>() {
			@Override
			public String getValue(DataSet dataset) {
				return dataset.getName();
			}
		};
		
		// Make Name-Col sortable
		nameColumn.setSortable(true);

		List<HasCell<DataSet, ?>> cells = new LinkedList<HasCell<DataSet, ?>>();

		ActionHasCell editBtn = new ActionHasCell("Edit",
				new Delegate<DataSet>() {
					@Override
					public void execute(DataSet object) {
						History.newItem("edit/" + object.getIdString());
					}
				});
		cells.add(editBtn);

		ActionHasCell viewBtn = new ActionHasCell("View",
				new Delegate<DataSet>() {
					@Override
					public void execute(DataSet object) {
						History.newItem("view/" + object.getIdString());
					}
				});
		cells.add(viewBtn);

		ActionHasCell deleteBtn = new ActionHasCell("Delete",
				new Delegate<DataSet>() {
					@Override
					public void execute(DataSet object) {
						History.newItem("delete/" + object.getIdString());
					}
				});
		cells.add(deleteBtn);
		
	    CompositeCell<DataSet> cell = new CompositeCell<DataSet>(cells);

	    Column<DataSet, DataSet> actionColumn = new Column<DataSet, DataSet>(cell) {
	        @Override
	        public DataSet getValue(DataSet object) {
	            return object;
	        }
	    };
		
		// Add the cols to table
		table.addColumn(idColumn, "Id");
		table.addColumn(nameColumn, "Name");
		table.addColumn(actionColumn, "Actions");
		
		// Create a data provider.
	    ListDataProvider<DataSet> dataProvider = new ListDataProvider<DataSet>();

	    // Connect the table to the data provider.
	    dataProvider.addDataDisplay(table);
		
	    List<DataSet> list = dataProvider.getList();
	    for (DataSet dataset : DATA) {
	      list.add(dataset);
	    }

		// Add a ColumnSortEvent.ListHandler to connect sorting to the
		// java.util.List.
		ListHandler<DataSet> columnSortHandler = new ListHandler<DataSet>(list);
		columnSortHandler.setComparator(nameColumn, new Comparator<DataSet>() {
			public int compare(DataSet o1, DataSet o2) {
				if (o1 == o2) {
					return 0;
				}

				// Compare the name columns.
				if (o1 != null) {
					return (o2 != null) ? o1.getName().compareTo(o2.getName())
							: 1;
				}
				return -1;
			}
		});

		table.addColumnSortHandler(columnSortHandler);
		
		// We know that the data is sorted alphabetically by default.
	    table.getColumnSortList().push(nameColumn);
	    
	    table.setWidth("100%");
	    
	    panel.add(table);
		
		Label lbl = new Label("<de.hdm.team7.client.gui.views.TableCellTesting.java>");
		panel.add(lbl);
		// End layout creation here
		
		// IMPORTANT: always return a Panel!
		return panel;
	}
	
	private class ActionHasCell implements HasCell<DataSet, DataSet> {
	    private ActionCell<DataSet> cell;

	    public ActionHasCell(String text, Delegate<DataSet> delegate) {
	        cell = new ActionCell<DataSet>(text, delegate);
	    }

	    @Override
	    public Cell<DataSet> getCell() {
	        return cell;
	    }

	    @Override
	    public FieldUpdater<DataSet, DataSet> getFieldUpdater() {
	        return null;
	    }

	    @Override
	    public DataSet getValue(DataSet object) {
	        return object;
	    }
	}
	
}
