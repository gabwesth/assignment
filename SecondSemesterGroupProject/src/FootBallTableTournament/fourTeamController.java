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
    String tour = UsePageController.tour;

    @FXML
    private Button SaveBtt;
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

    int SID = 4;


    public void saveResults(ActionEvent event) {
        String[] rounds = {r1.getText(), r12.getText(), r2.getText(), r22.getText(), r3.getText(), r32.getText()};
        String[] matches = {m1.getText(), m12.getText(), m2.getText(), m22.getText(), m3.getText(), m32.getText()};
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

            }

    }


