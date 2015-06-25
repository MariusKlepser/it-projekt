package de.hdm.team7.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.team7.shared.geschaeftsobjekte.*;
//import de.hdm.team7.shared.report.Methodehiereintragen;

public interface ReportGeneratorAsync {
	
	void init(AsyncCallback<Void> callback);
//
//	void createAllAccountsOfAllCustomersReport(
//		      AsyncCallback<Methodehiereintragen> callback);
//
//		  void createAllAccountsOfCustomerReport(Customer c,
//		      AsyncCallback<Methodehiereintragen> callback)
//
//		  void setBank(Bank b, AsyncCallback<Void> callback);
}
