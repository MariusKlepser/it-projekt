package de.hdm.team7.shared.geschaeftsobjekte;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

public class ZuordnungEEBG extends Zuordnung implements Serializable{

	private int zuordnungsID;
	private int enderzeugnisID;
	private int baugruppeID;
	private ArrayList<Integer> baugruppenIDS;
	
	
	public ZuordnungEEBG(int zuordnungsID, int menge, String aenderungsdatum, Benutzer aenderer, int enderzeugnisID, int baugruppeID) {
		super(zuordnungsID, menge, aenderungsdatum, aenderer);
		this.zuordnungsID = zuordnungsID;
		this.enderzeugnisID = enderzeugnisID;
		this.baugruppeID = baugruppeID;
	}

	public int getEnderzeugnisID()	{
		return enderzeugnisID;
	}
	
	public int getBaugruppeID()	{
		return baugruppeID;
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
	
	public void findeAlleKinder(Connection con)	{
		
	//	String query = "SELECT * FROM ZuordnungEEBG WHERE 'enderzeugnisID'" + this.enderzeugnisID;
	//	ResultSet rs = stmt.executeQuery("SELECT baugruppenID, menge FROM ZuordnungEEBG" + "WHERE id=" + enderzeugnisID + "ORDER BY baugruppenID");
		
		String query = "SELECT * FROM z_enderzeugnisBaugruppe WHERE id=" + this.enderzeugnisID;
		
		try (Statement stmt = con.createStatement()) {

			ResultSet rs = stmt.executeQuery("SELECT baugruppeID, Menge FROM z_enderzeugnisBaugruppe" + "WHERE enderzeugnisID=" + enderzeugnisID + "ORDER BY baugruppenID");

		        while (rs.next()) {
		            int baugruppeID = rs.getInt("baugruppeID");
		            int menge = rs.getInt("Menge");
		            System.out.println(baugruppeID + ", " + menge);
		        }
		    } catch (SQLException e) {
		    }
	
	}
	
}
