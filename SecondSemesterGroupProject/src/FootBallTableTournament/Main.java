package FootBallTableTournament;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("FirstPage.fxml"));
            primaryStage.setTitle("Welcome!");
            primaryStage.setScene(new Scene(root, 394, 251));
            primaryStage.show();
        }

        @FXML
        void openWindow(ActionEvent event, String FXMLpage, String Title,int width,int height) {
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXMLpage));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle(Title);
                stage.setScene(new Scene(root1,width,height));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        void openWindowAndClose(ActionEvent event, String FXMLpage, String Title,int width,int height) {
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXMLpage));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle(Title);
                stage.setScene(new Scene(root1,width,height));
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    public static void main(String[] args) {
        launch(args);
    }
}
