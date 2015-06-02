package de.hdm.team7.shared.businessObjects;

import java.util.Date;

public class BillOfMaterial extends BusinessObject{

	private static final long serialVersionUID = 1L;
	
	private Date creationDate;
	
	private ComponentAssembly rootElement;

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the rootElement
	 */
	public ComponentAssembly getRootElement() {
		return rootElement;
	}

	/**
	 * @param rootElement the rootElement to set
	 */
	public void setRootElement(ComponentAssembly rootElement) {
		this.rootElement = rootElement;
	}

}
