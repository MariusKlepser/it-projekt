package de.hdm.team7.shared.geschaeftsobjekte;

import java.sql.SQLException;
import java.util.ArrayList;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

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


public void findeAlleKinder(Connection con)	{
	
//	String query = "SELECT * FROM ZuordnungEEBG WHERE 'enderzeugnisID'" + this.enderzeugnisID;
//	ResultSet rs = stmt.executeQuery("SELECT baugruppenID, menge FROM ZuordnungEEBG" + "WHERE id=" + enderzeugnisID + "ORDER BY baugruppenID");
	
	String query = "SELECT * FROM z_baugruppeBaugruppe WHERE id=" + this.baugruppeID;
	
	try (Statement stmt = con.createStatement()) {

		ResultSet rs = stmt.executeQuery("SELECT baugruppe2ID, Menge FROM z_baugruppeBaugruppe" + "WHERE baugruppeID=" + baugruppeID + "ORDER BY baugruppe2ID");

	        while (rs.next()) {
	            int baugruppe2ID = rs.getInt("baugruppe2ID");
	            int menge = rs.getInt("Menge");
	            System.out.println(baugruppe2ID + ", " + menge);
	        }
	    } catch (SQLException e) {
	    }

}

}
