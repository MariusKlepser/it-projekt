package de.hdm.team7.shared.geschaeftsobjekte;

import com.google.gwt.view.client.ProvidesKey;

public class Enderzeugnis extends Baugruppe {

	private static final long serialVersionUID = 1L;
	
	/**
     * The key provider that provides the unique ID of a contact.
     */
    public static final ProvidesKey<Enderzeugnis> KEY_PROVIDER = new ProvidesKey<Enderzeugnis>() {
      @Override
      public Object getKey(Enderzeugnis item) {
        return item == null ? null : item.getId();
      }
    };
	
	public String toString(){
		return this.getId() + ", " + this.getName() + " (Enderzeugnis)";
	}
	
	@Override
    public int compareTo(Enderzeugnis o) {
      return (o == null || o.name == null) ? -1 : -o.name.compareTo(name);
    }

}
