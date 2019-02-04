package org.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.sql.*;

public class MyDelegate3 implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //Create connection.
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/activiti","sa","");

        //ERASE TABLE
        Statement stmt = conn.createStatement();
        String dropString = "DROP TABLE IF EXISTS CONVOCATION";
        System.out.println("** Dropped table ** ");

        stmt.execute(dropString );

        //Create Table
        String requete = "CREATE TABLE IF NOT EXISTS " + "CONVOCATION" + " ("  + "NUMETU VARCHAR(255), " +
                "NOM VARCHAR(255), PRENOM VARCHAR(255), " +
                "FILIAIRE VARCHAR(255), "
                + "DATEDEBUT DATE, DATEFIN DATE, POSTE VARCHAR(255), EMAILTUTOR VARCHAR(255), PRIMARY KEY(NUMETU)" + ");";
            //execution
        stmt.execute(requete);

        System.out.println("** Table create **");



        //add convocation in INSERT ROW.
        PreparedStatement pst = conn.prepareStatement("INSERT INTO \"CONVOCATION\" values (?,?,?,?,?,?,?,?)");
        pst.setString(1, (String) delegateExecution.getVariable("numIdEtu"));
        pst.setString(2, (String) delegateExecution.getVariable("nomEtu"));
        pst.setString(3, (String) delegateExecution.getVariable("prenom"));
        pst.setString(4, (String) delegateExecution.getVariable("filaire"));
        pst.setDate(5, (Date) delegateExecution.getVariable("dateDebut"));
        pst.setDate(6, (Date) delegateExecution.getVariable("dateFin"));
        pst.setString(7, (String) delegateExecution.getVariable("poste"));
        pst.setString(8, (String) delegateExecution.getVariable("mailTutor"));
        //UPDATE TABLE
        pst.executeUpdate();
        System.out.println("** Row inserted **");
        conn.close();
    }
}
