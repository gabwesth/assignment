package FootBallTableTournament;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import javax.lang.model.element.Name;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class InfoPageController {
    Main scene = new Main();
    @FXML
    private ChoiceBox ChooseTeam;
    String tour = UsePageController.tour;


    public String text;
    @FXML
    private Button backBtt;
@FXML
private Button display;

    public Label name1;
    public Label email1;
    public Label dob1;
    public Label teamname1;
    public Label name2;
    public Label email2;
    public Label dob2;
    public Label teamname2;


    @FXML
    ObservableList GetTeamName() throws SQLException {

        ObservableList<String> optionList = FXCollections.observableArrayList();

        Connection con = DBconnection.getConnection();
        Statement st = con.createStatement();
        String sql = ("SELECT `Name` FROM `footballtable`.`teams` WHERE `Tournament` = '" + UsePageController.chosenTournament + "' ");
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            optionList.add(rs.getString("Name"));
        }
        con.close();
        return optionList;
    }



    public String userID;
    public int finalID;


    @FXML
    void initialize() throws SQLException {
        ChooseTeam.setItems(GetTeamName());
        tour = (String) ChooseTeam.getSelectionModel().getSelectedItem();

    }


    @FXML
    void displayInfoBtt(ActionEvent event) throws SQLException {
        teamInfoDialogue();
        switch (display.getText()) {
            case "Edit":

                break;
             ///////////////////////////////
            case "Display Info":

                break;
        }
    }
    @FXML
    void deleteTeamBtt(ActionEvent event) throws SQLException{

    }
    @FXML
    void goBack(ActionEvent event) {
        scene.openWindowAndClose(event, "UsePageMenu.fxml", "Welcome", 395, 251);
    }

    void teamInfoDialogue() throws SQLException {

        String chosenTeam = (String) ChooseTeam.getValue();
        Connection conn = DBconnection.getConnection();
        Statement sta = conn.createStatement();

        String query = ("SELECT `Name`,`DateOfBirth`,`Email`,`TeamID`,`ID` FROM `footballtable`.`players` WHERE `TeamID` = '" + chosenTeam + "'");
        ResultSet rsa = sta.executeQuery(query);
        if (rsa.next()) {
            name1.setText("Name: " + rsa.getString("Name"));
            email1.setText("Email: " + rsa.getString("Email"));
            dob1.setText("Date of Birth: " + rsa.getString("DateOfBirth"));
            teamname1.setText("TeamName: " + rsa.getString("TeamID"));
            userID = rsa.getString("ID");
            System.out.println(userID);
        }
        finalID = (Integer.parseInt(userID) +1);
        Statement sta2 = conn.createStatement();
        String query2 = ("SELECT `Name`,`DateOfBirth`,`Email`,`TeamID`,`ID` FROM `footballtable`.`players` WHERE `ID` = '" + finalID + "' AND `TeamID` = '" + chosenTeam + "'");
        ResultSet rsa2 = sta2.executeQuery(query2);
        if(rsa2.next()) {
            name2.setText("Name: " + rsa2.getString("Name"));
            email2.setText("Email: " + rsa2.getString("Email"));
            dob2.setText("Date of Birth: " + rsa2.getString("DateOfBirth"));
            teamname2.setText("TeamName: " + rsa2.getString("TeamID"));
            System.out.println("Written to labels");
        }

            conn.close();
        }
    }
