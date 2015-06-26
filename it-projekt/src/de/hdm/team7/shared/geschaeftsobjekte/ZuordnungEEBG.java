package de.hdm.team7.shared.geschaeftsobjekte;

import java.sql.SQLException;
import java.util.ArrayList;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

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
