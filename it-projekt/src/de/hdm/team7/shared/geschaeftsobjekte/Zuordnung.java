package de.hdm.team7.shared.geschaeftsobjekte;

import java.io.Serializable;
import java.util.Date;

public class Zuordnung implements Serializable{

	private int zuordnungsID;
	private int menge;
	private String aenderungsdatum;
	private Benutzer aenderer;
	
	public Zuordnung(int zuordnungsID, int menge, String aenderungsdatum, Benutzer aenderer){
		this.zuordnungsID = zuordnungsID;
		this.menge = menge;
		this.aenderungsdatum = aenderungsdatum;
		this.aenderer = aenderer;
	}
	
	public int getId() {
		return zuordnungsID;
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
	public String getAenderungsdatum() {
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
	
	public void setId(int zuordnungsID)	{
		this.zuordnungsID = zuordnungsID;
	}
	
	public void setMenge(int menge) {
		this.menge = menge;
	}
	
	/**
	 * @param aenderungsDatum the aenderungsDatum to set
	 */
	public void setAenderungsdatum(String aenderungsdatum) {
		this.aenderungsdatum = aenderungsdatum;
	}
	
	public void setAenderer(Benutzer aenderer) {
		this.aenderer = aenderer;
	}
	
	public String toString(){
		return this.getId()+", "+this.getMenge()+", "+this.getAenderungsdatum()+", "+this.getAenderer();
	}
	
}
