package de.hdm.team7.shared.businessObjects;

import java.util.Date;

public class Component extends BusinessObject{

	private static final long serialVersionUID = 1L;
	
	protected String description = "";
	
	protected String materialIdentifier = "";
	
	protected String changeDate;
	protected Date dtChangeDate;

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
	public String getChangeDate() {
		return changeDate;
	}
	
	/**
	 * @return the aenderungsDatum
	 */
	public Date getdtChangeDate() {
		return dtChangeDate;
	}

	/**
	 * @param aenderungsDatum the aenderungsDatum to set
	 */
	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}
	
	/**
	 * @param aenderungsDatum the aenderungsDatum to set
	 */
	public void setChangeDate(Date changeDate) {
		this.dtChangeDate = changeDate;
	}

}
