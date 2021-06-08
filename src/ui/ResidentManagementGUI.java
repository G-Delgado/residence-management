package ui;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import exceptions.PasswordInvalidException;
import exceptions.UsernameInvalidException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import model.Apartament;
import model.Debt;
import model.Vehicle;
import threads.DateThread;
import threads.TimeThread;
import model.Doorman;
import model.Filler;
import model.Loader;
import model.Pet;
import model.ResidenceManagement;
import model.Resident;
import model.ServiceStaff;
import model.TypePet;

public class ResidentManagementGUI {

    private ResidenceManagement residentManagement;
    
    // Apartament
    
    @FXML
    private Label apartamentId;
    
    @FXML
    private TextField apartamentOwner;
    
    @FXML
    private TextField apartamentPassword;
    
    @FXML
    private TextField apartamentUsername;
    
    @FXML
    private ListView<Resident> residentsList;
    
    @FXML
    private ListView<Pet> petsList;
    
    @FXML
    private ListView<Vehicle> vehiclesList;
    
    @FXML
    private Label apartamentTotalDebt;
    

    // Primitives (Shapes)
      
      private boolean loading;
      
     @FXML
     private Line line;
      
     private Loader l;
      
     @FXML
     private Rectangle rectangleContainer;
      
     @FXML
     private Rectangle rectangleFill;
      
     private Filler f;

    // Threads
    @FXML
    private Label time;

    @FXML
    private Label date;

    @FXML
    private Pane paneAdministration;

    @FXML
    private Pane paneApartament;

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

    // Export

    @FXML
    private ChoiceBox<String> choiceboxExportApto;

    // Tables

    @FXML
    private Pane paneTables;

    //Debt

    @FXML
    private Pane paneDebts;

    // Invoices

    @FXML
    private Label administrationFee;

    @FXML
    private TextField invoiceTotal;

    @FXML
    private TextArea invoiceDescription;

    @FXML
    private ChoiceBox<String> choiceBoxApartaments;

    public ResidentManagementGUI(ResidenceManagement residentManagement) {
        this.residentManagement = residentManagement;
        Timeline count = new Timeline(new KeyFrame(Duration.seconds(3), action -> {
            try {
                login(null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        count.play();

    }
    
    public void updateLine(double x) {
    	line.setRotate(x);
    }
    
    public void updateRectangle(double w) {
    	rectangleFill.setWidth(w);
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
    public void invoices(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Invoice.fxml"));
        fxmlLoader.setController(this);
        Parent pane = fxmlLoader.load();
        paneAdministration.getChildren().clear();
        paneAdministration.getChildren().addAll(pane);


        administrationFee.setText(residentManagement.getAdministrationFee()+"");


        for (Apartament apartament : residentManagement.getApartaments()) {
            
            choiceBoxApartaments.getItems().add(apartament.getUsername());
        }
    }

    @FXML
    public void menuApartament(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuApartament.fxml"));
        fxmlLoader.setController(this);
        Parent pane = fxmlLoader.load();
        mainPane.getChildren().clear();
        mainPane.getChildren().addAll(pane);
    }

    @SuppressWarnings("unchecked")
	@FXML
    public void debtApartament(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Debt.fxml"));
        fxmlLoader.setController(this);
        Parent pane = fxmlLoader.load();
        paneApartament.getChildren().clear();
        paneApartament.getChildren().addAll(pane);

        
        
        TableView<Debt> table = new TableView<Debt>();

        TableColumn<Debt, String> descriptionCol = new TableColumn<Debt, String>("Description");
        TableColumn<Debt, Double> priceCol = new TableColumn<Debt, Double>("Price");
        TableColumn<Debt, String> dateCol = new TableColumn<Debt, String>("Date");


        table.getColumns().addAll(descriptionCol, priceCol, dateCol);


        table.setPrefSize(751,368);
        table.getStylesheets().setAll("/css/fullpackstyling.css");
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        paneDebts.getChildren().clear();
        paneDebts.getChildren().addAll(table);

        String apto=labelApto.getText();
        
        String number = apto.split("_")[0];
		String tower = apto.split("_")[1];
		Apartament apartament = new Apartament(tower, number, apto, "");
		int index = residentManagement.binarySearchApartament(apartament);

        apartament=residentManagement.getApartaments().get(index);

        ObservableList<Debt> observableList;
        observableList = FXCollections.observableArrayList(apartament.getDebt());
        table.setItems(observableList);

        descriptionCol.setCellValueFactory(new PropertyValueFactory<Debt, String>("description"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Debt ,Double>("price"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Debt ,String>("date"));
        

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
                if (residentManagement.loginAdmin(loginUsername, loginPassword)) {
                    menuAdministration(event);
                    labelAdmin.setText(loginUsername);
                } else {
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

        for (Apartament apartament : residentManagement.getApartaments()) {
            choiceboxExportApto.getItems().add(apartament.getUsername());

        }

    }


    @FXML
    public void exportApartamentsCSV(ActionEvent event) {

        try {
            FileChooser fc = new FileChooser();
            String apartament = choiceboxExportApto.getValue();

            fc.setInitialFileName(apartament + "_export");

            fc.getExtensionFilters().add(new ExtensionFilter("CSV", "*.csv"));
            File file = fc.showSaveDialog(mainPane.getScene().getWindow());
            residentManagement.exportResidentsPerApartaments(apartament, file);

        } catch (Exception e) {
            alert("Invalid");
        }

    }

    @FXML
    public void tables(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Tables.fxml"));
        fxmlLoader.setController(this);
        Parent pane = fxmlLoader.load();
        paneAdministration.getChildren().clear();
        paneAdministration.getChildren().addAll(pane);

    }

    @SuppressWarnings("unchecked")
    @FXML
    public void tableResidents(ActionEvent event) throws IOException {
        TableView<Resident> table = new TableView<Resident>();

        TableColumn<Resident, String> firstNameCol = new TableColumn<Resident, String>("First Name");
        TableColumn<Resident, String> lastNameCol = new TableColumn<Resident, String>("Last Name");
        TableColumn<Resident, Integer> phoneNumberCol = new TableColumn<Resident, Integer>("Phone Number");
        TableColumn<Resident, String> idCol = new TableColumn<Resident, String>("Id");

        table.getColumns().addAll(firstNameCol, lastNameCol, phoneNumberCol, idCol);

        table.setPrefSize(paneTables.getWidth(), paneTables.getHeight());
        table.getStylesheets().setAll("/css/fullpackstyling.css");
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        paneTables.getChildren().clear();
        paneTables.getChildren().addAll(table);

        ObservableList<Resident> observableList;
        observableList = FXCollections.observableArrayList(residentManagement.getResidents());
        table.setItems(observableList);

        firstNameCol.setCellValueFactory(new PropertyValueFactory<Resident, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Resident, String>("lastName"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<Resident, Integer>("phoneNumber"));
        idCol.setCellValueFactory(new PropertyValueFactory<Resident, String>("id"));

    }

    @SuppressWarnings("unchecked")
    @FXML
    public void tableApartaments(ActionEvent event) throws IOException {
        TableView<Apartament> table = new TableView<Apartament>();

        TableColumn<Apartament, String> towerCol = new TableColumn<Apartament, String>("Tower");
        TableColumn<Apartament, String> numberCol = new TableColumn<Apartament, String>("Number");
        TableColumn<Apartament, Double> debtCol = new TableColumn<Apartament, Double>("Debt");
        TableColumn<Apartament, String> ownerCol = new TableColumn<Apartament, String>("Owner");

        table.getColumns().addAll(towerCol, numberCol, debtCol, ownerCol);

        table.setPrefSize(paneTables.getWidth(), paneTables.getHeight());
        table.getStylesheets().setAll("/css/fullpackstyling.css");
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        paneTables.getChildren().clear();
        paneTables.getChildren().addAll(table);

        ObservableList<Apartament> observableList;
        observableList = FXCollections.observableArrayList(residentManagement.getApartaments());
        table.setItems(observableList);

        towerCol.setCellValueFactory(new PropertyValueFactory<Apartament, String>("tower"));
        numberCol.setCellValueFactory(new PropertyValueFactory<Apartament, String>("number"));
        debtCol.setCellValueFactory(new PropertyValueFactory<Apartament, Double>("totalDebt"));
        ownerCol.setCellValueFactory(new PropertyValueFactory<Apartament, String>("owner"));
        
        table.setOnMouseClicked(otherEvent -> {
        	if (otherEvent.getClickCount() == 2 && otherEvent.getButton().equals(MouseButton.PRIMARY)) {
        		// Cagar nuevo FXML con los datos elegidos
        		Apartament apt = table.getSelectionModel().getSelectedItem();
        		loadApartament(apt);
        	}
        });
        
    }
    
    public void loadApartament(Apartament apto) {
    	System.out.println(apto);
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apartament.fxml"));
    	fxmlLoader.setController(this);
    	
    	try {
			Parent apartament = fxmlLoader.load();
			paneAdministration.getChildren().clear();
			paneAdministration.getChildren().addAll(apartament);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	apartamentId.setText(apto.getNumber() + "_" + apto.getTower());
    	if (apto.getOwner() != null) {    		
    		apartamentOwner.setText(apto.getOwner().toStringJavaFX());
    	} else {
    		apartamentOwner.setText("None");
    	}
    	apartamentUsername.setText(apto.getUsername());
    	apartamentPassword.setText(apto.getPassword());
    	apartamentTotalDebt.setText(apto.getTotalDebt() + "");
    	ObservableList<Resident> residents = FXCollections.observableArrayList(apto.getResidents());
    	ObservableList<Pet> pets = FXCollections.observableArrayList(apto.getPets());
    	ObservableList<Vehicle> vehicles = FXCollections.observableArrayList(apto.getCars());
    	
    	residentsList.setItems(residents);
    	petsList.setItems(pets);
    	vehiclesList.setItems(vehicles);
    }

    @SuppressWarnings("unchecked")
    @FXML
    public void tableDoormen(ActionEvent event) throws IOException {
        TableView<Doorman> table = new TableView<Doorman>();

        TableColumn<Doorman, String> firstNameCol = new TableColumn<Doorman, String>("First Name");
        TableColumn<Doorman, String> lastNameCol = new TableColumn<Doorman, String>("Last Name");
        TableColumn<Doorman, Integer> phoneNumberCol = new TableColumn<Doorman, Integer>("Phone number");
        TableColumn<Doorman, String> idCol = new TableColumn<Doorman, String>("Id");

        table.getColumns().addAll(firstNameCol, lastNameCol, phoneNumberCol, idCol);

        table.setPrefSize(paneTables.getWidth(), paneTables.getHeight());
        table.getStylesheets().setAll("/css/fullpackstyling.css");
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        paneTables.getChildren().clear();
        paneTables.getChildren().addAll(table);

        ObservableList<Doorman> observableList;
        observableList = FXCollections.observableArrayList(residentManagement.getDoormen());
        table.setItems(observableList);

        firstNameCol.setCellValueFactory(new PropertyValueFactory<Doorman, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Doorman, String>("lastName"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<Doorman, Integer>("phoneNumber"));
        idCol.setCellValueFactory(new PropertyValueFactory<Doorman, String>("id"));

    }

    @SuppressWarnings("unchecked")
    @FXML
    public void tableServiceStaff(ActionEvent event) throws IOException {
        TableView<ServiceStaff> table = new TableView<ServiceStaff>();

        TableColumn<ServiceStaff, String> firstNameCol = new TableColumn<ServiceStaff, String>("First Name");
        TableColumn<ServiceStaff, String> lastNameCol = new TableColumn<ServiceStaff, String>("Last Name");
        TableColumn<ServiceStaff, Integer> phoneNumberCol = new TableColumn<ServiceStaff, Integer>("Phone number");
        TableColumn<ServiceStaff, String> idCol = new TableColumn<ServiceStaff, String>("Id");

        table.getColumns().addAll(firstNameCol, lastNameCol, phoneNumberCol, idCol);

        table.setPrefSize(paneTables.getWidth(), paneTables.getHeight());
        table.getStylesheets().setAll("/css/fullpackstyling.css");
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        paneTables.getChildren().clear();
        paneTables.getChildren().addAll(table);

        ObservableList<ServiceStaff> observableList;
        observableList = FXCollections.observableArrayList(residentManagement.getServiceStaff());
        table.setItems(observableList);

        firstNameCol.setCellValueFactory(new PropertyValueFactory<ServiceStaff, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<ServiceStaff, String>("lastName"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<ServiceStaff, Integer>("phoneNumber"));
        idCol.setCellValueFactory(new PropertyValueFactory<ServiceStaff, String>("id"));

    }

    @SuppressWarnings("unchecked")
    @FXML
    public void tablePets(ActionEvent event) throws IOException {
        TableView<Pet> table = new TableView<Pet>();

        TableColumn<Pet, String> nameCol = new TableColumn<Pet, String>("Name");
        TableColumn<Pet, TypePet> typeCol = new TableColumn<Pet, TypePet>("Type");

        table.getColumns().addAll(nameCol, typeCol);

        table.setPrefSize(paneTables.getWidth(), paneTables.getHeight());
        table.getStylesheets().setAll("/css/fullpackstyling.css");
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        paneTables.getChildren().clear();
        paneTables.getChildren().addAll(table);

        ObservableList<Pet> observableList;
        observableList = FXCollections.observableArrayList(residentManagement.getPets());
        table.setItems(observableList);

        nameCol.setCellValueFactory(new PropertyValueFactory<Pet, String>("firstName"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Pet, TypePet>("lastName"));

       /* table.setOnMouseClicked(otherEvent -> {
        	if (otherEvent.getClickCount() == 2 && otherEvent.getButton().equals(MouseButton.PRIMARY)) {
        		// Cagar nuevo FXML con los datos elegidos
        		Pet pet = table.getSelectionModel().getSelectedItem();
        		loadPet(pet);
        	}
        });*/
    }
    
    /*public void loadPet(Pet pet) {
    	if (pet != null) {    		
    		System.out.println(pet.toString());
    	}
    }*/

    @SuppressWarnings("unchecked")
    @FXML
    public void tableCars(ActionEvent event) throws IOException {
        TableView<Vehicle> table = new TableView<Vehicle>();

        TableColumn<Vehicle, String> licenseCol = new TableColumn<Vehicle, String>("License Plate");
        TableColumn<Vehicle, String> typeCol = new TableColumn<Vehicle, String>("Type");

        table.getColumns().addAll(licenseCol, typeCol);

        table.setPrefSize(paneTables.getWidth(), paneTables.getHeight());
        table.getStylesheets().setAll("/css/fullpackstyling.css");
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        paneTables.getChildren().clear();
        paneTables.getChildren().addAll(table);

        ObservableList<Vehicle> observableList;
        observableList = FXCollections.observableArrayList(residentManagement.getVehicles());
        table.setItems(observableList);

        licenseCol.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("licensePlate"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("type"));

    }

    // Threads

    public void initialize() {
    	loading = true;
        TimeThread clock = new TimeThread(time);
        DateThread calendar = new DateThread(date);
        clock.start();
        calendar.start();
        
        l = new Loader(line.getRotate());
        f = new Filler(rectangleFill.getWidth(), rectangleContainer.getWidth());
        
        new Thread() {
        	public void run() {
        		while (loading) {
        			l.load();
        			f.fill();
        			
        			Platform.runLater(new Thread() {
        				public void run() {
        					// Update figures
        					updateLine(l.getDegrees());
        					updateRectangle(f.getWidth());
        				}
        			});
        			
        			try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
        		}
        	}
        }.start();
    }



    @FXML
    public void generateAdministrationDebt(ActionEvent event) {
        residentManagement.generateAdministrationDebt(LocalDate.now());
        alert("Se genero el nuevo pago de administracion");

    }


    
    @FXML
    public void generateInvoices(ActionEvent event) {

        String apto=choiceBoxApartaments.getValue();
        
        String number = apto.split("_")[0];
		String tower = apto.split("_")[1];
		Apartament apartament = new Apartament(tower, number, apto, "");
		int index = residentManagement.binarySearchApartament(apartament);

		 apartament = residentManagement.getApartaments().get(index);


        String description=invoiceDescription.getText();
        double price=Double.parseDouble(invoiceTotal.getText());

        residentManagement.generateDebt(description,LocalDate.now(), price,apartament);
        alert("Se genero el nuevo pago de "+description);

    }

    private Apartament getLoginApartament(){
        String apto=labelApto.getText();
        
        String number = apto.split("_")[0];
		String tower = apto.split("_")[1];
		Apartament apartament = new Apartament(tower, number, apto, "");
		int index = residentManagement.binarySearchApartament(apartament);
        return residentManagement.getApartaments().get(index);
    }

    @FXML
    public void generateInvoicesForAll(ActionEvent event) {
        String description=invoiceDescription.getText();
        double price=Double.parseDouble(invoiceTotal.getText());

        residentManagement.generateInvoiceForAll(LocalDate.now(), description, price);
        alert("Se genero el nuevo pago de "+description);

    }


    @FXML
    public void generateReport(ActionEvent event) {
        Apartament apartament = getLoginApartament();
        residentManagement.GenerateReport(apartament);
    }

}
