package de.hdm.team7.server.businessObjects;

import java.util.Date;

public class Component extends BusinessObject{

	private static final long serialVersionUID = 1L;
	
	protected String description = "";
	
	protected String materialIdentifier = "";
	
	protected Date changeDate;

	/**
	 * @return the materialIdentifier
	 */
	public String getMaterialIdentifier() {
		return materialIdentifier;
	}

	/**
	 * @param materialIdentifier the materialIdentifier to set
	 */
	public void setMaterialIdentifier(String materialIdentifier) {
		this.materialIdentifier = materialIdentifier;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the aenderungsDatum
	 */
	public Date getChangeDate() {
		return changeDate;
	}

	/**
	 * @param aenderungsDatum the aenderungsDatum to set
	 */
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	
	public String toString(){
		return this.getName()+","+this.getMaterialIdentifier()+","+this.getDescription();
	}

}
