package de.hdm.team7.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hdm.team7.shared.ReportGenerator;

@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	public ReportGeneratorImpl() throws IllegalArgumentException {
		/*
		 * Eine weitergehende Funktion muss der No-Argument-Constructor nicht
		 * haben. Er muss einfach vorhanden sein.
		 */
	}
	
	@Override
	public void init() throws IllegalArgumentException {
	}
}
