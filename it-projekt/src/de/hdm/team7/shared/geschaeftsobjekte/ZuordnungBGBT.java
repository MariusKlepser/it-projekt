package de.hdm.team7.shared.geschaeftsobjekte;

import java.sql.SQLException;
import java.util.ArrayList;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

public class ZuordnungBGBT extends Zuordnung {

	private int baugruppeID;
	private int bauteilID;
	private ArrayList<Integer> bauteile;
	
	
	public ZuordnungBGBT() {
		// TODO Auto-generated constructor stub
	}

	public int getBaugruppeID()	{
		return baugruppeID;
	}
	
	public int getBauteilID()	{
		return bauteilID;
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


public void findeAlleKinder(Connection con)	{
	
//	String query = "SELECT * FROM ZuordnungEEBG WHERE 'enderzeugnisID'" + this.enderzeugnisID;
//	ResultSet rs = stmt.executeQuery("SELECT baugruppenID, menge FROM ZuordnungEEBG" + "WHERE id=" + enderzeugnisID + "ORDER BY baugruppenID");
	
	String query = "SELECT * FROM z_baugruppeBauteil WHERE id=" + this.baugruppeID;
	
	try (Statement stmt = con.createStatement()) {

		ResultSet rs = stmt.executeQuery("SELECT bauteilID, Menge FROM z_baugruppeBauteil" + "WHERE baugruppeID=" + baugruppeID + "ORDER BY bauteilID");

	        while (rs.next()) {
	            int bauteilID = rs.getInt("bauteilID");
	            int menge = rs.getInt("Menge");
	            System.out.println(bauteilID + ", " + menge);
	        }
	    } catch (SQLException e) {
	    }

}

}