package de.hdm.team7.shared.geschaeftsobjekte;


public class Stueckliste extends Geschaeftsobjekt {

	public Stueckliste(int id, String name) {
		super(id, name);
	}

	private static final long serialVersionUID = 1L;

	private String erstellungsDatum;

	/**
	 * @return the creationDate
	 */
	public String getErstellungsDatum() {
		return erstellungsDatum;
	}

	/**
	 * @param datum
	 *            the creationDate to set
	 */
	public void setErstellungsDatum(String datum) {
		this.erstellungsDatum = datum;
	}

}
