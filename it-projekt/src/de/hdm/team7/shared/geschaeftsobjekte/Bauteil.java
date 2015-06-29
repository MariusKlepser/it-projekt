package de.hdm.team7.shared.geschaeftsobjekte;

import java.util.Date;

public class Bauteil extends Geschaeftsobjekt {

	private static final long serialVersionUID = 1L;
	
	protected String beschreibung = "";
	
	protected String materialBezeichnung = "";
	
	protected String aenderungsDatum;
	protected Date dtAenderungsDatum;
	
	public Bauteil(int id, String name, String beschreibung, String materialBezeichnung, String aenderungsDatum){
		super(id, name);
		this.beschreibung = beschreibung;
		this.materialBezeichnung = materialBezeichnung;
		this.aenderungsDatum = aenderungsDatum;
	}

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
