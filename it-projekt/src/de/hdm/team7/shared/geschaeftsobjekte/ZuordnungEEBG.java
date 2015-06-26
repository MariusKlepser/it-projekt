package de.hdm.team7.shared.geschaeftsobjekte;

import java.util.ArrayList;

public class ZuordnungEEBG extends Zuordnung {

	private int enderzeugnisID;
	private ArrayList<Integer> baugruppenIDS;
	
	
	public ZuordnungEEBG() {
		// TODO Auto-generated constructor stub
	}

	public int getEnderzeugnisID()	{
		return enderzeugnisID;
	}
	
	public void setEnderzeugnisID(int enderzeugnisID)	{
		this.enderzeugnisID = enderzeugnisID;
	}
	
	public ArrayList<Integer> getBaugruppenIDS()	{
		return baugruppenIDS;
	}
	
	public void setEnderzeugnis (int enderzeugnisID)	{
		this.enderzeugnisID = enderzeugnisID;
	}
	
	public void addBaugruppe(int kinderteilID)	{
		baugruppenIDS.add(kinderteilID);
	}

	/* "SELECT id, name, erstellungsdatum FROM stueckliste "
							+ "WHERE id=" + id + " ORDER BY name"
	*/
	
	public void findeAlleKinder()	{
		
		String query = "SELECT * FROM ZuordnungEEBG WHERE 'enderzeugnisID'" + this.enderzeugnisID";
		ResultSet rs = stmt.executeQuery("SELECT baugruppenID, menge FROM ZuordnungEEBG" + "WHERE id=" + enderzeugnisID + "ORDER BY baugruppenID");
	}
	
}
