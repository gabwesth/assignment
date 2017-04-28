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
        void saveInfoBtt(ActionEvent event) throws SQLException{ //This gives an SQL syntax error at line 93 and I've no idea how to fix it
            name1.getText();
            email1.getText();
            dob1.getText();
            teamname.getText();
            name2.getText();
            email2.getText();
            dob2.getText();
            Connection connn = DBconnection.getConnection();
            Statement state = connn.createStatement();
            String savSQL = "UPDATE `footballtable`.`players` " +
            "VALUES ('" + name1 + "', '" + dob1 + "', '" + email1 + "', NULL, '" + teamname + "'), ('" + name2 + "', '" + dob2 + "', '" + email2 + "', NULL, '" + teamname + "')";
            int resSet = state.executeUpdate(savSQL);
            System.out.println(resSet);
            connn.close();



        }

    @FXML
    void deleteTeamBtt(ActionEvent event) throws SQLException{
       //The dialogue box to be moved elsewhere out of method or preferable to keep it in?
        Object[] options = {"Yes", "No"};
           int dialogue = JOptionPane.showOptionDialog(

                    null,
                    "Do you wish to delete this team and \n"
                            + "all information associated with this team?",
                    "DELETE?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                null,options,options[1]);
            if(dialogue ==JOptionPane.YES_OPTION){
                //DELETE TEAM FROM DATABASE
            }
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
            name2.setText("Name: " + rsa2.getString("Name"));
            email2.setText("Email: " + rsa2.getString("Email"));
            dob2.setText("Date of Birth: " + rsa2.getString("DateOfBirth"));
            System.out.println("Written to labels");
        }

            conn.close();
        }
    }
