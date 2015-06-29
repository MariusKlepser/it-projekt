package de.hdm.team7.server.database;

import java.sql.Date;

import de.hdm.team7.shared.geschaeftsobjekte.*;
import de.hdm.team7.server.database.*;

public class Test_Zuordnung {
	
	public Enderzeugnis test_ee = null;

	public Test_Zuordnung(){
		//TODO
	}
	
	public void main(String[] args){
		EnderzeugnisMapper x = new EnderzeugnisMapper();
		test_ee = null;
		test_ee = test_createEE(5, "Holztisch", "Tisch aus Holz", "Holz", "29.06.2015");
		x.insert(test_ee);
		System.out.println("Erfolgreich!");
	}

	public Enderzeugnis test_createEE(int id, String name, String beschreibung, String materialBezeichnung, String dtAenderungsDatum){
		Enderzeugnis test_ee = new Enderzeugnis(id, name, beschreibung, materialBezeichnung, dtAenderungsDatum);
		return test_ee;
	}

}
