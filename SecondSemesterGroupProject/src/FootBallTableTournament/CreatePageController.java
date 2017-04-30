package FootBallTableTournament;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//Page that creates a new Tournament and then Load The Table(for creating Teams and Players).
public class CreatePageController {

    Main scene = new Main();

    public static int choice; //variable to store the choosen number of team .
    public static String tour="";


    //ArrayList to store the option visualized in the ChoiceBox "TeamNumbers".
    ObservableList<Integer> optionList = FXCollections.observableArrayList(4,6,8,10);

        @FXML
        public ChoiceBox TeamNumbers;
        @FXML
        private TextField NewTournamentName;
        @FXML
        private Button BackBtt;
        @FXML
        private Button NextBtt;


    @FXML //return to the First Page.
    private void goBack(ActionEvent event) {
        scene.openWindowAndClose(event,"FirstPage.fxml","Welcome!",395,251 );
    }


    //Load Table and Create Tournament into DataBase.
    @FXML
    private void LoadTable(ActionEvent event){
        tour = NewTournamentName.getText(); //can be raplaced with tour?
        choice = (int) TeamNumbers.getValue();
        System.out.println("Chosen Value: "+tour +" _ "  + choice);

            try{
                Connection con = DBconnection.getConnection();
                Statement stmt = con.createStatement();

                String sql = "INSERT INTO `footballtable`.`tournaments` VALUES ('"+tour+"' , '"+choice+"')";
                System.out.println("SQL statement: "+sql);
                stmt.executeUpdate(sql);

                int NumbersOfGames = 0;
                if(choice == 4){ NumbersOfGames = 6;}
                if(choice == 6){ NumbersOfGames = 15;}
                if(choice == 8){NumbersOfGames = 28;}
                if(choice == 10){ NumbersOfGames = 45;}

                //creates games.
                for(int i=1; i<=NumbersOfGames; i++){
                    String GameSql = "INSERT INTO `footballtable`.`games` VALUES (NULL, NULL, NULL, NULL, NULL, NULL , NULL , '" + tour + "')";
                    System.out.println("game "+i+": "+GameSql);
                    stmt.executeUpdate(GameSql);
                }

                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        //Load the next page
        scene.openWindowAndClose(event,"Table.fxml","New Tournament",258,533);
    }


    @FXML
    private void initialize(){
        TeamNumbers.setItems(optionList);
    }

}
