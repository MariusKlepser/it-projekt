package de.hdm.team7.client.cell;

public class CustomCell {
	private final String address;
	private final String name;

	public CustomCell(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public String getName() {
		return name;
	}
}