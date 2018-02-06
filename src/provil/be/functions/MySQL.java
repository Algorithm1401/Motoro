package provil.be.functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    /**
     * JDBC database connectie methode om de verbinding te maken met de ingegeven URL
     * @return De connectie met de mysql database.
     * @throws SQLException
     */

    public Connection getConnection(String URL) throws SQLException{

        Connection conn;
        // Mijn JDBC adres: "jdbc:mysql://pixelgalaxy.org:3306/Motoro"

        try {

            conn = DriverManager.getConnection(URL);
            return conn;

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;

    }

}
