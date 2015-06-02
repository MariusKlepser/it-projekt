package de.hdm.team7.client.ui.views;

import java.util.List;

import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TreeNode;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.TreeViewModel;

import de.hdm.team7.client.presenter.BusinessObjectTreePresenter;

public class BusinessObjectTreeView extends Composite implements BusinessObjectTreePresenter.Display {
	
    private TreeViewModel model;
	
	public BusinessObjectTreeView() {
		
		DecoratorPanel contentTableDecorator = new DecoratorPanel();
	    initWidget(contentTableDecorator);
	    contentTableDecorator.setWidth("100%");
	    contentTableDecorator.setWidth("18em");
		
		 // Create a model for the tree (siehe set...())
	    /*
	     * Create the tree using the model. We use <code>null</code> as the default
	     * value of the root node. The default value will be passed to
	     * CustomTreeModel#getNodeInfo();
	     */
	    CellTree tree = new CellTree(model, null);
	    tree.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

	    // Open the first treenode by default.
	    TreeNode rootNode = tree.getRootTreeNode();
//	    TreeNode firstNode = rootNode.setChildOpen(0, true);
//	    firstNode.setChildOpen(0, true);
	    contentTableDecorator.add(tree);
		
	}

	@Override
	public void setTreeViewModel(TreeViewModel model) {
		this.model = model;
	}
	
	public Widget asWidget() {
		return this;
	}

}
