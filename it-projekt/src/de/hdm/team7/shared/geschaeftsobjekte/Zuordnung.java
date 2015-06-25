package de.hdm.team7.shared.geschaeftsobjekte;

import java.util.Date;

public class Zuordnung {

	private int id;
	private int menge;
	private Date aenderungsdatum;
	private Benutzer aenderer;
	
	
	public int getId() {
		return id;
	}

	/**
	 * @param materialBezeichnung the materialIdentifier to set
	 */
	public int getMenge() {
		return menge;
	}

	/**
	 * @return the description
	 */
	public Date getAenderungsdatum() {
		return aenderungsdatum;
	}

	/**
	 * @param beschreibung the description to set
	 */
	public Benutzer getAenderer() {
		return aenderer;
	}

	/**
	 * @param aenderungsDatum the aenderungsDatum to set
	 */
	
	public void setId(int id)	{
		this.id = id;
	}
	
	public void setMenge(int menge) {
		this.menge = menge;
	}
	
	/**
	 * @param aenderungsDatum the aenderungsDatum to set
	 */
	public void setAenderungsdatum(Date aenderungsdatum) {
		this.aenderungsdatum = aenderungsdatum;
	}
	
	public void setAenderer(Benutzer aenderer) {
		this.aenderer = aenderer;
	}
	
	public String toString(){
		return this.getId()+", "+this.getMenge()+", "+this.getAenderungsdatum()+", "+this.getAenderer();
	}
	
}
