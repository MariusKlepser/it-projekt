package de.hdm.team7.database;

public class ComponentAssemblyMapper {

	private static ComponentAssemblyMapper componentAssemblyMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected ComponentAssemblyMapper() {
	}

	public static ComponentAssemblyMapper componentAssemblyMapper() {
		if (componentAssemblyMapper == null) {
			componentAssemblyMapper = new ComponentAssemblyMapper();
		}

		return componentAssemblyMapper;
	}
}
