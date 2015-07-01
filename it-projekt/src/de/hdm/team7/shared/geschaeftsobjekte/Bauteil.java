package de.hdm.team7.shared.geschaeftsobjekte;

import java.util.Date;

import com.google.gwt.view.client.ProvidesKey;

public class Bauteil extends Geschaeftsobjekt implements Comparable<Bauteil>{

	private static final long serialVersionUID = 1L;
	
	protected String beschreibung = "";
	protected String materialBezeichnung = "";
	protected String aenderungsDatum;
	protected Date dtAenderungsDatum;
	protected String Menge = "0";
	
	/**
     * The key provider that provides the unique ID of a contact.
     */
    public static final ProvidesKey<Bauteil> KEY_PROVIDER = new ProvidesKey<Bauteil>() {
      @Override
      public Object getKey(Bauteil item) {
        return item == null ? null : item.getId();
      }
    };

	public String getMenge() {
		return Menge;
	}

	public void setMenge(String menge) {
		Menge = menge;
	}

	/**
	 * @return the materialIdentifier
	 */
	
	public String getMaterialBezeichnung() {
		return materialBezeichnung;
	}

	/**
	 * @param materialBezeichnung the materialIdentifier to set
	 */
	public void setMaterialBezeichnung(String materialBezeichnung) {
		this.materialBezeichnung = materialBezeichnung;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return beschreibung;
	}

	/**
	 * @param beschreibung the description to set
	 */
	public void setDescription(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * @return the aenderungsDatum
	 */
	public String getAenderungsDatum() {
		return aenderungsDatum;
	}
	
	/**
	 * @return the aenderungsDatum
	 */
	public Date getDtAenderungsDatum() {
		return dtAenderungsDatum;
	}

	/**
	 * @param aenderungsDatum the aenderungsDatum to set
	 */
	public void setAenderungsDatum(String aenderungsDatum) {
		this.aenderungsDatum = aenderungsDatum;
	}
	
	/**
	 * @param aenderungsDatum the aenderungsDatum to set
	 */
	public void setDtAenderungsDatum(Date aenderungsDatum) {
		this.dtAenderungsDatum = aenderungsDatum;
	}

	public String toString(){
		return this.getName()+", "+this.getMaterialBezeichnung()+", "+this.getDescription();
	}

	@Override
    public int compareTo(Bauteil o) {
      return (o == null || o.name == null) ? -1 : -o.name.compareTo(name);
    }

	public int compareTo(Baugruppe o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int compareTo(Enderzeugnis o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
