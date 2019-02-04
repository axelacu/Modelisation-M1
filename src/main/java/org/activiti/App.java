package org.activiti;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {

        //Create connection.
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/activiti","sa","");

        //ERASE TABLE
        Statement stmt = conn.createStatement();
        String tableName = "CONVOCATION";
        //Create Table
        String requete = "CREATE TABLE IF NOT EXISTS " + " ( "  + "NUMETU VARCHAR(255) PRIMARY KEY, " +
                "NOM VARCHAR(255), PRENOM VARCHAR(255), " +
                "FILIAIRE VARCHAR(255), "
                + "DATEDEBUT DATE, DATEFIN DATE, POSTE VARCHAR(255), EMAILTUTOR VARCHAR(255)" + ");";
        try {
            stmt.execute(requete);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
