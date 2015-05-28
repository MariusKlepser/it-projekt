package de.hdm.team7.shared.businessObjects;

import java.util.Date;

public class BillOfMaterial extends BusinessObject{

	private static final long serialVersionUID = 1L;
	
	private String creationDate;

	/**
	 * @return the creationDate
	 */
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * @param date the creationDate to set
	 */
	public void setCreationDate(String date) {
		this.creationDate = date;
	}

}
