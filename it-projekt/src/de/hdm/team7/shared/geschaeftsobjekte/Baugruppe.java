package de.hdm.team7.shared.geschaeftsobjekte;

import java.util.ArrayList;

import com.google.gwt.view.client.ProvidesKey;

public class Baugruppe extends Bauteil {

	private static final long serialVersionUID = 1L;
	
	protected ArrayList<Bauteil> kinderKomponenten;
	
	/**
     * The key provider that provides the unique ID of a contact.
     */
    public static final ProvidesKey<Baugruppe> KEY_PROVIDER = new ProvidesKey<Baugruppe>() {
      @Override
      public Object getKey(Baugruppe item) {
        return item == null ? null : item.getId();
      }
    };

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

	public String toStringList(){
		return this.getId() + ", " + this.getName() + " (Baugruppe)";
		
	}

	@Override
    public int compareTo(Baugruppe o) {
      return (o == null || o.name == null) ? -1 : -o.name.compareTo(name);
    }
}
