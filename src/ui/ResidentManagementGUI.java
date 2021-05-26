package ui;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.ResidenceManagement;

public class ResidentManagementGUI {

    private ResidenceManagement residentManagement;
    @FXML
    private AnchorPane mainPane;


    public ResidentManagementGUI(ResidenceManagement residentManagement) {
        this.residentManagement=residentManagement;
        Timeline count = new Timeline(
            new KeyFrame(Duration.seconds(1), action -> {
                try {
                    login(null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
    count.play();

    }



    @FXML
    public void exitApp(ActionEvent event) {
        Platform.exit();
    }




    @FXML
    public void test(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Copy.fxml"));
		fxmlLoader.setController(this);    	
		Parent pane = fxmlLoader.load();
		mainPane.getChildren().clear();
        mainPane.getChildren().addAll(pane);
    }
    


    @FXML
    public void menuAdministration(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuAdministration.fxml"));
		fxmlLoader.setController(this);    	
		Parent pane = fxmlLoader.load();
		mainPane.getChildren().clear();
        mainPane.getChildren().addAll(pane);
    }


    @FXML
    public void menuApartament(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuApartament.fxml"));
		fxmlLoader.setController(this);    	
		Parent pane = fxmlLoader.load();
		mainPane.getChildren().clear();
        mainPane.getChildren().addAll(pane);
    }


    @FXML
    public void login(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
		fxmlLoader.setController(this);    	
		Parent pane = fxmlLoader.load();
		mainPane.getChildren().clear();
        mainPane.getChildren().addAll(pane);
    }






    
}
