package FootBallTableTournament;

import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.lang.model.element.Name;
import javax.print.attribute.standard.MediaSize;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static jdk.nashorn.internal.runtime.GlobalFunctions.parseInt;

/**
 * Created by Chris on 21-04-17.
 */
public class fourTeamController {
    Main scene = new Main();
    String tour = UsePageController.chosenTournament;

    @FXML
    private Button Save4Button;
    @FXML
    private TextField r1;
    @FXML
    private TextField r12;
    @FXML
    private TextField r2;
    @FXML
    private TextField r22;
    @FXML
    private TextField r3;
    @FXML
    private TextField r32;
    @FXML
    private Label m1;
    @FXML
    private Label m12;
    @FXML
    private Label m2;
    @FXML
    private Label m22;
    @FXML
    private Label m3;
    @FXML
    private Label m32;
    @FXML
    private Label Label1;
    @FXML
    private Label Label2;
    @FXML
    private Label Label3;
    @FXML
     private Label Label4;

    public void saveResults(ActionEvent event) {
        String[] rounds = {r1.getText(), r12.getText(), r2.getText(), r22.getText(), r3.getText(), r32.getText()};
        String[] matches = {m1.getText(), m12.getText(), m2.getText(), m22.getText(), m3.getText(), m32.getText()};

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

            @FXML
            void initialize() throws SQLException{

                Connection con = DBconnection.getConnection();
                Statement stmt = con.createStatement();
                String getT1SQL = "SELECT `Name` FROM `teams` WHERE `Tournament`= '"+tour+"' AND ScheaduleID = '1'";
                ResultSet rs1 = stmt.executeQuery(getT1SQL);
                System.out.println(getT1SQL);

                if(rs1.next()) {
                    Label1.setText(rs1.getString("Name")); ;
                }
                String getT2SQL = "SELECT `Name` FROM `teams` WHERE `Tournament`= '"+tour+"' AND ScheaduleID = '2'";
                ResultSet rs2 = stmt.executeQuery(getT2SQL);
                System.out.println(getT2SQL);

                if(rs2.next()) {
                    Label2.setText(rs2.getString("Name")); ;
                }
                String getT3SQL = "SELECT `Name` FROM `teams` WHERE `Tournament`= '"+tour+"' AND ScheaduleID = '3'";
                ResultSet rs3 = stmt.executeQuery(getT3SQL);
                System.out.println(getT3SQL);

                if(rs3.next()) {
                    Label3.setText(rs3.getString("Name")); ;
                }
                String getT4SQL = "SELECT `Name` FROM `teams` WHERE `Tournament`= '"+tour+"' AND ScheaduleID = '4'";
                ResultSet rs4 = stmt.executeQuery(getT4SQL);
                System.out.println(getT4SQL);

                if(rs4.next()) {
                    Label4.setText(rs4.getString("Name")); ;
                }

            }

    }


