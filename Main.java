// Main.java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Welcome to our hotel main menu :)");
        RoomModel model = new RoomModel();
        RoomController controller = new RoomController(model, null);
        RoomView view = new RoomView(model, controller, primaryStage);
        controller.setView(view);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

    
