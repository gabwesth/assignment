package FootBallTableTournament;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Chris on 21-04-17.
 */
public class sixTeamController {
    Main scene = new Main();
    @FXML
    private Button Save6Button;
   @FXML
    private TextField r1;
    @FXML
    private TextField r12;
    @FXML
    private TextField r13;
    @FXML
    private TextField r2;
    @FXML
    private TextField r22;
    @FXML
    private TextField r23;
    @FXML
    private TextField r3;
    @FXML
    private TextField r32;
    @FXML
    private TextField r33;
    @FXML
    private TextField r4;
    @FXML
    private TextField r42;
    @FXML
    private TextField r43;
    @FXML
    private TextField r5;
    @FXML
    private TextField r52;
    @FXML
    private TextField r53;
 @FXML
 private Label q1;
 @FXML
 private Label q2;
 @FXML
 private Label q3;
 @FXML
 private Label q4;
 @FXML
 private Label q5;
 @FXML
 private Label q11;
 @FXML
 private Label q21;
 @FXML
 private Label q31;
 @FXML
 private Label q41;
 @FXML
 private Label q51;
 @FXML
 private Label q12;
 @FXML
 private Label q22;
 @FXML
 private Label q32;
 @FXML
 private Label q42;
 @FXML
 private Label q52;
 @FXML
 private Label q13;
 @FXML
 private Label q23;
 @FXML
 private Label q33;
 @FXML
 private Label q43;
 @FXML
 private Label q53;


 public void saveResults(ActionEvent event) {
  String[] rounds = {r1.getText(), r12.getText(), r2.getText(), r22.getText(), r3.getText(), r32.getText()};
  String[] matches = {q1.getText(), q12.getText(), q13.getText(), q2.getText(), q22.getText(), q23.getText(), q3.getText(), q32.getText(), q33.getText(), q41.getText(), q42.getText(), q43.getText(),q51.getText(), q52.getText(), q53.getText();

  for (int i = 0; i < rounds.length; i++) {

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


    String getIDSQL = "SELECT `ID` FROM `games` WHERE `games`.`TournamentID`= '" + tour + "'";
    ResultSet rs = stmt.executeQuery(getIDSQL);
    System.out.println(getIDSQL);

    if (rs.next()) {
     IDGame = rs.getInt("ID");
    }

    String Name1 = "";
    //SELECT Name FROM `teams` WHERE Tournament = 'test' AND ScheaduleID = 1
    String getName1SQL = "SELECT `Name` FROM `teams` WHERE `Tournament`= '" + tour + "' AND ScheaduleID = '" + team1 + "'";
    ResultSet rs1 = stmt.executeQuery(getName1SQL);
    System.out.println(getName1SQL);

    if (rs1.next()) {
     Name1 = rs1.getString("Name");
    }


    String Name2 = "";
    //SELECT Name FROM `teams` WHERE Tournament = 'test' AND ScheaduleID = 1
    String getName2SQL = "SELECT `Name` FROM `teams` WHERE `Tournament`= '" + tour + "' AND ScheaduleID = '" + team2 + "'";
    ResultSet rs2 = stmt.executeQuery(getName2SQL);
    System.out.println(getName2SQL);

    if (rs2.next()) {
     Name2 = rs2.getString("Name");
    }
    System.out.println(Name2);
    int score1 = Integer.parseInt(part1);
    int score2 = Integer.parseInt(part2);
    String Winner = "";

    if (score1 > score2) {
     Winner = Name1;
    }
    if (score1 < score2) {
     Winner = Name2;
    }


    String TeamSql = "UPDATE `games` SET `Team1ID` = '" + Name1 + "', `Team2ID` = '" + Name2 + "', `T1score` = '" + part1 + "', `T2score` = '" + part2 + "', `Winner` = '" + Winner + "' WHERE `games`.`ID`= '" + (IDGame + i) + "' AND games.TournamentID = '" + tour + "'";
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
  setPoints();
 }


 public void setPoints(){

  for(int i = 1; i<=4;i++){
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


}



}
