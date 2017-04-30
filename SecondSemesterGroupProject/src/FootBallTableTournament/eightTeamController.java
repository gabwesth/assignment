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
public class eightTeamController {
    Main scene = new Main();
    String tour = UsePageController.tour;
    int SID = 8;

    @FXML
    private Button SaveBtt;
    @FXML
    private TextField r1;
    @FXML
    private TextField r12;
    @FXML
    private TextField r13;
    @FXML
    private TextField r14;
    @FXML
    private TextField r2;
    @FXML
    private TextField r22;
    @FXML
    private TextField r23;
    @FXML
    private TextField r24;
    @FXML
    private TextField r3;
    @FXML
    private TextField r32;
    @FXML
    private TextField r33;
    @FXML
    private TextField r34;
    @FXML
    private TextField r4;
    @FXML
    private TextField r42;
    @FXML
    private TextField r43;
    @FXML
    private TextField r44;
    @FXML
    private TextField r5;
    @FXML
    private TextField r52;
    @FXML
    private TextField r53;
    @FXML
    private TextField r54;
    @FXML
    private TextField r6;
    @FXML
    private TextField r62;
    @FXML
    private TextField r63;
    @FXML
    private TextField r64;
    @FXML
    private TextField r7;
    @FXML
    private TextField r72;
    @FXML
    private TextField r73;
    @FXML
    private TextField r74;
    @FXML
    private Label m1;
    @FXML
    private Label m12;
    @FXML
    private Label m13;
    @FXML
    private Label m14;
    @FXML
    private Label m2;
    @FXML
    private Label m22;
    @FXML
    private Label m23;
    @FXML
    private Label m24;
    @FXML
    private Label m3;
    @FXML
    private Label m32;
    @FXML
    private Label m33;
    @FXML
    private Label m34;
    @FXML
    private Label m4;
    @FXML
    private Label m42;
    @FXML
    private Label m43;
    @FXML
    private Label m44;
    @FXML
    private Label m5;
    @FXML
    private Label m52;
    @FXML
    private Label m53;
    @FXML
    private Label m54;
    @FXML
    private Label m6;
    @FXML
    private Label m62;
    @FXML
    private Label m63;
    @FXML
    private Label m64;
    @FXML
    private Label m7;
    @FXML
    private Label m72;
    @FXML
    private Label m73;
    @FXML
    private Label m74;
    @FXML
    private Label Label1;
    @FXML
    private Label Label2;
    @FXML
    private Label Label3;
    @FXML
    private Label Label4;
    @FXML
    private Label Label5;
    @FXML
    private Label Label6;
    @FXML
    private Label Label7;
    @FXML
    private Label Label8;


    public void saveResults(ActionEvent event) {
        String[] rounds = {r1.getText(), r12.getText(), r13.getText(), r14.getText(), r2.getText(), r22.getText(), r23.getText(), r24.getText(), r3.getText(), r32.getText(), r33.getText(), r34.getText(), r4.getText(), r42.getText(), r43.getText(), r44.getText(), r5.getText(), r52.getText(), r53.getText(), r54.getText(), r6.getText(), r62.getText(), r63.getText(), r64.getText(), r7.getText(), r72.getText(), r73.getText(), r74.getText(),};
        String[] matches = {m1.getText(), m12.getText(), m13.getText(), m14.getText(), m2.getText(), m22.getText(), m23.getText(), m24.getText(), m3.getText(), m32.getText(), m33.getText(), m34.getText(), m4.getText(), m42.getText(), m43.getText(), m44.getText(), m5.getText(), m52.getText(), m53.getText(), m54.getText(), m6.getText(), m62.getText(), m63.getText(), m64.getText(), m7.getText(), m72.getText(), m73.getText(), m74.getText(),};
        scene.saveResults(event,rounds,matches,tour);
        scene.setPoints(SID,tour);
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
        String getT5SQL = "SELECT `Name` FROM `teams` WHERE `Tournament`= '"+tour+"' AND ScheaduleID = '5'";
        ResultSet rs5 = stmt.executeQuery(getT5SQL);
        System.out.println(getT5SQL);
        if(rs5.next()) {
            Label5.setText(rs5.getString("Name"));
        }
        String getT6SQL = "SELECT `Name` FROM `teams` WHERE `Tournament`= '"+tour+"' AND ScheaduleID = '6'";
        ResultSet rs6 = stmt.executeQuery(getT6SQL);
        System.out.println(getT6SQL);
        if(rs6.next()) {
            Label6.setText(rs6.getString("Name"));
        }
        String getT7SQL = "SELECT `Name` FROM `teams` WHERE `Tournament`= '"+tour+"' AND ScheaduleID = '7'";
        ResultSet rs7 = stmt.executeQuery(getT7SQL);
        System.out.println(getT7SQL);
        if(rs7.next()) {
            Label7.setText(rs7.getString("Name"));
        }
        String getT8SQL = "SELECT `Name` FROM `teams` WHERE `Tournament`= '"+tour+"' AND ScheaduleID = '8'";
        ResultSet rs8 = stmt.executeQuery(getT8SQL);
        System.out.println(getT8SQL);
        if(rs8.next()) {
            Label8.setText(rs8.getString("Name"));
        }

    }

}

