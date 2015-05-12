package de.hdm.team7.businessObjects;

import java.util.Date;

public class BillOfMaterial extends BusinessObject{

	private static final long serialVersionUID = 1L;
	
	private Date creationDate;

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

}
