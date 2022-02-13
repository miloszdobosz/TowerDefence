package engine;

import entities.Tower;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import world.Position;
import world.World;

public class App extends Application {
    World world;
    Menu menu;
    Stage primaryStage = new Stage();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        Group group = new Group();
        world = new World(group);
        primaryStage.setScene(new Scene(group));
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> System.exit(0));

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (world) {
                    world.update();
                }
            }
        });
        thread.start();
    }
}
