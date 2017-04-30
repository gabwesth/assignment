package FootBallTableTournament;

import javafx.application.Application;
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

public class Main extends Application {


        @Override
        public void start(Stage primaryStage) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("FirstPage.fxml"));
            primaryStage.setTitle("Welcome!");
            primaryStage.setScene(new Scene(root, 394, 251));
            primaryStage.show();
        }

        @FXML
        void openWindow(ActionEvent event, String FXMLpage, String Title,int width,int height) {
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXMLpage));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle(Title);
                stage.setScene(new Scene(root1,width,height));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        void openWindowAndClose(ActionEvent event, String FXMLpage, String Title,int width,int height) {
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXMLpage));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle(Title);
                stage.setScene(new Scene(root1,width,height));
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }



         @FXML
        public void saveResults(ActionEvent event,String[] rounds, String[] matches, String tour) {

        for(int i=0; i<rounds.length;i++){

            String[] parts = rounds[i].split("-");
            String[] teams = matches[i].split("v");
            String part1 = parts[0];
            String part2 = parts[1];
            String team1 = teams[0];
            String team2 = teams[1];
            System.out.println(part1 + "\t" + part2);
            System.out.println(team1 + "\t" + team2);//TEST CODE
            try {
                Connection con = DBconnection.getConnection();
                Statement stmt = con.createStatement();
                int IDGame = 0;



                String getIDSQL = "SELECT `ID` FROM `games` WHERE `games`.`TournamentID`= '"+tour+"'";
                ResultSet rs = stmt.executeQuery(getIDSQL);
                System.out.println(getIDSQL);

                if(rs.next()) {
                    IDGame = rs.getInt("ID");
                }

                String Name1 = "";
                //SELECT Name FROM `teams` WHERE Tournament = 'test' AND ScheaduleID = 1
                String getName1SQL = "SELECT `Name` FROM `teams` WHERE `Tournament`= '"+tour+"' AND ScheaduleID = '"+team1+"'";
                ResultSet rs1 = stmt.executeQuery(getName1SQL);
                System.out.println(getName1SQL);

                if(rs1.next()) {
                    Name1 = rs1.getString("Name");
                }



                String Name2 = "";
                //SELECT Name FROM `teams` WHERE Tournament = 'test' AND ScheaduleID = 1
                String getName2SQL = "SELECT `Name` FROM `teams` WHERE `Tournament`= '"+tour+"' AND ScheaduleID = '"+team2+"'";
                ResultSet rs2 = stmt.executeQuery(getName2SQL);
                System.out.println(getName2SQL);

                if(rs2.next()) {
                    Name2 = rs2.getString("Name");
                }
                System.out.println(Name2);
                int score1 = Integer.parseInt(part1);
                int score2 = Integer.parseInt(part2);
                String Winner = "";

                if( score1 > score2) {
                    Winner = Name1;
                }
                if ( score1 < score2) {
                    Winner = Name2;
                }


                String TeamSql = "UPDATE `games` SET `Team1ID` = '"+Name1+"', `Team2ID` = '"+Name2+"', `T1score` = '"+part1+"', `T2score` = '"+part2+"', `Winner` = '"+Winner+"' WHERE `games`.`ID`= '"+(IDGame+i)+"' AND games.TournamentID = '"+tour+"'";
                System.out.println(TeamSql);
                stmt.executeUpdate(TeamSql);

                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        System.out.println();
        System.out.println();

    }

    public void setPoints(int SID,String tour){

        for(int i = 1; i<=SID;i++){
            try {
                Connection con = DBconnection.getConnection();
                Statement stmt = con.createStatement();
                String x = "";


                String getName = "SELECT `Name` FROM `teams` WHERE `ScheaduleID`= " + i + " AND Tournament ='"+tour+"'";
                ResultSet rss = stmt.executeQuery(getName);
                System.out.println(getName);

                if (rss.next()) {
                    x = rss.getString("Name");
                }

                int Point = 0;
                String name ="";


                //SELECT Winner FROM `games` WHERE Team1ID OR Team2ID = 'evwev'
                String NumbersOfWin = "SELECT `Winner` FROM `games` WHERE `Winner` ='"+x+"'";
                ResultSet rss2 = stmt.executeQuery(NumbersOfWin);
                System.out.println(NumbersOfWin);

                while (rss2.next()) {
                    name = rss2.getString("Winner");

                    Point++;

                }
                System.out.println(name +": "+Point);

                String givePoints ="UPDATE `teams` SET `Points` = '"+Point+"' WHERE `teams`.`Name` = '"+name+"'";
                System.out.println(givePoints);
                System.out.println();
                stmt.executeUpdate(givePoints);

            }catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }


    public static void main(String[] args) {
        launch(args);
    }
}
