package engine;

import entities.Enemy;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import world.Map;

public class App extends Application {
    Stage primaryStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setScene(createScene());
        primaryStage.show();
    }

    public Scene createScene() {
        Map map = new Map();
        return new Scene(map.view());
    }
}
