package de.hdm.team7.database;

import java.sql.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import de.hdm.team7.server.businessObjects.User;

public class DBConnection {
	
	public static Connection connection()	{
		final Connection con = null;		//TODO Bestücken
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Worked!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static User connectionToMySql(int id, String password, String name)	{
		connection();

		String host = "jdbc:mysql://213.165.82.134:3306/whatsgoes";
		String username = "ps091";
		String passwort = "getshitdone";
		User u = new User();
		
		try {
			Connection connect = (Connection) DriverManager.getConnection(host, username, passwort);
			/*PreparedStatement statement = (PreparedStatement) connect.prepareStatement("INSERT INTO benutzer (id, password, name) VALUES (?, ?, ?)");
			statement.setInt(1, id);
			statement.setString(2, password);
			statement.setString(3, name);
			statement.executeUpdate();
			*/
			connect.close();
			//statement.close();
			System.out.println("Whoop!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	public static void main(String[] args) {
		connectionToMySql(10, "Dach", "Dominik");
	}

}
