package FootBallTableTournament;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Boti on 30-Apr-17.
 */
public class EightStandingController {

    String tour = UsePageController.tour;
    @FXML
    private Label no1,no2,no3,no4,no5,no6,no7,no8;


    public void ShowScores() throws SQLException {
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
            no1.setText(String.valueOf(scoreOfTeams.get(7)));
            no2.setText(String.valueOf(scoreOfTeams.get(6)));
            no3.setText(String.valueOf(scoreOfTeams.get(5)));
            no4.setText(String.valueOf(scoreOfTeams.get(4)));
            no5.setText(String.valueOf(scoreOfTeams.get(3)));
            no6.setText(String.valueOf(scoreOfTeams.get(2)));
            no7.setText(String.valueOf(scoreOfTeams.get(1)));
            no8.setText(String.valueOf(scoreOfTeams.get(0)));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() throws SQLException{
        ShowScores();
    }
}
