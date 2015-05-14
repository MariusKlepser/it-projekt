package de.hdm.team7.database;

import java.sql.Connection;
import java.sql.DriverManager;

import com.google.appengine.api.utils.SystemProperty;

public class DBConnection {

    private static Connection connection = null;

    /**
     * Die URL, mit deren Hilfe die Datenbank angesprochen wird. In einer
     * professionellen Applikation wÃ¼rde diese Zeichenkette aus einer
     * Konfigurationsdatei eingelesen oder Ã¼ber einen Parameter von auÃŸen
     * mitgegeben, um bei einer VerÃ¤nderung dieser URL nicht die gesamte
     * Software neu komilieren zu mÃ¼ssen.
     */
//  private static String googleUrl = "jdbc:google:mysql://prof-thies.de:thies-bankproject:thies-bankproject/bankproject?user=demo&password=demo";
    private static String testUrl = "jdbc:mysql://213.165.82.134:3306/whatsgoes";

    /**
     * Diese statische Methode kann aufgrufen werden durch
     * <code>DBConnection.connection()</code>. Sie stellt die
     * Singleton-Eigenschaft sicher, indem Sie dafÃ¼r sorgt, dass nur eine
     * einzige Instanz von <code>DBConnection</code> existiert.
     * <p>
     * 
     * <b>Fazit:</b> DBConnection sollte nicht mittels <code>new</code>
     * instantiiert werden, sondern stets durch Aufruf dieser statischen
     * Methode.
     * <p>
     * 
     * <b>Nachteil:</b> Bei Zusammenbruch der Verbindung zur Datenbank - dies
     * kann z.B. durch ein unbeabsichtigtes Herunterfahren der Datenbank
     * ausgelÃ¶st werden - wird keine neue Verbindung aufgebaut, so dass die in
     * einem solchen Fall die gesamte Software neu zu starten ist. In einer
     * robusten LÃ¶sung wÃ¼rde man hier die Klasse dahingehend modifizieren, dass
     * bei einer nicht mehr funktionsfÃ¤higen Verbindung stets versucht wÃ¼rde,
     * eine neue Verbindung aufzubauen. Dies wÃ¼rde allerdings ebenfalls den
     * Rahmen dieses Projekts sprengen.
     * 
     * @return DAS <code>DBConncetion</code>-Objekt.
     * @see con
     */
    public static Connection connection() {
        // Wenn es bisher keine Conncetion zur DB gab, ...
        if (connection == null) {
            String url = null;
            String user = "ps091";
            String password = "getshitdone";
            
            try {
                if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
                    // Load the class that provides the new
                    // "jdbc:google:mysql://" prefix.
//                    Class.forName("com.mysql.jdbc.GoogleDriver");
//                    url = googleUrl;
                } else {
                    // Local MySQL instance to use during development.
                    Class.forName("com.mysql.jdbc.Driver");
                    url = testUrl;
                    
                }
                /*
                 * Dann erst kann uns der DriverManager eine Verbindung mit den
                 * oben in der Variable url angegebenen Verbindungsinformationen
                 * aufbauen.
                 * 
                 * Diese Verbindung wird dann in der statischen Variable con
                 * abgespeichert und fortan verwendet.
                 */
                connection = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                connection = null;
                e.printStackTrace();
            }
        }

        // ZurÃ¼ckgegeben der Verbindung
        return connection;
    }
    
    public static void closeConnection() {
    	
    }
	
}