package de.hdm.team7.shared.geschaeftsobjekte;

import java.util.ArrayList;

import com.google.gwt.view.client.ProvidesKey;

public class Enderzeugnis extends Baugruppe {

	private static final long serialVersionUID = 1L;
	
	protected ArrayList<ZuordnungEEBG> kinderKomponentenEEBG;
	protected ArrayList<ZuordnungEEBT> kinderKomponentenEEBT;
	
	
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
	public ArrayList<ZuordnungEEBG> getKinderKomponentenEEBG() {
		return kinderKomponentenEEBG;
	}

	public ArrayList<ZuordnungEEBT> getKinderKomponentenEEBT() {
		return kinderKomponentenEEBT;
	}

	
	/**
	 * @param kinderKomponenten the childrenComponents to set
	 */
	public void setKinderKomponentenEEBG(ArrayList<ZuordnungEEBG> kinderKomponentenEEBG) {
		this.kinderKomponentenEEBG = kinderKomponentenEEBG;
	}
	
	public void setKinderKomponentenEEBT(ArrayList<ZuordnungEEBT> kinderKomponentenEEBT) {
		this.kinderKomponentenEEBT = kinderKomponentenEEBT;
	}


    
    
    
	public String toString(){
		return this.getId() + ", " + this.getName() + " (Enderzeugnis)";
	}
	
	@Override
    public int compareTo(Enderzeugnis o) {
      return (o == null || o.name == null) ? -1 : -o.name.compareTo(name);
    }

}
