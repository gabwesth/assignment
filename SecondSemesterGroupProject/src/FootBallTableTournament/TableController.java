package FootBallTableTournament;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
    String nameOfTournament = CreatePageController.nameOfTournament;

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
    private Button ShowShedulBtt;
    @FXML
    private Button FinalSave;
    @FXML
    private Button SavePlayer1;
    @FXML
    private Button SavePlayer2;

    @FXML
    private TableView<TableController> Table;

    @FXML
    private TableColumn<TableController, String> Name;
    @FXML
    private TableColumn<TableController, String> Email;
    @FXML
    private TableColumn<TableController, String> DOB;
    @FXML
    private TableColumn<TableController, String> Rank;
    @FXML
    private TableColumn<TableController, String> Team;

        @FXML
        void LoadPlayerOnTable(ActionEvent event) {
        String p1name = NameP1.getText();
        String p2name = NameP2.getText();
        String p1email = EmailP1.getText();
        String p2email = EmailP2.getText();
        String p1dob = DOBp1.getText();
        String p2dob = DOBp2.getText();
        if(limit<choice) {
            try {
                String sql = "INSERT INTO `" + nameOfTournament + "_players` VALUES ('" + p1name + "' ," + p1dob + ", '" + p1email + "', NULL)";
                String mySql = "INSERT INTO `" + nameOfTournament + "_players` VALUES ('" + p2name + "' ," + p2dob + ", '" + p2email + "', NULL)";
                //Create a connection and execute the Statement
                Connection con = DBconnection.getConnection();
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                stmt.executeUpdate(mySql);
                con.close();
                NameP1.setText("");
                NameP2.setText("");
                EmailP1.setText("");
                EmailP2.setText("");
                DOBp1.setText("");
                DOBp2.setText("");
                limit++;
            } catch (SQLException e) {
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

        @FXML
        void CommitToDB(ActionEvent event) {

        }

        @FXML
        void DeleteTableContentPlayer(ActionEvent event) {

        }

        @FXML
        void UpdateTableContentMatch(ActionEvent event) {

        }

        @FXML
        void DeleteTableContentMatch(ActionEvent event) {

        }

        @FXML
        void DispaySchedule(ActionEvent event) {

            //if 4team condition is satisfied, launch 4team schedule
            if(choice == 4) {
                scene.openWindow(event,"fourTeamSchedule.fxml","4-Team Tournament!",600,217 );
            }
            if(choice == 6) {
                scene.openWindow(event,"sixTeamSchedule.fxml","6-Team Tournament!",525,280 );
            }
            if(choice == 8) {
                scene.openWindow(event,"eightTeamSchedule.fxml","8-Team Tournament",700,340 );
            }
            if(choice == 10) {
                scene.openWindow(event,"tenTeamSchedule.fxml","10-Team Tournament",900,400 );
            }
        }
        }
