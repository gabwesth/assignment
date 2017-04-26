package FootBallTableTournament;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TableController {
    Main scene = new Main();

    int choice = CreatePageController.choice;
    int limit=0;
    boolean duplicatePrevention = true;
    String nameOfTournament = CreatePageController.TName;
    public String p1dob;
    public String p2dob;

    @FXML
    private TextField EmailP2;
    @FXML
    private TextField EmailP1;
    @FXML
    private TextField NameP2;
    @FXML
    private TextField NameP1;
    @FXML
    private TextField DOBp2;
    @FXML
    private TextField DOBp1;
    @FXML
    private TextField TeamName;
    @FXML
    private Button BackBtt;
    @FXML
    private Button SaveBtt;



    void ErrorMessageDOB() //The dialogue box method for DOB error
    {
        JOptionPane.showMessageDialog(null,
                "You have entered the wrong data type \n"
                        + "into the Date of birth field \n"
                        + "Please enter an integer(YYYY-MM-DD)",
                "NOW YOU FUCKED UP",
                JOptionPane.ERROR_MESSAGE);
    }
    void ErrorMessageTeam() //The dialogue box method for DOB error
    {
        JOptionPane.showMessageDialog(null,
                "You have entered a duplicate team name \n"
                        + "into team field. \n"
                        + "Please enter a new team name",
                "NOW YOU FUCKED UP",
                JOptionPane.ERROR_MESSAGE);
    }




    void parseShit() //Checks entered DOB values are integers
    {
        Double.parseDouble(p1dob);
        Double.parseDouble(p2dob);
    }

    @FXML
    void goBack(ActionEvent event) {
        scene.openWindowAndClose(event,"FirstPage.fxml","Welcome!",395, 251 );
    }


    @FXML
    void SaveOnDB(ActionEvent event) {
        String p1name = NameP1.getText();
        String p2name = NameP2.getText();
        String p1email = EmailP1.getText();
        String p2email = EmailP2.getText();
        p1dob= DOBp1.getText();
        p2dob= DOBp2.getText();
        String NameOfTeam = TeamName.getText();

        try{
            parseShit();
            duplicatePrevention = true;
        }
        catch (NumberFormatException nfe){
            ErrorMessageDOB();
            duplicatePrevention = false;

        }

        if(limit<choice && duplicatePrevention == true) {
            try {

                //Create a connection and execute the Statement
                Connection con = DBconnection.getConnection();
                Statement stmt = con.createStatement();

                //INSERT INTO `teams` VALUES ('TeamName', NULL, NULL, 'TName')
                String TeamSql = "INSERT INTO `footballtable`.`teams` VALUES ('" + NameOfTeam + "', NULL, NULL, '" + nameOfTournament + "',NULL)";
                System.out.println(TeamSql);
                stmt.executeUpdate(TeamSql);
                //INSERT INTO `players` VALUES ('Bob', '2017-04-04', 'bob@email.com', NULL, 'Eagles'), ('Ida', '2017-04-26', 'ida@gmail.com', NULL, 'Eagles');
                String PlSql = "INSERT INTO `footballtable`.`players` VALUES ('" + p1name + "', '" + p1dob + "', '" + p1email + "', NULL, '" + NameOfTeam + "'), ('" + p2name + "', '" + p2dob + "', '" + p2email + "', NULL, '" + NameOfTeam + "')";
                System.out.println(PlSql);
                stmt.executeUpdate(PlSql);


                con.close();
                NameP1.setText("");
                NameP2.setText("");
                EmailP1.setText("");
                EmailP2.setText("");
                DOBp1.setText("");
                DOBp2.setText("");
                TeamName.setText("");
                limit++;
            }catch (MySQLIntegrityConstraintViolationException e)
                {
                    ErrorMessageTeam();
                    duplicatePrevention = false;
                }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(choice==limit){
            NameP1.setText("No more Players");
            NameP2.setText("No more Players");
            EmailP1.setText("No more Players");
            EmailP2.setText("No more Players");
            DOBp1.setText("No more Players");
            DOBp2.setText("No more Players");}

    }

}
