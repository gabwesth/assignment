package FootBallTableTournament;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import jdk.nashorn.internal.scripts.JO;

import javax.lang.model.element.Name;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class InfoPageController {
    Main scene = new Main();
    @FXML
    private ChoiceBox ChooseTeam;
    String tour = UsePageController.tour;

    public String userID; //Used to pull info about first team member in displayInfo
    public int finalID; //Used to pull info about second team member in displayInfo
    @FXML
    private Button backBtt;
    @FXML
    private Button displayInfo;
    @FXML
    private Button saveInfo;
    @FXML
    private Button editInfo;

    public TextField name1;
    public TextField email1;
    public TextField dob1;
    public TextField teamname;
    public TextField name2;
    public TextField email2;
    public TextField dob2;


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
        editInfo.setOpacity(0);
        saveInfo.setOpacity(0);

    }
    @FXML
    void editInfoBtt(ActionEvent event) throws SQLException{
        saveInfo.setOpacity(1);
        editInfo.setOpacity(0);

        name1.setEditable(true);
        email1.setEditable(true);
        dob1.setEditable(true);
        teamname.setEditable(true);
        name2.setEditable(true);
        email2.setEditable(true);
        dob2.setEditable(true);

    }
    @FXML
    void displayInfoBtt(ActionEvent event) throws SQLException {
        teamInfoDialogue();
        editInfo.setOpacity(1);

        }
        @FXML
        void saveInfoBtt(ActionEvent event) throws SQLException{ //This gives an SQL syntax error at line 100 and I've no idea how to fix it

           editInfo.setOpacity(1);
           String namea = name1.getText();
            String emaila = email1.getText();
            String doba = dob1.getText();
            String tm = teamname.getText();
            String nameb = name2.getText();
            String emailb = email2.getText();
            String dobb = dob2.getText();
            int resSet;
            int resSet1;
            Connection connn = DBconnection.getConnection();
            String savSQL = "UPDATE `footballtable`.`players` SET `Name` = '"+ namea +"',`DateOfBirth` = '"+ doba +"',`Email` = '"+ emaila +"',`TeamID` = '"+ tm + "'WHERE `ID` = "+userID;
            String savSQL1 = "UPDATE `footballtable`.`players` SET `Name` = '"+ nameb +"',`DateOfBirth` = '"+ dobb +"',`Email` = '"+ emailb +"',`TeamID` = '"+ tm + "'WHERE `ID` = "+finalID;
            PreparedStatement st = connn.prepareStatement(savSQL);
            PreparedStatement sta = connn.prepareStatement(savSQL1);
            resSet = st.executeUpdate(savSQL);
            resSet1 = sta.executeUpdate(savSQL1);
            System.out.println(resSet);
            System.out.println(resSet1);
            connn.close();
            saveInfo.setOpacity(0);
            JOptionPane.showMessageDialog(null,
                    "Update saved successfully to database",
                    "Saved Successfully",
                    JOptionPane.INFORMATION_MESSAGE);
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
            name1.setText(rsa.getString("Name"));
            email1.setText(rsa.getString("Email"));
            dob1.setText(rsa.getString("DateOfBirth"));
            teamname.setText(rsa.getString("TeamID"));
            userID = rsa.getString("ID");
            System.out.println(userID);
        }
        finalID = (Integer.parseInt(userID) +1);
        Statement sta2 = conn.createStatement();
        String query2 = ("SELECT `Name`,`DateOfBirth`,`Email`,`TeamID`,`ID` FROM `footballtable`.`players` WHERE `ID` = '" + finalID + "' AND `TeamID` = '" + chosenTeam + "'");
        ResultSet rsa2 = sta2.executeQuery(query2);
        if(rsa2.next()) {
            name2.setText(rsa2.getString("Name"));
            email2.setText(rsa2.getString("Email"));
            dob2.setText(rsa2.getString("DateOfBirth"));
            System.out.println("Written to labels");
        }

            conn.close();
        }
    }
