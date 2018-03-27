package provil.be.functions;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MySQL {


    /**
     * JDBC database connectie methode om de verbinding te maken met de ingegeven URL
     * @return De connectie met de mysql database.
     * @throws SQLException
     */

    public static Connection getConnection() throws SQLException {

            Connection con = null;
            try {
                //registering the jdbc driver here, your string to use
                //here depends on what driver you are using.
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://pixelgalaxy.org:7000/user_data?" +
                                                        "user=algorithm1401&password=SAFEabc123");
                if(con != null){
                    System.out.println("Connection made!");
                }

                return con;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }catch (ClassNotFoundException ex){
                ex.printStackTrace();
            }
        return null;
    }
    /**
     *
     * @param key naam van de sleutel waarvan je data wilt terugkrijgen
     * @return een lijst met ongekende objecten ( wildcards )
     */

    public static List<Object> getKeyValues(int key){
        List<Object> keyValues = new ArrayList<>();

        try
        {

            Connection con = getConnection();

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from accounts");
            while(rs.next())
                keyValues.add(rs.getObject(key));

            con.close();
        }

        catch(Exception e)
        {
            System.out.println(e);
        }

        return keyValues;

    }

    public static void addUser(List<String> data){

        try {

            Connection con = getConnection();

            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

                String query = "UPDATE accounts SET email = ?, password = ?, created = ? WHERE codes = ?";

                PreparedStatement preparedStmt = con.prepareStatement(query);

                preparedStmt.setString(1, data.get(1));
                preparedStmt.setString(2, data.get(2));
                preparedStmt.setDate(3, startDate);
                preparedStmt.setString(4, data.get(0));

                // execute the preparedstatement
                preparedStmt.execute();
                preparedStmt.close();


            con.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
