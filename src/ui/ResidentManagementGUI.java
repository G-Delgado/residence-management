package ui;

import java.io.File;
import java.io.IOException;


import exceptions.PasswordInvalidException;
import exceptions.UsernameInvalidException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import model.Apartament;
import model.ResidenceManagement;

public class ResidentManagementGUI {

    private ResidenceManagement residentManagement;

    @FXML
    private Pane paneAdministration;

    @FXML
    private AnchorPane mainPane;

    // Login
    @FXML
    private ChoiceBox<String> optionLogin;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label labelApto;

    @FXML
    private Label labelAdmin;


    //Export


    @FXML
    private ChoiceBox<String> choiceboxExportApto;

    public ResidentManagementGUI(ResidenceManagement residentManagement) {
        this.residentManagement = residentManagement;
        Timeline count = new Timeline(new KeyFrame(Duration.seconds(1), action -> {
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

        optionLogin.getItems().addAll("Administrator", "Apartament");
    }

    @FXML
    public void signIn(ActionEvent event) throws IOException {

        String loginUsername = username.getText();
        String loginPassword = password.getText();

        try {

            if (optionLogin.getValue().equals("Administrator")) {
                if (residentManagement.loginAdmin(loginUsername, loginPassword)){
                    menuAdministration(event);
                    labelAdmin.setText(loginUsername);
                }
                else {
                    alert("Invalid...");
                }
            } else if (optionLogin.getValue().equals("Apartament")) {

                try {
                    residentManagement.loginApartament(loginUsername, loginPassword);
                    menuApartament(event);
                    labelApto.setText(loginUsername);

                } catch (UsernameInvalidException e) {
                    alert("Invalid username");
                } catch (PasswordInvalidException e) {
                    alert("Invalid password");
                }
            }

        } catch (Exception e) {
            alert("Seleccione una opcion");
        }

    }

    private void alert(String text) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Test");
        alert.setHeaderText(null);
        alert.setContentText(text);

        alert.showAndWait();
    }

    @FXML
    public void export(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Export.fxml"));
        fxmlLoader.setController(this);
        Parent pane = fxmlLoader.load();
        paneAdministration.getChildren().clear();
        paneAdministration.getChildren().addAll(pane);

        
        for (Apartament apartament : residentManagement.getApartaments()){
            choiceboxExportApto.getItems().add(apartament.getUsername());
            
        }
        

    }


    @FXML
    public void exportApartamentsCSV(ActionEvent event) {


        try {
            FileChooser fc = new FileChooser();
            String apartament=choiceboxExportApto.getValue();
            
            fc.setInitialFileName(apartament+"_export");
            
            fc.getExtensionFilters().add(new ExtensionFilter("CSV", "*.csv"));
            File file = fc.showSaveDialog(mainPane.getScene().getWindow());
            residentManagement.exportResidentsPerApartaments(apartament, file);
            
            
        } catch (Exception e) {
            alert("Invalid");
        }

      

    }




}
