package de.hdm.team7.shared.geschaeftsobjekte;

import java.io.Serializable;

public abstract class Geschaeftsobjekt implements Serializable {
	private static final long serialVersionUID = 1L;

	protected String name = "";

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Die eindeutige Identifikationsnummer einer Instanz dieser Klasse.
	 */
	protected int id = 0;

	/**
	 * Auslesen der ID.
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Setzen der ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		/*
		 * Wir geben den Klassennamen gefolgt von der ID des Objekts zurück.
		 */
		return this.getClass().getName() + " #" + this.id;
	}

	public boolean equals(Object o) {
		/*
		 * Abfragen, ob ein Objekt ungleich NULL ist und ob ein Objekt gecastet
		 * werden kann, sind immer wichtig!
		 */
		if (o != null && o instanceof Geschaeftsobjekt) {
			Geschaeftsobjekt bo = (Geschaeftsobjekt) o;
			try {
				if (bo.getId() == this.id)
					return true;
			} catch (IllegalArgumentException e) {
				/*
				 * Wenn irgendetwas schief geht, dann geben wir
				 * sicherheitshalber false zurück.
				 */
				return false;
			}
		}
		/*
		 * Wenn bislang keine Gleichheit bestimmt werden konnte, dann müssen
		 * schließlich false zurückgeben.
		 */
		return false;
	}

	@Override
	public int hashCode() {
		return this.id;
	}
}
