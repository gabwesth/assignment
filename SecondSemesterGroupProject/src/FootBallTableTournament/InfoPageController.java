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
    public String printShit;
    ArrayList<String> ar = new ArrayList<String>();
    public String text;
    @FXML
    private Button backBtt;
    @FXML
    public Label name1;
    public Label email1;



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
    void editInfoBtt(ActionEvent event) throws SQLException{
        teamInfoDialogue();
    }

    @FXML
    void goBack(ActionEvent event){
        scene.openWindowAndClose(event, "UsePageMenu.fxml", "Welcome", 395, 251);
    }


    void teamInfoDialogue() throws SQLException {

        String chosenTeam = (String) ChooseTeam.getValue();
        Connection conn = DBconnection.getConnection();
        Statement sta = conn.createStatement();
        String query = ("SELECT `Name`,`DateOfBirth`,`Email`,`TeamID` FROM `footballtable`.`players` WHERE `TeamID` = '" + chosenTeam + "' ");
        ResultSet rsa = sta.executeQuery(query);

        name1.setText(rsa.getString("Name"));
        email1.setText(rsa.getString("Email"));

        //ResultSetMetaData rsmda = rsa.getMetaData();

        //int columnsNumber = rsmda.getColumnCount();
       /* while (rsa.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                printShit =rsa.getString(i) + " ";
                ar.add(printShit);
                StringBuilder builder = new StringBuilder();
                for (String value : ar) {
                    builder.append(value);
                }
                text = builder.toString();
            }
            System.out.println();//Move to the next line to print the next row.
        }
        JOptionPane.showMessageDialog(null,
                text,
                "Team Info",
                JOptionPane.PLAIN_MESSAGE);*/

        conn.close();

    }


}