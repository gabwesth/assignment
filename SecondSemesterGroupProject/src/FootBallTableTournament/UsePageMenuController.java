package FootBallTableTournament;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsePageMenuController {
    Main scene = new Main();
    String tour = UsePageController.tour;

    @FXML
    private Button ScheduleBtt;
    @FXML
    private Button BackBtt;
    @FXML
    private Button ResultsBtt;
    @FXML
    private Button StandingBtt;

    public void goBack(ActionEvent event){
        scene.openWindowAndClose(event,"UsePage.fxml","Select Tournament", 394, 251 );
    }

    public void getMenu(ActionEvent event){
            //Load the previous page
        scene.openWindowAndClose(event,"UsePage.fxml","New Tournament", 737, 533 );
    }
    public void loadScheadule(ActionEvent event){
                int selection = 0;
                try{
                    //SELECT NumberOfTeams FROM `tournaments` WHERE Name='f'
                    String sql = "SELECT `NumberOfTeams` FROM `Tournaments` WHERE  Name = '"+UsePageController.chosenTournament+"'";
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
                    scene.openWindow(event,"fourTeamSchedule.fxml","4-Team Tournament!",600,217 );
                }
                if(selection == 6) {
                    scene.openWindow(event,"sixTeamSchedule.fxml","6-Team Tournament!",525,280 );
                }
                if(selection == 8) {
                    scene.openWindow(event,"eightTeamSchedule.fxml","8-Team Tournament",700,340 );
                }
                if(selection == 10) {
                    scene.openWindow(event,"tenTeamSchedule.fxml","10-Team Tournament",900,400 );
                }
    }
}

