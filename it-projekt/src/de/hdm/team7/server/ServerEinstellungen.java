package de.hdm.team7.server;

import java.util.logging.Logger;

import de.hdm.team7.shared.AllgemeineEinstellungen;

public class ServerEinstellungen extends AllgemeineEinstellungen {
	private static final String LOGGER_NAME = "Stuecklistenverwaltung Server";
	protected static final Logger log = Logger.getLogger(LOGGER_NAME);

	public static Logger getLogger() {
		return log;
	}
}
