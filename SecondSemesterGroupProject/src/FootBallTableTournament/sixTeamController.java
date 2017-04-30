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

   public class sixTeamController {
    Main scene = new Main();
    String tour = UsePageController.tour;
    int SID = 6;

    @FXML
    private Button SaveBtt;
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



       public void saveResults(ActionEvent event) {
           String[] rounds = {r1.getText(), r12.getText(), r13.getText(), r2.getText(), r22.getText(), r23.getText(), r3.getText(), r32.getText(), r33.getText(), r4.getText(), r42.getText(), r43.getText(), r5.getText(), r52.getText(), r53.getText()};
           String[] matches = {q1.getText(), q12.getText(), q13.getText(), q2.getText(), q22.getText(), q23.getText(), q3.getText(), q32.getText(), q33.getText(), q4.getText(), q42.getText(), q43.getText(), q5.getText(), q52.getText(), q53.getText()};
           scene.saveResults(event,rounds,matches,tour);
           scene.setPoints(SID,tour);
       }
    @FXML
    void initialize() throws SQLException {

     Connection con = DBconnection.getConnection();
     Statement stmt = con.createStatement();
     String getT1SQL = "SELECT `Name` FROM `teams` WHERE `Tournament`= '" + tour + "' AND ScheaduleID = '1'";
     ResultSet rs1 = stmt.executeQuery(getT1SQL);
     System.out.println(getT1SQL);

     if (rs1.next()) {
      Label1.setText(rs1.getString("Name"));

     }

     String getT2SQL = "SELECT `Name` FROM `teams` WHERE `Tournament`= '" + tour + "' AND ScheaduleID = '2'";
     ResultSet rs2 = stmt.executeQuery(getT2SQL);
     System.out.println(getT2SQL);

     if (rs2.next()) {
      Label2.setText(rs2.getString("Name"));

     }
     String getT3SQL = "SELECT `Name` FROM `teams` WHERE `Tournament`= '" + tour + "' AND ScheaduleID = '3'";
     ResultSet rs3 = stmt.executeQuery(getT3SQL);
     System.out.println(getT3SQL);

     if (rs3.next()) {
      Label3.setText(rs3.getString("Name"));

     }
     String getT4SQL = "SELECT `Name` FROM `teams` WHERE `Tournament`= '" + tour + "' AND ScheaduleID = '4'";
     ResultSet rs4 = stmt.executeQuery(getT4SQL);
     System.out.println(getT4SQL);

     if (rs4.next()) {
      Label4.setText(rs4.getString("Name"));

     }
     String getT5SQL = "SELECT `Name` FROM `teams` WHERE `Tournament`= '" + tour + "' AND ScheaduleID = '5'";
     ResultSet rs5 = stmt.executeQuery(getT5SQL);
     System.out.println(getT5SQL);
     if (rs5.next()) {
      Label5.setText(rs5.getString("Name"));
     }
     String getT6SQL = "SELECT `Name` FROM `teams` WHERE `Tournament`= '" + tour + "' AND ScheaduleID = '6'";
     ResultSet rs6 = stmt.executeQuery(getT6SQL);
     System.out.println(getT6SQL);
     if (rs6.next()) {
      Label6.setText(rs6.getString("Name"));
     }


    }


   }



