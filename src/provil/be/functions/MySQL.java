package provil.be.functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQL {

    public Connection getConnection() throws SQLException{

        Connection conn;
        String URL = "jdbc:mysql://pixelgalaxy.org:3306/Motoro";

        try {

            conn = DriverManager.getConnection(URL);
            return conn;

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;

    }

}
