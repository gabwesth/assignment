package FootBallTableTournament;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsePageMenuController {
    Main scene = new Main();
    String tour = UsePageController.tour;
    TableController tc = new TableController();

    @FXML
    private Button ScheduleBtt; //Ability to add results
    @FXML
    private Button BackBtt;
    @FXML
    private Button infoBtt; //Shows a list of teams that can be clicked on to display team info
    @FXML
    private Button StandingBtt; //Dialogue box/popup that shows a table of all teams and their GD, pts, and position
    @FXML
    public Button addTeam;

    public void goBack(ActionEvent event){
        scene.openWindowAndClose(event,"UsePage.fxml","Select Tournament", 394, 251 );
    }
    @FXML
    public void addNewTeam(ActionEvent event){
        // add a new team
        //tc.SaveOnDB();
    }

    @FXML
    public void loadInfo(ActionEvent event) {
        scene.openWindowAndClose(event, "InfoPage.fxml", "Team Info", 500, 350);
    }

    public void getMenu(ActionEvent event){
            //Load the previous page
        scene.openWindowAndClose(event,"UsePage.fxml","New Tournament", 737, 533 );
    }
    public void standings(ActionEvent event){
        //Load the previous page
        scene.openWindowAndClose(event,"Standings.fxml","New Tournament", 737, 533 );
    }

    public void loadScheadule(ActionEvent event){
                int selection = 0;
                try{
                    //SELECT NumberOfTeams FROM `tournaments` WHERE Name='f'
                    String sql = "SELECT `NumberOfTeams` FROM `footballtable`.`tournaments` WHERE  Name = '"+UsePageController.chosenTournament+"'";
                    System.out.println(sql);
                    //Create a connection and execute the Statement
                    Connection con = DBconnection.getConnection();
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next()){
                        selection = rs.getInt("NumberOfTeams");
                    }
                    System.out.println(selection);
                    rs.close();
                    con.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                if(selection== 4) {
                    scene.openWindow(event,"fourTeamSchedule.fxml","4-Team Tournament!",600,322 );
                }
                if(selection == 6) {
                    scene.openWindow(event,"sixTeamSchedule.fxml","6-Team Tournament!",525,322 );
                }
                if(selection == 8) {
                    scene.openWindow(event,"eightTeamSchedule.fxml","8-Team Tournament",700,322 );
                }
                if(selection == 10) {
                    scene.openWindow(event,"tenTeamSchedule.fxml","10-Team Tournament",900,322 );
                }
    }
}

