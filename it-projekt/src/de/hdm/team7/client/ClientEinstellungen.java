package de.hdm.team7.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.team7.shared.ReportGenerator;
import de.hdm.team7.shared.ReportGeneratorAsync;
import de.hdm.team7.shared.StuecklistenVerwaltungAsync;

public class ClientEinstellungen {

	private static StuecklistenVerwaltungAsync stuecklistenVerwaltung = null;

	private static ReportGeneratorAsync reportGenerator = null;

	private static final String LOGGER_NAME = "StücklistenVerwaltung Client";

	protected static final Logger log = Logger.getLogger(LOGGER_NAME);

	public static Logger getLogger() {
		return log;
	}

	public static StuecklistenVerwaltungAsync getStuecklistenVerwaltung() {
		// Gab es bislang noch keine BOMAdministration-Instanz, dann...
		if (stuecklistenVerwaltung == null) {
			// Zunächst instantiieren wir BOMAdministration
			stuecklistenVerwaltung = GWT
					.create(de.hdm.team7.shared.StuecklistenVerwaltung.class);
			
			final AsyncCallback<Void> initBOMCallback = new AsyncCallback<Void>() {
				@Override
				public void onFailure(Throwable caught) {
					ClientEinstellungen
							.getLogger()
							.severe("Die BOMAdministration konnte nicht initialisiert werden!");
				}

				@Override
				public void onSuccess(Void result) {
					ClientEinstellungen.getLogger().info(
							"Die BOMAdministration wurde initialisiert.");
				}
			};
			stuecklistenVerwaltung.init(initBOMCallback);
		}
		// So, nun brauchen wir die BOMAdministration nur noch zurückzugeben.
		return stuecklistenVerwaltung;
	}

	public static ReportGeneratorAsync getReportGenerator() {
		// Gab es bislang noch keine ReportGenerator-Instanz, dann...
		if (reportGenerator == null) {
			// Zunächst instantiieren wir ReportGenerator
			reportGenerator = GWT.create(ReportGenerator.class);

			final AsyncCallback<Void> initReportGeneratorCallback = new AsyncCallback<Void>() {
				@Override
				public void onFailure(Throwable caught) {
					ClientEinstellungen
							.getLogger()
							.severe("Der ReportGenerator konnte nicht initialisiert werden!");
				}

				@Override
				public void onSuccess(Void result) {
					ClientEinstellungen.getLogger().info(
							"Der ReportGenerator wurde initialisiert.");
				}
			};
			reportGenerator.init(initReportGeneratorCallback);
		}
		// So, nun brauchen wir den ReportGenerator nur noch zurückzugeben.
		return reportGenerator;
	}
}
