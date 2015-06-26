package de.hdm.team7.shared.geschaeftsobjekte;

import java.util.ArrayList;

public class ZuordnungBGBG extends Zuordnung {

	private int baugruppeID;
	private ArrayList<Integer> baugruppenIDS;
	
	
	public ZuordnungBGBG() {
		// TODO Auto-generated constructor stub
	}

	public int getBaugruppeID()	{
		return baugruppeID;
	}
	
	
	public ArrayList<Integer> getBaugruppenIDS()	{
		return baugruppenIDS;
	}
	
	public void setBaugruppeID (int baugruppeID)	{
		this.baugruppeID = baugruppeID;
	}
	
	public void addBaugruppe(int kinderteilID)	{
		baugruppenIDS.add(kinderteilID);
	}
}
