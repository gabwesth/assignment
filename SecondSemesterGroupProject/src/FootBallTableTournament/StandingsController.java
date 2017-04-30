package FootBallTableTournament;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.omg.CORBA.Object;


import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import java.sql.*;
import java.util.*;

public class StandingsController {

    String tour = UsePageController.chosenTournament;
    @FXML
    private Button test;
    @FXML
    private TableView table;
    @FXML
    public void ShowScores(ActionEvent event) throws SQLException {
        try {
            Connection conn = DBconnection.getConnection();
            Statement sta = conn.createStatement();
            String query = ("SELECT `Name`, `Points` FROM `teams` WHERE `Tournament` = '" + tour + "'");
            ArrayList<pointsAndTeams> scoreOfTeams = new ArrayList<>();
            ResultSet rsa = sta.executeQuery(query);

            while (rsa.next()) {
                String teamName = rsa.getString("Name");
                int points = rsa.getInt("Points");
                scoreOfTeams.add(new pointsAndTeams(teamName, points));
                Collections.sort(scoreOfTeams, new Comparator<pointsAndTeams>() {
                    @Override
                    public int compare(pointsAndTeams o1, pointsAndTeams o2) {
                        return o1.points- o2.points;
                    }
                });
            }
            System.out.println(scoreOfTeams);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
