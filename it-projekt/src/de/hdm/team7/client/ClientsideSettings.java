package de.hdm.team7.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.team7.shared.BOMAdministrationAsync;
import de.hdm.team7.shared.ReportGenerator;
import de.hdm.team7.shared.ReportGeneratorAsync;

public class ClientsideSettings {

	  protected static BOMAdministrationAsync BOMAdministration = null;

	  protected static ReportGeneratorAsync reportGenerator = null;

	  protected static final String LOGGER_NAME = "BOMAdministration Client";

	  protected static final Logger log = Logger.getLogger(LOGGER_NAME);

	  public static Logger getLogger() {
	    return log;
	  }

	  public static BOMAdministrationAsync getBOMAdministration() {
	    // Gab es bislang noch keine BOMAdministration-Instanz, dann...
	    if (BOMAdministration == null) {
	      // Zunächst instantiieren wir BOMAdministration
	      BOMAdministration = GWT.create(de.hdm.team7.shared.BOMAdministration.class);
	    }
	    // So, nun brauchen wir die BOMAdministration nur noch zurückzugeben.
	    return BOMAdministration;
	  }

	  public static ReportGeneratorAsync getReportGenerator() {
	    // Gab es bislang noch keine ReportGenerator-Instanz, dann...
	    if (reportGenerator == null) {
	      // Zunächst instantiieren wir ReportGenerator
	    	reportGenerator = GWT.create(ReportGenerator.class);

	      final AsyncCallback<Void> initReportGeneratorCallback = new AsyncCallback<Void>() {
	        @Override
			public void onFailure(Throwable caught) {
	          ClientsideSettings.getLogger().severe(
	              "Der ReportGenerator konnte nicht initialisiert werden!");
	        }
	        @Override
			public void onSuccess(Void result) {
	          ClientsideSettings.getLogger().info(
	              "Der ReportGenerator wurde initialisiert.");
	        }
	      };
	      reportGenerator.init(initReportGeneratorCallback);
	    }
	    // So, nun brauchen wir den ReportGenerator nur noch zurückzugeben.
	    return reportGenerator;
	  }
}
