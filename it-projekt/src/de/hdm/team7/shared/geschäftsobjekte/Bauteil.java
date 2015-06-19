package de.hdm.team7.shared.gesch�ftsobjekte;

import java.util.Date;

public class Bauteil extends Gesch�ftsobjekt {

	private static final long serialVersionUID = 1L;
	
	protected String beschreibung = "";
	
	protected String materialBezeichnung = "";
	
	protected String aenderungsDatum;
	protected Date dtAenderungsDatum;

	/**
	 * @return the materialIdentifier
	 */
	public String getMaterialBezeichnung() {
		return materialBezeichnung;
	}

	/**
	 * @param materialBezeichnung the materialIdentifier to set
	 */
	public void setMaterialBezeichnung(String materialBezeichnung) {
		this.materialBezeichnung = materialBezeichnung;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return beschreibung;
	}

	/**
	 * @param beschreibung the description to set
	 */
	public void setDescription(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * @return the aenderungsDatum
	 */
	public String getAenderungsDatum() {
		return aenderungsDatum;
	}
	
	/**
	 * @return the aenderungsDatum
	 */
	public Date getDtAenderungsDatum() {
		return dtAenderungsDatum;
	}

	/**
	 * @param aenderungsDatum the aenderungsDatum to set
	 */
	public void setAenderungsDatum(String aenderungsDatum) {
		this.aenderungsDatum = aenderungsDatum;
	}
	
	/**
	 * @param aenderungsDatum the aenderungsDatum to set
	 */
	public void setDtAenderungsDatum(Date aenderungsDatum) {
		this.dtAenderungsDatum = aenderungsDatum;
	}
	
	public String toString(){
		return this.getName()+", "+this.getMaterialBezeichnung()+", "+this.getDescription();
	}

}
