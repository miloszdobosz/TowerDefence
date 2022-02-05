package engine;

import entities.Enemy;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {
    Stage primaryStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setScene(createScene());
        primaryStage.show();
    }

    public Scene createScene() {
        Element element = new Element();
        Group group = new Group(new Element().view());
        return new Scene(group);
    }
}
