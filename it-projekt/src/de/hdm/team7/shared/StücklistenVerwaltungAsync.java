package de.hdm.team7.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.team7.shared.gesch�ftsobjekte.*;

public interface St�cklistenVerwaltungAsync {

	void init(AsyncCallback<Void> callback);

	void getSt�cklistenListe(AsyncCallback<ArrayList<St�ckliste>> callback);

	void setSt�cklistenListe(ArrayList<St�ckliste> bomList,
			AsyncCallback<Void> callback) throws IllegalArgumentException;

	void erstelleBauteil(Bauteil component, AsyncCallback<String> callback);

	void erstelleBaugruppe(Baugruppe compAssembly,
			ArrayList<Bauteil> childrenComponents,
			AsyncCallback<String> callback);

	void erstelleEnderzeugnis(Enderzeugnis endproduct,
			ArrayList<Bauteil> childrenComponents,
			AsyncCallback<String> callback);

	void erstelleSt�ckliste(St�ckliste bom,
			Baugruppe rootElement, AsyncCallback<String> callback);

	void erstelleBenutzer(Benutzer user, AsyncCallback<String> callback);

	void aktualisiereBauteil(Bauteil component, AsyncCallback<String> callback);

	void aktualisiereBaugruppe(Baugruppe compAssembly,
			ArrayList<Bauteil> childrenComponents,
			AsyncCallback<String> callback);

	void aktualisiereEnderzeugnis(Enderzeugnis endproduct,
			ArrayList<Bauteil> childrenComponents,
			AsyncCallback<String> callback);

	void aktualisiereSt�ckliste(St�ckliste bom,
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

	void l�scheBauteil(Bauteil component, AsyncCallback<String> callback);

	void l�scheBaugruppe(Baugruppe compAssembly,
			AsyncCallback<String> callback);

	void l�scheEnderzeugnis(Enderzeugnis endproduct, AsyncCallback<String> callback);

	void l�scheSt�ckliste(St�ckliste bom, AsyncCallback<String> callback);

	void l�scheBenutzer(Benutzer user, AsyncCallback<String> callback);

	void holeBauteilAnhandId(int id, AsyncCallback<Bauteil> callback)
			throws IllegalArgumentException;

	void holeBaugruppeAnhandId(int id,
			AsyncCallback<Baugruppe> callback)
			throws IllegalArgumentException;

	void holeEnderzeugnisAnhandId(int id, AsyncCallback<Enderzeugnis> callback)
			throws IllegalArgumentException;

	void holeSt�cklisteAnhandId(int id, AsyncCallback<St�ckliste> callback)
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

	void holeSt�cklisteAnhandName(String name,
			AsyncCallback<ArrayList<St�ckliste>> callback)
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

	void holeAlleSt�cklisten(AsyncCallback<ArrayList<St�ckliste>> callback)
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
