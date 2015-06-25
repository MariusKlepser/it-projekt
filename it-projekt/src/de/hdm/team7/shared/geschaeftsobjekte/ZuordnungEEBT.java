package de.hdm.team7.shared.geschaeftsobjekte;

import java.util.ArrayList;

public class ZuordnungEEBT extends Zuordnung {

	private int enderzeugnisID;
	private ArrayList<Integer> bauteileIDS;
	
	
	public ZuordnungEEBT() {
		// TODO Auto-generated constructor stub
	}

	public int getEnderzeugnis()	{
		return enderzeugnisID;
	}
	
	public ArrayList<Integer> getBauteile()	{
		return bauteileIDS;
	}
	
	public void setEnderzeugnis (int enderzeugnisID)	{
		this.enderzeugnisID = enderzeugnisID;
	}
	
	public void addBauteil(int kinderteilID)	{
		bauteileIDS.add(kinderteilID);
	}
}
