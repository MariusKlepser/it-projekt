package de.hdm.team7.shared.geschaeftsobjekte;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

public class ZuordnungBGBG extends Zuordnung implements Serializable{

	private int zuordnungsID;
	private int baugruppeID;
	private int baugruppe2ID;
	private ArrayList<Integer> baugruppenIDS;
	//@Marius: baugruppe2ID eingefügt, um ein einzelnes Kind-Element zu identifizieren - baugruppenIDS enthält alle Kind-Elemente(?)
	
	
	public ZuordnungBGBG(int zuordnungsID, int menge, String aenderungsdatum, Benutzer aenderer, int baugruppeID, int baugruppe2ID) {
		super(zuordnungsID, menge, aenderungsdatum, aenderer);
		this.zuordnungsID = zuordnungsID;
		this.baugruppe2ID = baugruppe2ID;
		this.baugruppeID = baugruppeID;
	}

	//@Marius: Methode, um die ID des Eltern-Elements zu erfragen
	
	public int getBaugruppeID()	{
		return baugruppeID;
	}
	
	//@Marius: Methode, um die ID eines einzelnen Kind-Elements zu erfragen
	
	public int getBaugruppe2ID()	{
		return baugruppe2ID;
	}

	//@Marius: Methode, um die IDs aller Kind-Elemente zu erfragen
	
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
