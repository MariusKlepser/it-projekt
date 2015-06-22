package de.hdm.team7.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.team7.shared.geschäftsobjekte.*;

public interface StücklistenVerwaltungAsync {

	void init(AsyncCallback<Void> callback);

	void getStücklistenListe(AsyncCallback<ArrayList<Stückliste>> callback);

	void setStücklistenListe(ArrayList<Stückliste> bomList,
			AsyncCallback<Void> callback) throws IllegalArgumentException;

	void erstelleBauteil(Bauteil component, AsyncCallback<String> callback);

	void erstelleBaugruppe(Baugruppe compAssembly,
			ArrayList<Bauteil> childrenComponents,
			AsyncCallback<String> callback);

	void erstelleEnderzeugnis(Enderzeugnis endproduct,
			ArrayList<Bauteil> childrenComponents,
			AsyncCallback<String> callback);

	void erstelleStückliste(Stückliste bom,
			Baugruppe rootElement, AsyncCallback<String> callback);

	void erstelleBenutzer(Benutzer user, AsyncCallback<String> callback);

	void aktualisiereBauteil(Bauteil component, AsyncCallback<String> callback);

	void aktualisiereBaugruppe(Baugruppe compAssembly,
			ArrayList<Bauteil> childrenComponents,
			AsyncCallback<String> callback);

	void aktualisiereEnderzeugnis(Enderzeugnis endproduct,
			ArrayList<Bauteil> childrenComponents,
			AsyncCallback<String> callback);

	void aktualisiereStückliste(Stückliste bom,
			Baugruppe rootElement, AsyncCallback<String> callback);

	void aktualisiereBenutzer(Benutzer user, AsyncCallback<String> callback);

	void ordneBauteileZuBaugruppe(ArrayList<Bauteil> components,
			ArrayList<Bauteil> compAssemblies, AsyncCallback<String> callback);

	void ordneBauteileZuEnderzeugnis(ArrayList<Bauteil> components,
			ArrayList<Bauteil> endProducts, AsyncCallback<String> callback);

	void ordneBaugruppenZuBaugruppe(
			ArrayList<Bauteil> selectedCompAssemblies,
			ArrayList<Bauteil> assignableCompAssemblies,
			AsyncCallback<String> callback);

	void ordneBaugruppenZuEnderzeugnis(
			ArrayList<Bauteil> compAssemblies,
			ArrayList<Bauteil> endProducts, AsyncCallback<String> callback);

	void ordneEnderzeugnisseZuBaugruppe(ArrayList<Bauteil> endProducts,
			ArrayList<Bauteil> compAssemblies, AsyncCallback<String> callback);

	void ordneEnderzeugnisseZuEnderzeugnis(ArrayList<Bauteil> selectedEndProducts,
			ArrayList<Bauteil> assignableEndProducts,
			AsyncCallback<String> callback);

	void löscheBauteil(Bauteil component, AsyncCallback<String> callback);

	void löscheBaugruppe(Baugruppe compAssembly,
			AsyncCallback<String> callback);

	void löscheEnderzeugnis(Enderzeugnis endproduct, AsyncCallback<String> callback);

	void löscheStückliste(Stückliste bom, AsyncCallback<String> callback);

	void löscheBenutzer(Benutzer user, AsyncCallback<String> callback);

	void holeBauteilAnhandId(int id, AsyncCallback<Bauteil> callback)
			throws IllegalArgumentException;

	void holeBaugruppeAnhandId(int id,
			AsyncCallback<Baugruppe> callback)
			throws IllegalArgumentException;

	void holeEnderzeugnisAnhandId(int id, AsyncCallback<Enderzeugnis> callback)
			throws IllegalArgumentException;

	void holeStücklisteAnhandId(int id, AsyncCallback<Stückliste> callback)
			throws IllegalArgumentException;

	void holeBenutzerAnhandId(int id, AsyncCallback<Benutzer> callback)
			throws IllegalArgumentException;

	void holeBauteilAnhandName(String name,
			AsyncCallback<ArrayList<Bauteil>> callback)
			throws IllegalArgumentException;

	void holeBaugruppeAnhandName(String name,
			AsyncCallback<ArrayList<Baugruppe>> callback)
			throws IllegalArgumentException;

	void holeEnderzeugnisAnhandName(String name,
			AsyncCallback<ArrayList<Enderzeugnis>> callback)
			throws IllegalArgumentException;

	void holeStücklisteAnhandName(String name,
			AsyncCallback<ArrayList<Stückliste>> callback)
			throws IllegalArgumentException;

	void holeBenutzerAnhandName(String name, AsyncCallback<ArrayList<Benutzer>> callback)
			throws IllegalArgumentException;

	void holeAlleBauteile(AsyncCallback<ArrayList<Bauteil>> callback)
			throws IllegalArgumentException;

	void holeAlleBaugruppen(
			AsyncCallback<ArrayList<Baugruppe>> callback)
			throws IllegalArgumentException;

	void holeAlleEnderzeugnisse(AsyncCallback<ArrayList<Enderzeugnis>> callback)
			throws IllegalArgumentException;

	void holeAlleStücklisten(AsyncCallback<ArrayList<Stückliste>> callback)
			throws IllegalArgumentException;

	void holeAlleBenutzer(AsyncCallback<ArrayList<Benutzer>> callback)
			throws IllegalArgumentException;

	void holeKinderKomponentenVon(Baugruppe compAss,
			AsyncCallback<ArrayList<Bauteil>> callback)
			throws IllegalArgumentException;

	void holeKinderKomponentenVon(Enderzeugnis endproduct,
			AsyncCallback<ArrayList<Bauteil>> callback)
			throws IllegalArgumentException;
}
