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
        String tableName = "CONVOCATION";


        eraseIFEXISTFonciton(stmt,tableName);
        System.out.println("** Dropped table ** " + tableName);

        createIFNOTEXISTFonciton(stmt,tableName);
        System.out.println("** Table create **");

        //add convocation in INSERT ROW.
        PreparedStatement pst = conn.prepareStatement(buildQuery(8,tableName));
        pst.setString(1, (String) delegateExecution.getVariable("numIdEtu"));
        pst.setString(2, (String) delegateExecution.getVariable("nomEtu"));
        pst.setString(3, (String) delegateExecution.getVariable("prenom"));
        pst.setString(4, (String) delegateExecution.getVariable("filaire"));
        pst.setDate(5, (Date) delegateExecution.getVariable("dateDebut"));
        pst.setDate(6, (Date) delegateExecution.getVariable("dateFin"));
        pst.setString(7, (String) delegateExecution.getVariable("poste"));
        pst.setString(8, (String) delegateExecution.getVariable("mailTutor"));
        System.out.println("** Row inserted **");
        conn.close();
    }

    public void eraseIFEXISTFonciton(Statement stmt,String TableName ){
        String dropString = "DROP TABLE IF EXISTS ";
        try {
            stmt.execute(dropString + TableName + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createIFNOTEXISTFonciton(Statement stmt, String TableName ){
        //Create Table
        String createTable = "CREATE TABLE IF NOT EXISTS ";
        String columns = " ( "  + "NUMETU VARCHAR(255) PRIMARY KEY, " +
                "NOM VARCHAR(255), PRENOM VARCHAR(255), " +
                "FILIAIRE VARCHAR(255), "
                + "DATEDEBUT DATE, DATEFIN DATE, POSTE VARCHAR(255), EMAILTUTOR VARCHAR(255)" + ");";
        try {
            stmt.execute(createTable + columns);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String buildQuery(int nbColumn, String tableName){
        StringBuilder sql = new StringBuilder("INSERT INTO \"" + tableName + "\" VALUES");
        for (int i = 0 ; i< nbColumn; i++){
            if(i == 0){
                sql.append("(?,");
            }
            else if(i == (nbColumn - 1)){
                sql.append("?)");
            }
            else{
                sql.append("?,");

            }
        }
        return sql.toString();
    }


        //PERMET D'AVOIR UNE TABLE. AVEC UNE SEUL LIGNE
}
