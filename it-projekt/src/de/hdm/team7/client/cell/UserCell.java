package de.hdm.team7.client.cell;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.hdm.team7.shared.geschaeftsobjekte.*;



/**
 * Klasse zur Darstellung von Customer-Objekten.
 * Solche Erweiterungen von <code>AbstractCell<T></code> dienen zur Erzeugung von
 * HTML-Code fÃ¼r benutzerdefinierte Objekte. In diesem Fall werden die Werte der Attribute
 * <code>lastName</code> und <code>firstName</code> eines Kundenobjekts mit einem Komma
 * und einer Leerstelle in einem <code>div-</code>Element
 * erzeugt.
 * 
 * In Anlehnung an: @author rathke
 *
 */

public class UserCell extends AbstractCell<Benutzer> {
	@Override
    public void render(Context context, Benutzer value, SafeHtmlBuilder sb) {
      // Value can be null, so do a null check..
      if (value == null) {
        return;
      }

      sb.appendHtmlConstant("<div>");
      sb.appendEscaped(value.getName());
      sb.appendHtmlConstant("</div>");
    }
}
