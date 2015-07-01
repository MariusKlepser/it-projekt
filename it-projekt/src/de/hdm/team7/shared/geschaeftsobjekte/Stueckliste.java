package de.hdm.team7.shared.geschaeftsobjekte;


public class Stueckliste extends Geschaeftsobjekt {

	private static final long serialVersionUID = 1L;

	protected String erstellungsDatum;
	
	protected Baugruppe wurzelKomponente;

	public Baugruppe getWurzelKomponente() {
		return wurzelKomponente;
	}

	public void setWurzelKomponente(Baugruppe wurzelKomponente) {
		this.wurzelKomponente = wurzelKomponente;
	}

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
