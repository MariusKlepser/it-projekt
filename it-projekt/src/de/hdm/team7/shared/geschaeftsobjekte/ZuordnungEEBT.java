package de.hdm.team7.shared.geschaeftsobjekte;

import java.sql.SQLException;
import java.util.ArrayList;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

public class ZuordnungEEBT extends Zuordnung {

	private int enderzeugnisID;
	private int bauteilID;
	private ArrayList<Integer> bauteileIDS;
	
	
	public ZuordnungEEBT() {
		// TODO Auto-generated constructor stub
	}

	public int getEnderzeugnisID()	{
		return enderzeugnisID;
	}
	
	public int getBauteilID()	{
		return bauteilID;
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


public void findeAlleKinder(Connection con)	{
	
//	String query = "SELECT * FROM ZuordnungEEBG WHERE 'enderzeugnisID'" + this.enderzeugnisID;
//	ResultSet rs = stmt.executeQuery("SELECT baugruppenID, menge FROM ZuordnungEEBG" + "WHERE id=" + enderzeugnisID + "ORDER BY baugruppenID");
	
	String query = "SELECT * FROM z_enderzeugnisBauteil WHERE id=" + this.enderzeugnisID;
	
	try (Statement stmt = con.createStatement()) {

		ResultSet rs = stmt.executeQuery("SELECT bauteilID, Menge FROM z_enderzeugnisBaugruppe" + "WHERE enderzeugnisID=" + this.enderzeugnisID + "ORDER BY bauteilID");

	        while (rs.next()) {
	            int bauteilID = rs.getInt("bauteilID");
	            int menge = rs.getInt("Menge");
	            System.out.println(bauteilID + ", " + menge);
	        }
	    } catch (SQLException e) {
	    }

}

}