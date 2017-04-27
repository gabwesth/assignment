package FootBallTableTournament;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InfoPageController {

    @FXML
    private ChoiceBox ChooseTeam;
    String tour = UsePageController.tour;


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
    void DisplayInfo(ActionEvent event) {
        teamInfoDialogue();
    }

    void teamInfoDialogue() {
        JOptionPane.showMessageDialog(null,
                "INPUT TEAM INFO HERE",
                "Team Info",
                JOptionPane.PLAIN_MESSAGE);
    }

}