package de.hdm.team7.client.gui;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.hdm.team7.shared.businessObjects.BusinessObject;



/**
 * Klasse zur Darstellung von Customer-Objekten.
 * Solche Erweiterungen von <code>AbstractCell<T></code> dienen zur Erzeugung von
 * HTML-Code fÃ¼r benutzerdefinierte Objekte. In diesem Fall werden die Werte der Attribute
 * <code>lastName</code> und <code>firstName</code> eines Kundenobjekts mit einem Komma
 * und einer Leerstelle in einem <code>div-</code>Element
 * erzeugt.
 * 
 * @author rathke
 *
 */public class BusinessObjectCell extends AbstractCell<BusinessObject> {
	@Override
    public void render(Context context, BusinessObject value, SafeHtmlBuilder sb) {
      // Value can be null, so do a null check..
      if (value == null) {
        return;
      }

      sb.appendHtmlConstant("<div>");
      sb.appendEscaped(value.getName());
      sb.appendHtmlConstant("</div>");
    }
}
