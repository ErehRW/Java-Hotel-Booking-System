// // // RoomView.java
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Parent;


public class RoomView {
    private VBox view;
    private Stage primaryStage;
    private RoomModel model;
    private RoomController controller;
    private TableView<OceanRoom> oceanTable;
    private TableView<MedievalRoom> medievalTable;
    private TableView<ForestRoom> forestTable;
    private TextField roomIdField;
    private Button oceanRoomButton;
    private Button medievalRoomButton;
    private Button forestRoomButton;
    private Button confirmButton;

    public RoomView(RoomModel model, RoomController controller, Stage primaryStage){
        this.model = model;
        this.controller = controller;
        this.primaryStage = primaryStage;
        view = new VBox();

        //Initialize the tables
        oceanTable = new TableView<>();
        medievalTable = new TableView<>();
        forestTable = new TableView<>();

        //OceanRoom Table
        TableColumn<OceanRoom, String> oceanIdColumn = new TableColumn<>("Room ID");

        oceanIdColumn.setCellValueFactory(cellData -> cellData.getValue().iDProperty());

        TableColumn<OceanRoom, RoomType> oceanTypeColumn = new TableColumn<>("Room Type");

        oceanTypeColumn.setCellValueFactory(cellData -> cellData.getValue().roomTypeProperty());

        TableColumn<OceanRoom, RoomAvailability> ocenaAvailabilityColumn = new TableColumn<>("Availability");

        ocenaAvailabilityColumn.setCellValueFactory(cellData -> cellData.getValue().roomAvailabilityProperty());
        
        oceanTable.getColumns().addAll(oceanIdColumn, oceanTypeColumn, ocenaAvailabilityColumn );

        //MedievalRoom Table
        TableColumn<MedievalRoom, String> medievalIdColumn = new TableColumn<>("Room ID");

        medievalIdColumn.setCellValueFactory(cellData -> cellData.getValue().iDProperty());

        TableColumn<MedievalRoom, RoomType> medievalTypeColumn = new TableColumn<>("Room Type");

        medievalTypeColumn.setCellValueFactory(cellData -> cellData.getValue().roomTypeProperty());

        TableColumn<MedievalRoom, RoomAvailability> medievalAvailabilityColumn = new TableColumn<>("Availability");

        medievalAvailabilityColumn.setCellValueFactory(cellData -> cellData.getValue().roomAvailabilityProperty());
        medievalTable.getColumns().addAll(medievalIdColumn, medievalTypeColumn, medievalAvailabilityColumn );

        //ForestRoom table
        TableColumn<ForestRoom, String> forestIdColumn = new TableColumn<>("Room ID");

        forestIdColumn.setCellValueFactory(cellData -> cellData.getValue().iDProperty());

        TableColumn<ForestRoom, RoomType> forestTypeColumn = new TableColumn<>("Room Type");

        forestTypeColumn.setCellValueFactory(cellData -> cellData.getValue().roomTypeProperty());

        TableColumn<ForestRoom, RoomAvailability> forestAvailabilityColumn = new TableColumn<>("Availability");

        forestAvailabilityColumn.setCellValueFactory(cellData -> cellData.getValue().roomAvailabilityProperty());
        forestTable.getColumns().addAll(forestIdColumn, forestTypeColumn, forestAvailabilityColumn );

        //Set items for the rooms in their respective table
        oceanTable.setItems(model.getOceanRooms());
        medievalTable.setItems(model.getMedievalRooms());
        forestTable.setItems(model.getForestRooms());
        startPage();
        
    }
    public void setController(RoomController controller) {
        this.controller = controller;
    }
    public Parent asParent(){
        return view;
    }

    //Back button to go to startPage
    private HBox createBackButtonHBox() {
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");
        backButton.setOnAction(event -> startPage());
    
        HBox backButtonHBox = new HBox(backButton);
        backButtonHBox.setAlignment(Pos.CENTER_RIGHT);
        backButtonHBox.setPadding(new Insets(10));
    
        return backButtonHBox;
    }

    //The startPage and the main menu
    public void startPage(){
    VBox startPageRoot = new VBox(20); 
    startPageRoot.setAlignment(Pos.CENTER); 

    Label welcomeLabel = new Label("Welcome to our hotel system");
    welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");
    welcomeLabel.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.7), new CornerRadii(5), Insets.EMPTY)));
    welcomeLabel.setPadding(new Insets(10));
    
    //Buttons that will lead to other functions when you click on it
    Button displayRoomButton = new Button("Display Rooms");
    displayRoomButton.setStyle("-fx-background-color: #1E90FF; -fx-text-fill: white;");
    displayRoomButton.setOnAction(event -> displayRooms());

    Button bookRoomButton = new Button("Book your room!");
    bookRoomButton.setStyle("-fx-background-color: #00008B; -fx-text-fill: white;");
    bookRoomButton.setOnAction(event -> bookRoom());

    Button removeRoomButton = new Button("Unbook a room you booked :[");
    removeRoomButton.setStyle("-fx-background-color: #4169E1; -fx-text-fill: white;");
    removeRoomButton.setOnAction(e -> removeRoom());

    startPageRoot.getChildren().addAll(welcomeLabel, displayRoomButton, bookRoomButton, removeRoomButton);
    
    //Background image
    BackgroundImage backgroundImage = new BackgroundImage (new Image("HotelBG.jpg", 800, 600, false, true),
    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
    BackgroundSize.DEFAULT);
    Background background = new Background(backgroundImage);
    
    //Add dropshadow effects to the buttons
    DropShadow dropShadow = new DropShadow();
    dropShadow.setRadius(5.0);
    dropShadow.setOffsetX(3.0);
    dropShadow.setOffsetY(3.0);
    dropShadow.setColor(Color.color(0.4, 0.4, 0.4));

    displayRoomButton.setEffect(dropShadow);
    bookRoomButton.setEffect(dropShadow);
    removeRoomButton.setEffect(dropShadow);
    
    
    Scene startPageScene = new Scene(startPageRoot, 800, 600);
    startPageRoot.setBackground(background);
    primaryStage.setScene(startPageScene);
    primaryStage.show();
    }

    //Book room function
    public void bookRoom(){
        HBox backButtonHBox = createBackButtonHBox();
        VBox bookPageRoot = new VBox(20);
        bookPageRoot.setAlignment(Pos.CENTER);
        bookPageRoot.setPadding(new Insets(20));
        bookPageRoot.setStyle("-fx-background-color: #f4f4f4;");

        Label enterRoomLabel = new Label("Enter the Room ID you wish to book");
        enterRoomLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // User can enter the room id in the text field
        roomIdField = new TextField();
        roomIdField.setPromptText("Room ID");
        roomIdField.setStyle("-fx-pref-width: 200px; -fx-pref-height: 30px;");

        confirmButton = new Button("Confirm");
        confirmButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-pref-width: 100px; -fx-pref-height: 30px;");

        bookPageRoot.getChildren().addAll(enterRoomLabel, roomIdField,confirmButton, backButtonHBox );
        
        //When confirmed button is clicked, call bookRoomID from the controller and depending on the result, display different message
        confirmButton.setOnMouseClicked(e -> {
            boolean check = controller.bookRoomID(roomIdField.getText().trim());
            if (check == true) {
                showBookingSuccessMessage();
            } else {
                showBookingFailureMessage();
            }
        });

        //Switch scene to the the bookRoom Scene
        Scene enterRoomIdScene = new Scene(bookPageRoot, 400, 300);
        primaryStage.setTitle("Book a room");
        primaryStage.setScene(enterRoomIdScene);
        primaryStage.show();
    }

    //Same like bookRoom but vice versa
    public void removeRoom(){
        HBox backButtonHBox = createBackButtonHBox();
        VBox removePageRoot = new VBox(20);
        removePageRoot.setAlignment(Pos.CENTER);
        removePageRoot.setPadding(new Insets(20));
        removePageRoot.setStyle("-fx-background-color: #f4f4f4;");
        
        Label enterRoomLabel = new Label("Enter the Room ID you wish to unbook");
        enterRoomLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        roomIdField = new TextField();
        roomIdField.setPromptText("Room ID");
        roomIdField.setStyle("-fx-pref-width: 200px; -fx-pref-height: 30px;");
        
        confirmButton = new Button("Confirm");
        confirmButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 16px; -fx-pref-width: 100px; -fx-pref-height: 30px;");

        removePageRoot.getChildren().addAll(enterRoomLabel, roomIdField,confirmButton, backButtonHBox );
        
        confirmButton.setOnMouseClicked(e -> {
            boolean check = controller.removeBookingID(roomIdField.getText().trim());
            if (check == true) {
                showRemoveBookingSuccess();
            } else {
                showRemoveBookingFail();
            }
        });

        Scene enterRoomIdScene = new Scene(removePageRoot, 400, 300);
        primaryStage.setTitle("Enter Room ID");
        primaryStage.setScene(enterRoomIdScene);
        primaryStage.show();
    }

    public String getRoomID(){
        return roomIdField.getText();
    }

    //Message that will pop up if unbooking room is a success
    public void showRemoveBookingSuccess() {
        Stage stage = new Stage();
        //New stage so a window will pop up as a message

        Label successLabel = new Label("You have removed your room booking :(");
        successLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: green;");

        VBox successRoot = new VBox(successLabel);

        successRoot.setAlignment(Pos.CENTER);
        successRoot.setStyle("-fx-background-color: white; -fx-padding: 20px;");

        Scene successScene = new Scene(successRoot, 300, 200);
        stage.setScene(successScene);
        stage.show();
    }
    //Message that pops up if unbooking room fails
    public void showRemoveBookingFail() {
        Stage stage = new Stage();

        Label failureLabel = new Label("You cannot unbook a room you haven't booked >:( ");
        failureLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: red;");

        VBox failureRoot = new VBox(failureLabel);

        failureRoot.setAlignment(Pos.CENTER);
        failureRoot.setStyle("-fx-background-color: white; -fx-padding: 20px;");

        Scene successScene = new Scene(failureRoot, 300, 200);
        stage.setScene(successScene);
        stage.show();
    }

    //Message that pops up if booking room is a success
    public void showBookingSuccessMessage() {
        Stage stage = new Stage();

        Label successLabel = new Label("Booking successful!");
        successLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: green;");

        VBox successRoot = new VBox(successLabel);
        successRoot.setAlignment(Pos.CENTER);
        successRoot.setStyle("-fx-background-color: white; -fx-padding: 20px;");

        Scene successScene = new Scene(successRoot, 300, 200);
        stage.setScene(successScene);
        stage.show();
    }

    //Message that pops up if booking room fails
    public void showBookingFailureMessage() {
        Stage stage = new Stage();

        Label failureLabel = new Label("Booking failed. Room is not available or invalid Room ID.");
        failureLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: red;");

        VBox failureRoot = new VBox(failureLabel);

        failureRoot.setAlignment(Pos.CENTER);
        failureRoot.setStyle("-fx-background-color: white; -fx-padding: 20px;");

        Scene failureScene = new Scene(failureRoot, 300, 150);
        stage.setScene(failureScene);
        stage.show();
    }

    public void displayRooms() {
        HBox backButtonHBox = createBackButtonHBox();
        model.initializeRooms();
        
        Label titleLabel = new Label("Hotel Room Booking System");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");
        titleLabel.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.5), new CornerRadii(5), Insets.EMPTY)));
        titleLabel.setPadding(new Insets(10));
        //OceanTable is the first to be visible
        oceanTable.setVisible(true);
        medievalTable.setVisible(false);
        forestTable.setVisible(false);

        oceanRoomButton = new Button("Ocean Room");
        oceanRoomButton.setStyle("-fx-background-color: #1E90FF; -fx-text-fill: white; -fx-font-size: 16px; -fx-pref-width: 150px; -fx-pref-height: 40px;");

        medievalRoomButton = new Button("Medieval Room");
        medievalRoomButton.setStyle("-fx-background-color: #8B4513; -fx-text-fill: white; -fx-font-size: 16px; -fx-pref-width: 150px; -fx-pref-height: 40px;");

        forestRoomButton = new Button("Forest Room");
        forestRoomButton.setStyle("-fx-background-color: #228B22; -fx-text-fill: white; -fx-font-size: 16px; -fx-pref-width: 150px; -fx-pref-height: 40px;");


        //Event handlers for when each button is clicked to display the different rooms
        oceanRoomButton.setOnAction(event -> {
            oceanTable.setVisible(true);
            medievalTable.setVisible(false);
            forestTable.setVisible(false);
        });

        medievalRoomButton.setOnAction(event -> {
            oceanTable.setVisible(false);
            medievalTable.setVisible(true);
            forestTable.setVisible(false);
        });

        forestRoomButton.setOnAction(event -> {
            oceanTable.setVisible(false);
            medievalTable.setVisible(false);
            forestTable.setVisible(true);
        });

        BackgroundImage backgroundImage = new BackgroundImage (new Image("HotelRoomsBG.jpg", 800, 600, false, true),
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);

        //HBox for the diiferent room buttons
        HBox differentRoomBox = new HBox(10, oceanRoomButton, medievalRoomButton, forestRoomButton);
        differentRoomBox.setPadding(new Insets(10));

        oceanTable.setStyle("-fx-background-color: transparent; -fx-font-size: 14px; -fx-text-fill: white;");
        medievalTable.setStyle("-fx-background-color: transparent; -fx-font-size: 14px; -fx-text-fill: white;");
        forestTable.setStyle("-fx-background-color: transparent; -fx-font-size: 14px; -fx-text-fill: white;");

        
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10.0);
        dropShadow.setOffsetX(2.0);
        dropShadow.setOffsetY(2.0);
        dropShadow.setColor(Color.color(0.4, 0.4, 0.4));

        oceanTable.setEffect(dropShadow);
        medievalTable.setEffect(dropShadow);
        forestTable.setEffect(dropShadow);

        //Stackpane created to hold all the tables
        StackPane tables = new StackPane(oceanTable, medievalTable, forestTable);
        tables.setAlignment(Pos.CENTER);
        tables.setPadding(new Insets(40));
        tables.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);"); 

        VBox root = new VBox(10, titleLabel, tables, differentRoomBox, backButtonHBox);
        root.setPadding(new Insets(10));
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3);");
    

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        root.setBackground(background);
    }



    
    
}
