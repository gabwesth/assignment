package FootBallTableTournament;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.*;

import java.io.IOException;
import java.sql.SQLException;

public class FirstPageController {
    Main scene = new Main();

    @FXML
    private Button UseExisting;

    @FXML
    private Button CreateNew;

    @FXML
    void OpenCreatePage(ActionEvent event) throws SQLException {
        scene.openWindowAndClose(event,"CreatePage.fxml","Create New Tournament", 395, 251 );

    }


    @FXML
    void OpenExistingPage(ActionEvent event) throws SQLException {
        scene.openWindowAndClose(event,"UsePage.fxml","Use Existing Tournament", 395, 251 );
    }


}
