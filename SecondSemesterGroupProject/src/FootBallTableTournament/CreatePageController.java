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

public class CreatePageController {
    Main scene = new Main();
    public static int choice;
    //public static String nameOfTournament;
    public static String TName="";
    public static int TNumbers;

    ObservableList<Integer> optionList = FXCollections.observableArrayList(4,6,8,10);

        @FXML
        public ChoiceBox TeamNumbers;
        @FXML
        private TextField NewTournamentName;
        @FXML
        private Button BackBtt;
        @FXML
        private Button NextBtt;


    @FXML
    private void goBack(ActionEvent event) {
        scene.openWindowAndClose(event,"FirstPage.fxml","Welcome!",395,251 );
    }


    //load Table and Create Tournament into DataBase
    @FXML
    private void LoadTable(ActionEvent event){
        TName = NewTournamentName.getText();
        //nameOfTournament = TName;
        TNumbers = (int) TeamNumbers.getValue();
        System.out.println("Chosen Value: "+TName +" _ "  + TNumbers);
        choice = (int) TeamNumbers.getSelectionModel().getSelectedItem();

            try{
                //INSERT INTO `tournaments`(`Name`, `NumberOfTeams`) VALUES ('TName','TNumbers')
                String sql = "INSERT INTO `footballtable`.`tournaments` VALUES ('"+TName+"' , '"+TNumbers+"')";

                System.out.println("SQL statement: "+sql);
                //Create a connection and execute the Statement
                Connection con = DBconnection.getConnection();
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        //Load the next page
        scene.openWindowAndClose(event,"Table.fxml","New Tournament",737,533);
    }


    @FXML
    private void initialize(){
        TeamNumbers.setItems(optionList);
    }

}
