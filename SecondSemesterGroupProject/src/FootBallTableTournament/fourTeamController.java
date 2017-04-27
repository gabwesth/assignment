package FootBallTableTournament;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Chris on 21-04-17.
 */
public class fourTeamController {
    Main scene = new Main();
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

    public String T1score;
    public String T2score;

    public void saveResults(ActionEvent event) {
        String[] rounds = {r1.getText(), r12.getText(), r2.getText(), r22.getText(), r3.getText(), r32.getText()};

        for (String s : rounds) {
            String[] parts = s.split("-");

            String part1 = parts[0];
            String part2 = parts[1];
            T1score = part1;
            T2score = part2;

            System.out.println(T1score + T2score);

        }
    }

}
