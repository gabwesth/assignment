package FootBallTableTournament;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.*;
import java.util.*;

public class fourStandingsController {

    String tour = UsePageController.tour;
    @FXML
    private Label no1;
    @FXML
    private Label no2;
    @FXML
    private Label no3;
    @FXML
    private Label no4;

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
            no1.setText(String.valueOf(scoreOfTeams.get(3)));
            no2.setText(String.valueOf(scoreOfTeams.get(2)));
            no3.setText(String.valueOf(scoreOfTeams.get(1)));
            no4.setText(String.valueOf(scoreOfTeams.get(0)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() throws SQLException{
        ShowScores();
    }
}