package de.hdm.team7.shared.geschaeftsobjekte;

import java.util.ArrayList;

public class Baugruppe extends Bauteil {

	private static final long serialVersionUID = 1L;
	
	protected ArrayList<Bauteil> kinderKomponenten;

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
