package de.hdm.team7.shared.geschaeftsobjekte;

import java.util.ArrayList;

import com.google.gwt.view.client.ProvidesKey;

public class Enderzeugnis extends Baugruppe {

	private static final long serialVersionUID = 1L;
	
	protected ArrayList<ZuordnungEEBG> kinderKomponentenBG;
	protected ArrayList<ZuordnungEEBT> kinderKomponentenBT;
	
	
	/**
     * The key provider that provides the unique ID of a contact.
     */
    public static final ProvidesKey<Enderzeugnis> KEY_PROVIDER = new ProvidesKey<Enderzeugnis>() {
      @Override
      public Object getKey(Enderzeugnis item) {
        return item == null ? null : item.getId();
      }
    };
	

	/**
	 * @return the childrenComponents
	 */
	public ArrayList<ZuordnungBGBG> getKinderKomponentenBG() {
		return kinderKomponentenBG;
	}

	public ArrayList<ZuordnungBGBT> getKinderKomponentenBT() {
		return kinderKomponentenBT;
	}

	
	/**
	 * @param kinderKomponenten the childrenComponents to set
	 */
	public void setKinderKomponentenBG(ArrayList<ZuordnungBGBG> kinderKomponentenBG) {
		this.kinderKomponentenBG = kinderKomponentenBG;
	}
	
	public void setKinderKomponentenBT(ArrayList<ZuordnungBGBT> kinderKomponentenBT) {
		this.kinderKomponentenBT = kinderKomponentenBT;
	}


    
    
    
	public String toString(){
		return this.getId() + ", " + this.getName() + " (Enderzeugnis)";
	}
	
	@Override
    public int compareTo(Enderzeugnis o) {
      return (o == null || o.name == null) ? -1 : -o.name.compareTo(name);
    }

}
