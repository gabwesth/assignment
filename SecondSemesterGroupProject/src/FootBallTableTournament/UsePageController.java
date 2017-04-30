package FootBallTableTournament;

import com.sun.javafx.binding.StringFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsePageController {

        Main scene = new Main();
        public static String tour;
       // public static String chosenTournament;


        @FXML
        private ChoiceBox TournamentsOption;

        @FXML
        private Button OKbutton;

        @FXML
        private Button BackBtt;

        @FXML
        private Button DeleteBtt;

        @FXML
        void ShowTournamentMenu(ActionEvent event) throws SQLException
        {
            tour = (String) TournamentsOption.getValue();
            scene.openWindowAndClose(event,"UsePageMenu.fxml",tour,394, 251 );
        }

        @FXML
        void goBack(ActionEvent event) {scene.openWindowAndClose(event,"FirstPage.fxml","Welcome!",395, 251 );}

        @FXML
        ObservableList GetTournamentsName() throws SQLException {

            ObservableList<String> optionList = FXCollections.observableArrayList();

            Connection con = DBconnection.getConnection();
            Statement st = con.createStatement();

            String sql = ("SELECT Name FROM `footballtable`.`tournaments`");
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()) {
                optionList.add(rs.getString("Name"));
            }

            con.close();
            return optionList;
        }




         @FXML
        void DeleteTournament(ActionEvent event) throws SQLException
        {
            Object[] options = {"Yes", "No"};
            int dialogue = JOptionPane.showOptionDialog(

                    null,
                    "Do you wish to delete this tournament and \n"
                            + "all information associated with this tournament? \n"
                            + "(Teams, players, games)",
                    "DELETE?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,options,options[1]);
            if(dialogue ==JOptionPane.YES_OPTION){
                tour = (String) TournamentsOption.getValue();

                Connection con = DBconnection.getConnection();
                Statement st = con.createStatement();
                String sql = ("DELETE FROM `footballtable`.`tournaments` WHERE `tournaments`.`Name` = '"+tour+"'");
                st.executeUpdate(sql);
                System.out.println(sql);
                System.out.println(tour + " Tournament has been Deleted");
                con.close();
            }

        }


        @FXML
        void initialize() throws SQLException {
            TournamentsOption.setItems(GetTournamentsName());
            tour = (String) TournamentsOption.getValue();
        }

}