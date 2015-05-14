package de.hdm.team7.client;

// Sample DataSet for testing
public class DataSet {
	private final Integer id;
	private final String name;
	private final String type;

	public DataSet(Integer id, String name, String type) {
		this.id = id;
		this.name = name;
		this.type = type;
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
	
	public String getType() {
		return type;
	}
}
