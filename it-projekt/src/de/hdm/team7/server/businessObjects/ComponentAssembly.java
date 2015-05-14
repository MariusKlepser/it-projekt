package de.hdm.team7.server.businessObjects;

import java.util.ArrayList;

public class ComponentAssembly extends Component {

	private static final long serialVersionUID = 1L;
	
	protected ArrayList<Component> childrenComponents;

	/**
	 * @return the childrenComponents
	 */
	public ArrayList<Component> getChildrenComponents() {
		return childrenComponents;
	}

	/**
	 * @param childrenComponents the childrenComponents to set
	 */
	public void setChildrenComponents(ArrayList<Component> childrenComponents) {
		this.childrenComponents = childrenComponents;
	}

}
