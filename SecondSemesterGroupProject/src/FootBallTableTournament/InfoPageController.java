package FootBallTableTournament;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class InfoPageController {

    @FXML
    private ChoiceBox ChooseTeam;
    String tour = UsePageController.tour;
    public String printShit;
    ArrayList<String> ar = new ArrayList<String>();
    public String printShit2;
    public String printShit3;


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

    @FXML
    void initialize() throws SQLException {
        ChooseTeam.setItems(GetTeamName());
        tour = (String) ChooseTeam.getSelectionModel().getSelectedItem();
    }

    @FXML
    void DisplayInfo(ActionEvent event) throws SQLException {
        teamInfoDialogue();
    }

    void teamInfoDialogue() throws SQLException {

        String chosenTeam = (String) ChooseTeam.getValue();
        Connection conn = DBconnection.getConnection();
        Statement sta = conn.createStatement();
        String query = ("SELECT `Name`,`DateOfBirth`,`Email`,`TeamID` FROM `footballtable`.`players` WHERE `TeamID` = '" + chosenTeam + "' ");
        ResultSet rsa = sta.executeQuery(query);
        ResultSetMetaData rsmda = rsa.getMetaData();

        int columnsNumber = rsmda.getColumnCount();

// Iterate through the data in the result set and display it.
        while (rsa.next()) {
//Print one row
            for (int i = 1; i <= columnsNumber; i++) {
                printShit =rsa.getString(i) + " ";
                ar.add(printShit);

               // System.out.print(rsa.getString(i) + " "); //Print one element of a row
            }

            System.out.println();//Move to the next line to print the next row.
        }
        //}

        JOptionPane.showMessageDialog(null,
                ar,
                "Team Info",
                JOptionPane.PLAIN_MESSAGE);
        conn.close();

    }


}