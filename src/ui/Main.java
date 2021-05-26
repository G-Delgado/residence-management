package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.ResidenceManagement;

public class Main extends Application {
    // define your offsets here
    private double xOffset = 0;
    private double yOffset = 0;

    
    private ResidentManagementGUI residentManagementGUI;
    private ResidenceManagement residentManagement;

    public Main(){
        residentManagement = new ResidenceManagement(4,10,4,150.000);
        residentManagementGUI = new ResidentManagementGUI(residentManagement);
        
    }


    @Override
    public void start(Stage primaryStage) throws Exception {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Welcome.fxml"));
        fxmlLoader.setController(residentManagementGUI);
        Parent root = fxmlLoader.load();
        primaryStage.initStyle(StageStyle.TRANSPARENT);


        // grab your root here
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        // move around here
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        Scene scene = new Scene(root);
        // set transparent
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
