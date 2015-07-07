package de.hdm.team7.shared.geschaeftsobjekte;

import java.util.ArrayList;

import com.google.gwt.view.client.ProvidesKey;

//import de.hdm.team7.server.ZuordnungBGBG;
//import de.hdm.team7.server.ZuordnungBGBT;

public class Baugruppe extends Bauteil {

	private static final long serialVersionUID = 1L;
	
	protected ArrayList<ZuordnungBGBG> kinderKomponentenBGBG;
	protected ArrayList<ZuordnungBGBT> kinderKomponentenBGBT;
	
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
    	public ArrayList<ZuordnungBGBG> getKinderKomponentenBGBG() {
    		return kinderKomponentenBGBG;
    }

    	public ArrayList<ZuordnungBGBT> getKinderKomponentenBGBT() {
    		return kinderKomponentenBGBT;
    }

	
	/**
	 * @param kinderKomponenten the childrenComponents to set
	 */
    public void setKinderKomponentenBG(ArrayList<ZuordnungBGBG> kinderKomponentenBGBG) {
    	this.kinderKomponentenBGBG = kinderKomponentenBGBG;
    }
	
    public void setKinderKomponentenBT(ArrayList<ZuordnungBGBT> kinderKomponentenBGBT) {
    	this.kinderKomponentenBGBT = kinderKomponentenBGBT;
    }


	public String toStringList(){
		return this.getId() + ", " + this.getName() + " (Baugruppe)";
		
	}

	@Override
    public int compareTo(Baugruppe o) {
      return (o == null || o.name == null) ? -1 : -o.name.compareTo(name);
    }
}
