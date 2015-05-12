package de.hdm.team7.client;

// Sample DataSet for testing
public class DataSet {
	private final Integer id;
	private final String name;

	public DataSet(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getIdString() { 
		return this.id.toString();
	}
	
	public int getId(){
		return this.id;
	}

	public String getName() {
		return name;
	}
}
