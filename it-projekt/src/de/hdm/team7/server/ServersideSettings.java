package de.hdm.team7.server;

import java.util.logging.Logger;

import de.hdm.team7.shared.CommonSettings;

public class ServersideSettings extends CommonSettings {
	private static final String LOGGER_NAME = "BOMAdministration Server";
	protected static final Logger log = Logger.getLogger(LOGGER_NAME);

	public static Logger getLogger() {
		return log;
	}
}
