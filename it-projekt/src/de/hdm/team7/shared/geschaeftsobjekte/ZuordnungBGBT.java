package de.hdm.team7.shared.geschaeftsobjekte;

import java.util.ArrayList;

public class ZuordnungBGBT extends Zuordnung {

	private int baugruppeID;
	private ArrayList<Integer> bauteile;
	
	
	public ZuordnungBGBT() {
		// TODO Auto-generated constructor stub
	}

	public int getBaugruppeID()	{
		return baugruppeID;
	}
	
	public ArrayList<Integer> getBauteile()	{
		return bauteile;
	}
	
	public void setBaugruppe (int baugruppeID)	{
		this.baugruppeID = baugruppeID;
	}
	
	public void addBauteil(int kinderteilID)	{
		bauteile.add(kinderteilID);
	}
}
