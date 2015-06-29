package de.hdm.team7.shared.geschaeftsobjekte;

import java.util.ArrayList;

public class Baugruppe extends Bauteil {

	private static final long serialVersionUID = 1L;
	
	protected ArrayList<Bauteil> kinderKomponenten;

	
	public Baugruppe(int id, String name, String beschreibung, String materialBezeichnung, String aenderungsDatum){
		super(id, name, beschreibung, materialBezeichnung, aenderungsDatum);
	}
	
	/**
	 * @return the childrenComponents
	 */
	public ArrayList<Bauteil> getKinderKomponenten() {
		return kinderKomponenten;
	}

	/**
	 * @param kinderKomponenten the childrenComponents to set
	 */
	public void setKinderKomponenten(ArrayList<Bauteil> kinderKomponenten) {
		this.kinderKomponenten = kinderKomponenten;
	}

}
