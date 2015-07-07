package de.hdm.team7.shared.geschaeftsobjekte;

import java.util.Date;


public class Zuordnung {
	
	
	public Zuordnung() {
		// TODO Auto-generated constructor stub
	}
	
	private int zuordnungsID;
	private int menge;
	private Date aenderungsdatum;
	private int benutzerId;
	
	public int getId()	{
		return zuordnungsID;
	}
	
	public void setId(int id)	{
		this.zuordnungsID = id;
	}
	
	public int getMenge()	{
		return menge;
	}
	
	public void setMenge(int menge)	{
		this.menge = menge;
	}
	
	public Date getAenderungsdatum()	{
		return aenderungsdatum;
	}
	
	public void setAenderungsdatum(Date aenderungsdatum)	{
		this.aenderungsdatum = aenderungsdatum;
	}
	
	public int getBenutzerId()	{
		return benutzerId; 
	}
	
	public void setBenutzerId (int benutzerId)	{
		this.benutzerId = benutzerId;
	}

}
