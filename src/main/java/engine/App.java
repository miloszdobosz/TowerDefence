package engine;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import world.World;

public class App extends Application {
    World world;
//    Menu menu;
//    Stage primaryStage = new Stage();
    public static final Group parentView = new Group();

    @Override
    public void start(Stage primaryStage) {
//        this.primaryStage = primaryStage;
        primaryStage.setScene(new Scene(parentView));

        world = new World();

        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.show();
    }
}
